import java.io.*
import java.util.*

var N = 0
var K = 0
lateinit var jewelries: PriorityQueue<Pair<Int, Int>>
lateinit var bags: TreeSet<Pair<Int, Int>>

fun main() {
    init()

    var maxPrice = 0L
    while(jewelries.isNotEmpty()) {
        var (w, p) = jewelries.poll()

        val ceiling = bags.ceiling(Pair(w, 0)) ?: continue

        maxPrice += p
        bags.remove(ceiling)
    }

    println(maxPrice)
}

fun init() {
    var reader = BufferedReader(InputStreamReader(System.`in`))
    var line = reader.readLine()
    var words = line.split(" ")
    N = words[0].toInt()
    K = words[1].toInt()

    jewelries = PriorityQueue{o1, o2 -> o2.second - o1.second}
    for(i in 0 until N) {
        words = reader.readLine().split(" ")
        jewelries.add(Pair(words[0].toInt(), words[1].toInt()))
    }

    bags = TreeSet{o1, o2, ->
        if(o1.first == o2.first)
            o1.second - o2.second
        else
            o1.first - o2.first
    }
    for(i in 0 until K) {
        words = reader.readLine().split(" ")
        bags.add(Pair(words[0].toInt(), i))
    }
}