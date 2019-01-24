package com.ambrose.wecryptserver

import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

class WordDeckTest {

    private val deck = WordDeck(Random(0))

    @Test
    fun smokeTest() {
        deck.draw()
    }

    @Test
    fun testNoRepeats() {
        val seen = mutableSetOf<String>()
        Assert.assertTrue((1..100).all { _ ->
            val words = deck.draw()
            words.none { word -> seen.contains(word) }.also {
                seen.addAll(words)
            }
        })
    }

    @Test
    fun testRepeats() {
        val seen = (1..100).flatMap { deck.draw() }.toSet()
        Assert.assertTrue(deck.draw().toSet().all { seen.contains(it) })
    }


}