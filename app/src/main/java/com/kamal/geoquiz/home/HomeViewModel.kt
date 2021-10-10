package com.kamal.geoquiz.home

import androidx.lifecycle.ViewModel
import com.kamal.geoquiz.R
import com.kamal.geoquiz.model.Question

class HomeViewModel:ViewModel() {
     var questionsList = mutableListOf<Question>(
        Question(R.string.Question_Egypt,true),
        Question(R.string.Question_America,false),
        Question(R.string.Question_Brazil,true),
        Question(R.string.Question_Australia,false),
        Question(R.string.Question_England,true),
        Question(R.string.Question_Canada,false),
        Question(R.string.Question_France,false)
    )
     var totalQuestions = mutableListOf<Int>()
     var currentIndex = 0
    fun moveToNext()
    {
        currentIndex = (currentIndex + 1) % questionsList.size
    }
    fun moveToPrevious()
    {
        currentIndex = (currentIndex - 1) % questionsList.size
    }
    fun getLastQuestion()
    {
        currentIndex = (currentIndex + 6) % questionsList.size
    }
}