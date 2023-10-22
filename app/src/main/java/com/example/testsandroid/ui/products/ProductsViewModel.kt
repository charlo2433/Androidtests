package com.example.testsandroid.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsandroid.data.remote.model.ProductsListResponse
import com.example.testsandroid.data.remote.model.ProductsResponse
import com.example.testsandroid.repository.ProductsRepository
import com.example.testsandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private var productsRepository: ProductsRepository):ViewModel() {
    private val _productsList: MutableLiveData<Resource<ProductsListResponse>> = MutableLiveData()
    val productsList: LiveData<Resource<ProductsListResponse>> get() = _productsList

    fun getProductsList() = viewModelScope.launch {
        _productsList.postValue(Resource.Loading)
        _productsList.postValue(productsRepository.getProductsList())
    }


}