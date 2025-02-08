fun main() {
    val b = 1
    val c = if (b == 1) {
        1
    }  // if是有返回值的
    else if (b == 2) {
        5
    } else {
        2
    } // 但是如果想要有返回值就必须要有else 总之一定要有以何种情况存在
    // 如果什么返回值都没写 默认是Unit类型 相当于void

    println(c)

    // kt里面没有switch只有when
    when (b) {
        1,3 -> {println("1")} // 这个是多个条件
        2 -> println("2")
        else -> println("else") // 这个相当于default

        // 最后不用写break 自动break
    }

    val d:Any = 1;
    val e = when (d) {
        is Int -> {println("1")} // 可以进行类型判断
        is String -> println("2")
        in 1 .. 10 -> println("3") // 还可以判断范围是不是1-10的区间里面
        else -> println("else")
    }
    // when也是有返回值的 也是每个代码块的最后一行
    // 如果出现返回值是多类型就返回的是Any类型

    // kt中没有三元运算符但是可以用if 写在一行里面
}