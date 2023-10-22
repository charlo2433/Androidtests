package com.example.testsandroid.ui.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testsandroid.data.remote.model.ProductsListResponse
import com.example.testsandroid.repository.ProductsRepository
import com.example.testsandroid.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.ContentHandler
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class ProductsViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    private val productsRepository: ProductsRepository = mockk()
    private lateinit var viewModel : ProductsViewModel

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        viewModel = ProductsViewModel(productsRepository)
    }

    @Test
    fun `When fetch products is called, loading state is shown`() {
        // Given: Mock the behavior of getProductsList in the repository to return a Loading state
        coEvery { productsRepository.getProductsList() } returns Resource.Loading

        // Create a LiveData observer to observe changes in the ViewModel's LiveData
        val observer = mockk<Observer<Resource<ProductsListResponse>>>(relaxed = true)
        viewModel.productsList.observeForever(observer)

        // When: Call the ViewModel's function to fetch products
        viewModel.getProductsList()
        dispatcher.scheduler.advanceUntilIdle()

        // Then: Verify that the repository function was called
        coVerify { productsRepository.getProductsList() }

        // And: Verify that LiveData is updated with a Loading state
        verify { observer.onChanged(Resource.Loading) }
    }
}