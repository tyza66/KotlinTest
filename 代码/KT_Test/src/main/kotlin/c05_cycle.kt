import kotlin.math.atan2

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // 有得迭代器的集合可以用listIterator()方法
    if (list.listIterator().hasNext()) {
        println("hasNext")
        println(list.listIterator().next())
    }
    // 每次初始化的时候都是从头开始的
    if (list.listIterator().hasNext()) {
        println("hasNext")
        println(list.listIterator().next())
    }

    for ((index, i) in list.withIndex()) { // 如果想要下标就加上withIndex()方法 拿值的时候还可以用解构
        // 使用for循环进行迭代
        println(i)
    }

    // 迭代器的集合可以用forEach方法
    list.forEach { // 扩展函数 里面也是调用的for循环迭代
            value ->
        println(value)  // 默认是it 也可以自定义value
    }

    list.forEachIndexed { index, i ->
        println("$index $i")
    }

    // wilie循环跟java一样
    var a = 1
    while (a < 10) {
        println(a)
        a++
    }
    // 也有do_while循环

    // 也有break和continue 可以用 标识符 a@啥的进行标识层级
    a@ for (i in 1..10) {
        for (j in 1..10) {
            if (j == 5) {
                break@a  // continue也可以用标识符
            }
            println(j)
        }
    }

    // 如果有的函数层级嵌套循环太多 可以用return@标识符来直接返回到标识符的地方

    run b@{
        (1..10).forEach {
            if (it == 2) {
                return@b
            }
            println(it)
        }
    }

    // 默认会把函数名当做标识符
}