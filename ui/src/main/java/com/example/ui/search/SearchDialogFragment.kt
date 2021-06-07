package com.example.ui.search

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.ui.databinding.DialogSearchResultBinding


class SearchDialogFragment : DialogFragment() {

    private val args: SearchDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            Dialog(it)
        } ?: return onCreateDialog(savedInstanceState)

        val binding = DialogSearchResultBinding.inflate(layoutInflater)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        val album = args.result

        binding.copyright.text = album.copyright
        binding.genre.text = album.genre
        binding.price.text = album.formattedPrice()
        binding.closeBtn.setOnClickListener {
            dismiss()
        }
        
        dialog.setContentView(binding.root)
        return dialog
    }
}