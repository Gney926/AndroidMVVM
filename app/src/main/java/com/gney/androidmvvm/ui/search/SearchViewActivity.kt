package com.gney.androidmvvm.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gney.androidmvvm.R
import com.gney.androidmvvm.databinding.ActivitySearchBinding
import com.gney.androidmvvm.ui.base.BaseViewActivity
import com.gney.androidmvvm.ui.bookmark.BookmarkViewActivity
import com.gney.androidmvvm.util.Status
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchViewActivity : BaseViewActivity<ActivitySearchBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = getViewModel()
        viewDataBinding.lifecycleOwner = this

        observeEvent()
    }


    private fun observeEvent() {
        viewDataBinding.vm?.observableEvent?.observe(this, Observer {
            if (it == Status.SAVE) {
                Toast.makeText(this, "북마크에 저장되었습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.bookmark -> {
                startActivity(Intent(this, BookmarkViewActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
