package com.example.mymessageapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mymessageapp.databinding.PostsDetailActivityBinding
import com.example.mymessageapp.model.MessageData
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.toDataBaseData
import com.example.mymessageapp.view.adapters.CommentRecyclerAdapter
import com.example.mymessageapp.viewmodel.AddFavoriteViewModel
import com.example.mymessageapp.viewmodel.CommentsViewModel
import com.example.mymessageapp.viewmodel.UsersViewModel

class PostsDetailActivity : AppCompatActivity() {

    private lateinit var detailActivityBinding : PostsDetailActivityBinding
    private val usersViewModel: UsersViewModel by viewModels()
    private val commentsViewModel: CommentsViewModel by viewModels ()
    private val favoritesViewModel: AddFavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = PostsDetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        val post = intent.getParcelableExtra<PostsDataItem>(EXTRA_POST)
        setPostData(post!!)
        detailActivityBinding.characterFavorite.setOnClickListener {
            favoritesViewModel.getFavorite(post.toDataBaseData(), application)
        }
    }

    fun setPostData(post:PostsDataItem){
        detailActivityBinding.postDescBody.text = post.body
        setUserData(post)
        setCommentsData(post)
    }

    fun setUserData(post:PostsDataItem){
        usersViewModel.getUser(post.userId.toString())
        usersViewModel.usersModel.observe(this,
            {userList ->
                detailActivityBinding.userNameBody.text = userList.name
                detailActivityBinding.userMailBody.text = userList.email
                detailActivityBinding.userPhoneBody.text = userList.phone
                detailActivityBinding.userWebBody.text = userList.website
            })
    }

    fun setCommentsData(post:PostsDataItem){
        var commentsList = mutableListOf("")
        commentsList.remove("")
        commentsViewModel.getComments(post.id.toString())
        commentsViewModel.commentsModel.observe(this,
            {comments ->
                comments.forEach {
                    commentsList.add( "\'" + it.body + "\'")
                }
                detailActivityBinding.rvCommentsList.adapter = CommentRecyclerAdapter(commentsList)
            })
    }

    companion object {
        var EXTRA_POST = "POST"
    }

}