
//문제
//자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
//
//출력
//첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
//
//예제 입력 1
//10 11 12
//예제 출력 1
//4

var dp = HashMap<Int, Int>()

fun main() {
    val inputs = readLine()!!.split(" ").map { it.toInt() }
    val variable = inputs[0]
    var exponent = inputs[1]
    var mod = inputs[2]

    var result = calc(variable, exponent, mod)
    println(result)
}

fun calc(variable: Int, exponent: Int, mod: Int): Int {
    if(exponent == 1) {
        return variable % mod
    }

    if(dp[exponent] != null)
        return dp[exponent]!!

    return if(exponent % 2 == 0) {
        var tmp = calc(variable, exponent/2, mod).toLong()
        var result = ((tmp*tmp) % mod).toInt()
        dp[exponent] = result
        result
    } else {
        var tmp1 = calc(variable, exponent/2, mod).toLong()
        var tmp2 = calc(variable, exponent/2+1, mod).toLong()
        var result = ((tmp1*tmp2) % mod).toInt()
        dp[exponent] = result
        result
    }
}