package com.example.articleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articleapp.MainActivity
import com.example.articleapp.R
import com.example.articleapp.databinding.LayoutFragmentNewsDetailBinding
import com.example.articleapp.databinding.LayoutNewsCategoryBinding
import com.example.articleapp.ui.adapter.NewsCategoryAdapter
import com.example.articleapp.ui.model.NewsCategory

class NewsDetailFragment: Fragment(R.layout.layout_fragment_news_detail) {

  private val navigationArgs: NewsDetailFragmentArgs by navArgs()

  private val viewBinding: LayoutFragmentNewsDetailBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentNewsDetailBinding? = null

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
    _viewBinding = LayoutFragmentNewsDetailBinding.inflate(inflater, container, false)
    val source = navigationArgs.item
    viewBinding.wvDetail.webViewClient = WebViewClient()
    viewBinding.wvDetail.loadUrl(source)
    return viewBinding.root
  }

  private fun getCurrentActivity(): MainActivity?{
    return (activity as? MainActivity)
  }
}