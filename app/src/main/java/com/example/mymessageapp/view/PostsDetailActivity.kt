package com.example.mymessageapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mymessageapp.R
import com.example.mymessageapp.databinding.PostsDetailActivityBinding
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.entities.toDataBaseData
import com.example.mymessageapp.view.adapters.CommentRecyclerAdapter
import com.example.mymessageapp.viewmodel.ChangeFavoriteStateViewModel
import com.example.mymessageapp.viewmodel.CommentsViewModel
import com.example.mymessageapp.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsDetailActivity: AppCompatActivity() {

    private lateinit var detailActivityBinding : PostsDetailActivityBinding

    private val usersViewModel: UsersViewModel by viewModels()
    private val commentsViewModel: CommentsViewModel by viewModels ()
    private val favoritesViewModel: ChangeFavoriteStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = PostsDetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        detailActivityBinding.commentsProgressBar.isVisible = true
        val post = intent.getParcelableExtra<PostsDataItem>(EXTRA_POST)
        onFavButtonClickListener(post!!)
        setPostData(post)
        getFavoriteState(post)
    }

    fun onFavButtonClickListener(post: PostsDataItem){
        detailActivityBinding.postFavorite.setOnClickListener {
            favoritesViewModel.setFavoriteState(post.toDataBaseData())
        }
    }
    fun getFavoriteState(post:PostsDataItem){
        favoritesViewModel.getFavoriteState(post.toDataBaseData())
        favoritesViewModel.isFavoriteModel.observe(this) { isFav ->
            onFavButtonResState(isFav)
        }
    }

    fun setPostData(post:PostsDataItem){
        detailActivityBinding.postDescBody.text = post.body
        setUserData(post)
        setCommentsData(post)
    }

    fun setUserData(post:PostsDataItem){
        usersViewModel.getUser(post.userId.toString())
        usersViewModel.usersModel.observe(this
        ) { userList ->
            detailActivityBinding.userNameBody.text = userList.name
            detailActivityBinding.userMailBody.text = userList.email
            detailActivityBinding.userPhoneBody.text = userList.phone
            detailActivityBinding.userWebBody.text = userList.website
        }
    }

    fun setCommentsData(post:PostsDataItem){
        val commentsList = mutableListOf("")
        commentsList.remove("")
        commentsViewModel.getComments(post.id.toString())
        commentsViewModel.commentsModel.observe(this
        ) { comments ->
            detailActivityBinding.commentsProgressBar.isVisible = false
            comments.forEach {
                commentsList.add("\'" + it.body + "\'")
            }
            detailActivityBinding.rvCommentsList.adapter = CommentRecyclerAdapter(commentsList)
        }
    }

    fun onFavButtonResState(isFavorite: Boolean){
        if(isFavorite) {
            detailActivityBinding.postFavorite.setImageResource(R.drawable.ic_fav_indicator_item)
        }else{
            detailActivityBinding.postFavorite.setImageResource(R.drawable.ic_fav_border)
        }
    }

    companion object {
        var EXTRA_POST = "POST"
    }

}