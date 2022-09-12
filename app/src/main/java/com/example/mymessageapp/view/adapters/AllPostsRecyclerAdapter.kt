package com.example.mymessageapp.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mymessageapp.databinding.AllMessagesRecyclerItemBinding
import com.example.mymessageapp.model.data.PostsDataItem

class AllPostsRecyclerAdapter (private val posts: List<PostsDataItem>, private val postsItemClickedListener:
    (PostsDataItem) -> Unit) : RecyclerView.Adapter<AllPostsRecyclerAdapter.MessageHolder>() {

    lateinit var postCardViewItemBinding:AllMessagesRecyclerItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): MessageHolder {
        postCardViewItemBinding = AllMessagesRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return MessageHolder(postCardViewItemBinding)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.onPostCardListener()
        val postId = posts[position]
        holder.setPostTittle(postId)
        holder.setFavIndicator(postId.isFav)
        holder.itemView.setOnClickListener {postsItemClickedListener(postId)}

    }

    override fun getItemCount(): Int = posts.size

    class MessageHolder(private val cardViewBinding: AllMessagesRecyclerItemBinding):
        RecyclerView.ViewHolder(cardViewBinding.root) {

        fun onPostCardListener() {
            cardViewBinding.deleteImgView.setOnClickListener {
                cardViewBinding.postTittle.isVisible = false
                cardViewBinding.deleteImgView.isVisible = false
            }
        }

            fun setPostTittle(postId: PostsDataItem) {
                cardViewBinding.postTittle.text = postId.title
            }

            fun setFavIndicator(isVisible: Boolean) {
                cardViewBinding.favImgView.isVisible = isVisible
            }

    }

}