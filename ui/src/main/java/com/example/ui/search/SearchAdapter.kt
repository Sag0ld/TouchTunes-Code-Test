package com.example.ui.search

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.domains.Album
import com.example.domains.Entity
import com.example.ui.R
import com.example.ui.databinding.SearchResultBinding
import com.example.ui.toFormattedDate

class SearchAdapter(private val context: Context, searchResult: List<Entity>? = null) :
    RecyclerView.Adapter<SearchViewHolder>() {
    private var _searchResult: MutableList<Entity> = searchResult.orEmpty().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val searchResultBinding =
            SearchResultBinding.inflate(LayoutInflater.from(context), parent, false)
        return SearchViewHolder(searchResultBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val result = _searchResult[position] ?: return
        when (result) {
            is Album -> {
                with(holder) {
                    Glide.with(context).load(result.artworkUrl)
                        .apply(
                            RequestOptions.bitmapTransform(RoundedCorners(14))
                        )
                        .into(binding.artwork)
                    binding.title.text = result.name
                    binding.releaseDate.text = result.releaseDate.toFormattedDate()
                    binding.root.setOnClickListener {
                        val navController =
                            (context as Activity).findNavController(R.id.nav_host_fragment)
                        navController
                            .navigate(
                                R.id.search_result_dialog, bundleOf("result" to result)
                            )
                    }
                }
            }
            else -> {
            }
        }
    }

    override fun getItemCount(): Int = _searchResult.size
    fun updateResults(searchResult: List<Entity>? = null) {
        this._searchResult.clear()
        searchResult?.let {
            this._searchResult.addAll(it)
        }
        notifyDataSetChanged()
    }

}

class SearchViewHolder(val binding: SearchResultBinding) : RecyclerView.ViewHolder(binding.root)