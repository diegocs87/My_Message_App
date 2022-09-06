package com.example.mymessageapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymessageapp.databinding.AllMessagesRecyclerItemBinding
import com.example.mymessageapp.databinding.CommentRecyclerItemBinding
import com.example.mymessageapp.model.MessageData

class CommentRecyclerAdapter (val comments : List<String>) :
    RecyclerView.Adapter<CommentRecyclerAdapter.CommentHolder>(){

    private lateinit var commentRecyclerItemBinding: CommentRecyclerItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentHolder {
        commentRecyclerItemBinding = CommentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false);
        return CommentHolder(commentRecyclerItemBinding);
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        var messageId = comments[position]
        holder.setComment(messageId)
    }

    override fun getItemCount(): Int = comments.size

    class CommentHolder(private val messageViewBinding: CommentRecyclerItemBinding):
        RecyclerView.ViewHolder(messageViewBinding.root) {

        fun setComment(messageId: String){
            messageViewBinding.commentItemTV.text = messageId
        }
    }
}