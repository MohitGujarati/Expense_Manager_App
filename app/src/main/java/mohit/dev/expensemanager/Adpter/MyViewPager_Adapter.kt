package mohit.dev.expensemanager.Adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import mohit.dev.expensemanager.R

class MyViewPager_Adapter(var contex: Context) : PagerAdapter() {

    var layoutInflater: LayoutInflater? = null


    val imgArray = arrayOf(
        R.drawable.onbording1,
        R.drawable.onboarding2,
        R.drawable.onboarding3
    )

    val headArray = arrayOf(
        "TITLE 1", "TITLE 2", "TITLE 3"
    )
    val desArray = arrayOf(
        "1 simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took ",
        "2 simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took ",
        "3 simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took "
    )


    override fun getCount(): Int {

        return headArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view ==`object`as RelativeLayout

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater=contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view= layoutInflater!!.inflate(R.layout.introslider,container,false)

        val img=view.findViewById<ImageView>(R.id.iv_Onbording)
        val txt_head=view.findViewById<TextView>(R.id.tv_Onbording)
        val txt_des=view.findViewById<TextView>(R.id.tv_Onbording_desc)

        img.setImageResource(imgArray[position])
        txt_head.text=(headArray[position])
        txt_des.text=(desArray[position])

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}