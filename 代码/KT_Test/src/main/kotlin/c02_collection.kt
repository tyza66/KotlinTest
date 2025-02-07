fun main(){
    val array = arrayOf<Int>(1,2,3)
    println(array.get(1))

    val array1:IntArray = intArrayOf(1,2,3)
    println(array1[1])

    val array2:Array<Int> = emptyArray()
    println(array2.size)

    val list = listOf(1,2,3) // 普通创建的是不可变的List
    println(list.get(1))    // 不可变的可以减少性能的消耗

    // 要想创建可变的List
    val mutableList = mutableListOf(1,2,3)
    mutableList.add(4)
    println(mutableList[1])

    // set集合也是有可变和不可变的 都一样也是不可重复数组
    val set = setOf(1,2,3)
    println(set.contains(1))

    val mutableSet = mutableSetOf(1,2,3)
    mutableSet.add(4)

    // map也是 键值对用to连接
    val map = mapOf("a" to 1, "b" to 2)
    println(map.get("a"))
    println(map["b"]) // 获取也可以用运算符重载的方式

    val mutableMap = mutableMapOf("a" to 1, "b" to 2)
    mutableMap.put("c", 3)
    println(mutableMap.keys)
    println(mutableMap.keys.size)
    mutableMap.keys.forEach {
        println(it)
    }
    // 虽然显示的数组键集合是可变的 但是实际上是不可修改的 修改会报错


    // 数组是没有可变和不可变的区别的 只有不可变的
    // 数组语义上就是长度不可变 但是内容是可以变的

    // KT中特有的二元组和三元组
    Pair(1,2) // 之后可以用first和second获取

    Triple(1,2,3) // 之后可以用first second third获取

    // kt中Any是基类
    // Nothing是最底层的类 表示没有任何东西
    var b:Int? = null //null就是一个Nothing类型 通常不可以直接将null赋值给Int类型 可以加一个?允许
    println(b?.plus(2)) // 如果在定义的时候加了? 说明这个变量肯恶搞为空了 所以调用的时候在后面加一个? 这样这个就可以为空了 意思是如果为空就不调用后面的了
    println(b.toString()) // toString是任何类型都存在的
    println(b!!.plus(2)) // 如果想强制不做空判断的话就加两个!
}

class A{
    var b:Int = 1;
    var c:Int? = null;
    lateinit var d:String; // 如果想过一会再初始化就使用lateinit修饰 这个修饰符只能用在复杂的数据类型上
    // 对于laterinit类型的成员 如果没有初始化 那调用它的时候会报错没有初始化 所以不建议这样用
    // 类中的成员变量必须有初值
    fun sout(){
        println(c?.toLong()) // 成员变量可能为空的时候打印也要检查 或者使用双感叹号
        // 因为想出现空指针的情况比较麻烦 所以一定程度上能避免空指针的问题
    }
}