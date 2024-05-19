package com.example.komalkotlin2024.mvvm

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komalkotlin2024.KomalApplication
import com.example.komalkotlin2024.R
import com.example.komalkotlin2024.dagger.PostViewModel
import com.example.komalkotlin2024.mvvm.model.Post
import java.util.ArrayList
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PostsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: PostViewModel

    private var param1: String? = null
    private var param2: String? = null
    override fun onAttach(context: Context) {
        //(context as KomalApplication).diComponent.inject(this)

        super.onAttach(context)
    }

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.posts_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
         viewModel = ViewModelProvider(this)[PostViewModel::class.java]
        viewModel.getPosts()

        observePosts()


    }
    private fun observePosts(){
        viewModel.posts.observe(viewLifecycleOwner, Observer<List<Post>> {
            recyclerView.adapter = PostsListAdapter(ArrayList(it))
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}