package converter

fun main() {
    val lengthUnitsList = listOf(
        "m", "km", "cm", "mm", "mi", "yd", "ft", "in",
        "meter", "kilometer", "centimeter", "millimeter", "mile", "yard", "foot", "inch",
        "meters", "kilometers", "centimeters", "millimeters", "miles", "yards", "feet", "inches"
    )
    val weightUnitsList = listOf(
        "g", "kg", "mg", "lb", "oz",
        "gram", "kilogram", "milligram", "pound", "ounce",
        "grams", "kilograms", "milligrams", "pounds", "ounces"
    )

    val (lengthUnits, lengthUnitsSingular, lengthUnitsPlural) = lengthUnitsList.chunked(8)
    val (weightUnits, weightUnitsSingular, weightUnitsPlural) = weightUnitsList.chunked(5)

    do {
        print("Enter what you want to convert (or exit): ")
        val input = readln().split(" ").map {it.lowercase()}

        if(input.size == 4) {
            val (number, unitSource, word, unitTarget) = input
            val source = number.toDouble()
            var result: Double
            when (unitSource) {
                in lengthUnitsList if unitTarget in lengthUnitsList -> {
                    val indexSource = lengthUnitsList.indexOf(unitSource) % lengthUnits.size
                    val indexTarget = lengthUnitsList.indexOf(unitTarget) % lengthUnits.size
                    val meters = when (indexSource) {
                        0 -> { source }
                        1 -> { source * 1000.0 }
                        2 -> { source * 0.01 }
                        3 -> { source * 0.001 }
                        4 -> { source * 1609.35 }
                        5 -> { source * 0.9144 }
                        6 -> { source * 0.3048 }
                        7 -> { source * 0.0254 }
                        else -> { -1.0 }
                    }
                    result = when (indexTarget) {
                        0 -> { meters }
                        1 -> { meters * 0.001 }
                        2 -> { meters * 100.0 }
                        3 -> { meters * 1000.0 }
                        4 -> { meters * 0.00062136887563 }
                        5 -> { meters * 1.09361 }
                        6 -> { meters * 3.28084 }
                        7 -> { meters * 39.3701 }
                        else -> { -1.0 }
                    }
                    println("$source ${if (source == 1.0) lengthUnitsSingular[indexSource] else lengthUnitsPlural[indexSource]}" +
                            " is $result ${if (result == 1.0) lengthUnitsSingular[indexTarget] else lengthUnitsPlural[indexTarget]}\n")
                }
                in weightUnitsList if unitTarget in weightUnitsList -> {
                    val indexSource = weightUnitsList.indexOf(unitSource) % weightUnits.size
                    val indexTarget = weightUnitsList.indexOf(unitTarget) % weightUnits.size
                    val grams = when (indexSource) {
                        0 -> { source }
                        1 -> { source * 1000.0 }
                        2 -> { source * 0.001 }
                        3 -> { source * 453.592 }
                        4 -> { source * 28.3495 }
                        else -> { -1.0 }
                    }
                    result = when (indexTarget) {
                        0 -> { grams }
                        1 -> { grams * 0.001 }
                        2 -> { grams * 1000.0 }
                        3 -> { grams * 0.00220462 }
                        4 -> { grams * 0.03527399072294044}
                        else -> { -1.0 }
                    }
                    println("$source ${if (source == 1.0) weightUnitsSingular[indexSource] else weightUnitsPlural[indexSource]}" +
                            " is $result ${if (result == 1.0) weightUnitsSingular[indexTarget] else weightUnitsPlural[indexTarget]}\n")
                }
                else -> {
                    fun pluralName(unit: String): String? = when (unit) {
                        in lengthUnitsList -> lengthUnitsPlural[lengthUnitsList.indexOf(unit) % lengthUnits.size]
                        in weightUnitsList -> weightUnitsPlural[weightUnitsList.indexOf(unit) % weightUnits.size]
                        else -> null
                    }
                    println(
                        "Conversion from ${pluralName(unitSource) ?: "???"}" +
                                " to ${pluralName(unitTarget) ?: "???"} is impossible\n"
                    )
                }
            }

        }

    }while(input.first() != "exit")
}