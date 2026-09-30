package converter

fun main() {
    println("Enter a number and a measure: ")
    val input = readln().split(" ")
    lateinit var meters: String
    if(input.size != 2 || input[1].lowercase() !in listOf("km", "kilometer", "kilometers") || (input[0] == "1" && input[1] !in listOf("km", "kilometer"))) {
        println("Wrong input")
    }else {
        println("${input[0]} kilometer${if (input[0] != "1") "s" else ""} is ${input[0].toInt() * 1000} meters")
    }


}
