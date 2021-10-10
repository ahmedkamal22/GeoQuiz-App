package com.kamal.geoquiz.splach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.kamal.geoquiz.R
import com.kamal.geoquiz.home.HomeActivity

class Splach : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        Handler(Looper.getMainLooper())
            .postDelayed({
                         goTOMain()
            },2000)
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }

    private fun goTOMain() {
        val intent  = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}