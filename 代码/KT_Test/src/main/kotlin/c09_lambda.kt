fun main(){
    // kt中lambda表达式是有类型的
    val fun1:(Int,Int) ->Int = {x,_ -> // 返回值不写也不会报错
        x+1  // 默认最后一行作为返回值
    }// 如果函数参数只有一个 可以省略 it就是默认的参数名

    println(fun1(3,0))

    val fun2 = {1} // 自动识别

    // 当然我们可以把函数作为参数传给函数
    1.test {
        println(this)
    }
}

fun <T>T.test(callback:T.()->Unit){
    this.callback()
}

// lambda表达式可以理解为匿名函数的简写
fun test2():() -> Int{
    var count = 0
    return fun():Int{ // 直接返回匿名函数也不会报错
        return count++
    }
}