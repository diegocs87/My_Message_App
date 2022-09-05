package com.example.mymessageapp.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymessageapp.databinding.AllMessagesRecyclerItemBinding
import com.example.mymessageapp.model.MessageData

class AllPostsRecyclerAdapter (private val posts: List<MessageData>, private val postsItemClickedListener:
    (MessageData) -> Unit) : RecyclerView.Adapter<AllPostsRecyclerAdapter.MessageHolder>() {

    lateinit var postCardViewItemBinding:AllMessagesRecyclerItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllPostsRecyclerAdapter.MessageHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AllPostsRecyclerAdapter.MessageHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MessageHolder(private val cardViewBinding: AllMessagesRecyclerItemBinding):
        RecyclerView.ViewHolder(cardViewBinding.root) {

    }

}