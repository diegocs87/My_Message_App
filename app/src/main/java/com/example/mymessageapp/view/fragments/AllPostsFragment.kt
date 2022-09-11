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
import com.example.mymessageapp.model.data.database.entities.toDataBaseData
import com.example.mymessageapp.model.data.database.entities.toDataPostBaseData
import com.example.mymessageapp.view.PostsDetailActivity
import com.example.mymessageapp.view.adapters.AllPostsRecyclerAdapter
import com.example.mymessageapp.viewmodel.ChangeFavoriteStateViewModel
import com.example.mymessageapp.viewmodel.PostsViewModel

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
    private val postsViewModel: PostsViewModel by viewModels()
    private val favoritesViewModel: ChangeFavoriteStateViewModel by viewModels()

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
        onFragmentsCreate()
        setListeners()
        return PostsFragmentbinding!!.root
    }

    override fun onStart() {
        super.onStart()
    }


    private fun onFragmentsCreate(){
        postsViewModel.getPosts()
        onPostsViewModelObserver()
        onDataBaseSaveObserver()
    }

    private fun onPostsViewModelObserver(){
        postsViewModel.postsModel.observe(viewLifecycleOwner, Observer { postsList ->
            postsViewModel.savePosts(requireContext(), postsList.map {postsDataItem -> postsDataItem.toDataPostBaseData()})
            PostsFragmentbinding!!.allPostsRecyclerView.adapter = AllPostsRecyclerAdapter(postsList)
            { post ->
                onPostDetailActivity(post)
            }
            PostsFragmentbinding!!.progressBar.isVisible = false
        })
    }

    private fun onDataBaseSaveObserver(){
        postsViewModel.savePostsModel.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Posts saved on DB", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListeners(){
        onDeleteAllButtonClickListener()
        onLoadAllPostsButtonClickListener()
    }

    private fun onLoadAllPostsButtonClickListener(){
        PostsFragmentbinding!!.updateAllButton.setOnClickListener { view ->
            postsViewModel.getPosts()
            onPostsViewModelObserver()
        }
    }

    private fun onDeleteAllButtonClickListener(){
        PostsFragmentbinding!!.deleteallbutton.setOnClickListener{ view ->
            PostsFragmentbinding!!.allPostsRecyclerView.adapter = AllPostsRecyclerAdapter(emptyList()){ post ->
                onPostDetailActivity(post)
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