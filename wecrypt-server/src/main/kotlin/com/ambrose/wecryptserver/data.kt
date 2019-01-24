package com.ambrose.wecryptserver

data class Turn(val code: String, val clue: List<String>? = null, val defenseGuess: String? = null, val offenseGuess: String? = null)
data class Game(val words: List<String>, val turns: List<Turn> = listOf())

data class UiState(
        val turnNumber: Int,
        val words: List<String>,
        val clue: List<String>?,
        val offenseGuess: String?,
        val defenseGuess: String?,
        val state: String,
        val previous: String?,
        val keysSoFar: List<List<String>>,
        val misCommunications: Int,
        val interceptions: Int
)