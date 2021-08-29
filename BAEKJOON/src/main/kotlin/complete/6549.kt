import java.io.*

var reader = BufferedReader(InputStreamReader(System.`in`))
var writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    while(true) {
        var inputs = reader.readLine()
        if(inputs == "0")
            break

        var list = inputs.split(" ").map { it.toLong() }
        var node = Node.set(list, 1, list.size-1)!!
        var answer = node.cal(list, 1, list.size-1)

        writer.write("$answer\n")
    }
    writer.flush()
}

class Node(
    var from: Int,
    var to: Int
) {
    var left: Node? = null
    var right: Node? = null
    var index = -1

    companion object {
        fun set(inputs: List<Long>, from: Int, to: Int): Node? {
            var node = Node(from, to)
            if(from == to) {
                node.index = from
                return node
            }

            var left = Node.set(inputs, from, (from+to)/2)
            var right = Node.set(inputs, (from+to)/2+1, to)

            node.left = left
            node.right = right
            node.index = when {
                inputs[left!!.index] <= inputs[right!!.index] -> left!!.index
                else -> right!!.index
            }
            return node
        }
    }

    fun get(inputs: List<Long>, from: Int, to: Int): Int {
        var mid = (this.from+this.to)/2
        return if(this.from == from && this.to == to) {
            index
        } else if(from >= mid+1) {
            right!!.get(inputs, from, to)
        } else if(to <= mid) {
            left!!.get(inputs, from, to)
        } else {
            var leftIndex = left!!.get(inputs, from, mid)
            var rightIndex = right!!.get(inputs,mid+1, to)

            return when{
                inputs[leftIndex] <= inputs[rightIndex] -> leftIndex
                else -> rightIndex
            }
        }
    }

    fun cal(inputs: List<Long>, from: Int, to: Int): Long {
        if(from>to)
            return 0L

        if(from == to)
            return inputs[from]

        val pivot = get(inputs, from, to)
        if(pivot in from..to) {
            var tmp1 = (to-from+1)*inputs[pivot]
            var tmp2 = cal(inputs, from, pivot-1)
            var tmp3 = cal(inputs, pivot+1, to)
            return listOf(tmp1, tmp2, tmp3).maxOrNull()!!
        }

        return 0
    }
}