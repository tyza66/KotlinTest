fun main() {
    val a = A1()
    a.print()
    a.a = 3
    a.print()
    val b = A2(2, 3)
    A2()
    val c = A3(1, 2)
    // A3() 没有主构造函数就不能这样写
}

class A1 constructor(b: Int = 1) { // 我们也可以用var在构造函数里面声明变量 // constructor是可以省略的 主构造函数
    val d: Int

    init { // 初始化代码块
        println("init")
        d = b + 1 // 在这初始化也可以
    }

    // 如果声明了稍后初始化 就不可以直接赋值了
    var a = 3  // 类里面的每个成员是自带get和set方法的
        get() = field - 2 // 也可以自定义 也field是默认的 可以自己改
        set(value) { // 注意产量是没有set的
            field = value + 1
        }

    fun print() {
        println(a) // 获取时自动使用的是get方法
    }

    constructor(b: Int, c: Int) : this(1) { // 次构造函数 需要填写传递给主构造函数
        println("constructor")
    }

    constructor(b: Int, c: Int, d: Int) : this(b, c) { // 代理一个代理主构造函数的次构造函数
        println("constructor")
    }
}

class A2(b: Int = 1) { // 不写constructor也是可以的

    constructor(a: Int, b: Int) : this()
}

class A3 { // 不写括号就是没有主构造函数

    constructor(a: Int, b: Int) // 第一个就会被识别为主构造函数
}

class A4 { // 不写括号就是没有主构造函数
    // 新增了一个访问修饰符internal 只能在一个模块内部访问
    internal var a: Int = 1

    constructor(a: Int, b: Int) // 第一个就会被识别为主构造函数
}

// kt中类的默认修饰符不是java里面的default 而是public final 所以kt中的类默认是不可以被继承的
// 如果要继承就要加上open修饰符
// kt中注解也是一个class类型 就是前面用annotation修饰的 注解里面也可以有属性默认值

open class BParent(val name: String, val age: Int) {
    constructor(name: String) : this(name, 1) // 代理主构造函数
}

class BChild(name:String,age: Int) : BParent(name) { // 一定要调用父类的构造函数
    // constructor(name:String) : super(name) // 如果父类没有无参构造函数就要这样写
    // 可以选择性的选择父类的主次构造函数 写在类名那里也一样
}

// kt中函数前面也有默认的修饰符 public final
// 如果想要重写就要加上open修饰符

open class CParent {
    open fun sout() {
        println("CParent")
    }
}

class CChild : CParent() {
    override fun sout() {  // override是必须的 明文写出来
        println("CChild")
    }
}