package com.kamal.geoquiz.home

import android.os.Bundle
import android.app.Activity
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.geoquiz.R
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import com.kamal.geoquiz.Const
import com.kamal.geoquiz.cheat.CheatActivity


class HomeActivity : BaseActivity() {

  private lateinit var homeViewModel:HomeViewModel
    var currentScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val index = savedInstanceState?.getInt(Const.currentIndex,0)?:0
        homeViewModel.currentIndex = index
        cheat.setOnClickListener {
            val answerIsTrue = homeViewModel.questionsList[homeViewModel.currentIndex].answer
            val intent = CheatActivity.createIntent(this,answerIsTrue!!)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                val options = ActivityOptions
                    .makeClipRevealAnimation(it,0,0,it.width,it.height)
                startActivity(intent,options.toBundle())
            }
            else
            {
                startActivity(intent)
            }

        }
        true_btn.setOnClickListener {
            prevent(homeViewModel.currentIndex)
            checkAnswer(true)
            homeViewModel.totalQuestions.add(homeViewModel.currentIndex)
        }
        false_btn.setOnClickListener {
            prevent(homeViewModel.currentIndex)
            checkAnswer(false)
            homeViewModel.totalQuestions.add(homeViewModel.currentIndex)
        }
        next_btn.setOnClickListener {
            getFollowingQuestion()
        }
        theQuestion.setOnClickListener {
            getFollowingQuestion()
        }
        prev_btn.setOnClickListener {
            if (homeViewModel.currentIndex == 0)
            {
                homeViewModel.getLastQuestion()
                updateQuestion()
            }
            else
            {
                homeViewModel.moveToPrevious()
                updateQuestion()
            }
        }
        updateQuestion()
    }
    fun checkAnswer(userAnswer:Boolean)
    {
        var message:Int
        val correctAnswer = homeViewModel.questionsList[homeViewModel.currentIndex].answer
        if(userAnswer == correctAnswer)
        {
            message = R.string.Correct
            currentScore++
        }
        else
        {
            message = R.string.notCorrect
//            if(currentScore%2==1)
//            {
//                currentScore--
//            }
//            else if(currentScore%1==2)
//            {
//                currentScore--
//            }
            when{
                currentScore%2==1 -> currentScore--
                currentScore%1==2 -> currentScore--
            }
        }
        showToast(message)
        if (homeViewModel.currentIndex == 6)
        {
            showDialog(title = R.string.totalScore,message = "Your current score is: ${currentScore}",negActionName = R.string.cancel,
            negAction = DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() },
            isCancellable = false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(Const.tag,"onSaveInstanceState")
        outState.putInt(Const.currentIndex,homeViewModel.currentIndex)
    }



    override fun onStart() {
        super.onStart()
        changeLanguage.setOnClickListener {
            val langList = arrayOf("En","Ar")
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.choose_language)
            builder.setSingleChoiceItems(langList, -1) { dialog, which ->
                if (which == 0) {
                    setLocale(this, "en")
                    recreate()
                } else if (which == 1) {
                    setLocale(this, "ar")
                    recreate()
                }
                dialog.dismiss()
            }
            val mDialog = builder.create()
            mDialog.show()
        }
    }
    private fun updateQuestion() {
        val questionText = homeViewModel.questionsList[homeViewModel.currentIndex].questionText
        theQuestion.setText(questionText!!)
    }

    private fun getFollowingQuestion()
    {
        refresh()
        homeViewModel.moveToNext()
        updateQuestion()
    }
   private fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.getDisplayMetrics())
    }
   private fun refresh()
    {
        true_btn.isEnabled = true
        false_btn.isEnabled = true
    }
    private fun prevent(index:Int)
    {
        if(homeViewModel.questionsList[index].answer == true)
        {
            false_btn.isEnabled = false
        }
        else
        {
            true_btn.isEnabled = false
        }
    }
}