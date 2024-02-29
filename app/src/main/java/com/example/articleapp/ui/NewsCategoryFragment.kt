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
import com.example.articleapp.databinding.LayoutNewsCategoryBinding
import com.example.articleapp.ui.adapter.NewsCategoryAdapter
import com.example.articleapp.ui.model.NewsCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsCategoryFragment: Fragment(R.layout.layout_fragment_news_list) {

  private val viewBinding: LayoutNewsCategoryBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutNewsCategoryBinding? = null

  private var newsCategoryAdapter: NewsCategoryAdapter? = null

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
    val category = listOf(
      NewsCategory("business"),
      NewsCategory("entertainment"),
      NewsCategory("general"),
      NewsCategory("health"),
      NewsCategory("science"),
      NewsCategory("sports"),
      NewsCategory("technology")
    )

    _viewBinding = LayoutNewsCategoryBinding.inflate(inflater, container, false)
    newsCategoryAdapter = NewsCategoryAdapter { category ->
      val action = NewsCategoryFragmentDirections.actionNewsCategoryFragmentToSourcesFragment(category.categoryName)
      findNavController().navigate(action)
    }
    newsCategoryAdapter?.submitList(category)
    viewBinding.rvCategory.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = newsCategoryAdapter
    }

    return viewBinding.root
  }

  private fun getCurrentActivity(): MainActivity?{
    return (activity as? MainActivity)
  }
}