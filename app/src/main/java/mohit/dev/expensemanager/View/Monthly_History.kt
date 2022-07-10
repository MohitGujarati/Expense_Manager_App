package mohit.dev.expensemanager.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.expensemanager.Adpter.Mynotes_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R

class Monthly_History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_history)

        var tv_month=findViewById<TextView>(R.id.tv_month)
        var tv_monthlybudget=findViewById<TextView>(R.id.tv_monthlybudget)
        var tv_amountspend=findViewById<TextView>(R.id.tv_amountspend)
        var iv_arrow=findViewById<ImageView>(R.id.iv_arrow)
        var rechistory=findViewById<RecyclerView>(R.id.rec_history)

        var onclick=true

        iv_arrow.setOnClickListener {

        if (onclick==true){
            iv_arrow.setImageResource(R.drawable.ic_arrow_up)
            rechistory.visibility=View.VISIBLE
            loadhistrory(rechistory)

            onclick=false
        }else if(onclick==false){
            iv_arrow.setImageResource(R.drawable.ic_arrow_down)
            rechistory.visibility=View.GONE
            onclick=true
        }

        }
    }

    private fun loadhistrory(rechistory: RecyclerView) {
        rechistory.layoutManager = LinearLayoutManager(this)

        var db_helper = note_database(this)

        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getall_Note()

        var connect_Adapter = Mynotes_Adapter(this, userlist)
        rechistory.adapter = connect_Adapter

    }
}