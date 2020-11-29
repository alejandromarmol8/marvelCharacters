package com.app.marvelcharacters.presentation.views.selection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.marvelcharacters.R
import com.app.marvelcharacters.data.charactersdata.CharacterData
import kotlinx.android.synthetic.main.character_item.view.*

class CharactersAdapter(private val list: List<CharacterData>, private var onDetailClickedListener: OnDetailClickedListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewModel =
        CharacterViewModel(LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false))

    override fun onBindViewHolder(holder: CharacterViewModel, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class CharacterViewModel(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterData) {
            itemView.apply {
                character_name.text = item.name
                character_item.setOnClickListener { onDetailClickedListener.goToDetail(item.id) }
            }
        }
    }

    interface OnDetailClickedListener{
        fun goToDetail(id: Int)
    }

}