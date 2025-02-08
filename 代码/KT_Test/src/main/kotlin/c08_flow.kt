import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow = flow { // 这块叫做生产者
        for (i in 1..10) {
            emit(i)
        }
    }

    // 这里叫做消费者 如果不消费就不生产了
//    flow.map{ it*it }  // 每次操作都会产生一个新的流
//        .filter { it % 2 == 0 } // 过滤
//        .take( 2) // 只取前两个
//        .onEach {   // 再打一下
//            println(it)
//        }
//        .collect() //它是一个挂起的函数

    // 流程是消费者通知生产者生产 之后在逐步生产之后消费

    // 流操作也是可以调用transform方法
    // 重新消费不会影响之前的消费
    flow.transform {
        emit(it * 2)
    }.collect {
        println(it)
    }
}
