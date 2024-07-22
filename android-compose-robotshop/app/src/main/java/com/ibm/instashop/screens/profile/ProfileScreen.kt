package com.ibm.instashop.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibm.instashop.R
import com.ibm.instashop.screens.settings.SettingsViewModel
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.TextColor

@Composable
fun ProfileScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "Hi, ${settingsViewModel.getUserName()}",
                color = MaterialTheme.colors.TextColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (image, cameraIcon) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .constrainAs(image) {
                        linkTo(start = parent.start, end = parent.end)
                    }
                    .background(color = MaterialTheme.colors.PrimaryLightColor)
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.constrainAs(cameraIcon) {
                bottom.linkTo(image.bottom)
                end.linkTo(image.end)

            }) {

                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Change Picture",
                        modifier = Modifier.background(Color.LightGray)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)

                .background(
                    MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.order_history),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Order History", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)

                .background(
                    MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Notification", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }


        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Wishlist Items", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }


        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)

                .background(
                    MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.password),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Change Password", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Logout", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}