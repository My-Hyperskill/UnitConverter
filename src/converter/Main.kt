package converter

fun main() {
    val unitsList = listOf("m", "km", "cm", "mm", "mi", "yd", "ft", "in")
    val unitsListSingular = listOf("meter", "kilometer", "centimeter", "millimeter", "mile", "yard", "foot", "inch")
    val unitsListPlural =
        listOf("meters", "kilometers", "centimeters", "millimeters", "miles", "yards", "feet", "inches")

    println("Enter a number and a measure of length: ")
    val input = readln().split(" ").map { it.lowercase() }
    if (input.size != 2 || input[1] !in (unitsList.union(unitsListSingular.union(unitsListPlural)))
    ) {
        println("Wrong input. Unknown unit ${input[1]}")
    } else {
        val x = input[0].toDouble()
        val unit = input[1]

        val union = unitsList.union(unitsListSingular.union(unitsListPlural))
        val index = union.indexOf(unit) % unitsList.size
        val result = when (index) {
            0 -> { x }
            1 -> { x * 1000 }
            2 -> { x * 0.01 }
            3 -> { x * 0.001 }
            4 -> { x * 1609.35 }
            5 -> { x * 0.9144 }
            6 -> { x * 0.3048 }
            7 -> { x * 0.0254 }
            else -> { -1.0 }
        }

        val resultUnit = if(x != 1.0) unitsListPlural[index] else unitsListSingular[index]

        println("$x $resultUnit is $result meter${if (result != 1.0) "s" else ""}")
    }
}
