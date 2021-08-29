import java.io.*
import java.util.*

var reader = BufferedReader(InputStreamReader(System.`in`))
var writer = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var M = 0

var wordKnownList = Array<LinkedList<Word>>(26){ LinkedList() }
var wordUnknownList = Array<LinkedList<Word>>(26){ LinkedList() }

const val FORGET = 1
const val REMEMBER = 2

fun main() {
    var inputs = reader.readLine().split(" ")
    N = inputs[0].toInt()
    M = inputs[1].toInt()

    var count = 0

    for(i in 0 until N) {
        var bitmask = toBitmask(reader.readLine())
        var word = Word(bitmask, bitmask)
        for(c in 'a'..'z') {
            if(bitmask.containsCharacter(c)) {
                wordKnownList[c-'a'].add(word)
            }
        }
        count++
    }

    for(i in 0 until M) {
        inputs = reader.readLine().split(" ")
        var op = inputs[0].toInt()
        var c = inputs[1][0]

        if(op == FORGET) {
            val wordKnownListIterator = wordKnownList[alphaToInt(c)].iterator()
            while(wordKnownListIterator.hasNext()) {
                val word = wordKnownListIterator.next()
                if(word.current == word.origin) {
                    count--
                }
                word.current = word.current and bitPosition(c).inv()
                wordKnownListIterator.remove()
                wordUnknownList[alphaToInt(c)].add(word)
            }

        } else {
            val wordUnknownListIterator = wordUnknownList[alphaToInt(c)].iterator()
            while(wordUnknownListIterator.hasNext()) {
                val word = wordUnknownListIterator.next()
                word.current = word.current or bitPosition(c)
                wordUnknownListIterator.remove()
                wordKnownList[alphaToInt(c)].add(word)

                if(word.current == word.origin) {
                    count++
                }
            }
        }
        writer.write("$count\n")
    }

    writer.flush()
}

fun Int.containsCharacter(c: Char) = this and bitPosition(c) > 0

inline fun toBitmask(s: String): Int {
    var ret = 0
    for(i in s.indices) {
        ret = ret or bitPosition(s[i])
    }
    return ret
}

inline fun bitPosition(c: Char): Int {
    return 1 shl c-'a'
}

inline fun alphaToInt(c: Char) = c-'a'

class Word(
    val origin: Int,
    var current: Int
)