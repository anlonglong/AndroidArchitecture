package com.aillen.cleanarchitectire.core.extension

/**
 * Created by anlonglong on 2018/6/29.
 * Email： 940752944@qq.com
 */
/**
 * 类的静态方法的扩展的语法,
 * 一个类能否进行静态的扩展，要看这个类的内部是否有伴身对象，没有的是不能进行
 * 静态扩展的。
 *
 * fun 类名.Companion.扩展函数名() {
 *
 * }
 *
 * **/
fun String.Companion.empty() = ""