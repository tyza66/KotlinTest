fun main() {
    println(sum(1, 2))
    println(sum(3, 4))
    println(sum(5, 6))
    println(sum(7, 8))
    println(sum(9, 10))
    println(sum2(9, 10))
    println(1.sum3(2))
    println(1 sum3 2) // 还可以这样简写
    sum4(a = 1, b = 2, c = {
        println(it)
    })  // 通过标识能指定参数

    println(sum4(1,2){
        // 当最后一个参数是lambda表达式的时候可以把{}放到括号外面
        // 当然只有一个lambda表达式的时候也可以直接在方法后面写{}省略()
    })

    // 泛型一般在fun后 方法名前

    val arr = arrayOf("1","2","3","4","5")
    sum5(list = arr) // 这个*是展开操作符 但是当指定的时候就不用了
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b // 这个是简写形式

infix fun Int.sum3(c: Int): Int {  // 带数据接受着的lambda表达式
    return this + c
}

fun sum4(a: Int, b: Int = 2, c: (Int) -> Unit) {  // 参数可以设置默认值 // 也可以用?设置为null
    c(a + b)
}

fun sum5(test:String="",vararg list:String){ // 多参
    list.forEach {
        println(it)
    }
}