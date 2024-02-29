package com.example.articleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articleapp.MainActivity
import com.example.articleapp.R
import com.example.articleapp.databinding.LayoutFragmentSearchBinding
import com.example.articleapp.databinding.LayoutNewsCategoryBinding
import com.example.articleapp.ui.adapter.NewsCategoryAdapter
import com.example.articleapp.ui.model.NewsCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment(R.layout.layout_fragment_search) {

  private val viewBinding: LayoutFragmentSearchBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentSearchBinding? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
    getCurrentActivity()?.getBottomNav()?.visibility = View.VISIBLE
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _viewBinding = LayoutFragmentSearchBinding.inflate(inflater, container, false)

    return viewBinding.root
  }

  private fun getCurrentActivity(): MainActivity?{
    return (activity as? MainActivity)
  }
}