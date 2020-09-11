package utils

import java.util.*
import java.util.stream.Collectors

fun main(){


}

fun kmp(a:String,b:String) : Boolean{

    val chara: CharArray = a.toCharArray()
    val charb: CharArray = b.toCharArray()

    val table = KMPAlgorithm.calStringMatch(b)

    var places = 0
    var i = 0
    first@ while (i < chara.size) {
        for (j in i..charb.size) {
            if (chara[i] == charb[j]) {
                places++
                if (places == charb.size) {
                    return true
                }
                i++
                continue@first
            } else {
                i += places - table[j]
                places = 0
            }
        }
        i++
    }
    return false
}

fun calStringMatch(v : String) : IntArray? {
    val result = IntArray(v.length)

    for (i in 0..v.length) {
        val temp: String = v.substring(0, i + 1)
        val prefixs: MutableList<String> = ArrayList()
        val suffixs: MutableList<String> = ArrayList()
        for (j in 1..i) {
            val prefix: String = temp.substring(0, j)
            prefixs.add(prefix)
            val suffix: String = temp.substring(temp.length - j, temp.length)
            suffixs.add(suffix)
        }
        val intersection = prefixs.stream().filter { item: String -> suffixs.contains(item) }.collect(Collectors.toList())
        if (!intersection.isEmpty()) {
            result[i] = intersection.stream().findFirst().get().length
        }
    }

    return null;
}
