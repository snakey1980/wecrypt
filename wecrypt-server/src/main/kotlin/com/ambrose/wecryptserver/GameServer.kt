package com.ambrose.wecryptserver

import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.random.Random

@Component
class GameServer(private val wordDeck: WordDeck, private val random: Random) {

    private val lock = ReentrantLock()
    private val game = AtomicReference<Game>()

    fun getGame(): Game = game.get()?: throw IllegalStateException("Game has not been started!")

    fun newGame(): Game = lock.withLock {
        game.set(Game(wordDeck.draw()))
        turnUnlocked()
        game.get()
    }

    fun turn(): Game = lock.withLock {
        turnUnlocked()
    }

    private fun turnUnlocked(): Game {
        val current = getGame()
        if (current.turns.size == 24) {
            throw IllegalStateException("There are no more turns in this game!")
        }
        current.turns.last().run {
            if (clue == null || defenseGuess == null || offenseGuess == null) {
                throw IllegalStateException("Last turn is not over!")
            }
        }
        game.set(current.copy(turns = current.turns.plus(Turn(allCodes.minus(current.turns.map { it.code }).random(random)))))
        return game.get()
    }

    fun clue(clue: List<String>): Game = lock.withLock {
        if (clue.size != 4 || clue.any { it.isBlank() }) {
            throw IllegalArgumentException("Invalid clue: $clue")
        }
        val current = getGame()
        val turn = current.turns.last()
        if (turn.clue != null) {
            throw IllegalStateException("We already have a clue for this turn!")
        }
        game.set(current.copy(turns = current.turns.take(current.turns.size - 1).plus(current.turns.last().copy(clue = clue))))
        game.get()
    }

    fun offenseGuess(guess: String): Game = lock.withLock {
        val current = getGame()
        val turn = current.turns.last()
        if (turn.clue == null) {
            throw IllegalStateException("Turn has not been clued yet")
        }
        game.set(current.copy(turns = current.turns.take(current.turns.size - 1).plus(current.turns.last().copy(offenseGuess = guess))))
        turnIfReady()
    }

    fun defenseGuess(guess: String): Game = lock.withLock {
        val current = getGame()
        val turn = current.turns.last()
        if (turn.clue == null) {
            throw IllegalStateException("Turn has not been clued yet")
        }
        game.set(current.copy(turns = current.turns.take(current.turns.size - 1).plus(current.turns.last().copy(defenseGuess = guess))))
        turnIfReady()
    }

    private fun turnIfReady(): Game  {
        val current = getGame()
        if (current.turns.last().offenseGuess != null && current.turns.last().defenseGuess != null) {
            turnUnlocked()
        }
        return game.get()
    }


}