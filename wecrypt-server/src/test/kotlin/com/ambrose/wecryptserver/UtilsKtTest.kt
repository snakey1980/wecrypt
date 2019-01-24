package com.ambrose.wecryptserver

import org.junit.Assert
import org.junit.Test

class UtilsKtTest {

    @Test
    fun testAllCodes() {
        Assert.assertTrue(allCodes.size == 24)
        println(allCodes)
    }

}