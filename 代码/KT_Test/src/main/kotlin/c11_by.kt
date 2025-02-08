import sun.text.resources.cldr.my.FormatData_my
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    println(HomeService(HomeDaoImpl(), ADaoImpl()).getAllData())
    println(HomeService(HomeDaoImpl(), ADaoImpl()).getById(123))

    var  testString:String by MyTestClass()

    println(testString)

    A222().apply {
        aTest = "hello"
        print(aTest)
    }

    // 延迟委托
    val testLazy by lazy { // 用于多个线程访问的时候或者平时 只初始化一次 并且只在调用的时候初始化
        println("初始化了testLazy")
        "hello111"
    }

    println(testLazy)

    val a3 = A333()
    a3.aTest
    a3.aTest // 初始化一次 后面都是直接拿值

    // 监听委托
    var testJT by Delegates.observable("hello") { property, oldValue, newValue -> // onChange会在属性被赋值的时候调用
        println("property:${property.name},oldValue:$oldValue,newValue:$newValue")
    }

    testJT = "test"
    testJT = "test1"
}

// 类的委托
interface HomeDao {
    fun getAllData(): List<String>
}

class HomeDaoImpl : HomeDao {
    override fun getAllData(): List<String> = listOf("1", "2", "3")
}

interface ADao {
    fun getById(id: Int): String
}

class ADaoImpl : ADao {
    override fun getById(id: Int): String {
        return "AImpl"
    }
}

// 委托可以委托多个 但是只能在接口继承中使用
class HomeService(homeDaoImpl: HomeDaoImpl, aDaoImpl: ADaoImpl) : HomeDao by homeDaoImpl,
    ADao by aDaoImpl { // 继承于HomeDao 但是实现的方法是HomeDaoImpl的方法 这就叫委托
    fun sout() {
        println("HomeService")
    }
}

class A222{
    var aTest:String by MyTestClass2()
}

class B222{
    var aTest:String by MyTestClass2()
}

// 属性委托
class MyTestClass:ReadWriteProperty<Nothing?,String>{ // 因为main写在顶层函数里面 并不属于哪个类 所一第一个参数写Nothing
    private var myVar = "这里是属性委托"
    override fun getValue(thisRef: Nothing?, property: KProperty<*>): String = myVar

    override fun setValue(thisRef: Nothing?, property: KProperty<*>, value: String) {
        myVar = value
    }
}

class MyTestClass2:ReadWriteProperty<Any,String>{ // 可以将前面的值改成Any 但是不建议这样做
    private var myVar = "这里是属性委托1"
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
       return  myVar
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        myVar = value
    }

}

class A333{
    val aTest:Boolean by lazy {
        println("初始化了testLazy333")
        true
    }
}