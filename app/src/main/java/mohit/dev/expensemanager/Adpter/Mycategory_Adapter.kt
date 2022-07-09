package mohit.dev.expensemanager.Adpter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mohit.de.Category_DatabaseHelper
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.R
import mohit.dev.expensemanager.View.MainActivity

class Mycategory_Adapter(var context: Context, var cat_Arraylist: MutableList<Category_ModelClass>) :
    RecyclerView.Adapter<Mycategory_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_category, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var mymodel = cat_Arraylist[position]


        // holder.img.setImageResource(mymodel.img)
        holder.title.text = mymodel.userCategory

        var onclick = true

        holder.categorylayout.setOnClickListener {
            if (onclick == true) {
                holder.categorylayout.setBackgroundColor(context.getColor(R.color.purple_200))
                val passdata = Intent(context, MainActivity::class.java)
                passdata.putExtra("categoryname", mymodel.userCategory)
                context.startActivity(passdata)
                onclick = false
            } else {
                holder.categorylayout.setBackgroundColor(context.getColor(R.color.grey))
                onclick = true
            }


        }
        holder.btn_delete.setOnClickListener {
            var dbhelper = Category_DatabaseHelper(context)
            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
            var id_delete = dbhelper.delete(Category_ModelClass(mymodel.userid, ""))
            var k = Intent(context, MainActivity::class.java)
            context.startActivity(k)
        }

    }

    override fun getItemCount(): Int {
        return cat_Arraylist.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        var img = itemView.findViewById<ImageView>(R.id.cat_img)
        var title = itemView.findViewById<TextView>(R.id.cat_title)
        var btn_delete = itemView.findViewById<ImageView>(R.id.btn_delete)
        var categorylayout = itemView.findViewById<LinearLayout>(R.id.categorylayout)

    }
}