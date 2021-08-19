import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.util.*

//문제
//트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.
//
//입력
//트리가 입력으로 주어진다. 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.
//먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다. 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다. 각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.
//
//출력
//첫째 줄에 트리의 지름을 출력한다.

//예제 입력 1
//5
//1 3 2 -1
//2 4 4 -1
//3 1 2 4 3 -1
//4 2 4 3 3 5 6 -1
//5 4 6 -1
//예제 출력 1
//11

lateinit var nodes: Array<Node>
var max = 0

fun main() {
    var reader = BufferedReader(InputStreamReader(System.`in`))
    val V = reader.readLine()!!.toInt()
    nodes = Array<Node>(V+1){Node(it)}

    for(i in 0 until V) {
        var input = reader.readLine()!!.split(" ")
        var from = input[0].toInt()

        for(j in 1 until input.size-1 step (2)) {
            var to = input[j].toInt()
            var weight = input[j+1].toInt()

            var edge = Edge(from, to, weight)
            nodes[from].edges.add(edge)
        }
    }

    nodes[1].getMaxChildLength()
    println(max)
}

class Node(
    val nodeNum: Int
) {
    var edges = mutableListOf<Edge>()

    // 자식의 최대 길이 2개 반환
    // 자식이 없거나 하나인 경우 0 반환
    fun getMaxChildLength(): Pair<Int, Int> {
        var childLen = PriorityQueue<Int>(Collections.reverseOrder())
        childLen.add(0)
        childLen.add(0)

        for(edge in edges) {
            var child = nodes[edge.to]
            // 자식 노드에서 현재 노드로의 edge 제거
            nodes[child.nodeNum].edges.removeIf { childNodeEdge ->
                (childNodeEdge.from == child.nodeNum
                        && childNodeEdge.to == this.nodeNum)
            }

            var (childFirst, _) = child.getMaxChildLength()
            childLen.add(childFirst + edge.weight)
        }

        var first = childLen.poll()
        var second = childLen.poll()
        max = max(max, first+second)
        return Pair(first, second)
    }
}

data class Edge(
    val from: Int,
    val to: Int,
    val weight: Int
)