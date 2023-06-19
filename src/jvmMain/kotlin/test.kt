import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

object MainViewModel {

    var bigList = mutableListOf<Employee>(
        Employee("test1"),
        Employee("test2"),
        Employee("test3"),
        Employee("aatest1")
    )

    var _state = mutableStateOf(
        MainState(
            
        )
    )

    var _employeesListStateLeft: MutableState<List<Employee>> = mutableStateOf(bigList)
    var employeesListStateLeft: State<List<Employee>> = _employeesListStateLeft

    var _employeesListStateRight: MutableState<List<Employee>> = mutableStateOf(listOf<Employee>())
    var employeesListStateRight: State<List<Employee>> = _employeesListStateRight

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnItemClickLeft -> {
                checkItemLeft(event.position)
            }

            is Event.OnItemClickRight -> {
                checkItemRight(event.position)
            }
        }
    }

    fun checkItemLeft(position: Int) {
//        listEmployeeState.value[position].isChecked = !listEmployeeState.value[position].isChecked
        _employeesListStateLeft.value = employeesListStateLeft.value.let {
            it[position].isChecked = !employeesListStateLeft.value[position].isChecked
        }
    }

    fun checkItemRight(position: Int) {
//        listEmployeeState.value[position].isChecked = !listEmployeeState.value[position].isChecked
        _employeesListStateLeft.value = employeesListStateLeft.value.also {
            it[position].isChecked = !employeesListStateLeft.value[position].isChecked
        }
    }

    fun findRight(name: String) = bigList.find { it.employeeName.startsWith(name) }

}

sealed class Event {
    data class OnItemClickLeft(val position: Int) : Event()
    data class OnItemClickRight(val position: Int) : Event()


}

data class MainState(
    val ListEmployeeleft: MutableList<Employee>,
    val ListEmployeeRight: MutableList<Employee>
)

