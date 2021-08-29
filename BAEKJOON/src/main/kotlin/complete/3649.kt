import java.io.BufferedReader
import java.io.InputStreamReader

var reader = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    var inputs: String? = null
    while(true) {
        inputs = reader.readLine()
        if(inputs == null || inputs.isEmpty())
            break

        var holeSize = inputs.toLong()*10000000
        var n = reader.readLine().toInt()
        var inputs = LongArray(n)
        for(i in 0 until n) {
            inputs[i] = reader.readLine().toLong()
        }
        inputs.sort()

        var answer1 = -1L
        var answer2 = -1L
        var max = -1L
        for(i in 0 until n-1) {
            val index = inputs.binarySearch(holeSize - inputs[i], i + 1, n)
            if(index < 0)
                continue

            if(inputs[index] - inputs[i] >= max) {
                answer1 = inputs[i]
                answer2 = inputs[index]
                max = answer2 - answer1
            }
        }

        if(max < 0) {
            println("danger")
        } else {
            println("yes $answer1 $answer2")
        }
    }
}