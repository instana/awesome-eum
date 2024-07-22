package com.ibm.instashop.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ibm.instashop.business_unit.models.OrderSuccess
import com.ibm.instashop.data.local.DBMock
import com.ibm.instashop.graphs.home.RoboShopHomeScreen
import com.ibm.instashop.screens.cart.CartViewModel
import com.ibm.instashop.screens.common.components.PrimaryButton
import com.ibm.instashop.ui.theme.SuccessGreen
import com.ibm.instashop.ui.theme.White
import com.instana.android.CustomEvent
import com.instana.android.Instana

@Composable
fun PaymentComplete(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Payment Successful!",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.SuccessGreen
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Order ID: ${cartViewModel.getOrderId()}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Text(
                    text = "Total Amount Paid: ${cartViewModel.getCalculatedFinalValue()}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                PrimaryButton(shapeSize = 15f, btnText = "Home") {
                    Instana.reportEvent(CustomEvent("payment_done_count").apply {
                        customMetric = 1.0 //person complted payment count
                    })
                    DBMock.cartItemsList.clear()
                    DBMock.selectedItem = null
                    navController.navigate(RoboShopHomeScreen.DashboardScreen.route)
                }
            }
        }
    }

}