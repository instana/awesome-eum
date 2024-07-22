package com.ibm.instashop.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibm.instashop.R
import com.ibm.instashop.screens.dashboard.DashboardViewModel
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.TextColor
import com.ibm.instashop.ui.theme.White

@Composable
fun AppBar(
    isVisible: Boolean,
    searchCharSequence: (String) -> Unit,
    onNotificationIconClick: () -> Unit,
    dashboardViewModel: DashboardViewModel =  hiltViewModel(),
    onCartIconClick: () -> Unit
) {
    var typedText by remember {
        mutableStateOf(TextFieldValue())
    }
    if (isVisible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.White)
                .padding(start = 15.dp, end = 15.dp, top = 30.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TextField(
                value = typedText,
                onValueChange = { newText ->
                    typedText = newText
                    searchCharSequence(newText.text)
                },
                singleLine = true,
                placeholder = { Text(text = "Search product", color = MaterialTheme.colors.TextColor) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Product Search Icon", tint = MaterialTheme.colors.TextColor
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.PrimaryColor
                ),


                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.PrimaryLightColor,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .weight(1f),

                )

            ConstraintLayout() {
                val (notification, notificationCounter) = createRefs()

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.PrimaryLightColor)
                        .constrainAs(notification) {}
                        .clickable {
                            onCartIconClick()
                        },

                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Notification Icon"
                    )

                }
                //cart count
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(color = Color.Red, shape = CircleShape)
                        .padding(3.dp)
                        .constrainAs(notificationCounter) {
                            top.linkTo(notification.top)
                            end.linkTo(notification.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = dashboardViewModel.getTotalCartValue().toString(), fontSize = 11.sp, color = Color.White)
                }

            }
            ConstraintLayout() {
                val (notification, notificationCounter) = createRefs()

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.PrimaryLightColor)
                        .constrainAs(notification) {}
                        .clickable {
                            onNotificationIconClick()
                        },

                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "Notification Icon"
                    )

                }
                //notification count
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(color = Color.Red, shape = CircleShape)
                        .padding(3.dp)
                        .constrainAs(notificationCounter) {
                            top.linkTo(notification.top)
                            end.linkTo(notification.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "4", fontSize = 11.sp, color = Color.White)
                }

            }


        }
    }


}