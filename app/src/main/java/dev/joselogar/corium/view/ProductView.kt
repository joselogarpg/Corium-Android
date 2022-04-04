package dev.joselogar.corium.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dev.joselogar.corium.model.ProductModel
import dev.joselogar.corium.model.ProductProvider
import dev.joselogar.corium.ui.theme.Teal200

@Composable
fun ProductView(product: ProductModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        /* shadow below the card */
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, Teal200),
        elevation = 8.dp
    ) {
        Row {
            Column {
                var color: Color

                if (product.available)
                    color = Color(27, 94, 32)
                else
                    color = Color(183, 28, 28)

                Image(
                    painter = rememberImagePainter(product.image[0]),
                    contentDescription = product.model,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 8.dp)
                        .size(125.dp)
                        .border(4.dp, color, CircleShape)
                        .padding(8.dp)
                        .clip(CircleShape)
                )
            }

            Column {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    text = product.company
                )

                Divider(
                    modifier = Modifier.padding(top = 8.dp),
                    thickness = 2.dp,
                    color = Teal200)

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    text = product.model
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp, end = 16.dp, bottom = 8.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    text = product.year.toString()
                )
            }

            Row {
                for (each in product.color_image) {
                    IconButton(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
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