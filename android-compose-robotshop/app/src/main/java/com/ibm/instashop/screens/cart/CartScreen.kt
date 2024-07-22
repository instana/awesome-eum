package com.ibm.instashop.screens.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ibm.instashop.R
import com.ibm.instashop.business_unit.models.DiscountCoupon
import com.ibm.instashop.business_unit.models.DiscountResponse
import com.ibm.instashop.common.utils.efffects.getExactImageUrl
import com.ibm.instashop.graphs.items_detail.DetailScreen
import com.ibm.instashop.screens.common.components.BackButton
import com.ibm.instashop.screens.common.components.CustomInputField
import com.ibm.instashop.screens.common.components.PrimaryButton
import com.ibm.instashop.screens.dashboard.DashboardViewModel
import com.ibm.instashop.screens.dashboard.PriceInputFields
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.TextColor
import com.ibm.instashop.ui.theme.White


@Composable
fun CartScreen(navController: NavController,cartViewModel: CartViewModel = hiltViewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    val discountDataState = cartViewModel.discountState.collectAsState().value
    val context = LocalContext.current
    discountDataState.data?.let {
        val isApplicable = it as DiscountResponse
        if (isApplicable.applicable){
            Toast.makeText(context,"Applied Coupon",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Coupon Not Valid",Toast.LENGTH_SHORT).show()
        }
    }
    if(showDialog){
        CouponsPopup(showDialog = showDialog, onDismiss = { showDialog = false }, onApply = {
            cartViewModel.getDiscount(DiscountCoupon(
                couponId = it,
                cartValue = cartViewModel.getCalculatedFinalValue(),
                userId = "1231231",
                percentage = 5.0
            ))
        })
    }

    ConstraintLayout( modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.White)) {
        val topGuideline = createGuidelineFromTop(0.11f)
        val (topAppBarSection,scrollSection,bottomSection) = createRefs()
        Row(
            modifier = Modifier
                .constrainAs(topAppBarSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onClick = { navController.popBackStack() })
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Your Cart",
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${cartViewModel.getCartItems().size} items",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.TextColor
                )
            }
            Spacer(modifier = Modifier.width(48.dp)) // Placeholder for symmetry
        }
        // Product List
        LazyColumn(
            modifier = Modifier
                .constrainAs(scrollSection) {
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomSection.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .padding(horizontal = 15.dp)
        ) {
            items(cartViewModel.getCartItems()) { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = product.item.image.getExactImageUrl()),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                MaterialTheme.colors.PrimaryLightColor,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Column {
                        Text(
                            text = product.item.title?:"",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.TextColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "$${product.item.price}",
                                color = MaterialTheme.colors.PrimaryColor,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "  x${product.count}", color = MaterialTheme.colors.TextColor)
                        }
                    }
                }
            }
        }
        // Checkout Section
        Column(
            modifier = Modifier
                .constrainAs(bottomSection) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .background(
                    color = MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.scan),
                    contentDescription = null,
                    tint = MaterialTheme.colors.PrimaryColor,
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(15.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text("Add voucher Code", modifier = Modifier.clickable {
                        showDialog = true
                    })
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null
                    )
                }
            }
            // Checkout Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Total")
                    Text(
                        text = "$${cartViewModel.getCalculatedFinalValue()}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.PrimaryColor
                    )
                }
                PrimaryButton(
                    shapeSize = 15f,
                    btnText = "Check Out",
                    onClick = {
                        cartViewModel.count = 0;
                        navController.navigate(DetailScreen.PaymentScreen.route)
                    },
                    width = 120,
                    enabled = cartViewModel.getCartItems().size!=0
                )
            }
        }
    }


}

@Composable
fun CouponsPopup(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onApply: (voucher: String) -> Unit
) {
    var voucherValue by remember { mutableStateOf("") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(text = "Filter")
            },
            text = {
                Column {
                    Text(text = "Apply The Coupon", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomInputField(
                        placeholder = "Coupon",
                        trailingIcon = R.drawable.password,
                        label = "Coupon",
                        keyboardType = KeyboardType.Email,
                        visualTransformation = VisualTransformation.None,
                        onChanged = { voucher ->
                            voucherValue = voucher.text
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onApply(voucherValue)
                        onDismiss()
                    }
                ) {
                    Text("Apply")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            },
            shape = RoundedCornerShape(16.dp),
        )
    }
}