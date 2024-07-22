package com.ibm.instashop.screens.payment

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ibm.instashop.R
import com.ibm.instashop.business_unit.models.Payment
import com.ibm.instashop.common.Constants
import com.ibm.instashop.graphs.items_detail.DetailScreen
import com.ibm.instashop.screens.cart.CartViewModel
import com.ibm.instashop.screens.common.components.BackButton
import com.ibm.instashop.screens.common.components.CustomInputField
import com.ibm.instashop.screens.common.components.PrimaryButton
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.TextColor
import com.ibm.instashop.ui.theme.White
import com.instana.android.CustomEvent
import com.instana.android.Instana


@Composable
fun PaymentPage(navController: NavController,cartViewModel: CartViewModel = hiltViewModel()) {

    // State for text field values
    val cardNumberState = remember { mutableStateOf("") }
    val expirationDateState = remember { mutableStateOf("") }
    val cvvState = remember { mutableStateOf("") }
    val paymentStatus = cartViewModel.paymentState.collectAsState().value
    if (cartViewModel.count==0){
        Instana.reportEvent(CustomEvent("reached_payment").apply {
            customMetric = 1.0 //person reached payment page
        })
        cartViewModel.count += 1
    }
    val context = LocalContext.current
    LaunchedEffect(paymentStatus) {
        paymentStatus.data?.let {
            Instana.reportEvent(CustomEvent(Constants.PAYMENT_SUCCESS).also {
                it.customMetric = cartViewModel.getCalculatedFinalValue()
                it.meta = mapOf(
                    Pair(Constants.ORDER_ID, cartViewModel.getOrderId()),
                    Pair(Constants.INTERESTED_CATEGORY, cartViewModel.findMostOrderedCategory() ?: "cloths") // Cart item's dominant category
                )
            })
            navController.navigate(DetailScreen.PaymentSuccess.route)
        }
        paymentStatus.errorMessage?.let {
            if (paymentStatus.errorMessage.isNotEmpty()) {
                Toast.makeText(context, "Payment Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .background(color = MaterialTheme.colors.White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically ){
            BackButton {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Payment")
        }
        Text(
            text = buildAnnotatedString {
                append("Total Amount : ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.PrimaryColor,
                    )
                ) {
                    append("$${cartViewModel.getCalculatedFinalValue()}")
                }
            },
            color = MaterialTheme.colors.TextColor,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
        // Card number field
        CustomInputField(
            placeholder = "Card Number",
            trailingIcon = R.drawable.card_number,
            label = "Card Number",
            keyboardType = KeyboardType.Number,
            visualTransformation = PasswordVisualTransformation(),
            onChanged = { newPass ->
                cardNumberState.value = newPass.text
            }
        )

        // Expiration date field
        // CVV field
        CustomInputField(
            placeholder = "Expiration Date",
            trailingIcon = R.drawable.date,
            label = "Expiration Date",
            keyboardType = KeyboardType.Number,
            visualTransformation = PasswordVisualTransformation(),
            onChanged = { newPass ->
                expirationDateState.value = newPass.text
            }
        )

        // CVV field
        CustomInputField(
            placeholder = "CVV",
            trailingIcon = R.drawable.password,
            label = "CVV",
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            onChanged = { newPass ->
                cvvState.value = newPass.text
            }
        )

        // Pay button
        PrimaryButton(
            btnText = "Pay Now",
            shapeSize = 15f
        ) {
            cartViewModel.doPayment(Payment(cardNumberState.value.toString(),cvvState.value.toString(),expirationDateState.value.toString()))
        }
    }
}
