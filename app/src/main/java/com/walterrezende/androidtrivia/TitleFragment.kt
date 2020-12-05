package com.walterrezende.androidtrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.walterrezende.androidtrivia.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_title,
            container,
            false
        )

        return binding.root
    }
}