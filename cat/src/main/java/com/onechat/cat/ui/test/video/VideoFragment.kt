package com.onechat.cat.ui.test.video

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.SeekBar
import com.benjamin.base.BaseFragment
import com.danikula.videocache.CacheListener
import com.onechat.cat.CatApplication
import com.onechat.cat.R
import kotlinx.android.synthetic.main.fragment_video.*
import java.io.File


class VideoFragment : BaseFragment(), CacheListener {
    override fun getLayoutId(): Int {
        return R.layout.fragment_video
    }

    private val url by lazy { arguments?.getString(KEY_URL) }
//    private val url: String =
//        "https://vd.yinyuetai.com/hc.yinyuetai.com/uploads/videos/common/C89A016A1F017779225598F3E666BC34.mp4"

    private val updater = VideoProgressUpdater()

    override fun initView() {
        super.initView()
//        val mediaController = MediaController(this.context)
//        videoView.setMediaController(mediaController)
        videoView.setOnCompletionListener { mp ->
            mp.start()
            mp.isLooping = true
        }
//        videoView.setOnCompletionListener { startVideo() }
        videoView.setOnPreparedListener { progressBar.max = videoView.duration }
        progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//        val videoPosition = videoView.getDuration() * progressBar.getProgress() / 100
//        videoView.seekTo(videoPosition)
                videoView.seekTo(progressBar.progress)
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

        })
        cacheStatusImageView.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            } else {
                videoView.start()
            }
        }
    }

    override fun initData() {
        super.initData()
        checkCachedState()
        startVideo()
    }

    private fun checkCachedState() {
        val proxy = CatApplication.getProxy(activity!!)
        val fullyCached = proxy.isCached(url)
        setCachedState(fullyCached)
        if (fullyCached) {
            progressBar.secondaryProgress = progressBar.max
        }
    }

    private fun startVideo() {
        val proxy = CatApplication.getProxy(activity!!)
        proxy.registerCacheListener(this, url)
        val proxyUrl = proxy.getProxyUrl(url)
        videoView.setVideoPath(proxyUrl)
        videoView.start()
        Log.d(LOG_TAG, "Use proxy url $proxyUrl instead of original url $url")
    }

    override fun onResume() {
        super.onResume()
        updater.start()
        if (!videoView.isPlaying) videoView.resume()
    }


    override fun onPause() {
        super.onPause()
        updater.stop()
        videoView.pause()
    }

    override fun onDestroyView() {
        if (videoView.isPlaying) videoView.stopPlayback()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        CatApplication.getProxy(activity!!).unregisterCacheListener(this)
    }

    override fun onCacheAvailable(file: File, url: String, percentsAvailable: Int) {
        progressBar.secondaryProgress = percentsAvailable * videoView.duration
        setCachedState(percentsAvailable == 100)
        Log.d(LOG_TAG, String.format("onCacheAvailable. percents: %d, file: %s, url: %s", percentsAvailable, file, url))
    }

    private fun setCachedState(cached: Boolean) {
        val statusIconId = if (cached) R.drawable.ic_cloud_done else R.drawable.ic_cloud_download
        cacheStatusImageView.setImageResource(statusIconId)
    }

    private inner class VideoProgressUpdater : Handler() {

        fun start() {
            sendEmptyMessage(0)
        }

        fun stop() {
            removeMessages(0)
        }

        override fun handleMessage(msg: Message) {
//        val videoProgress = videoView.getCurrentPosition() * 100 / videoView.getDuration()
//        progressBar.setProgress(videoProgress)
            progressBar.progress = videoView.currentPosition
            sendEmptyMessageDelayed(0, 500)
        }
    }

    companion object {
        private const val LOG_TAG = "VideoFragment"
        private const val KEY_URL = "key_url"

        fun newInstance(url: String): VideoFragment {
            return VideoFragment()
                .apply { arguments = Bundle().apply { putString(KEY_URL, url) } }
        }

    }
}