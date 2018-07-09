package com.aillen.cleanarchitectire.module.movies

import android.Manifest
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.aillen.cleanarchitectire.R
import com.aillen.cleanarchitectire.base.BaseFragment
import com.aillen.cleanarchitectire.core.extension.failure
import com.aillen.cleanarchitectire.core.extension.observer
import com.aillen.cleanarchitectire.core.extension.viewModule
import com.aillen.cleanarchitectire.exception.Failure
import com.aillen.cleanarchitectire.navigation.Navigator
import com.aillen.cleanarchitectire.net.BaseHttpOutput
import com.aillen.cleanarchitectire.net.http.*
import kotlinx.android.synthetic.main.activity_movies.*
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import com.aillen.cleanarchitectire.core.extension.appContext
import com.aillen.cleanarchitectire.navigation.Extras
import com.aillen.cleanarchitectire.net.http.anni.AnniHttp
import com.aillen.cleanarchitectire.net.http.anni.AnniInterceptor
import com.aillen.cleanarchitectire.net.http.anni.AnniRequest
import com.aillen.cleanarchitectire.net.http.anni.AnniResponse
import com.aillen.cleanarchitectire.net.http.anno.AnnoHttp
import com.aillen.cleanarchitectire.net.http.anno.AnnoRequestWrapper


/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 */
class MovieFragment : BaseFragment() {


    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var adapter:MovieAdapter

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)


    private lateinit var movieViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        showProgress()
        movieViewModel = viewModule(factory) {
            observer(movies, ::renderMovieList)
            failure(failure, ::handleFailure)
        }
    }

    private fun renderMovieList(list: List<MovieView>?) {
        hideProgress()
        println("list = [${list?.size}]")
        adapter.submitList(list)
    }

    private fun handleFailure(failure: Failure?) {
        hideProgress()
        println("failure = [${failure.toString()}]")
        emptyView.visibility = View.VISIBLE
    }

    override fun onBackPressed() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moveLists.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        moveLists.adapter = adapter
        adapter.onMovieItemClickListener = { movieView: MovieView, i: Int,shareView ->
            println("movieView = [${movieView}], i = [${i}]")
            navigator.showMovieDetail(activity!!,movieView, Extras(shareView))

        }

        movieViewModel.loadMovies()
        //verifyStoragePermissions()

        //test2()
    }

    private fun test2() {
        val hashMapOf =
                hashMapOf("method" to "GetDistricts",
                        "version" to "cyou_1.0.0",
                        "token" to "Y|66150061c090fc07427ba9b9558960",
                        "districtPid" to "1")

        val myRequest = AnniRequest()
        myRequest.setUrl("http://115.159.71.171/api.cyoubike.com/")
        myRequest.setPostBodyMap(hashMapOf)
        AnniHttp.getAnniHttp().setAnniRequest(myRequest).executeHttpRequest(HttpMethod.POST, object : HttpListener<Entity> {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?, data: Entity) {
                println("call = [${call}], response = [${response}], data = [${data}]")
                val myRequest = AnniRequest()
                myRequest.setUrl(data.version.versionPath)
                AnniHttp.getAnniHttp().setAnniRequest(myRequest).executeDownloadRequest("jiklf", object : DownloadListener {
                    override fun onResponse(call: Call?, totalProgress: Long, currentProgress: Long, currentPercent: Double) {
                        println("call = [${call}], totalProgress = [${totalProgress}], currentProgress = [${currentProgress}], currentPercent = [${currentPercent}]")
                    }

                    override fun onFailure(call: Call?, e: IOException?) {

                    }
                })
            }

        })
    }

    private fun test() {
        val hashMapOf =
                hashMapOf("method" to "GetDistricts",
                        "version" to "cyou_1.0.0",
                        "token" to "Y|66150061c090fc07427ba9b9558960",
                        "districtPid" to "1")
        val requestWrapper = AnnoRequestWrapper()
        requestWrapper.setUrl("http://115.159.71.171/api.cyoubike.com/")
        requestWrapper.setPostBodyMap(hashMapOf)
        AnnoHttp.getAnnoHttp().setRequestWrapper(requestWrapper).executeRequest(HttpMethod.POST, object : HttpListener<Entity> {
            override fun onFailure(call: Call, e: IOException?) {

            }

            override fun onResponse(call: Call, response: Response?, data: Entity) {


                val wrapper = AnnoRequestWrapper()
                wrapper.setDownloadUrl(data.version.versionPath)
                AnnoHttp.getAnnoHttp().setRequestWrapper(wrapper).executeDownloadRequest("cyoubike",object : DownloadListener {
                    override fun onResponse(call: Call?, totalProgress: Long, currentProgress: Long, currentPercent: Double) {
                        println("call = [${call}], totalProgress = [${totalProgress}], currentProgress = [${currentProgress}], currentPercent = [${currentPercent}]")
                    }

                    override fun onFailure(call: Call?, e: IOException?) {

                    }
                })
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==REQUEST_EXTERNAL_STORAGE&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            test()
        }
    }

    override fun layoutId() = R.layout.activity_movies

    fun verifyStoragePermissions() {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this.activity!!, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE)
        }else{
            test()
        }
    }
}

data class Entity(val desc:String,
                  val code:Int,
                  val version:Version) : BaseHttpOutput() {
    data class Version(val versionLast:String,
                       val versionPath:String,
                       val versionDesc:String,
                       val forceUpdate:Boolean
                       )
}