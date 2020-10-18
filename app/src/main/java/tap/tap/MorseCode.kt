package tap.tap

 class MorseCode {
    internal var ALPHA = arrayOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", ",", "?", ".", "'")
    internal var MORSE = arrayOf<String>(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "-.-.--", "--..--", "..--..", ".-.-.-", ".----.")
    var ALPHA_TO_MORSE:HashMap<String, String> = HashMap()
    var MORSE_TO_ALPHA:HashMap<String, String> = HashMap()
    init{
        var i = 0
        while (i < ALPHA.size && i < MORSE.size)
        {
            ALPHA_TO_MORSE.put(ALPHA[i], MORSE[i])
            MORSE_TO_ALPHA.put(MORSE[i], ALPHA[i])
            i++
        }
    }
   public fun morseToAlpha(morseCode:String):String {
        val builder = StringBuilder()
        val words = morseCode.trim({ it <= ' ' }).split((" ").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (word in words)
        {
            for (letter in word.split((" ").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray())
            {
                val alpha = MORSE_TO_ALPHA.get(letter)
                builder.append(alpha)
            }
            builder.append(" ")
        }
       if (builder.toString()==null){
           return ""
       }else{
           return builder.toString().toUpperCase()
       }
    }
   public fun alphaToMorse(englishCode:String):String {
        val builder = StringBuilder()
        val words = englishCode.trim({ it <= ' ' }).split((" ").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (word in words)
        {
            for (i in 0 until word.length)
            {
                val morse = ALPHA_TO_MORSE.get(word.substring(i, i + 1).toLowerCase())
                builder.append(morse).append(" ")
            }
            builder.append(" ")
        }
       if (builder.toString()==null){
           return ""
       }else{
           return builder.toString()
       }

    }
}