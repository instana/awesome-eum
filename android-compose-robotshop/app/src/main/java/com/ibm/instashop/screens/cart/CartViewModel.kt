package com.ibm.instashop.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.instashop.business_unit.models.CartItems
import com.ibm.instashop.business_unit.models.DiscountCoupon
import com.ibm.instashop.business_unit.models.DiscountResponse
import com.ibm.instashop.business_unit.models.Payment
import com.ibm.instashop.business_unit.usecases.DiscountUseCase
import com.ibm.instashop.business_unit.usecases.PaymentUseCase
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
class CartViewModel @Inject constructor(
    private val discountUseCase: DiscountUseCase,
    private val paymentUseCase: PaymentUseCase
) : ViewModel() {

    private var orderId = "ROB-12345678"

    var count = 0
    private val _discountState = MutableStateFlow(DataState())
    val discountState: StateFlow<DataState> = _discountState

    private val _paymentState = MutableStateFlow(DataState())
    val paymentState: StateFlow<DataState> = _paymentState

    fun getDiscount(discountCoupon: DiscountCoupon) {
        discountUseCase.getDiscount(discountCoupon).onEach { result ->
            when (result) {
                is EventState.Loading -> {
                    _discountState.value = DataState(isLoading = true)
                }

                is EventState.Success -> {
                    try {
                        _discountState.value = DataState(data = result.data as DiscountResponse)
                    } catch (e: Exception) {
                        _discountState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }

                is EventState.Error -> {
                    _discountState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCartItems(): ArrayList<CartItems> {
        return DBMock.cartItemsList
    }

    fun getCalculatedFinalValue(): Double {
        return "%.2f".format(DBMock.cartItemsList.sumOf { it.count * it.item.price }).toDouble()
    }

    fun getOrderId(): String {
        return orderId;
    }

    fun findMostOrderedCategory(): String? {
        val categoryCounts = DBMock.cartItemsList
            .groupBy { it.item.category }
            .mapValues { entry -> entry.value.sumOf { it.count } }
        return categoryCounts.maxByOrNull { it.value }?.key
    }

    fun doPayment(payment: Payment) {
        paymentUseCase.validatePayment(payment).onEach { result ->
            when (result) {
                is EventState.Loading -> {
                    _paymentState.value = DataState(isLoading = true)
                }

                is EventState.Success -> {
                    try {
                        _paymentState.value = DataState(data = result.data)
                    } catch (e: Exception) {
                        _paymentState.value =
                            DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                    }
                }

                is EventState.Error -> {
                    _paymentState.value =
                        DataState(errorMessage = result.message ?: Constants.UN_EXPECTED_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }


}