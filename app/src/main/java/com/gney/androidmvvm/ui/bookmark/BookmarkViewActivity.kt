package com.gney.androidmvvm.ui.bookmark

import android.os.Bundle
import com.gney.androidmvvm.R
import com.gney.androidmvvm.databinding.ActivityBookmarkBinding
import com.gney.androidmvvm.ui.base.BaseViewActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BookmarkViewActivity: BaseViewActivity<ActivityBookmarkBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_bookmark


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this
    }
}