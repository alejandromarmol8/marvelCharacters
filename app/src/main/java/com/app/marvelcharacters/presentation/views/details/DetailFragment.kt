package com.app.marvelcharacters.presentation.views.details

import androidx.core.os.bundleOf
import com.app.marvelcharacters.R
import com.app.marvelcharacters.databinding.DetailFragmentBinding
import com.app.marvelcharacters.loadImage
import com.app.marvelcharacters.presentation.views.bases.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    override val viewModel: DetailViewModel by viewModel()
    override fun getFragmentBinding(): DetailFragmentBinding = DetailFragmentBinding.inflate(layoutInflater)

    companion object {
        private const val CHARACTER_ID = "CHARACTER_ID"
        fun createBundle(id: Int) = bundleOf(CHARACTER_ID to id)
    }

    override fun initViews() {
        arguments?.let { bundle ->
            viewModel.getCharacter(bundle.getInt(CHARACTER_ID))
        }
    }

    override fun initObservers() {
        viewModel.descriptionCharacter.observe(this, {data ->
            data?.let {cdd ->
                binding.apply {
                    name.text = cdd.name
                    description.text = cdd.description
                    loadImage(cdd.thumbnail.path, cdd.thumbnail.extension, thumbnail)
                    comicsAvailable.text = getString(R.string.comics_available, cdd.comics.available)
                    storiesAvailable.text = getString(R.string.stories_available, cdd.stories.available)
                    seriesAvailable.text = getString(R.string.series_available, cdd.series.available)
                    eventsAvailable.text = getString(R.string.events_available, cdd.events.available)
                }
            }
        })
    }
}