package com.nikhil.slice.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.nikhil.slice.R

@BindingAdapter("app:glideImage")
fun glideImage(imageView: ImageView, icon: Any?){
    Glide.with(imageView.context)
        .load(icon)
        .error(R.drawable.ic_person_24dp)
        .transform(CircleCrop())
        .into(imageView)
}

@BindingAdapter("app:printInt")
fun printInt(textView: TextView, i: Int){
    textView.text = i.toString()
}