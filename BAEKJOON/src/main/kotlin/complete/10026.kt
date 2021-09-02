import java.io.BufferedReader
import java.io.InputStreamReader

var reader = BufferedReader(InputStreamReader(System.`in`))

var N = 0
lateinit var inputArr: Array<CharArray>


fun main() {
    N = reader.readLine().toInt()
    inputArr = Array(N){CharArray(0)}

    for(i in 0 until N) {
        inputArr[i] = reader.readLine().toCharArray()
    }

    var checkArray = Array<BooleanArray>(N){BooleanArray(N)}

    var count = 0
    for(i in 0 until N) {
        for(j in 0 until N) {
            if(checkArray[i][j])
                continue
            dfs(checkArray, i, j, inputArr[i][j])
            count++
        }
    }

    print("$count ")

    checkArray = Array<BooleanArray>(N){BooleanArray(N)}
    count = 0
    for(i in 0 until N) {
        for(j in 0 until N) {
            if(checkArray[i][j])
                continue
            dfsRG(checkArray, i, j, inputArr[i][j]=='B')
            count++
        }
    }
    println(count)
}

fun dfs(checkArray: Array<BooleanArray>, r: Int, c: Int, color: Char) {
    if(r < 0 || r >= N)
        return

    if(c < 0 || c >= N)
        return

    if(checkArray[r][c])
        return

    if(inputArr[r][c] != color)
        return

    checkArray[r][c] = true
    dfs(checkArray, r-1, c, color)
    dfs(checkArray, r+1, c, color)
    dfs(checkArray, r, c-1, color)
    dfs(checkArray, r, c+1, color)
}

fun dfsRG(checkArray: Array<BooleanArray>, r: Int, c: Int, isBlue: Boolean) {
    if(r < 0 || r >= N)
        return

    if(c < 0 || c >= N)
        return

    if(checkArray[r][c])
        return

    if(isBlue && inputArr[r][c] != 'B')
        return

    if(!isBlue && inputArr[r][c] == 'B')
        return

    checkArray[r][c] = true
    dfsRG(checkArray, r-1, c, isBlue)
    dfsRG(checkArray, r+1, c, isBlue)
    dfsRG(checkArray, r, c-1, isBlue)
    dfsRG(checkArray, r, c+1, isBlue)
}