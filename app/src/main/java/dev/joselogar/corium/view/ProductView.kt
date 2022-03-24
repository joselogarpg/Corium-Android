package dev.joselogar.corium.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dev.joselogar.corium.model.ProductModel
import dev.joselogar.corium.model.ProductProvider

@Composable
fun ProductView(product: ProductModel) {
    Card(
        Modifier
            .fillMaxWidth(),
        /* shadow below the card */
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = 8.dp
    ) {
        Column {
            Row {
                Column {
                    var color = #b71c1c

                    if (product.available == true)
                        color = #b71c1c
                    else
                        color = #b71c1c

                    Image(
                        painter = rememberImagePainter(product.image[0]),
                        contentDescription = product.model,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(125.dp)
                            .border(4.dp, Color(0xFF2E2D88), CircleShape)
                            .padding(8.dp)
                            .clip(CircleShape)
                    )
                }

                Column {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp),
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        text = product.company
                    )

                    Divider(thickness = 2.dp)

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(20.dp),
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        text = product.model
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(16.dp),
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        text = product.year.toString()
                    )
                }
            }

            Row {
                for (each in product.color_image) {
                    IconButton(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 16.dp)
                            .size(25.dp)
                            .clip(CircleShape),
                        onClick = { /*TODO*/ }
                    ) {
                        Image(
                            painter = rememberImagePainter(each),
                            contentDescription = product.model,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    /* Product */
    ProductView(product = ProductProvider.products.first())
    /* Products */
    //ProductViewModel(products = ProductProvider.products)
}