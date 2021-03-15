package com.app.marvelcharacters.presentation.views.characterslist

import com.app.marvelcharacters.R
import com.app.marvelcharacters.databinding.SelectionFragmentBinding
import com.app.marvelcharacters.presentation.views.bases.BaseFragment
import com.app.marvelcharacters.presentation.views.characterdetail.CharacterDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment: BaseFragment<SelectionFragmentBinding>() ,
    CharactersListAdapter.OnDetailClickedListener {

    override fun getFragmentBinding(): SelectionFragmentBinding = SelectionFragmentBinding.inflate(layoutInflater)
    override val viewModel: CharactersListViewModel by viewModel()

    override fun initViews() {
        super.initViews()
        viewModel.getCharacters()
    }

    override fun initObservers() {
        viewModel.characters.observe(this, {
            binding.charactersRecycler.adapter = CharactersListAdapter(it, this)
        })
    }

    override fun goToDetail(id: Int) {
        navigateTo(R.id.action_selection_fragment_to_detailFragment, CharacterDetailFragment.createBundle(id))
    }

}