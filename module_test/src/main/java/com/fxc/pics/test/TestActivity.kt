package com.fxc.pics.test

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.fxc.pics.common.base.BaseActivity
import com.fxc.pics.common.events.EventUtil
import kotlinx.android.synthetic.main.test_activity_layout.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TestActivity : BaseActivity() {
	override fun getContentViewId(): Int {
		return R.layout.test_activity_layout
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		EventBus.getDefault().register(this)
//		EventBus.getDefault().unregister(this)

//		val data = ArrayList<String>()
//		for (i in 0..50) {
//			data.add("Asdasd $i")
//		}
////		val adapter = WrapRecyclerViewAdapter(TestAdapter(data))
//		val adapter = TestAdapter(data)
//		image.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//		image.adapter = adapter
//		val header = LayoutInflater.from(this).inflate(R.layout.test_activity_1, image, false)
//		val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
////		header.layoutParams = params
//		header.setOnClickListener {
////			image.removeHeaderView(it)
//		}
//		image.addHeaderView(header)
//		image.setOnItemClickListener { view, position ->
////			Toast.makeText(this@TestActivity, "remove ${data[position]}  position $position  size${data.size}", Toast.LENGTH_SHORT).show()
////			data.removeAt(position)
////			adapter.notifyDataSetChanged()
////			EventBus.getDefault().post(ArrayList<String>())
//			val intent = Intent()
//			intent.setClass(this, Second::class.java)
//			val dd = ArrayList<Bean>()
//			startActivity(intent)
//
//		}
//
//		image_vv.setOnClickListener {
//			Toast.makeText(this, "FFFF", Toast.LENGTH_SHORT).show()
//		}
	}

	override fun onStop() {
		super.onStop()
//		EventUtil.post(Bean("aa"))

	}

	override fun initWidget() {
		super.initWidget()
	}

	override fun afterInitWidget() {
		super.afterInitWidget()
//		target.setOnClickListener {
//			ViewCompat.offsetTopAndBottom(it, 5)
////			it.scrollTo(0, 10)
//		}
//		image_view_1.setOnClickListener {
//			startActivityByShareElement(HashMap(), Second::class.java, Pair(image_view_1, Second.KEY))
//		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun event(test: Test) {
		Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
	}

	override fun onDestroy() {
		super.onDestroy()
	}
}
