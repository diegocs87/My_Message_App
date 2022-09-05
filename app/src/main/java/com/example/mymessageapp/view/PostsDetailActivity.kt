package com.example.mymessageapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymessageapp.databinding.PostsDetailActivityBinding
import com.example.mymessageapp.model.MessageData
import com.example.mymessageapp.view.adapters.CommentRecyclerAdapter

class PostsDetailActivity : AppCompatActivity() {

    private lateinit var detailActivityBinding : PostsDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = PostsDetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        detailActivityBinding.rvCommentsList.adapter = CommentRecyclerAdapter(fillComments())
        val post = intent.getParcelableExtra<MessageData>("data")
        detailActivityBinding.postDescBody.text = post!!.description
        detailActivityBinding.userNameBody.text = post.user.name
        detailActivityBinding.userMailBody.text = post.user.email
        detailActivityBinding.userPhoneBody.text = post.user.phone
        detailActivityBinding.userWebBody.text = post.user.webSite
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