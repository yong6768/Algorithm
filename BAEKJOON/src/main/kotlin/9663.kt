var count = 0
var N = 0

fun main() {
    N = readLine()!!.toInt()
    var board = Array<IntArray>(N+1){IntArray(N+1){0} }

    dfs(1, board)

    println(count)
}

fun dfs(depth: Int, board: Array<IntArray>) {
    if(isValid(depth, board)) {
        count++
        return
    }

    for(i in 1..N) {
        if(board[depth][i] == 0) {
            setImpossibleSquare(depth, i, board)
            dfs(depth+1, board)
            revertImpossibleSquare(depth, i, board)
        }
    }
}

fun isValid(depth: Int, board: Array<IntArray>): Boolean {
    if(depth != N)
        return false

    for(i in 1..N) {
        if(board[depth][i] == 0)
            return true
    }
    return false
}

inline fun setImpossibleSquare(row: Int, column: Int, board: Array<IntArray>) {
    setBoardSquare(row, column, false, board)
}

inline fun revertImpossibleSquare(row: Int, column: Int, board: Array<IntArray>) {
    setBoardSquare(row, column, true, board)
}

fun setBoardSquare(row: Int, column: Int, value: Boolean, board: Array<IntArray>) {

    for(i in 1..N) {
        var r = row
        var c = column
        setSquare(r, c, value, board)

        r = row
        c = column-i
        setSquare(r, c, value, board)

        r = row+i
        c = column-i
        setSquare(r, c, value, board)

        r = row+i
        c = column
        setSquare(r, c, value, board)

        r = row+i
        c = column+i
        setSquare(r, c, value, board)

        r = row
        c = column+i
        setSquare(r, c, value, board)
    }
}

inline fun setSquare(row: Int, column: Int, value: Boolean, board: Array<IntArray>) {
    if(row in 1..N && column in 1..N) {
        if(value) {
            board[row][column]++
        } else {
            board[row][column]--
        }
    }
}