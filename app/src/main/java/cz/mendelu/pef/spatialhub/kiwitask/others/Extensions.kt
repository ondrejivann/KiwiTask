package cz.mendelu.pef.spatialhub.kiwitask.others

fun String.replacePunctions(): String {
    val a = this.map { char ->
        when(char) {
            'á' -> 'a'
            'é' -> 'e'
            'í' -> 'i'
            'ó' -> 'o'
            'ů' -> 'u'
            'ú' -> 'u'
            'ý' -> 'y'
            else -> char
        }
    }
    return a.joinToString("")
}