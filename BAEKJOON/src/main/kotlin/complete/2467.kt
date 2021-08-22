import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    var inputs = reader.readLine().split(" ").map { it.toInt() }.sorted()

    var min = Int.MAX_VALUE
    var answer = mutableListOf<Int>()
    for(i in 0 until N-1) {
        var index = inputs.lowerBound(i+1, N-1, -inputs[i])
        if(index > i+1
            && (inputs[index-1]+inputs[i]).absoluteValue < (inputs[index]+inputs[i]).absoluteValue
        ) {
            index--
        }

        var result = (inputs[i]+inputs[index]).absoluteValue
        if(result < min) {
            min = result
            answer = mutableListOf(inputs[i], inputs[index])
        }
    }

    println(answer.joinToString(" "))
}

fun List<Int>.lowerBound(from: Int, to: Int, target: Int): Int {
    var left = from
    var right = to
    while(left < right) {
        var mid = (left+right)/2
        var value = this[mid]
        if(value < target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}