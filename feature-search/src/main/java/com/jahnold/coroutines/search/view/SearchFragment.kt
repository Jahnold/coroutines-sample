package com.jahnold.coroutines.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jahnold.coroutines.base.extension.bindView
import com.jahnold.coroutines.base.view.BaseFragment
import com.jahnold.coroutines.list.R
import com.jahnold.coroutines.search.viewmodel.SearchViewModel
import javax.inject.Inject

class SearchFragment: BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel

    private val searchBox: EditText by bindView(R.id.search_text)
    private val searchButton: Button by bindView(R.id.search_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchClickListener()
    }

    private fun initSearchClickListener() {

        searchButton.setOnClickListener {
            viewModel.search(searchBox.text.toString())
            hideKeyboard(searchBox)
        }
    }
}