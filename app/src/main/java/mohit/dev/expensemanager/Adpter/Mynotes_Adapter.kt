package mohit.dev.expensemanager.Adpter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.Model.userModel
import mohit.dev.expensemanager.R

class Mynotes_Adapter(var context: Context, var Notes_Arraylist: MutableList<Notes_ModelClass>) :
    RecyclerView.Adapter<Mynotes_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_notes, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var mymodel = Notes_Arraylist[position]

        holder.tvamount.text = mymodel.user_amount
        holder.tvcategory.text = mymodel.user_category
        holder.tvdate.text = mymodel.user_date
        holder.tvnote.text = mymodel.user_note




    }

    override fun getItemCount(): Int {
        return Notes_Arraylist.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        var tvamount = itemView.findViewById<TextView>(R.id.tv_amount)
        var tvcategory = itemView.findViewById<TextView>(R.id.tv_cat)
        var tvdate = itemView.findViewById<TextView>(R.id.tv_date)
        var tvnote = itemView.findViewById<TextView>(R.id.tv_note)
        var pb_bar = itemView.findViewById<ProgressBar>(R.id.pbar)


    }
}