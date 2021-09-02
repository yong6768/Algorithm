import java.io.*

var reader = BufferedReader(InputStreamReader(System.`in`))
var writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    var T = reader.readLine().toInt()
    for(t in 0 until T) {
        var p = reader.readLine()
        var n = reader.readLine().toInt()
        var inputs = reader.readLine()
        var arr = when (n) {
            0 -> listOf<Int>()
            else -> inputs.substring(1, inputs.length-1).split(",").map { it.toInt() }
        }

        var direction = true
        var start = 0
        var end = arr.size
        var hasError = false

        for(c in p) {
            if(c == 'R') {
                direction = !direction
            } else {
                if(direction) {
                    start++
                } else {
                    end--
                }
            }

            if(start > end) {
                hasError = true
                break
            }
        }

        if(hasError) {
            writer.write("error\n")
        } else {
            var subList = when(direction) {
                true -> arr.subList(start, end)
                else -> arr.subList(start, end).reversed()
            }

            writer.write("[${subList.joinToString(",")}]\n")
        }
    }

    writer.flush()
}