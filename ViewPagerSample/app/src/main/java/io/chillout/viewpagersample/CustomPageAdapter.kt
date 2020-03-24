package io.chillout.viewpagersample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class CustomPageAdapter(context:Context) : PagerAdapter(){
    private val mContext = context
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view
    }

    override fun getCount(): Int {
        return 6
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.view_pager_item,null)
        val Image = view.findViewById<ImageView>(R.id.image_adapter)
        Image.setImageDrawable(mContext.resources.getDrawable(getImageAt(position),null))
        container.addView(view)
        return view!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object`as View)
    }
    fun getImageAt(position: Int) : Int{
        when(position){
            1 -> return R.drawable.image_1
            2 -> return R.drawable.image_2
            3 -> return R.drawable.image_3
            4 -> return R.drawable.image_4
            5 -> return R.drawable.image_5
            6 -> return R.drawable.image_6
            else -> return R.drawable.image_d
        }
    }
}