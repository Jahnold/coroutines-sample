package com.jahnold.coroutines.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jahnold.coroutines.base.extension.bindView
import com.jahnold.coroutines.base.view.BaseFragment
import com.jahnold.coroutines.details.R
import com.jahnold.coroutines.details.domain.DetailsViewModel
import com.jahnold.coroutines.details.view.data.DetailsState
import com.jahnold.coroutines.details.view.data.DetailsUiModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailsFragment: BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailsViewModel

    private val image: ImageView by bindView(R.id.details_image)
    private val name: TextView by bindView(R.id.details_name)
    private val artist: TextView by bindView(R.id.details_artist)
    private val published: TextView by bindView(R.id.details_published)
    private val summary: TextView by bindView(R.id.details_summary)

    private val content: ViewGroup by bindView(R.id.details_content)
    private val loading: ProgressBar by bindView(R.id.details_loading)
    private val error: TextView by bindView(R.id.details_error)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToData()
    }

    private fun subscribeToData() {

        viewModel
            .getDetailsData(getUuid())
            .subscribe(
                { result ->
                    setVisibilities(result)
                    if (result is DetailsState.Content) {
                        setViewFromData(result.data)
                    }
                },
                { it.printStackTrace() }
            )
            .track()
    }

    private fun getUuid(): String? {
        return arguments?.getString(ARG_UUID)
    }

    private fun setVisibilities(result: DetailsState) {
        content.isVisible = result.isContentVisible
        loading.isVisible = result.isLoadingVisible
        error.isVisible = result.isErrorVisible
    }

    private fun setViewFromData(data: DetailsUiModel) {

        Picasso.get().load(data.imageUrl).into(image)

        name.text = data.name
        artist.text = data.artist
        published.text = data.published
        summary.text = data.summary
    }

    companion object {

        private const val ARG_UUID = "arg_search"

        fun create(uuid: String): DetailsFragment {

            val bundle = Bundle(1)
            bundle.putString(ARG_UUID, uuid)

            val fragment = DetailsFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}