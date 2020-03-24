package io.chillout.viewpagersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "GALLERY APP"
        val customPageAdapter : CustomPageAdapter = CustomPageAdapter(this)
        view_pager.adapter = customPageAdapter
    }
}
