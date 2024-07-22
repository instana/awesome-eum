package com.ibm.instashop.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.instashop.business_unit.models.Banner
import com.ibm.instashop.business_unit.models.Notification
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.usecases.BannerUseCase
import com.ibm.instashop.business_unit.usecases.CategoriesUseCase
import com.ibm.instashop.business_unit.usecases.CategoryUseCase
import com.ibm.instashop.business_unit.usecases.NotificationsUseCase
import com.ibm.instashop.business_unit.usecases.ProductsUseCase
import com.ibm.instashop.common.Constants
import com.ibm.instashop.common.DataState
import com.ibm.instashop.common.EventState
import com.ibm.instashop.data.local.DBMock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val productUseCase: ProductsUseCase,
    private val categoriesUseCase: CategoriesUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val bannerUseCase: BannerUseCase,
    private val notificationsUseCase: NotificationsUseCase
) : ViewModel() {
    private var localCopy = arrayListOf<ProductItem>()

    private val _productState = MutableStateFlow(DataState())
    val productState: StateFlow<DataState> = _productState

    private val _categoriesState = MutableStateFlow(DataState())
    val categoriesState: StateFlow<DataState> = _categoriesState

    private val _bannerState = MutableStateFlow(DataState())
    val bannerState: StateFlow<DataState> = _bannerState

    private val _notificationState = MutableStateFlow(DataState())
    val notificationState: StateFlow<DataState> = _notificationState

    init {
        getBannerItems()
        getProduct()
        getCategories()
    }

    private fun getProduct() {
        productUseCase().onEach { result ->
            updateStatesOfProduct(result)
        }.launchIn(viewModelScope)
    }

    private fun getBannerItems(){
        bannerUseCase().onEach { result->
            when (result) {
                is EventState.Loading -> {
                    _bannerState.value = DataState(isLoading = true)
                }
                is EventState.Success -> {
                    try {
                        _bannerState.value = DataState(data = result.data as ArrayList<Banner>)
                    }catch (e:Exception){
                        _bannerState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }
                is EventState.Error -> {
                    _bannerState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        categoriesUseCase().onEach { result ->
            when (result) {
                is EventState.Loading -> {
                    _categoriesState.value = DataState(isLoading = true)
                }
                is EventState.Success -> {
                    try {
                        _categoriesState.value = DataState(data = result.data as ArrayList<String>)
                    }catch (e:Exception){
                        _categoriesState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }
                is EventState.Error -> {
                    _categoriesState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProductWithCategory(category:String){
        if(category=="All"){
            getProduct()
        }else{
            categoryUseCase(category).onEach { result ->
                updateStatesOfProduct(result)
            }.launchIn(viewModelScope)
        }
    }

    private fun updateStatesOfProduct(result:  EventState<ArrayList<ProductItem>>){
        when (result) {
            is EventState.Loading -> {
                _productState.value = DataState(isLoading = true)
            }
            is EventState.Success -> {
                try {
                    _productState.value = DataState(data = result.data as ArrayList<ProductItem>)
                    localCopy = result.data
                }catch (e:Exception){
                    _productState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
            is EventState.Error -> {
                _productState.value =
                    DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
            }
        }
    }

    fun getTotalCartValue():Int{
        return DBMock.cartItemsList.sumOf { it.count }
    }

    fun getItemSelectedCount(itemId:Int):Int{
        return DBMock.cartItemsList.filter { it.item.id == itemId }.sumOf { it.count }
    }

    fun applyFilter(minValue:String,maxValue: String){
        val filteredList = localCopy.filter { it.price >= minValue.toDouble() && it.price <= maxValue.toDouble() }
        _productState.value = DataState(data = ArrayList(filteredList))
    }

    fun resetFilters(){
        getProduct()
    }

    fun getNotifications(){
        notificationsUseCase().onEach { result ->
            when (result) {
                is EventState.Loading -> {
                    _notificationState.value = DataState(isLoading = true)
                }
                is EventState.Success -> {
                    try {
                        _notificationState.value = DataState(data = result.data as ArrayList<Notification>)
                    }catch (e:Exception){
                        _notificationState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }
                is EventState.Error -> {
                    _notificationState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }
}