package com.kamal.geoquiz.cheat

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kamal.geoquiz.Const
import com.kamal.geoquiz.R
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {
    private var answerIsTrue = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerIsTrue = intent.getBooleanExtra(Const.Extra_Answer_Is_True,true)
        show_answer.setOnClickListener {
            var answer = when {
                answerIsTrue -> R.string.True
                else -> R.string.False
            }
            text.setText(answer)
        }
    }
    companion object
    {
        fun createIntent(context: Context,answerIsTrue:Boolean):Intent
        {
           return Intent(context,CheatActivity::class.java).apply {
               putExtra(Const.Extra_Answer_Is_True,answerIsTrue)
           }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,R.string.judgement_toast,Toast.LENGTH_LONG).show()
    }
}