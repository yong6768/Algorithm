import java.io.*
import java.util.*

fun main() {
    var reader = BufferedReader(InputStreamReader(System.`in`))
    var pq = PriorityQueue<Int>()
    var N = reader.readLine().toInt()

    for(i in 0 until N) {
        pq.add(reader.readLine().toInt())
    }

    var answer = 0
    while(pq.size >= 2) {
        var num1 = pq.poll()
        var num2 = pq.poll()
        var sum = num1+num2
        answer+=sum
        pq.add(sum)
    }
    println(answer)
}