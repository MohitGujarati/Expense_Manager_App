package mohit.dev.expensemanager.Adpter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.R
import mohit.dev.expensemanager.View.User_Notes

class MyFrag_category_Adapter(
    var context: Context,
    var Fragcat_Arraylist: MutableList<Category_ModelClass>
) :
    RecyclerView.Adapter<MyFrag_category_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_frag_chip, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var mymodel = Fragcat_Arraylist[position]
        holder.frg_chip.text = mymodel.userCategory


        holder.frg_chip.setOnClickListener {
            Toast.makeText(context, "${mymodel.userCategory}", Toast.LENGTH_SHORT).show()
            var i= Intent(context,User_Notes::class.java)
            i.putExtra("chiptext","${mymodel.userCategory}")
            context.startActivity(i)
        }


    }


    override fun getItemCount(): Int {
        return Fragcat_Arraylist.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        var frg_chip = itemView.findViewById<Chip>(R.id.frg_chip)


    }
}