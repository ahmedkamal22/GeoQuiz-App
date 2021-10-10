package com.example.islami.base

import android.content.DialogInterface
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity:AppCompatActivity(){

    fun showToast(message:Int)
    {
        val toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM,10,200)
        toast.show()
    }
    fun showDialog(title:Int?=null,message:String,posActionName:String?=null,
                   posAction:DialogInterface.OnClickListener?=null,negActionName:Int?=null,
                   negAction:DialogInterface.OnClickListener?=null,isCancellable:Boolean = true)
    {
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(isCancellable)
        dialog.setTitle(title!!)
        dialog.setMessage(message)
        dialog.setPositiveButton(posActionName,posAction)
        dialog.setNegativeButton(negActionName!!,negAction)
                .show()
    }
}