package com.aillen.cleanarchitectire.exception

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 */
abstract sealed class Failure{
   abstract  fun failureDesc()
    class NetworkConnection: Failure() {
        override fun failureDesc() {
            println("NetworkConnection")
        }
    }

    class ServerError: Failure() {
        override fun failureDesc() {
            println("ServerError")
        }
    }

    abstract class FeatureFailure: Failure()
}