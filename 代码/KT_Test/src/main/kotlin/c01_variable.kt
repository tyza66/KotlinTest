fun main() {
    var b = 1; // 会自动推断类型
    val a = 2; // 定义产量

    var c = b.toLong(); // 还有比较灵活的to函数

    var d = "c" + c; // 能转换会自动转换之后拼接

    var e = 1L; // 定义长整形
    var f = 0.1; // 定义浮点型 默认是Double 像定义Float需要加f

    var string = "Hello, World!";
    println(string);

    println(string[3]);

    println(string.plus(3)) // 这个是拼接相当于string + 3

    // 这种叫做 运算符重载
    // 所以在kt中字符串比较可以直接用==
    println(string == "Hello, World!");

    // 字符串模板
    var name = "Kotlin";
    println("Hello, ${name}!");

    // 多行字符串
    var text = """
        |多行字符串
        | 多行字符串
        |多行字符串
    """.trimMargin();
    println(text);

}