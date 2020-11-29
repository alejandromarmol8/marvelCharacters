package com.app.marvelcharacters

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

fun Fragment.loadImage(path: String, extension: String, imageView: ImageView){
        var newpath = path
        if(path.startsWith("http://"))
            newpath = path.replace("http", "https")
        Picasso.get().load(newpath.plus(".").plus(extension)).into(imageView)
}
