package dev.joselogar.corium

/* default imports
import android.os.Bundle
import androidx.activity.CompntActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.joselogar.myapplication.ui.theme.MyApplicationTheme
*/

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joselogar.corium.model.ProductProvider
import dev.joselogar.corium.view.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /* default setContent
            CoriumTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
            */

            //ScreenStateProductList()
            //ScreenStateHoistingProductList()
            //ScreenStateHoistingTextFieldProductList()
            //MainScreen()
            //MessageList(messages = listOf("Hola", ":)", "Adiós", ":("))

            //ProductViewModel(products = ProductProvider.products)
            HomeScreen()
        }
    }
}

/* default function
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
*/

/* default preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoriumTheme {
        Greeting("Android")
    }

    //MyButton(text = "Android")
}
*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductViewModel(products = ProductProvider.products)
}

@Composable
fun MyText(text: String) {
    Text(
        text = "$text",
        style = MaterialTheme.typography.button,
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .padding(bottom = 2.dp)
    )
}

@Composable
fun MyButton(text: String) {
    Button(onClick = {
        // Do something
        Log.d("TAG", "On click: $text")
    }) {
        MyText(text = "$text")
    }
}

@Composable
fun ScreenStateProductList() {
    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StateProductList()
    }
}

@Composable
fun StateProductList() {
    val products = remember { mutableStateListOf(
        "315H",
        "332D",
        "332J"
    )}

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        for (product in products) {
            Text(text = product)
        }

        Button(onClick = { products.add("396A") }) {
            Text(text = "Añadir")
        }
    }
}

@Composable
fun ScreenStateHoistingProductList() {
    val products = remember {mutableStateListOf(
        "315H",
        "332D",
        "332J"
    )}

    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StateHoistingProductList(products) {
            products.add("396A")
        }
    }
}

@Composable
fun StateHoistingProductList(
    products: List<String>,
    onButtonClick: () -> Unit
) {
    Column (modifier = Modifier.fillMaxSize()) {
        for (product in products) {
            Text(text = product)
        }

        Button(onClick = onButtonClick) {
            Text(text = "Añadir")
        }
    }
}

@Composable
fun ScreenStateHoistingTextFieldProductList() {
    val products = remember {mutableStateListOf(
        "315H",
        "332D",
        "332J"
    )}
    val newProductModel = remember {mutableStateOf("")}

    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StateHoistingTextFieldProductList(
            products,
            {products.add(newProductModel.value)},
            newProductModel.value,
            {newProduct -> newProductModel.value = newProduct}
        )
    }
}

@Composable
fun StateHoistingTextFieldProductList(
    products : List<String>,
    onButtonClick: () -> Unit,
    productModel: String,
    onProductModelChanged: (String) -> Unit
) {
    Column (modifier = Modifier.fillMaxSize()) {
        for (product in products) {
            Text(text = product)
        }

        TextField(
            value = productModel,
            onValueChange = onProductModelChanged,
        )

        Button(onClick = onButtonClick) {
            Text(text = "Añadir")
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
    val nameState = viewModel.textFieldState.observeAsState("")

    Surface(
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ) {
        MainLayout(name = nameState.value) {
            newName -> viewModel.onTextField(newName)
        }
    }
}

@Composable
fun MainLayout(
    name: String,
    onTextFieldChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = name,
            onValueChange = onTextFieldChange
        )

        Text(text = name)
    }
}

@Composable
fun MessageList(messages: List<String>) {
    LazyColumn {
        items(messages) {
            message -> MessageInfo(message = message)
        }
    }
}

@Composable
fun MessageInfo(message: String) {
    Text(text = message)
}