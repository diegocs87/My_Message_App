package com.example.mymessageapp.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymessageapp.databinding.FragmentAllPostsBinding
import com.example.mymessageapp.model.MessageData
import com.example.mymessageapp.model.UserData
import com.example.mymessageapp.view.PostsDetailActivity
import com.example.mymessageapp.view.adapters.AllPostsRecyclerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllPostsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var PostsFragmentbinding: FragmentAllPostsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        PostsFragmentbinding = FragmentAllPostsBinding.inflate(inflater, container, false)
        PostsFragmentbinding!!.allPostsRecyclerView.adapter = AllPostsRecyclerAdapter(fillRecyclerTest())
        { post ->
            onPostDetailActivity(post)
        };
        // Inflate the layout for this fragment
        return PostsFragmentbinding!!.root
    }

    private fun fillRecyclerTest(): List<MessageData> {
        var userList = fillUserDataTest()
        return listOf(
            MessageData(
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "hahahaha",
                userList[0],
                "yayayayuua"
            ),
            MessageData("qui est esse", "hohohoh", userList[1], "rkrkrkrk"),
            MessageData("ea molestias quasi exercitationem repellat qui ipsa sit aut", "hahahaha", userList[2], "kakakaka"),
            MessageData("eum et est occaecati", "gagagagag", userList[3], "hahahahahhaha")
        )
    }

    private fun fillUserDataTest(): List<UserData> {
        return listOf(
            UserData("juan", "juan@gmail.com", "312456", "ww.juan.com"),
            UserData("daniel", "daniel@gmail.com", "311438", "ww.daniel.com"),
            UserData("felipe", "felipe@gmail.com", "316789", "ww.felipe.com"),
            UserData("camilo", "camilo@gmail.com", "3167345", "ww.camilo.com")
        )
    }

    private fun onPostDetailActivity (postData: MessageData){
        val intent = Intent(context, PostsDetailActivity:: class.java)
        intent.putExtra("data", postData)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllPostsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}