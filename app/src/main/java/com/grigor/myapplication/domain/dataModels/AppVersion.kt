package com.grigor.myapplication.domain.dataModels

import androidx.annotation.Keep

@Keep
data class AppVersion(val versionCode: Int, val versionName:String)