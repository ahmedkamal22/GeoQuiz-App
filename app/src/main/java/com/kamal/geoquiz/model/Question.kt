package com.kamal.geoquiz.model

import androidx.annotation.StringRes

data class Question(@StringRes val questionText:Int?=null, val answer:Boolean?=null)