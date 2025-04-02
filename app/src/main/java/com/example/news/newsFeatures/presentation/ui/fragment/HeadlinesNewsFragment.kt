package com.example.news.newsFeatures.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.core.Resource
import com.example.news.databinding.FragmentHeadlinesNewsBinding
import com.example.news.newsFeatures.presentation.adapter.HeadlinesNewsAdapter
import com.example.news.newsFeatures.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeadlinesNewsFragment : Fragment() {

    private var _binding: FragmentHeadlinesNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: HeadlinesNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeadlinesNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = HeadlinesNewsAdapter()

        fetchDataFromNewsViewModel()
        setUpRecyclerViewForHeadlinesNews()

        newsAdapter.setOnClickListener {
            val action = HeadlinesNewsFragmentDirections.actionHeadlinesNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun fetchDataFromNewsViewModel() {

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {state ->
                    binding.loaingProgressBar.visibility = if(state.isLoading) View.VISIBLE else View.INVISIBLE

                    if(state.newsState.isNotEmpty()) {
                        newsAdapter.differ.submitList(state.newsState)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect {event ->
                    when(event) {
                        is NewsViewModel.UiEvent.ToastMessage -> {
                            Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerViewForHeadlinesNews() {
        binding.rcvHeadlineNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}