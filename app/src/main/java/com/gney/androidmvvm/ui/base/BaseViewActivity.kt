package com.gney.androidmvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseViewActivity<T : ViewDataBinding> : AppCompatActivity() {

    /*
        onCreate 에서 레이아웃 리소스를 설정해주는 부분
    */
    abstract val layoutResourceId: Int


    lateinit var viewDataBinding: T
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            setContentView 가 아닌 데이터 바인딩하여 레이아웃 리소스를 설정
        */
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }
}
