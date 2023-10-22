package com.example.testsandroid.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testsandroid.data.local.model.Profile
import com.example.testsandroid.repository.ProfileRepository
import com.example.testsandroid.util.Event
import com.example.testsandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository): ViewModel() {
    val userprofile = profileRepository.observeProfile()

    private val _products = MutableLiveData<Event<Resource<Profile>>>()
    val products : LiveData<Event<Resource<Profile>>> = _products
}
