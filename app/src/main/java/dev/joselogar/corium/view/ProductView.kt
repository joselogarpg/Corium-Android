package dev.joselogar.corium.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.joselogar.corium.model.ModelProduct
import dev.joselogar.corium.model.ProviderProduct
import dev.joselogar.corium.ui.theme.Green500
import dev.joselogar.corium.ui.theme.Red500

@Composable
fun ProductView(product: ModelProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                top = 32.dp,
                end = 24.dp
            ),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(
            2.dp,
            MaterialTheme.colors.onBackground
        ),
        elevation = 0.dp
    ) {
        Row {
            Column {
                val color =
                    if (product.available)
                        Green500
                    else
                        Red500

                Image(
                    painter = rememberImagePainter(product.image[0]),
                    contentDescription = product.model,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 8.dp
                        )
                        .size(128.dp)
                        .border(
                            4.dp,
                            color,
                            CircleShape
                        )
                        .padding(all = 8.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = product.colorName[0],
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp
                        ),
                    style = MaterialTheme.typography.subtitle2
                )
            }

            Column {
                Text(
                    text = product.company,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(
                            top = 16.dp
                        ),
                    style = MaterialTheme.typography.h4
                )

                Divider(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            bottom = 16.dp
                        ),
                    thickness = 0.dp
                )

                Text(
                    text = product.model,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h6
                )

                LazyRow {
                    for (each in product.colorImage) {
                        item {
                            IconButton(
                                onClick = { /* TODO */ },
                                modifier = Modifier
                                    .padding(
                                        top = 16.dp,
                                        end = 12.dp,
                                        bottom = 8.dp
                                    )
                                    .size(24.dp)
                                    .clip(CircleShape)
                            ) {
                                Image(
                                    painter = rememberImagePainter(each),
                                    contentDescription = product.model,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductView(ProviderProduct.products.first())
}