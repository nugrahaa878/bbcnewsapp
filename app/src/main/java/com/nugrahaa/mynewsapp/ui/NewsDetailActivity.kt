package com.nugrahaa.mynewsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.mynewsapp.R
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var imgPhoto: ImageView
    private lateinit var tvAuthor: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvTitle: TextView

    companion object {
        const val EXTRA_URL = "extra_url"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        supportActionBar?.hide()

        prepare()

        val img = intent.getStringExtra(EXTRA_URL)
        val author = intent.getStringExtra(EXTRA_AUTHOR)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        Glide.with(this@NewsDetailActivity)
            .load(img)
            .apply(RequestOptions())
            .into(imgPhoto)

        tvAuthor.text = author
        tvTitle.text = title
        tvDescription.text = description

        btn_home.setOnClickListener {
            val mIntent = Intent(this@NewsDetailActivity, MainActivity::class.java)
            startActivity(mIntent)
        }
    }

    fun prepare() {
        imgPhoto = img_detail
        tvAuthor = tv_author
        tvTitle = tv_title
        tvDescription = tv_description
    }
}