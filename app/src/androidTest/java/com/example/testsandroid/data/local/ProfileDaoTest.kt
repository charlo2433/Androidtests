package com.example.testsandroid.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.testsandroid.data.local.database.ProfileDao
import com.example.testsandroid.data.local.database.ProfileDatabase
import com.example.testsandroid.data.local.model.Profile
import com.example.testsandroid.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class ProfileDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var profileDao: ProfileDao
    private lateinit var profileDatabase: ProfileDatabase

    @Before
    fun setUp(){
        profileDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ProfileDatabase::class.java).allowMainThreadQueries().build()
        profileDao = profileDatabase.profileDao()
    }

    @After
    fun tearDown(){
        profileDatabase.close()
    }

    @Test
    fun insertData() = runTest {
        val profile = Profile(1, "Falcao", "Male")
        profileDao.insertProfile(profile)

        val userProfile = profileDao.observeProfile().getOrAwaitValue()
        assertThat(userProfile).contains(profile)
    }
}