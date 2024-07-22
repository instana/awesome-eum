package com.ibm.instashop.screens.product_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ibm.instashop.R
import com.ibm.instashop.common.utils.efffects.getExactImageUrl
import com.ibm.instashop.graphs.items_detail.DetailScreen
import com.ibm.instashop.screens.common.components.PrimaryButton
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.TextColor
import com.ibm.instashop.ui.theme.White

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    var productCount by remember { mutableStateOf(productDetailViewModel.selectedItemCount()) }
    val productDetails = productDetailViewModel.getSelectedItem()
    val scrollState = rememberScrollState()
    ConstraintLayout (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.White)
    ){
        val topGuideline = createGuidelineFromTop(0.10f)
        val bottomGuideline = createGuidelineFromBottom(0.20f)
        val (topAppBarSection, scrollableSection, bottomSection) = createRefs()
        TopAppBar(
            title = { Text(productDetails?.category?:"", maxLines = 1, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis) },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            navigationIcon = { Icon(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "ds" , modifier = Modifier
                .constrainAs(topAppBarSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {
                    onBack.invoke()
                }) }
        )

        // Scrollable Content
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .constrainAs(scrollableSection) {
                    top.linkTo(topGuideline)
                    bottom.linkTo(bottomSection.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = productDetails.image.getExactImageUrl()), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = productDetails.category?:"",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\$${productDetails.price?:""}",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row (modifier = Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                RatingBar(rating = productDetails.rating.rate.toFloat())
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "(${productDetails.rating?.count} reviews)",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = productDetails.title?:"",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.TextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = productDetails.description?:"",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colors.TextColor
            )
        }

        // Bottom Section
        Column(
            modifier = Modifier
                .constrainAs(bottomSection) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(bottomGuideline)
                }
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .background(
                    color = MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(modifier = Modifier
                    .background(MaterialTheme.colors.PrimaryLightColor, shape = CircleShape)
                    .padding(2.dp),
                    onClick = {
                    if (productCount > 0) {
                        productCount--
                        productDetailViewModel.updateCart(productDetails,productCount)
                    }
                }) {
                    Text(text = "-", fontSize = 34.sp)
                }
                Text(text = productCount.toString(), fontSize = 20.sp, color = MaterialTheme.colors.TextColor)
                TextButton(modifier = Modifier
                    .background(MaterialTheme.colors.PrimaryLightColor, shape = CircleShape)
                    .padding(2.dp),
                    onClick = {
                    productCount++
                    productDetailViewModel.updateCart(productDetails,productCount)
                }) {
                    Text(text = "+", fontSize = 34.sp)
                }
            }
            PrimaryButton(
                shapeSize = 16f,
                onClick = {
                    navController.navigate(DetailScreen.CartScreen.route)
                },
                btnText = "Checkout Now \uD83D\uDED2"
            )
        }
    }
}

@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    spaceBetween: Dp = 0.dp
) {

    val image = ImageBitmap.imageResource(id = R.drawable.star_border)
    val imageFull = ImageBitmap.imageResource(id = R.drawable.star_filled)

    val totalCount = 5

    val height = LocalDensity.current.run { image.height.toDp() }
    val width = LocalDensity.current.run { image.width.toDp() }
    val space = LocalDensity.current.run { spaceBetween.toPx() }
    val totalWidth = width * totalCount + spaceBetween * (totalCount - 1)


    Box(
        modifier
            .width(totalWidth)
            .height(height)
            .drawBehind {
                drawRating(rating, image, imageFull, space)
            })
}

private fun DrawScope.drawRating(
    rating: Float,
    image: ImageBitmap,
    imageFull: ImageBitmap,
    space: Float
) {

    val totalCount = 5

    val imageWidth = image.width.toFloat()
    val imageHeight = size.height

    val reminder = rating - rating.toInt()
    val ratingInt = (rating - reminder).toInt()

    for (i in 0 until totalCount) {

        val start = imageWidth * i + space * i

        drawImage(
            image = image,
            topLeft = Offset(start, 0f)
        )
    }

    drawWithLayer {
        for (i in 0 until totalCount) {
            val start = imageWidth * i + space * i
            // Destination
            drawImage(
                image = imageFull,
                topLeft = Offset(start, 0f)
            )
        }

        val end = imageWidth * totalCount + space * (totalCount - 1)
        val start = rating * imageWidth + ratingInt * space
        val size = end - start

        // Source
        drawRect(
            Color.Transparent,
            topLeft = Offset(start, 0f),
            size = Size(size, height = imageHeight),
            blendMode = BlendMode.SrcIn
        )
    }
}

private fun DrawScope.drawWithLayer(block: DrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}