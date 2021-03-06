package com.fxc.pics.pic.network

import android.util.Log
import com.fxc.pics.pic.BuildConfig
import com.fxc.pics.pic.network.interceptors.AuthorizationInterceptor
import com.fxc.pics.pic.network.requests.RemoteService
import com.fxc.pics.pic.network.url.U_API_HOST
import com.fxc.pics.pic.network.url.U_RELATED_HOST
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author fxc
 * @date 2018/1/14
 */
internal object RetrofitManager {
	private const val TAG = "RetrofitManager"
	private val RETROFIT_MAP = HashMap<String, Retrofit>()
	private val mLogInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { msg ->
		Log.d(TAG, msg)
	})
	private val mAuthorizationInterceptor = AuthorizationInterceptor()
	private var mClient = OkHttpClient.Builder()
			.build()
	private val mConverterFactory = GsonConverterFactory.create(GsonBuilder().create())
	internal val UNSPLASH_API = "unsplash"
	internal const val UNSPLASH_WEB = "unsplash_web"
	private const val TIME_OUT_FIELD = 60_000L

	init {
		if (BuildConfig.DEBUG) {
			Log.i(TAG, "current is debug")
			createOkHttpClient()
		}
		createRetrofit()
	}

	private fun createOkHttpClient() {
		mLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
		mClient = if (BuildConfig.DEBUG) {
			mClient.newBuilder()
					.addInterceptor(mLogInterceptor)
					.addInterceptor(mAuthorizationInterceptor)
					.connectTimeout(TIME_OUT_FIELD, TimeUnit.SECONDS)
					.readTimeout(TIME_OUT_FIELD, TimeUnit.SECONDS)
					.writeTimeout(TIME_OUT_FIELD, TimeUnit.SECONDS)
					.retryOnConnectionFailure(true)
					.build()
		} else {
			mClient.newBuilder()
					.retryOnConnectionFailure(true)
					.addInterceptor(mAuthorizationInterceptor)
					.connectTimeout(TIME_OUT_FIELD, TimeUnit.SECONDS)
					.readTimeout(TIME_OUT_FIELD, TimeUnit.SECONDS)
					.writeTimeout(TIME_OUT_FIELD, TimeUnit.SECONDS)
					.build()
		}
	}

	private fun createRetrofit() {
		RETROFIT_MAP[UNSPLASH_API] = Retrofit.Builder()
				.baseUrl(U_API_HOST)
				.client(mClient)
				.addConverterFactory(mConverterFactory)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
		RETROFIT_MAP[UNSPLASH_WEB] = Retrofit.Builder()
				.baseUrl(U_RELATED_HOST)
				.client(mClient)
				.addConverterFactory(mConverterFactory)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
	}

	fun getRetrofit(name: String): Retrofit {
		if (!RETROFIT_MAP.containsKey(name)) {
			throw IllegalArgumentException("A illegal retrofit key")
		}
		return RETROFIT_MAP[name]!!
	}

	fun remount(name: String): RemoteService {
		return getRetrofit(name).create(RemoteService::class.java)
	}
}