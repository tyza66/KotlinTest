import kotlin.reflect.KFunction

fun main() {
    // 可以当成反射来理解
    val a = ::test // KFunction1类型
    val b: KFunction<Unit> = ::test // 因为都是根据这个类型生成的类似 所以这样写也没有问题

    // 如果我们想用test1作为参数传递给test2
    test2(::test) // 这样也可以 因为应用对象 表示的是函数本身的定义

    val kf = A111::fn
    val a1 = A111(1)   // 在类里面的应用调用需要传递上下文
    kf(a1) // 这样也可以调用

    test3(kf)

    // 类中的函数我们一般不像上面那么用 实际上都是用来看信息的

    println(kf.isOpen)

    val inA = A111::a1
    println(inA.isConst)

    val Aself =  ::A111 // 拿到的其实是构造函数
    println(Aself(1).a1)
    println(Aself.isAbstract)

    println(inA.get(A111(2))) // 类内部的东西都要传上下文

    val kClass = A111::class // 这个KClass类具备一些kt的特征
    println(kClass.simpleName)

    val java = A111::class.java // 这个是为了兼容java生态的
    println(java.simpleName)
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