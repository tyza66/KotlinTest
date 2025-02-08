import kotlin.reflect.KFunction

fun main() {
    // 可以当成反射来理解
    val a = ::test // KFunction1类型
    val b: KFunction<Unit> = ::test // 因为都是根据这个类型生成的类似 所以这样写也没有问题

    // 如果我们想用test1作为参数传递给test2
    test2(::test) // 这样也可以 因为应用对象 表示的是函数本身的定义

    val kf = A111::fn
    val a1 = A111(1)
    kf(a1) // 这样也可以调用

    test3(kf)
}

fun test() {
    println("打印了一下")
}

fun test2(callback: () -> Unit) {
    callback()
}

// 类中的函数也可以用::来引用
class A111(val c:Int){
    val a1 =1
    fun fn(){
        println(c)
    }
}

fun test3(n:(A111)->Unit){
    n(A111(2))
}