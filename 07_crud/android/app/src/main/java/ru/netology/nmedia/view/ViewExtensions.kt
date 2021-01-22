package ru.netology.nmedia.view

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.load(url: String) = Glide.with(this)
    .load(url)
    .timeout(10_000)
    .circleCrop()
    .into(this)

