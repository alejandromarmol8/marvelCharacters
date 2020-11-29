package com.app.marvelcharacters.presentation.views.selection

import com.app.marvelcharacters.R
import com.app.marvelcharacters.databinding.SelectionFragmentBinding
import com.app.marvelcharacters.presentation.views.bases.BaseFragment
import com.app.marvelcharacters.presentation.views.details.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectionFragment: BaseFragment<SelectionFragmentBinding>() ,
    CharactersAdapter.OnDetailClickedListener {

    override fun getFragmentBinding(): SelectionFragmentBinding = SelectionFragmentBinding.inflate(layoutInflater)
    override val viewModel: SelectionViewModel by viewModel()

    override fun initViews() {
        super.initViews()
        viewModel.getCharacters()
    }

    override fun initObservers() {
        viewModel.characters.observe(this, {
            binding.charactersRecycler.adapter = CharactersAdapter(it, this)
        })
    }

    override fun goToDetail(id: Int) {
        navigateTo(R.id.action_selection_fragment_to_detailFragment, DetailFragment.createBundle(id))
    }

}