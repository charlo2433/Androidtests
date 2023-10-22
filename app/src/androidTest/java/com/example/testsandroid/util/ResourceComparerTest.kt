package com.example.testsandroid.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.example.testsandroid.R

class ResourceComparerTest{
    private lateinit var resourceComparer: ResourceComparer

    @Before
    @Test
    fun setup(){
        resourceComparer = ResourceComparer()
    }

    @Test
    fun stringcomparison(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name,"TestsAndroid")
        assertTrue(result)
    }


}