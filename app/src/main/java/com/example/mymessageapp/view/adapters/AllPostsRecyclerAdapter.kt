package com.example.mymessageapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mymessageapp.databinding.AllMessagesRecyclerItemBinding
import com.example.mymessageapp.model.MessageData
import com.example.mymessageapp.model.data.PostsDataItem

class AllPostsRecyclerAdapter (private val posts: List<PostsDataItem>, private val postsItemClickedListener:
    (PostsDataItem) -> Unit) : RecyclerView.Adapter<AllPostsRecyclerAdapter.MessageHolder>() {

    lateinit var postCardViewItemBinding:AllMessagesRecyclerItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): MessageHolder {
        postCardViewItemBinding = AllMessagesRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false);
        return MessageHolder(postCardViewItemBinding);
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val postId = posts[position]
        holder.setPostTittle(postId)
        holder.itemView.setOnClickListener {postsItemClickedListener(postId)}
    }

    override fun getItemCount(): Int = posts.size

    class MessageHolder(private val cardViewBinding: AllMessagesRecyclerItemBinding):
        RecyclerView.ViewHolder(cardViewBinding.root) {

            fun setPostTittle(postId: PostsDataItem){
                cardViewBinding.postTittle.text = postId.title
            }
    }

}