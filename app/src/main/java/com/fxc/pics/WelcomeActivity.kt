package com.fxc.pics

import android.content.Intent
import android.os.Handler
import android.support.v4.app.SharedElementCallback
import android.support.v4.util.Pair
import android.util.Log
import android.view.View
import com.fxc.pics.common.base.BaseActivity
import com.fxc.pics.pic.picHome.fragments.PicHomeFragment
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 *
 * @author fxc
 * @date 2018/1/13
 */
class WelcomeActivity : BaseActivity() {

	override fun getContentViewId(): Int {
		return R.layout.activity_welcome
	}

	override fun initWidget() {
		Handler().postDelayed( {
			startActivityByShareElement(HashMap(), Class.forName("com.fxc.pics.pic.picHome.PicActivity"), Pair.create(welcome_title, PicHomeFragment.KEY_PIC_TITLE), Pair.create(welcome_subtitle, PicHomeFragment.KEY_PIC_SUBTITLE))
		}, 1000)
		setExitSharedElementCallback(object : SharedElementCallback() {
			override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
				super.onMapSharedElements(names, sharedElements)
				Log.d("qweasfd", "wel onMapSharedElements")

			}
		})
	}

	override fun onActivityReenter(resultCode: Int, data: Intent?) {
		super.onActivityReenter(resultCode, data)

	}
}