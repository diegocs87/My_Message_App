package com.example.mymessageapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.databinding.PostsDetailActivityBinding
import com.example.mymessageapp.model.MessageData
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.dao.FavoritesDao
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.toDataBaseData
import com.example.mymessageapp.view.adapters.CommentRecyclerAdapter
import com.example.mymessageapp.viewmodel.FavoritePostsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsDetailActivity : AppCompatActivity() {

    private lateinit var detailActivityBinding : PostsDetailActivityBinding
    private val favoritePostsViewModel: FavoritePostsViewModel by viewModels()
    private lateinit var favoritesDao: FavoritesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = PostsDetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        detailActivityBinding.rvCommentsList.adapter = CommentRecyclerAdapter(fillComments())
        val post = intent.getParcelableExtra<PostsDataItem>(EXTRA_POST)
        detailActivityBinding.postDescBody.text = post!!.body
        detailActivityBinding.userNameBody.text = post.id.toString()
        detailActivityBinding.userMailBody.text = post.userId.toString()
        favoritesDao = PostsDataBase.buildDatabase(application).favoritesDao()
        detailActivityBinding.buttonFavorite.setOnClickListener {
            addPostToFavorite(post.toDataBaseData(), application)}
    }

    fun addPostToFavorite(postId: FavoritesEntity, context: Context){
        favoritesDao.insertFavorite(postId)
    }


    companion object {
        var EXTRA_POST = "POST"
    }

    fun fillComments() : List<String>{
        return listOf(
            "st natus enim nihil est dolore omnis voluptatem numquam\\net omnis occaecati quod ullam at\\nvoluptatem error expedita pariatur\\nnihil sint nostrum voluptatem reiciendis et",
            "audantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium",
            "uia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione",
            "non et atque\\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\\nquia voluptas consequuntur itaque dolor\\net qui rerum deleniti ut occaecati",
            "harum non quasi et ratione\\ntempore iure ex voluptates in ratione\\nharum architecto fugit inventore cupiditate\\nvoluptates magni quo et"
        )
    }
}