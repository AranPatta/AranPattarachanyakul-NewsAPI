package com.example.newsapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapi.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        val args = ArticleDetailFragmentArgs.fromBundle(requireArguments())
        binding.articleTitleTextView.text = args.articleTitle
        binding.articleContentTextView.text = if (args.articleDescription.isEmpty()) "[No Description]" else args.articleDescription

        return binding.root
    }
}