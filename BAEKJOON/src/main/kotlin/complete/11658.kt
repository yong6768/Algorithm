import java.io.*

var reader = BufferedReader(InputStreamReader(System.`in`))
var writer = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var M = 0

fun main() {
    var words = reader.readLine().split(" ").map { it.toInt() }
    N = words[0]
    M = words[1]

    var arr = Array<IntArray>(N+1){IntArray(N+1)}
    var tree = Array<IntArray>(N+1){ IntArray(N+1) }

    for(i in 1..N) {
        words = reader.readLine().split(" ").map { it.toInt() }
        for(j in 1..N) {
            arr[i][j] = words[j-1]
            update(tree, i, j, arr[i][j])
        }
    }

    for(i in 1..M) {
        words = reader.readLine().split(" ").map { it.toInt() }
        if(words[0] == 0) {
            var r = words[1]
            var c = words[2]
            var value = words[3]
            update(tree, r, c, value-arr[r][c])
            arr[r][c] = value
        } else {
            var r1 = words[1]
            var c1 = words[2]
            var r2 = words[3]
            var c2 = words[4]
            var answer = sum(tree, r2, c2) - sum(tree, r1-1, c2) - sum(tree, r2, c1-1) + sum(tree, r1-1, c1-1)
            writer.write("$answer\n")
        }
    }

    writer.flush()
}

fun update(tree: Array<IntArray>, r: Int, c: Int, diff: Int) {
    var indexR = r
    while(indexR <= N) {
        var indexC = c
        while(indexC <= N) {
            tree[indexR][indexC] += diff
            indexC += indexC.and(-indexC)
        }
        indexR += indexR.and(-indexR)
    }
}

fun sum(tree: Array<IntArray>, r: Int, c: Int): Int {
    var sum = 0
    var indexR = r
    while(indexR > 0) {
        var indexC = c
        while(indexC > 0) {
            sum += tree[indexR][indexC]
            indexC -= indexC.and(-indexC)
        }
        indexR -= indexR.and(-indexR)
    }
    return sum
}