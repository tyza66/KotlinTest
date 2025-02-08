import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
//    flow.transform {
//        emit(it * 2)
//    }.collect {
//        println(it)
//    }

    flow.transform { value ->
        val newValue = value * value
        if (newValue % 2 == 0) {
            emit(newValue)
        }
    }.take(2)
        .onEach {
            println(it)
        }
        .collect()


    // 热流 - 共享数据流

    val sharedFolw = MutableSharedFlow<Int>(
        replay = 3, // 重播缓存
        extraBufferCapacity = 1 ,// 缓存区大小 默认会 r+e  然后这个就是相当于存多存一个 取的时候有两个才开始取 这样就不会阻塞了
        onBufferOverflow = BufferOverflow.DROP_OLDEST     // 缓存区溢出的时候的操作 挂起 丢弃最末尾的 丢弃最新的
    )
    sharedFolw.onEach { // 如果没有消费者 默认就不会使用缓存区了 也就避免了一直等待的问题
        delay(1000) // 等了一会 会发现缓存区的作用 只剩下最新的四个了
        println(it)
    }.launchIn(this) // 开到新的携程作用域里面 就不会阻塞代码了

    delay(1000) // 避免携程开启的时候还没准备好

    repeat(10) {
        println("发送次数${it + 1}")
        sharedFolw.emit(it)
    }

    println("结束")

    // 热流 - 状态数据流
    val stateFlow = MutableStateFlow(1)
    stateFlow.emit(2)

    launch {
        stateFlow.collect{
            println(it)
        }
    }
    stateFlow.emit(3)
}
