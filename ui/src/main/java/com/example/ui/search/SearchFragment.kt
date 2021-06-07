package com.example.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.domains.Status
import com.example.ui.R
import com.example.ui.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.core.component.inject

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by inject()
    private lateinit var adapter: SearchAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.model = searchViewModel

        context?.let {
            adapter = SearchAdapter(it)
            binding.searchResults.adapter = adapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.results.observe(this, Observer { results ->
            when (results.status) {
                Status.ERROR -> {
                    view?.let {
                        showErrorMessage(results.message, it)
                        binding.refreshLayout.isRefreshing = false
                    }
                }
                Status.LOADING -> {
                    binding.refreshLayout.isRefreshing = true
                }
                Status.SUCCESS -> {
                    binding.refreshLayout.isRefreshing = false
                    if (results.data.isNullOrEmpty()) {
                        adapter.updateResults()
                    } else {
                        adapter.updateResults(results.data)
                    }
                    binding.searchResults.smoothScrollToPosition(0);
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.results.removeObservers(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorMessage(message: String?, view: View) {
        context?.let {
            Snackbar.make(it, view, message.orEmpty(), Snackbar.LENGTH_LONG).show()
        }
    }
}