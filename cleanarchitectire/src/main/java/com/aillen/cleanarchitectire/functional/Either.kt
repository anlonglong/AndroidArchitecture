package com.aillen.cleanarchitectire.functional

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 * 对返最终的结果进行一个封装
 * 网络请求只有两种结果成功/失败二选一，所以类名起了Either，
 * Either只有两个实例，Left，Right。
 */
sealed class Either<out L,out R> {
    //失败
    data class Left<out L>(val a:L): Either<L, Nothing>()
    //成功
    data class Right<out R>(val b:R): Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a:L) = Left(a)

    fun <R> right(b:R) = Right(b)

    fun either(fnL:(L) ->Any,fnR:(R) -> Any){
        when(this) {
            is Left ->fnL(a)
            is Right ->fnR(b)
        }
    }

}

fun <T,L,R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> {
    return when(this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }
}
