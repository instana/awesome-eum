package com.ibm.instashop.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ibm.instashop.R
import com.ibm.instashop.business_unit.models.Notification
import com.ibm.instashop.common.utils.efffects.ShimmerEffect
import com.ibm.instashop.common.utils.efffects.millisToDateTime
import com.ibm.instashop.screens.dashboard.DashboardViewModel
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.White

@Composable
fun NotificationScreen(
    navController: NavController,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    var notificationList: ArrayList<Notification> = remember { arrayListOf() }
    LaunchedEffect(Unit) {
        dashboardViewModel.getNotifications()
    }
    val notificationState = dashboardViewModel.notificationState.collectAsState().value
    notificationState.data?.let {
        notificationList = it as ArrayList<Notification>
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.White)
    ) {
        val (topAppBarSection, scrollableSection) = createRefs()
        TopAppBar(
            title = { Text("Notifications") },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            navigationIcon = {
                Icon(painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "notificaiton",
                    modifier = Modifier
                        .constrainAs(topAppBarSection) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .clickable {
                            navController.popBackStack()
                        })
            }
        )
        if (notificationState.isLoading) {
            ShimmerNotification()
        } else {
            LazyColumn(
                modifier = Modifier
                    .constrainAs(scrollableSection) {
                        top.linkTo(topAppBarSection.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(8.dp, bottom = 50.dp, top = 90.dp),
                verticalArrangement = Arrangement.Top
            ) {
                items(notificationList) { item ->
                    NotificationItem(notification = item)
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

}

@Composable
fun ShimmerNotification(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), // 2 columns
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, top = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .height(100.dp)
                    .padding(7.dp),
                elevation = 4.dp
            ) {
                ShimmerEffect()
            }
        }
    }
}

@Composable
fun NotificationItem(notification: Notification) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {

            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.PrimaryLightColor)
                .padding(16.dp)
        ) {
            Text(text = notification.title, style = MaterialTheme.typography.h6)
            Text(text = notification.subTitle, style = MaterialTheme.typography.body2)
            Text(
                text = notification.dateTime.toLong().millisToDateTime(),
                style = MaterialTheme.typography.caption
            )
        }
    }
}