package com.app.marvelcharacters

import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso

fun AppCompatImageView.loadImage(path: String, extension: String){
        var newPath = path
        if(path.startsWith("http://"))
            newPath = path.replace("http", "https")
        Picasso.get().load(newPath.plus(".").plus(extension)).into(this)
}
