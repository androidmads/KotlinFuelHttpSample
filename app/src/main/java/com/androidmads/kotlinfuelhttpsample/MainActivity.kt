package com.androidmads.kotlinfuelhttpsample

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var tvGetResponse: TextView? = null
    var tvPostResponse: TextView? = null
    var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewsAndWidgets()
        FuelManager.instance.basePath = "http://demosmushtaq.16mb.com";
    }

    private fun initViewsAndWidgets() {
        tvGetResponse = findViewById(R.id.tvGetResponse)
        tvPostResponse = findViewById(R.id.tvPostResponse)
        progress = ProgressDialog(this)
        progress!!.setTitle("Kotlin Fuel Http Sample")
        progress!!.setMessage("Loading...")
    }

    fun httpGetJson(view: View) {
        try {
            progress!!.show()
            Fuel.get("api/get_sample.php").responseJson { request, response, result ->
                tvGetResponse!!.text = result.get().content
            }
        } catch (e: Exception) {
            tvGetResponse!!.text = e.message
        } finally {
            progress!!.dismiss()
        }
    }

    fun httpPostJson(view: View) {
        try {
            progress!!.show()
            Fuel.post("api/post_sample.php", listOf("version_index" to "1")).responseJson { request, response, result ->
                tvPostResponse!!.text = result.get().content
            }
        } catch (e: Exception) {
            tvPostResponse!!.text = e.message
        } finally {
            progress!!.dismiss()
        }
    }
}
