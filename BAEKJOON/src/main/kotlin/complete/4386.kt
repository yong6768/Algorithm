import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.round
import kotlin.math.sqrt

fun main() {
    var reader = BufferedReader(InputStreamReader(System.`in`))
    var N = reader.readLine().toInt()

    var stars = ArrayList<Pair<Double, Double>>(N)
    for(i in 0 until N) {
        var position = reader.readLine().split(" ").map { it.toDouble() }
        stars.add(Pair(position[0], position[1]))
    }

    var minDistance = 0.0

    var visited = BooleanArray(N)
    var pqueue = PriorityQueue<Distance>()

    visited[0] = true
    for(i in 1 until N) {
        var dX = (stars[0].first - stars[i].first)
        var dY = (stars[0].second - stars[i].second)
        var weight = sqrt(dX*dX + dY*dY)
        pqueue.add(Distance(i, weight))
    }

    while(pqueue.isNotEmpty()) {
        var distance = pqueue.poll()!!

        if(visited[distance.to])
            continue

        visited[distance.to] = true
        for(i in 0 until N) {
            var dX = (stars[distance.to].first - stars[i].first)
            var dY = (stars[distance.to].second - stars[i].second)
            var weight = sqrt(dX*dX + dY*dY)
            pqueue.add(Distance(i, weight))
        }

        minDistance += distance.weight
    }
    println(round(minDistance*100)/100)
}

class Distance (
    var to: Int,
    var weight: Double
): Comparable<Distance> {

    override fun compareTo(o: Distance): Int {
        return (this.weight - o.weight).toInt()
    }
}