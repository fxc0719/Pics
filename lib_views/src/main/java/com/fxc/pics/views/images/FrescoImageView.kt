package com.fxc.pics.views.images

import android.content.Context
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo

/**
 * 调用方式: [setUrl]
 *
 * 已生成配置：
 *  ScaleType: [ScalingUtils.ScaleType.CENTER_CROP]
 *  渐进加载
 *
 * @author fxc
 * @date 2018/1/14
 */
class FrescoImageView : SimpleDraweeView {
	companion object {
		private const val TAG = "FrescoImageView"
	}
	constructor(ctx: Context) : this(ctx, null)
	constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)
	constructor(ctx: Context, attr: AttributeSet?, defStyle: Int) : super(ctx, attr, defStyle) {
		initScaleType()
	}

	var listener:(id:String, imageInfo:ImageInfo?, animatable: Animatable?)->Unit = { _, _, _ ->

	}

	private fun initScaleType() {
		hierarchy.actualImageScaleType = ScalingUtils.ScaleType.CENTER_CROP
	}

	/**
	 * 直接下载指定图片
	 * @param url 指定图片 url
	 */
	fun setUrl(url: String) {
		val listenerController = Fresco.newDraweeControllerBuilder()
				.setOldController(controller)
				.setControllerListener(FrescoControllerListener())
				.setUri(url)
				.build()
		controller = listenerController
//		setImageURI(url)
	}

	internal inner class FrescoControllerListener : BaseControllerListener<ImageInfo>() {
		override fun onFinalImageSet(id: String, imageInfo: ImageInfo?, animatable: Animatable?) {
			if (imageInfo == null) {
				Log.w(TAG, "FrescoControllerListener imageInfo is null")
				return
			}
			listener.invoke(id, imageInfo, animatable)
		}
	}

}