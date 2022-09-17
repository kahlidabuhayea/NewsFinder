package com.app.newsfinder.presentation.news.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.newsfinder.R
import com.app.newsfinder.presentation.news.model.Article
import com.app.newsfinder.presentation.utils.formatDate
import com.bumptech.glide.Glide


class NewsAdapter internal constructor(val context: Context, data: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val mData: List<Article>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = mData[position]
        holder.title.text = article.title
        holder.description.text = article.description
        holder.author.text = article.author
        holder.date.text = article.date.formatDate()
        Glide.with(context).load(article.image).into(holder.image)
    }


    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var title: TextView
        var description: TextView
        var author: TextView
        var date: TextView
        var image: ImageView

        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(mData[adapterPosition])
        }

        init {
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
            author = itemView.findViewById(R.id.author)
            date = itemView.findViewById(R.id.date)
            image = itemView.findViewById(R.id.image)

            itemView.setOnClickListener(this)
        }
    }


    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(article: Article)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }

    override fun getItemCount(): Int = mData.size
}