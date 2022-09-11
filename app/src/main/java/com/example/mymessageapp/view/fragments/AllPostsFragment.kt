package com.example.mymessageapp.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mymessageapp.databinding.FragmentAllPostsBinding
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.entities.toDataPostBaseData
import com.example.mymessageapp.view.PostsDetailActivity
import com.example.mymessageapp.view.adapters.AllPostsRecyclerAdapter
import com.example.mymessageapp.viewmodel.ChangeFavoriteStateViewModel
import com.example.mymessageapp.viewmodel.PostsAPIViewModel
import com.example.mymessageapp.viewmodel.PostsDBViewModel

import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class AllPostsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var PostsFragmentbinding: FragmentAllPostsBinding? = null
    private val postsViewModel: PostsAPIViewModel by viewModels()
    private val  postsDBViewModel: PostsDBViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("message")
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        PostsFragmentbinding = FragmentAllPostsBinding.inflate(inflater, container, false)
        PostsFragmentbinding!!.progressBar.isVisible = true
        // Inflate the layout for this fragment
        getDataSource()
        setListeners()
        return PostsFragmentbinding!!.root
    }

    private fun getDataSource(){
        postsDBViewModel.getDBState(requireContext())
        postsDBViewModel.savePostsDBModel.observe(viewLifecycleOwner){
            isEmpty ->
            if(isEmpty){
                getPostsFromAPI(true)
            }else{
                getPostsFromDB()
            }
        }
    }

    private fun getPostsFromAPI(isSave: Boolean){
        postsViewModel.getPosts()
        onPostsAPIViewModelObserver(isSave)
        Toast.makeText(context, "Posts getted from API", Toast.LENGTH_SHORT).show()
    }

    private fun getPostsFromDB(){
        postsDBViewModel.getPosts(requireContext())
        onPostsDBViewModelObserver()
        Toast.makeText(context, "Posts getted from DB", Toast.LENGTH_SHORT).show()
    }

    private fun onPostsDBViewModelObserver(){
        postsDBViewModel.postsDBModel.observe(viewLifecycleOwner){
            list ->
            PostsFragmentbinding!!.allPostsRecyclerView.adapter = AllPostsRecyclerAdapter(list)
            { post ->
                onPostDetailActivity(post)
            }
            PostsFragmentbinding!!.progressBar.isVisible = false
        }
    }


    private fun onPostsAPIViewModelObserver(isSave: Boolean){
        postsViewModel.postsModel.observe(viewLifecycleOwner, Observer { postsList ->
            if(isSave) {
                savePostsFromAPItoDB(postsList)
            }
            PostsFragmentbinding!!.allPostsRecyclerView.adapter = AllPostsRecyclerAdapter(postsList)
            { post ->
                onPostDetailActivity(post)
            }
            PostsFragmentbinding!!.progressBar.isVisible = false
        })
    }

    private fun savePostsFromAPItoDB(postsList: List<PostsDataItem>){
        postsDBViewModel.savePosts(requireContext(), postsList.map {postsDataItem -> postsDataItem.toDataPostBaseData()})
    }

    private fun setListeners(){
        onDeleteAllButtonClickListener()
        onLoadAllPostsButtonClickListener()
    }

    private fun onLoadAllPostsButtonClickListener(){
        PostsFragmentbinding!!.updateAllButton.setOnClickListener { view ->
            getPostsFromAPI(false)
        }
    }

    private fun onDeleteAllButtonClickListener(){
        PostsFragmentbinding!!.deleteallbutton.setOnClickListener{ view ->
            postsDBViewModel.deleteAll(requireContext())
            PostsFragmentbinding!!.allPostsRecyclerView.adapter = AllPostsRecyclerAdapter(emptyList()){ post ->

            }
        }
    }

    private fun onPostDetailActivity (postData: PostsDataItem){
        val intent = Intent(context, PostsDetailActivity:: class.java)
        intent.putExtra(PostsDetailActivity.EXTRA_POST, postData)
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