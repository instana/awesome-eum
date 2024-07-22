package com.ibm.instashop.screens.dashboard

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ibm.instashop.business_unit.models.Banner
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.common.utils.efffects.ShimmerGrid
import com.ibm.instashop.common.utils.efffects.getExactImageUrl
import com.ibm.instashop.data.local.DBMock
import com.ibm.instashop.graphs.items_detail.DetailScreen
import com.ibm.instashop.ui.theme.CountColor
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.TextColor
import com.ibm.instashop.ui.theme.Verity
import com.ibm.instashop.ui.theme.White
import com.instana.android.Instana
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    navController: NavController,
    productViewModel: DashboardViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val productState = productViewModel.productState.collectAsState()
    val categoriesState = productViewModel.categoriesState.collectAsState()
    val bannerState = productViewModel.bannerState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var productList: ArrayList<ProductItem> = remember { arrayListOf() }
    var bannerList: ArrayList<Banner> = remember { arrayListOf() }
    productState.value.data?.let {
        productList = it as ArrayList<ProductItem>
    }

    bannerState.value.data?.let {
        bannerList = it as ArrayList<Banner>
    }

    var categoryList: ArrayList<String> = remember { arrayListOf() }
    categoriesState.value.data?.let {
        categoryList = it as ArrayList<String>
    }
    if (!categoryList.contains("All")) {
        categoryList.add(0, "All")
    }
    categoryList.sort()
    var selectedCategory by remember { mutableStateOf<String>("All") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.White)
    ) {
        val (lazyColumn, fab) = createRefs()

        if (productState.value.isLoading || productState.value.errorMessage.isNotEmpty()) {
            ShimmerGrid(modifier = Modifier.constrainAs(lazyColumn) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        } else {
            LazyColumn(
                modifier = Modifier
                    .constrainAs(lazyColumn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .wrapContentHeight()
                    .padding(8.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    MediaCarousel(bannerList, "dsds") {

                    }
                }
                item {
                    LazyRow(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            categoryList
                        ) { category ->
                            CategoryBubble(
                                category = category,
                                isSelected = category == selectedCategory,
                                onItemClick = { categoryClicked ->
                                    selectedCategory = categoryClicked
                                    productViewModel.getProductWithCategory(selectedCategory)
                                })
                        }
                    }
                }
                items((productList).chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (item in rowItems) {
                            ProductItem(
                                navController = navController,
                                product = item,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }

            }
        }
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .constrainAs(fab) {
                    bottom.linkTo(parent.bottom, margin = 60.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                },
            backgroundColor = MaterialTheme.colors.Verity,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Menu, // Replace with your icon if necessary
                contentDescription = "Add"
            )
        }
        if (showDialog) {
            FilterPopup(
                showDialog = showDialog,
                productViewModel = productViewModel,
                onDismiss = { showDialog = false },
                onApply = { minPriceValue, maxPriceValue ->
                    Instana.view = "Filter pop"
                    showDialog = false // Dismiss the dialog after applying
                    productViewModel.applyFilter(minPriceValue, maxPriceValue)
                }
            )
        }
    }
}

@Composable
fun CategoryBubble(category: String, isSelected: Boolean, onItemClick: (String) -> Unit) {
    val backgroundColor =
        if (isSelected) MaterialTheme.colors.PrimaryColor else MaterialTheme.colors.PrimaryLightColor
    val textColor = if (isSelected) Color.White else MaterialTheme.colors.TextColor
    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = CircleShape)
            .padding(8.dp)
            .clickable { onItemClick.invoke(category) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = category.replaceFirstChar { it.uppercase() }, color = textColor)
    }
}

@Composable
fun ProductItem(
    navController: NavController,
    product: ProductItem,
    modifier: Modifier = Modifier,
    productViewModel: DashboardViewModel = hiltViewModel()
) {
    val countOfItem = productViewModel.getItemSelectedCount(product.id)
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .height(245.dp)
            .padding(7.dp)
            .clickable {
                DBMock.selectedItem = product;
                navController.navigate(DetailScreen.ProductDetailScreen.route)
            },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colors.PrimaryLightColor)) {
            Image(
                painter = rememberAsyncImagePainter(model = product.image.getExactImageUrl()),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                maxLines = 2,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 7.dp)
            ) {
                Text(
                    text = "$ ${product.price}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.Verity,
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                if (countOfItem != 0) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(
                                color = MaterialTheme.colors.CountColor,
                                shape = CircleShape
                            )
                            .padding(3.dp, end = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${countOfItem}",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun MediaCarousel(
    list: List<Banner>,
    carouselLabel: String = "",
    autoScrollDuration: Long = 3000,
    onItemClicked: () -> Unit
) {
    val pageCount = list.size
    val pagerState: PagerState = rememberPagerState(initialPage = 0)
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(
                            page = nextPage,
                            animationSpec = tween(
                                durationMillis = 3000
                            )
                        )
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.height(100.dp)) {
        Box {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 1.dp),
                itemSpacing = 10.dp,
                count = pageCount
            ) { page: Int ->
                val item: Banner? = list[page]
                item?.let {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 5.dp,
                        onClick = { onItemClicked() },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(item.color)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            AsyncImage(
                                model = item.backgroundImageUrl.getExactImageUrl(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.3f))
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 10.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(10.dp)
                                    ) {
                                        Text(
                                            text = item.bannerTitle,
                                            color = Color.White,
                                            style = MaterialTheme.typography.h6
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = item.bannerSubTitle,
                                            color = Color.White,
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                    AsyncImage(
                                        model = item.itemImageUrl.getExactImageUrl(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(70.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (carouselLabel.isNotBlank()) {
            Text(
                text = carouselLabel,
            )
        }
    }
}

@Composable
fun FilterPopup(
    showDialog: Boolean,
    productViewModel: DashboardViewModel,
    onDismiss: () -> Unit,
    onApply: (minPrice: String, maxPrice: String) -> Unit
) {
    var minPrice by remember { mutableStateOf("4") }
    var maxPrice by remember { mutableStateOf("40") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(text = "Filter")
            },
            text = {
                Column {
                    Text(text = "Set Price Range", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    PriceInputFields(
                        onMaxPriceChange = {
                            maxPrice = it
                        },
                        onMinPriceChange = {
                            minPrice = it
                        }
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        TextButton(onClick = {
                            productViewModel.resetFilters()
                            onDismiss()
                        }) {
                            Text(text = "Clear Filters")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onApply(minPrice, maxPrice)
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

@Composable
fun PriceInputFields(
    minPrice: String = "4",
    maxPrice: String = "40",
    onMinPriceChange: (String) -> Unit,
    onMaxPriceChange: (String) -> Unit
) {
    var minPriceText by remember { mutableStateOf(minPrice) }
    var maxPriceText by remember { mutableStateOf(maxPrice) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Min Price", fontSize = 14.sp)
            TextField(
                value = minPriceText,
                onValueChange = {
                    minPriceText = it
                    onMinPriceChange(it)
                },
                modifier = Modifier.width(100.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Max Price", fontSize = 14.sp)
            TextField(
                value = maxPriceText,
                onValueChange = {
                    maxPriceText = it
                    onMaxPriceChange(it)
                },
                modifier = Modifier.width(100.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
}


