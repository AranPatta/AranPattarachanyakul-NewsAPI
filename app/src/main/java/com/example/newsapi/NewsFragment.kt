package com.example.newsapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        val adapter = NewsAdapter(viewModel.articles.value ?: emptyList()) { article ->
            val action = NewsFragmentDirections.actionNewsFragmentToArticleDetailFragment(article.title, article.description!!)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            newsAdapter = NewsAdapter(articles) { article ->
                val action = NewsFragmentDirections.actionNewsFragmentToArticleDetailFragment(
                    articleTitle = article.title.orEmpty(),
                    articleDescription = article.description.orEmpty()
                )
                findNavController().navigate(action)
            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = newsAdapter
            }
        }

        viewModel.fetchTopHeadlines()

        view.findViewById<Button>(R.id.button_business).setOnClickListener {
            viewModel.fetchTopHeadlines("business")
        }

        view.findViewById<Button>(R.id.button_entertainment).setOnClickListener {
            viewModel.fetchTopHeadlines("entertainment")
        }

        view.findViewById<Button>(R.id.button_general).setOnClickListener {
            viewModel.fetchTopHeadlines("general")
        }

        view.findViewById<Button>(R.id.button_health).setOnClickListener {
            viewModel.fetchTopHeadlines("health")
        }

        view.findViewById<Button>(R.id.button_science).setOnClickListener {
            viewModel.fetchTopHeadlines("science")
        }

        view.findViewById<Button>(R.id.button_sports).setOnClickListener {
            viewModel.fetchTopHeadlines("sports")
        }

        view.findViewById<Button>(R.id.button_technology).setOnClickListener {
            viewModel.fetchTopHeadlines("technology")
        }

    }

}
