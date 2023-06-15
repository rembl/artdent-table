import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

data class ListItem(
    val title: String,
    val isSelected: Boolean
)

@Composable
@Preview
fun App() {
    val baseColor = Color(240,220,4)
    var text by remember { mutableStateOf("Hello, World!") }
    var items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(
                    title = "Item $it",
                    isSelected = false
                )
            }
        )
    }

    MaterialTheme {
        Column {
            Button(onClick = {
                text = "Hello, Desktop!"
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier.absoluteOffset(50.dp, 30.dp))
            {
                Text(text)
            }

            LazyColumn(modifier = Modifier
                .absoluteOffset(20.dp, 30.dp)
                .width(200.dp)
                .height(400.dp)) {
                items(items.size) { i ->
                    Row(
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp)
                            .clickable {
                                items = items.mapIndexed { j, item ->
                                    if (i == j) {
                                        item.copy(isSelected = !item.isSelected)
                                    } else item
                                }
                            }
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = items[i].title)
                        if(items[i].isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = baseColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }

        Column {
            Button(onClick = {
                text = "Hello, Desktop!"
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier.absoluteOffset(350.dp, 30.dp))
            {
                Text(text)
            }

            LazyColumn(modifier = Modifier
                .absoluteOffset(320.dp, 30.dp)
                .width(200.dp)
                .height(400.dp)) {
                items(items.size) { i ->
                    Row(
                        modifier = Modifier
                            .width(200.dp)
                            .height(40.dp)
                            .clickable {
                                items = items.mapIndexed { j, item ->
                                    if (i == j) {
                                        item.copy(isSelected = !item.isSelected)
                                    } else item
                                }
                            }
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = items[i].title)
                        if(items[i].isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = baseColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
            title = "Артдент",
            state = WindowState(width = 850.dp, height = 550.dp)
    ) {
        App()
    }
}
