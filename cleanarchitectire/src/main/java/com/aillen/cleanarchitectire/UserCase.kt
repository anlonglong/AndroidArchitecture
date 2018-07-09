package com.aillen.cleanarchitectire

import com.aillen.cleanarchitectire.exception.Failure
import com.aillen.cleanarchitectire.functional.Either
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 */

abstract class UserCase<out Type,in Params> where Type : Any{

    /**
     * Params 网络请求的参数类型，当没有参数时候，传下面的None
     * Type 解析后的返回值类型
     */
    fun execute(params: Params,onResult:(Either<Failure, Type>) ->Unit){
        val async = async {
            run(params)
        }

        launch(UI) {
            onResult.invoke(async.await())
        }
    }

    abstract suspend fun run(params: Params): Either<Failure, Type>

    class None
}