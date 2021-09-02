import java.io.*

var reader = BufferedReader(InputStreamReader(System.`in`))
var writer = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var M = 0
var K = 0

lateinit var inputs: LongArray

fun main() {
    var words = reader.readLine().split(" ").map { it.toInt() }
    N = words[0]
    M = words[1]
    K = words[2]

    inputs = LongArray(N+1)
    for(i in 1..N) {
        inputs[i] = reader.readLine().toLong()
    }
    var tree = Node.create(1, N)

    for(i in 1..M+K) {
        words = reader.readLine().split(" ").map { it.toInt() }
        var a = words[0]
        var b = words[1]
        var c = words[2]
        if(a == 1) {
            tree.change(b, c.toLong())
        } else {
            var answer = tree.get(b, c)
            writer.write("$answer\n")
        }
    }
    writer.flush()
}

class Node(
    val from :Int,
    val to: Int
) {
    var mul = 1L
    var left: Node? = null
    var right: Node? = null

    companion object {
        fun create(from: Int, to: Int): Node {
            var node = Node(from ,to)
            if(from == to) {
                node.mul = inputs[from]
                return node
            }

            node.left = create(from, (from+to)/2)
            node.right = create((from+to)/2+1, to)
            node.mul = (node.left!!.mul * node.right!!.mul)%1000000007
            return node
        }
    }

    fun get(from: Int, to: Int): Long {
        if(this.from == from && this.to == to) {
            return mul
        }

        var mid = (this.from+this.to)/2
        if(from <= mid && to <= mid) {
            return left!!.get(from, to)
        } else if(from <= mid && to > mid) {
            return (left!!.get(from, mid)*right!!.get(mid+1, to))%1000000007
        } else {
            return right!!.get(from, to)
        }
    }

    fun change(target: Int, value: Long) {
        if(this.from == target && this.to == target) {
            mul = value
            return
        }

        var mid = (this.from + this.to)/2
        if(target <= mid) {
            left!!.change(target, value)
        } else {
            right!!.change(target, value)
        }

        this.mul = (this.left!!.mul*this.right!!.mul)%1000000007
    }
}