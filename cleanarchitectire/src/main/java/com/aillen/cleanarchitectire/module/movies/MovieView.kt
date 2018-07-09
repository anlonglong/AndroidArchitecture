package com.aillen.cleanarchitectire.module.movies

import android.os.Parcel

/**
 * Created by anlonglong on 2018/6/26.
 * Email： 940752944@qq.com
 */
data class MovieView(val id:Int, val poster:String):KotlinParcelable {


    companion object {
        @JvmField val CREATOR = parcelableCreator(::MovieView)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(poster)
        }
    }

}