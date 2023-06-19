//class to create an employee
data class Employee(val employeeNameToSet: String) {
    val employeeName = employeeNameToSet
    private var totalMoney = 0
    private var totalWorks = 0

    //format - 'code':[amount, total_money_per_code]
    private var generalWorks: HashMap<Int, MutableList<Int>> = HashMap()
    private var additionalWorks: HashMap<Int, MutableList<Int>> = HashMap()

    private var shownLeft = false
    private var shownRight = false
    private var chosenLeft = false
    private var chosenRight = false
    var isChecked = false

    fun setGeneralWorks(generalWorksToSet: HashMap<Int, MutableList<Int>>) {
        this.generalWorks = generalWorksToSet
    }

    fun setAdditionalWorks(additionalWorksToSet: HashMap<Int, MutableList<Int>>) {
        this.additionalWorks = additionalWorksToSet
    }

    //update generalWorks or additionalWorks
    //to access generalWorks you should use 0 at worksToChange, otherwise any other number
    fun updateWorks(worksToChange: Int, codeOfManipulation: Int, numberOfManipulations: Int, priceOfManipulation: Int)
    {
        var tempMap: HashMap<Int, MutableList<Int>> = if (worksToChange == 0)
            HashMap(this.generalWorks)
        else
            HashMap(this.additionalWorks)


        var number = if (tempMap[codeOfManipulation]?.get(0) == null) 0 else tempMap[codeOfManipulation]?.get(0)
        number = number!! + (numberOfManipulations)
        var money = if (tempMap[codeOfManipulation]?.get(1) == null) 0 else tempMap[codeOfManipulation]?.get(1)
        money = money!! + priceOfManipulation
        tempMap[codeOfManipulation] = mutableListOf(number, money)

        this.addTotalWorks(numberOfManipulations)
        this.addTotalMoney(priceOfManipulation)

        if (worksToChange == 0) {
            this.setGeneralWorks(tempMap)
        } else {
            this.setAdditionalWorks(tempMap)
        }
    }

    fun addTotalMoney(moneyToAdd: Int) {
        this.totalMoney += moneyToAdd
    }

    fun addTotalWorks(worksToAdd: Int) {
        this.totalWorks += worksToAdd
    }

    fun isShownLeft(): Boolean {
        return this.shownLeft
    }

    fun isShownRight(): Boolean {
        return this.shownRight
    }

    fun isChosenLeft(): Boolean {
        return this.chosenLeft
    }

    fun isChosenRight(): Boolean {
        return this.chosenRight
    }

    fun switchShowLeft() {
        this.shownLeft = !this.shownLeft
    }

    fun switchShowRight() {
        this.shownRight = !this.shownRight
    }

    fun switchChosenLeft() {
        this.chosenLeft = !this.chosenLeft
    }

    fun switchChosenRight() {
        this.chosenRight = !this.chosenRight
    }

}

fun main() {
    val employee = Employee("Fedor")
    var list: HashMap<Int, MutableList<Int>> = HashMap()
    list[123] = mutableListOf(10, 20)
    employee.setGeneralWorks(list)
    employee.updateWorks(0, 123, 100, 200)
}