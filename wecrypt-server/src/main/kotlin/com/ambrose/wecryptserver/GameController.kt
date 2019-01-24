package com.ambrose.wecryptserver

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Component
class GameController(private val gameServer: GameServer) {

    @RequestMapping("/getState", method = [RequestMethod.GET])
    fun getState(): UiState {
        return gameServer.getGame().toUiState()
    }

    @RequestMapping("/newGame", method = [RequestMethod.POST])
    fun newGame(): UiState {
        return gameServer.newGame().toUiState()
    }

    @RequestMapping("/clue", method = [RequestMethod.POST])
    fun clue(@RequestBody clue: List<String>): UiState {
        return gameServer.clue(clue).toUiState()
    }

    @RequestMapping("/offenseGuess", method = [RequestMethod.POST])
    fun offenseGuess(@RequestBody guess: String): UiState {
        return gameServer.offenseGuess(guess).toUiState()
    }

    @RequestMapping("/defenseGuess", method = [RequestMethod.POST])
    fun defenseGuess(@RequestBody guess: String): UiState {
        return gameServer.defenseGuess(guess).toUiState()
    }


    private fun Game.toStateSummary(): String {
        val turn = turns.last()
        return if (turn.clue == null) {
            "Awaiting clue from defense team"
        }
        else {
            if (turn.defenseGuess == null && turn.offenseGuess == null) {
                "Awaiting guesses from both teams"
            } else {
                if (turn.defenseGuess == null) {
                    "Awaiting guess from defense team"
                } else {
                    "Awaiting guess from offense team"
                }
            }
        }
    }

    private fun Game.toPreviousSummary(): String? {
        return turns.dropLast(1).lastOrNull()?.run {
            "In the previous turn, the true code was $code.  The clue given was $clue.  The defense guessed $defenseGuess and the offense guessed $offenseGuess"
        }
    }

    private fun Game.toUiState(): UiState {
        return UiState(
                turns.size,
                words,
                turns.last().clue,
                turns.last().offenseGuess,
                turns.last().defenseGuess,
                toStateSummary(),
                toPreviousSummary(),
                (1..4).map { i ->
                    words[i - 1].let { word ->
                        turns.dropLast(1).mapNotNull { turn ->
                            turn.code.indexOf(i.toString()).takeIf { it != -1 }?.let { index ->
                                turn.clue!![index]
                            }
                        }
                    }
                },
                turns.count {
                    it.defenseGuess != null && it.offenseGuess != null && it.defenseGuess != it.code
                },
                turns.count {
                    it.defenseGuess != null && it.offenseGuess != null && it.offenseGuess == it.code
                }
        )
    }

}