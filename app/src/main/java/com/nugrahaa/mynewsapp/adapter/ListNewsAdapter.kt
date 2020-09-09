package com.nugrahaa.mynewsapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.mynewsapp.R
import com.nugrahaa.mynewsapp.model.Article
import com.nugrahaa.mynewsapp.ui.NewsDetailActivity
import kotlinx.android.synthetic.main.item_row_news.view.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ListNewsAdapter(private val listNews: ArrayList<Article>) : RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.img_news
        val tvTitle: TextView = itemView.tv_title
        val tvDate: TextView = itemView.tv_date
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListNewsAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_news, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListNewsAdapter.ListViewHolder, position: Int) {
        val news = listNews[position]

        Glide.with(holder.itemView.context)
            .load(news.urlToImage)
            .apply(RequestOptions().override(140, 100))
            .into(holder.imgPhoto)

        holder.tvTitle.text = news.title
        holder.tvDate.text = getDate(news.publishedAt)

        val mContext = holder.itemView.context
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.EXTRA_URL, news.urlToImage)
            intent.putExtra(NewsDetailActivity.EXTRA_AUTHOR, news.author)
            intent.putExtra(NewsDetailActivity.EXTRA_TITLE, news.title)
            intent.putExtra(NewsDetailActivity.EXTRA_DESCRIPTION, news.description)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    fun getDate(date: String?): String? {
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val mDate = formatter.parse(date)
            return mDate.toString()
        } catch (e: Exception) {
            Log.e("mDate", e.toString())
            return date
        }
    }

}