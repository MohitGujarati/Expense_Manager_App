package mohit.dev.expensemanager.Adpter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R
import mohit.dev.expensemanager.View.User_Notes

class Mycategory_notes_Adapter(
    var context: Context,
    var Notes_Arraylist: MutableList<Notes_ModelClass>,
    progressArray: ArrayList<Int>
) : RecyclerView.Adapter<Mycategory_notes_Adapter.ViewHolder>() {

    var progress_persentage = progressArray

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_category_notes, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var mymodel = Notes_Arraylist[position]
        holder.cate_tvamount.text = mymodel.user_amount.toString()
        holder.cate_tvcategory.text = mymodel.user_category
        holder.cate_tvdate.text = mymodel.user_date
        holder.cate_tvnote.text = mymodel.user_note




        holder.cate_progress_horizontal.setProgress(progress_persentage[position])

        Log.d("bar_pg", "${progress_persentage}")


            val rnd = java.util.Random()
            val color: Int = Color.argb(200, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            holder.cate_imageview.setBackgroundColor(color)

            holder.cate_iv_btndelete.setOnClickListener {
                var dbhelper = note_database(context)
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
                var id_delete =
                    dbhelper.note_delete(Notes_ModelClass(mymodel.noteid, 0, "", "", "", 0))
                var i = Intent(context, User_Notes::class.java)
                context.startActivity(i)
            }


        }

        override fun getItemCount(): Int {
            return Notes_Arraylist.size
        }

        class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            var cate_tvamount = itemView.findViewById<TextView>(R.id.cate_tv_amount)
            var cate_tvcategory = itemView.findViewById<TextView>(R.id.cate_tv_cat)
            var cate_tvdate = itemView.findViewById<TextView>(R.id.cate_tv_date)
            var cate_tvnote = itemView.findViewById<TextView>(R.id.cate_tv_note)
            var cate_iv_btndelete = itemView.findViewById<ImageView>(R.id.cate_iv_btndelete)
            var cate_imageview = itemView.findViewById<ImageView>(R.id.cate_imageview)
            var cate_progress_horizontal = itemView.findViewById<ProgressBar>(R.id.cate_progress_horizontal)

    }
}