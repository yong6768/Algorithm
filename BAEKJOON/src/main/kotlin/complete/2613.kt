import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    var reader = BufferedReader(InputStreamReader(System.`in`))
    var words = reader.readLine().split(" ").map { it.toInt() }

    var N = words[0]
    var M = words[1]
    var inputs = reader.readLine().split(" ").map { it.toInt() }

    var left = 0
    var right = inputs.sum()
    while(left < right) {
        var mid = (left+right)/2
        if(check(inputs, mid, M)) {
            right = mid
        } else {
            left = mid+1
        }
    }

    printAnswer(inputs, left, M)
}

fun check(inputs: List<Int>, value: Int, split: Int): Boolean {
    var i = 0
    var count = 0
    var sum = 0
    while(i < inputs.size) {
        if(inputs[i] > value)
            return false

        sum += inputs[i]
        if(sum > value) {
            sum = 0
            count++
            continue
        }
        i++
    }

    if(count >= split)
        return false
    if(sum > value)
        return false

    return true
}

fun printAnswer(inputs: List<Int>, value: Int, split: Int) {
    println(value)
    var answer = ArrayList<Int>()

    var i = 0
    var sum = 0
    var count = 0
    while( i < inputs.size ) {
        count++
        sum += inputs[i]

        if(sum == value) {
            answer.add(count)
            count = 0
            sum = 0
        } else if(sum > value) {
            answer.add(count-1)
            count = 0
            sum = 0
            continue
        }

        if(split == answer.size + (inputs.size-i)) {
            answer.add(count)
            count=0
            sum = 0
        }

        i++
    }

    println(answer.joinToString(" "))
}