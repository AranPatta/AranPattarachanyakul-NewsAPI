package com.example.newsapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
     val _articles = MutableLiveData<List<Article>>()
    val articles : LiveData<List<Article>> = _articles

    fun fetchTopHeadlines(category: String = "general") {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTopHeadlines(apiKey = "1328fe645b0a49488394dbe7d6f3ee7d", country = "us", category = category)
                if (response.isSuccessful && response.body() != null) {
                    _articles.postValue(response.body()!!.articles)
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "API call failed with exception: ${e.message}")

            }
        }
    }

}
