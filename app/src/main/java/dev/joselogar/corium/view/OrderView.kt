package dev.joselogar.corium.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.joselogar.corium.model.ModelOrder
import dev.joselogar.corium.ui.theme.Green500
import dev.joselogar.corium.ui.theme.Red500

@Composable
fun OrderView(order: ModelOrder) {
    val color =
        if (order.inProgress)
            Green500
        else
            Red500

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
            color
        ),
        elevation = 0.dp
    ) {
        Row {
            Column {
                Text(
                    text = order.model,
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

                Row {
                    Text(
                        text = order.quantity.toString(),
                        modifier = Modifier
                        .padding(
                            start = 16.dp,
                            bottom = 16.dp
                        ),
                        style = MaterialTheme.typography.h6
                    )

                    Text(
                        text = order.date,
                        modifier = Modifier
                            .padding(
                                start = 170.dp,
                                bottom = 16.dp
                            ),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}