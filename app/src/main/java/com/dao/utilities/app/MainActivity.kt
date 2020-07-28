package com.dao.utilities.app

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initializeView() {
        buttonRecycler.setOnClickListener {
            navigationTo(RecyclerActivity::class)
        }
    }
}