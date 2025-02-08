fun main(){
    1 .. 10 // 1-10的区间
    1 until 10 // 1-9的区间
    1 ..< 10 // 1-9的区间 跟上面一样
    10 downTo 1 // 10-1的区间
    1 .. 10 step 2 // 1-10的区间 步长为2


    (1 .. 10 step 2).forEach{
        println(it)
    }


    if(1.2f in 1.0 .. 2.0){
        println("在范围内")
    }


    ('a' .. 'z').forEach{
        println(it)
    }

    for(c in 1 ..10 step 2){
        println(c)
    }
}