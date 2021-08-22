import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    var inputs = reader.readLine().split(" ").map { it.toLong() }.sorted()

    var min = Long.MAX_VALUE
    var answers = mutableListOf<Long>()
    for(i in 0 until N-2) {
        for(j in i+1 until N-1) {
            var tmp = inputs[i] + inputs[j]
            var index = inputs.lowerBound(j+1, N-1, -tmp)

            if(index-1 > j
                && ((tmp+inputs[index-1]).absoluteValue < (tmp+inputs[index]).absoluteValue)
            ) {
                index--
            }

            var result = (tmp+inputs[index]).absoluteValue
            if(result < min) {
                min = result
                answers = mutableListOf(inputs[i], inputs[j], inputs[index])
            }
        }
    }

    println(answers.joinToString(" "))
}

fun List<Long>.lowerBound(from: Int, to: Int, target: Long): Int {
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