package com.aillen.cleanarchitectire.test

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
class PresenterProviders {

   companion object {
       fun <T : BasePresenter> of(factory: Factory,pClass:Class<T>):T{
           return factory.creator(pClass)
       }
   }
}