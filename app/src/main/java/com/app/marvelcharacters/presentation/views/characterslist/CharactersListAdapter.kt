package com.app.marvelcharacters.presentation.views.characterslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.marvelcharacters.R
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.loadImage
import kotlinx.android.synthetic.main.character_item.view.*

class CharactersListAdapter(private val list: List<CharacterData>, private var onDetailClickedListener: OnDetailClickedListener) :
    RecyclerView.Adapter<CharactersListAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class CharacterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterData) {
            itemView.apply {
                character_name.text = item.name
                character_item.setOnClickListener { onDetailClickedListener.goToDetail(item.id) }
                character_img.loadImage(item.thumbnail.path, item.thumbnail.extension)
            }
        }
    }

    interface OnDetailClickedListener{
        fun goToDetail(id: Int)
    }

}