package com.nugrahaa.mynewsapp.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.mynewsapp.R
import com.nugrahaa.mynewsapp.adapter.ListNewsAdapter
import com.nugrahaa.mynewsapp.model.Article
import com.nugrahaa.mynewsapp.model.ResponseUser
import com.nugrahaa.mynewsapp.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var listArticle = arrayListOf<Article>()
    private lateinit var rvNews: RecyclerView
    private lateinit var listNewsAdapter: ListNewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        rvNews = rv_news
        rvNews.setHasFixedSize(true)

        addItemApi("Indonesia")
    }

    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(this)
        listNewsAdapter = ListNewsAdapter(listArticle)
        rvNews.adapter = listNewsAdapter
    }

    private fun addItemApi(title: String) {
        if (isConnect()) {
            val client = ApiConfig.getApiService().getListNewsByName(title, "230fd988de08429c81a3e27c4934058b")
            client.enqueue(object : Callback<ResponseUser> {

                override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                    try {
                        val dataArray = response.body()?.articles as ArrayList<Article>
                        for (data in dataArray) {
                            listArticle.add(data)
                        }
                        progress.visibility = View.INVISIBLE
                        showRecyclerList()
                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                    Log.d("Main Activity","GAGAL KARENA " + t.message)
                }

            })
        } else {
            Toast.makeText(this, "Anda tidak terhubung ke Internet", Toast.LENGTH_LONG).show()
            internet_dc.visibility = View.VISIBLE
            progress.visibility = View.INVISIBLE
        }
    }

    private fun isConnect(): Boolean {
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }


}