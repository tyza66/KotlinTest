fun main() {
    val a = A1()
    a.print()
    a.a = 3
    a.print()
    val b = A2(2, 3)
    A2()
    val c = A3(1, 2)
    // A3() 没有主构造函数就不能这样写

    val d = CChild()
    d.sout()
    d.ssout()
    println(d.count1)
    println(d.count2)

    // 使用抽象类
    var cx:DParent = DChild() // 支持多态

    // 使用嵌套类
    val ia = iA.iB() // 嵌套类不需要外部类的实例 因为他是静态的

    // 使用内部类
    val ic = iA().iC()
    ic.sout()

    var person = DATA("小明",99)
    var older_persion = person.copy(age = 100) // 这个是复制一个新的对象
    println(older_persion) // 打印的时候调用的是默认的toString

    F.Acc.sout() // 伴生类的调用
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

    open val count1 = 1
    open var count2 = 2
    open fun sout() {
        println("CParent")
    }

    open fun ssout() {
        println("CParent ss")
    }
}

class CChild : CParent() {
    override val count1:Int // 其实属性重写重写的是get和set方法 我们还可以给它变成可变的来覆盖 翻译成java代码就是新建了变量
        get() = 9

    override var count2:Int = 0 // 可变变量的重写
        get() = super.count2
        set(value) {
            field = value
        }
    override fun sout() {  // override是必须的 明文写出来
        super.sout() // super能调用父类里面的方法
        println("CChild")
    }
}

// kt中的几种类 抽象类 嵌套类 内部类

abstract class DParent { // 抽象类不能被实例化 只能被继承
    abstract fun sout() // 抽象方法不能有方法体
    fun s2(){ // 抽象类里面可以有普通方法
        println("s2")
    }
}

class DChild : DParent() {
    override fun sout() {
        println("DChild")
    }
}

// 在java里面这种属于内部类 但是在kt里面这种属于嵌套类
class iA{
    val a = 1
    class iB{
        fun sout(){
            println("这是嵌套类,拿不到父级类的参数")
        }
    }
    inner class iC{ // 这种是内部类
        fun sout(){
            println("这是内部类,可以拿到父级类的参数$a")
        }

        fun sout2(){
                    println(this@iA.a) // 这样也可以拿到父级类的参数 且可以做区分
         }
    }
}

// kt中的接口也是用interface关键字定义
interface EInterface{
    val a:Int
    fun sout() // 默认不用加abstract
    fun c1(){ // 也可以有函数体

    }
}

abstract class EAbstract{
    abstract fun sout1()
    fun c2(){}
}

class EChild : EAbstract(),EInterface{
    override val a: Int = 1 // 属性也是需要实现的 但是如果想从不可变的变成可变的要实现get和set方法
    override fun sout() {
        println("EChild")
    }

    override fun sout1() {
        println("EChild sout1")
    }

    override fun c1() { // 有函数体的也可以被继承 java里面生成对应的静态类实现的
        super.c1()
    }
}

// 多个接口不能有相同函数.有的话需要用泛型制定 super<A>.eat() 这样调用


// 数据类 伴生类 密封类
// 数据类是用data修饰的
// 数据类会自动生成toString equals hashCode copy方法
data class DATA( // 数据类的主构造函数至少要有一个参数
    val name:String,
    val age:Int
){

}

class F{
    // 伴生类可以自己命名 默认Companion 并且支持接口实现和继承关系
    companion object Acc{ // 伴生类 用于定义静态成员 直接的实现原理其实是新建了一个别的类的静态对象进行实现
        // 我们也可以用@JvmStatic注解来实现静态方法
        @JvmStatic // 这样会另外生成静态方法在F中 但是java里也是调用外面类的方法
        fun sout(){
            println("伴生类")
        }
        // 换句话说加了注解的可以直接点调用 没加的要用F.Companion调用
        // 成员也是这样的机制 调用的时候还是通过伴生类机制调用的
        var a = 1;

        const val b = 2; // 这个是常量 也会静态化 只能在伴生类和外面使用 只用用于基本数据类型 并且const只能加在可变的变量上

        // 如果想要那种可以点调用的公开静态成员
        @JvmField
        var c = 3;
    }
}

