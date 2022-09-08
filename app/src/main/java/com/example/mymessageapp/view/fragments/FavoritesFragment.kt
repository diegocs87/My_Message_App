package com.example.mymessageapp.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mymessageapp.databinding.FragmentFavoritesBinding
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.view.PostsDetailActivity
import com.example.mymessageapp.view.adapters.FavoritesPostsRecyclerAdapter
import com.example.mymessageapp.viewmodel.ChangeFavoriteStateViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var favoritesFragmentbinding: FragmentFavoritesBinding? = null
    private val favoritesViewModel: ChangeFavoriteStateViewModel by viewModels()

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
        favoritesFragmentbinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return favoritesFragmentbinding!!.root
    }

    override fun onStart() {
        super.onStart()
        onFragmentsCreate()
    }

    private fun onFragmentsCreate(){
        favoritesViewModel.getAllFavorites(context!!.applicationContext)
        onFavoritesListObserver()
    }

    private fun onFavoritesListObserver(){
        favoritesViewModel.favoritesList.observe(this,
            {favList ->
                favoritesFragmentbinding!!.favPostsRecyclerView.adapter =
                    FavoritesPostsRecyclerAdapter(favList)
                    { post ->
                        onPostDetailActivity(post)
                    }
            }
        )
    }

    private fun onPostDetailActivity (postData: PostsDataItem){
        val intent = Intent(context, PostsDetailActivity:: class.java)
        intent.putExtra(PostsDetailActivity.EXTRA_POST, postData)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}