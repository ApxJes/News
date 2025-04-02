package com.example.news.newsFeatures.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.newsFeatures.domain.model.Article

class HeadlinesNewsAdapter: RecyclerView.Adapter<HeadlinesNewsAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.imvNewsImage)
        val newsTitle: TextView = itemView.findViewById(R.id.txvNewsTitle)
        val newsDescription: TextView = itemView.findViewById(R.id.txvNewsDescription)
        val author: TextView = itemView.findViewById(R.id.txvAuthor)
        val newsReleaseDate: TextView = itemView.findViewById(R.id.txvNewsReleaseDate)
    }

    private val differCallBack = object: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsViewHolder {
        return ItemsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ItemsViewHolder,
        position: Int
    ) {
        val headLineNews = differ.currentList[position]

        holder.apply {
            Glide.with(itemView.context).load(headLineNews.urlToImage).into(newsImage)
            newsTitle.text = headLineNews.title
            newsDescription.text = headLineNews.description
            author.text = headLineNews.author
            newsReleaseDate.text = headLineNews.publishedAt

            itemView.setOnClickListener {
                onClick?.invoke(headLineNews)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onClick: ((Article) -> Unit)? = null

    fun setOnClickListener(listener: (Article) -> Unit) {
        onClick = listener
    }
}