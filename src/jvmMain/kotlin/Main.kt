import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.text.font.FontWeight

data class ListItem(
    val title: String,
    val isSelected: Boolean
)

@Composable
@Preview
fun App() {
    val baseColor = Color(240,220,4)
    val secondaryColor = Color(174, 160, 0)
    val backgroundColor = Color(151, 145, 112, 50)
    val backgroundFullColor = Color(151, 145, 112)
    val listItemWidth = 200.dp
    val listItemHeight = 43.dp
    val listHeight = 400.dp
    val textFieldHeight = 50.dp
    var stateLeft by remember { mutableStateOf("") }
    var stateRight by remember { mutableStateOf("") }
    var groupsExpanded by remember { mutableStateOf(false) }

    val state = MainViewModel.employeesListStateLeft.value

    var groupText by remember { mutableStateOf("Выберите группу") }
    var groups = arrayOf("group1", "group2", "Отмена")
    var employees by remember {
        mutableStateOf(
            (1..20).map {
                Employee(
                    employeeNameToSet = "hello"
                )
            }
        )
    }

    MaterialTheme {
        Column(modifier = Modifier.absoluteOffset(30.dp, 30.dp)) {

            Button(onClick = { findEmployees() },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier
                    .absoluteOffset(15.dp, 0.dp)
                    .width(170.dp))
            {
                Text("Найти техников", fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier
                .width(listItemWidth)
                .height(listHeight)
                .absoluteOffset(0.dp, 5.dp)
                .background(backgroundColor, shape = RoundedCornerShape(7.dp))) {

                TextField(modifier = Modifier
                    .height(textFieldHeight)
                    .width(listItemWidth),
                    value = stateLeft,
                    onValueChange = { value ->
                        stateLeft = value
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        leadingIconColor = Color.Black,
                        focusedIndicatorColor = backgroundFullColor,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = ""
                        )
                    })

                LazyColumn() {
                    items(state.size) { i ->
                        Box(modifier = Modifier
                            .clickable(
                                onClick = { MainViewModel.onEvent(Event.OnItemClickLeft(i)) }
                            )
                                .width(listItemWidth)
                                .height(listItemHeight)
                                .padding(10.dp),
                            //horizontalArrangement = Arrangement.SpaceBetween,
                            //verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = employees[i].employeeName)
                            if(employees[i].isChecked) {
                                println("hello")
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = secondaryColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

        }

        Column(modifier = Modifier.absoluteOffset(245.dp, 150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier
                    .width(110.dp)) {
                Text("Добавить", fontWeight = FontWeight.Bold)
            }
            Button(onClick = {

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier
                    .width(110.dp)) {
                Text("Удалить", fontWeight = FontWeight.Bold)
            }
            Button(onClick = {

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier
                    .width(155.dp)) {
                Text("Добавить всех", fontWeight = FontWeight.Bold)
            }
            Button(onClick = {

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                modifier = Modifier
                    .width(155.dp)) {
                Text("Удалить всех", fontWeight = FontWeight.Bold)
            }
        }

        Column(modifier = Modifier.absoluteOffset(410.dp, 30.dp)) {

            Box() {
                Button(onClick = {
                    groupsExpanded = true
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = baseColor),
                    modifier = Modifier
                        .absoluteOffset(15.dp, 0.dp)
                        .width(170.dp))
                {
                    Text(groupText, fontWeight = FontWeight.Bold)
                }
                DropdownMenu(
                    expanded = groupsExpanded,
                    onDismissRequest = {
                        groupsExpanded = false
                    },
                    offset = DpOffset(15.dp, 0.dp)
                ) {
                    // adding items
                    groups.forEachIndexed { _, itemValue ->
                        DropdownMenuItem(
                            onClick = {
                                groupText = if (itemValue == "Отмена") "Выберите группу"
                                else itemValue
                                groupsExpanded = false
                            }
                        ) {
                            Text(text = itemValue)
                        }
                    }
                }
            }

            Column(modifier = Modifier
                .width(listItemWidth)
                .height(listHeight)
                .absoluteOffset(0.dp, 5.dp)
                .background(backgroundColor, shape = RoundedCornerShape(7.dp))) {

                TextField(modifier = Modifier
                    .height(textFieldHeight)
                    .width(listItemWidth),
                    value = stateRight,
                    onValueChange = { value ->
                        stateRight = value
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        leadingIconColor = Color.Black,
                        focusedIndicatorColor = backgroundFullColor,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = ""
                        )
                    })

                LazyColumn() {
                    items(employees.size) { i ->
                        Row(modifier = Modifier
                            .width(listItemWidth)
                            .height(listItemHeight)
                            .clickable (
                                onClick = { MainViewModel.onEvent(Event.OnItemClickRight(i)) }
                            )

                            .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = employees[i].employeeName)
                            if(employees[i].isChosenRight()) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = secondaryColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun findEmployees() {
    TODO("Not yet implemented")
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
            title = "Артдент",
            state = WindowState(width = 850.dp, height = 550.dp)
    ) {
        App()
    }
}
