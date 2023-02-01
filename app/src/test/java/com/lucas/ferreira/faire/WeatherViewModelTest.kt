package com.lucas.ferreira.faire

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucas.ferreira.faire.factory.getWeather
import com.lucas.ferreira.faire.feature.weather.core.ConnectivityManager
import com.lucas.ferreira.faire.feature.weather.mapper.toInfo
import com.lucas.ferreira.faire.feature.weather.repository.WeatherRepository
import com.lucas.ferreira.faire.feature.weather.viewmodel.WeatherViewModel
import com.lucas.ferreira.faire.feature.weather.viewstate.WeatherViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: WeatherViewModel

    private val argumentCaptor: ArgumentCaptor<WeatherViewState> =
        ArgumentCaptor.forClass(WeatherViewState::class.java)

    @Mock
    private lateinit var repository: WeatherRepository

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @Mock
    private lateinit var observer: Observer<WeatherViewState>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = WeatherViewModel(repository, connectivityManager)
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun `when request weather success should emit loading then success`() = runBlocking {
        `when`(repository.getWeather()).thenReturn(getWeather())

        viewModel.loadWeather()

        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())

            val (loadingState, successState) = allValues
            assertEquals(WeatherViewState.Loading, loadingState)
            assertEquals(WeatherViewState.Success(getWeather().toInfo()), successState)
        }
    }

    @Test
    fun `when request weather fails should emit loading then error`() = runBlocking {
        `when`(connectivityManager.isConnectionAvailable()).thenReturn(true)

        `when`(repository.getWeather()).thenThrow(RuntimeException())

        viewModel.loadWeather()

        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())

            val (loadingState, errorState) = allValues
            assertEquals(WeatherViewState.Loading, loadingState)
            assertEquals(WeatherViewState.Error, errorState)
        }
    }

    @Test
    fun `when connection gets lost should emit loading then network error`() = runBlocking {
        `when`(connectivityManager.isConnectionAvailable()).thenReturn(false)

        val exception = RuntimeException()

        `when`(repository.getWeather()).thenThrow(exception)

        viewModel.loadWeather()

        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())

            val (loadingState, networkErrorState) = allValues
            assertEquals(WeatherViewState.Loading, loadingState)
            assertEquals(WeatherViewState.NetworkError(exception), networkErrorState)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
