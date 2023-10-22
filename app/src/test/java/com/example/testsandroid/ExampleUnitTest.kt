package com.example.testsandroid

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Before
    fun start(){
        println("new")
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(9, 2 + 2)
    }

    @Test
    fun checkifisequal(){
      var name = "steve"
      var  gender = "steve"

      assertEquals(name, gender)
    }
}