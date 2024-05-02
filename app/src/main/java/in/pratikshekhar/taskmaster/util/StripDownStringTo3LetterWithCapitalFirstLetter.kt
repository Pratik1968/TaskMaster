package `in`.pratikshekhar.taskmaster.util

fun StripDownStringTo3LetterWithCapitalFirstLetter(text:String):String{

    return text.toString().lowercase().replaceFirstChar(transform ={char->char.uppercase()}).substring(
        IntRange(0,2))
}