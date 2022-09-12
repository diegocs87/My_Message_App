package com.example.mymessageapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymessageapp.databinding.AllMessagesRecyclerItemBinding
import com.example.mymessageapp.model.data.PostsDataItem

class FavoritesPostsRecyclerAdapter (private val posts: List<PostsDataItem>, private val postsItemClickedListener:
    (PostsDataItem) -> Unit) : RecyclerView.Adapter<FavoritesPostsRecyclerAdapter.PostsHolder>() {

    lateinit var postFavViewItemBinding: AllMessagesRecyclerItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): PostsHolder {
        postFavViewItemBinding = AllMessagesRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return PostsHolder(postFavViewItemBinding)
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        val postId = posts[position]
        holder.setPostTittle(postId)
        holder.itemView.setOnClickListener {postsItemClickedListener(postId)}
    }

    override fun getItemCount(): Int = posts.size

    class PostsHolder(private val cardViewBinding: AllMessagesRecyclerItemBinding):
        RecyclerView.ViewHolder(cardViewBinding.root) {

        fun setPostTittle(postId: PostsDataItem){
            cardViewBinding.postTittle.text = postId.title
        }
    }

}