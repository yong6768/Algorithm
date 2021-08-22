import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

var max = 0
var R = 0
var C = 0
lateinit var map: Array<CharArray>

fun main() {
    init()
    dfs(1, 0, 0, setPath(0, map[0][0]))
    println(max)
}

fun init() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var line = reader.readLine()
    var words = line.split(" ")
    R = words[0].toInt()
    C = words[1].toInt()
    map = Array<CharArray>(R){ CharArray(C) }
    for(r in 0 until R) {
        line = reader.readLine()
        map[r] = line.toCharArray()
    }
}

inline fun setPath(path: Int, c: Char) = path.or(1 shl (c-'A'))
inline fun isValid(path: Int, c: Char) = path.and(1 shl(c-'A')) == 0

fun dfs(depth: Int, r: Int, c: Int, path: Int) {
    max = max(max, depth)

    //left
    if(c > 0 && isValid(path, map[r][c-1]))
        dfs(depth+1, r, c-1, setPath(path, map[r][c-1]))

    //right
    if(c < C-1 && isValid(path, map[r][c+1]))
        dfs(depth+1, r, c+1, setPath(path, map[r][c+1]))

    //up
    if(r > 0 && isValid(path, map[r-1][c]))
        dfs(depth+1, r-1, c, setPath(path, map[r-1][c]))

    //down
    if(r < R-1 && isValid(path, map[r+1][c]))
        dfs(depth+1, r+1, c, setPath(path, map[r+1][c]))
}