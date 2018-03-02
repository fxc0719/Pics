package com.fxc.pics.pic.picDetail

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout
import com.fxc.pics.common.base.PresenterFragment
import com.fxc.pics.pic.R
import kotlinx.android.synthetic.main.pic_fragment_pic_detail.view.*

/**
 * @author fxc
 * @date 2018/3/2
 */
class PicDetailFragment : PresenterFragment<PicDetailFragmentPresenterImp>() {

	companion object {
		fun getInstance() : PicDetailFragment {
			val instance = PicDetailFragment()
			val bundle = Bundle()
			instance.arguments = bundle
			return instance
		}
	}

	override fun initPresenter(): PicDetailFragmentPresenterImp = PicDetailFragmentPresenterImp(this)

	override fun getContentViewId(): Int = R.layout.pic_fragment_pic_detail

	override fun initWidget() {
		super.initWidget()
		initRecyclerView()
	}

	private fun initRecyclerView() {
		rootView.pic_detail_related_list.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VISIBLE)
	}

	override fun error(failReason: String) {

	}
}