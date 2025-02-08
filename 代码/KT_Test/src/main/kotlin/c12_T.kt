fun main() {
    // kt中的函数有一定自动推断功能
    A777(1)

    B777(1).test(1)

    // 泛型的上界默认是Any kt中所有类的根类

    // 泛型可以用在函数上
    test111<Int>(2) // 也会自动根据传参类型进行反推 也可以写上

    1.test111(3)

    "2".tesT222<String, String, String>("GIAO")

    // 因为跟java以一样是运行在jvm里面的 所以泛型一样会在编译的时候被擦除 java中变成Object类型
    // 但是在kt中内联函数是可以保留泛型的
    A888("123").test("123") // 但是其实底层还是擦除了 只是在外面用代码保存了类型 最多只能看看是什么类型啥的类型操作

    val dog: AnimalManager<Dog> = object : AnimalManager<Dog> {
        override fun getAnimal(): Dog {
            return Dog()
        }
    }

    handlerAnimal(dog)

    // kt中的星号通配符指的是未知的泛型类型
    val list33: List<*> = listOf("123", null, 1) // <*>可以理解为Any

    list33.forEach(::println)

    // where关键字 用来添加约束
    MyClass<AAAA,AAAA>()
}

class A777<T : Number>(prm: T) { // 可以限定泛型的类型为指定类的子类
}

class B777<T>(override val prm: T) : C777<T>(), D777<T> {
    override fun test(data: T) { // test函数是B里面的 所以B是什么类型test的参数就是什么类型
        println(data)
    }
}

abstract class C777<T> {
    abstract fun test(data: T)
}

interface D777<T> {
    val prm: T
}

fun <T> test111(data: T) { // 泛型可以在函数中使用
    println(data)
}

fun <T> T.test111(data: T) { // 泛型还可以用在扩展方式上面
    println(data)
}

fun <T, E, F> T.tesT222(data: T) { // 泛型还可以用在扩展方式上面
    println(data)
}

class A888<T>(prm: T) {
    inline fun <reified T> test(data: T) {
        println(T::class.java.simpleName)
        println(data)
    }
}

abstract class Animal
class Dog : Animal() {
    val name = "dog"
}

// 就是说这个类型 原本要求是必须是一样的类型 但是加了out关键字之后就可以是子类类型了 注意这个是在泛型里面的规则
interface AnimalManager<out T> { // 增加out关键字 使得泛型可以协变 就是子类可以转换为父类 这个out只能用于类和接口还有抽象类 不能用于函数
    fun getAnimal(): T // out 泛型作为返回值类型
}

fun handlerAnimal(animalManager: AnimalManager<Animal>) { // 这个地方如果上面没有使用协变 是不能传子类类型的 只能传父类类型 但是这里可以传子类类型了 相当于进行了转换
    val animal: Dog = animalManager.getAnimal() as Dog // 这里返回值类型允许为子类类型
    println(animal.name)
}

interface Read<out T> {
    fun get(): T
    // fun get(data:T):T 如果是out类型的就不能作为参数类型了 就是说这个用了out就是用在返回值上面的 例如上面案例中的get方法
}

interface Write<in T> { // in是逆变 只能作为参数类型 不能作为返回值类型
    fun set(t: T)    // in是用在参数上面的 用在返回值上面是没有意义的
}

interface A9
interface B9
interface C9
class AAAA:A9,B9,C9
class MyClass<T, E> where T : A9, T : B9, T : C9, E : A9 { // 约束条件都放在where的后面 这里要求T必须把三个接口都实现了 并且比较灵活

}