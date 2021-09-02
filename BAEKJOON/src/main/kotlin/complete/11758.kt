
fun main() {
    var words = readLine()!!.split(" ").map { it.toInt() }
    var x1 = words[0]
    var y1 = words[1]
    words = readLine()!!.split(" ").map { it.toInt() }
    var x2 = words[0]
    var y2 = words[1]
    words = readLine()!!.split(" ").map { it.toInt() }
    var x3 = words[0]
    var y3 = words[1]

    var tmp = ccw(x1, y1, x2, y2, x3, y3)
    var answer = when {
        tmp < 0 -> -1
        tmp == 0 -> 0
        else -> 1
    }
    println(answer)
}

fun ccw(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Int {
    var a= x1*y2+x2*y3+x3*y1
    var b = x2*y1+x3*y2+x1*y3
    return a-b
}