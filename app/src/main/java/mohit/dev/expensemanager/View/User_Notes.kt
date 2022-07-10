package mohit.dev.expensemanager.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mohit.dev.expensemanager.Adpter.Mynotes_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R
import java.util.*

class User_Notes : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notes)

        var Todaydate = Calendar.getInstance()
        var year = Todaydate.get(Calendar.YEAR)
        var month = Todaydate.get(Calendar.MONTH)
        var day = Todaydate.get(Calendar.DAY_OF_MONTH)


        var currentmonth = findViewById<TextView>(R.id.tv_curmonth)
        var totalamount = findViewById<TextView>(R.id.tv_totalExpence)
        var btn_addnotes = findViewById<ExtendedFloatingActionButton>(R.id.btn_addnotes)
        var rec_notes = findViewById<RecyclerView>(R.id.rec_savednotes)
        var tv_Leftcash = findViewById<TextView>(R.id.tv_Leftcash)
        var txt_budget = findViewById<TextView>(R.id.txt_budget)






        setmonth(month, currentmonth, txt_budget)

        loadrecview(rec_notes)

        loadamount(totalamount)



        if (txt_budget.text != null && totalamount.text != null) {
            amountleft(txt_budget, totalamount, tv_Leftcash)
        } else {
            txt_budget.text == "0" && totalamount.text == "0" && tv_Leftcash.text == "0"
        }



        btn_addnotes.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


    }

    private fun loadamount(totalamount: TextView) {
        var db_helper = note_database(this)

        var amountlist: MutableList<Int>
        amountlist = db_helper.getamount()

        var sum=0
        for (i in 0..amountlist.size-1){

            var amountlistdata=sum+amountlist[i]
            sum=amountlistdata
        }
        totalamount.text=sum.toString()
        Log.d("amount_list_data","$amountlist, sum=$sum")

    }

    private fun amountleft(txt_budget: TextView,totalamount: TextView, tv_Leftcash: TextView) {
        var intbug = Integer.valueOf(txt_budget.text.toString())
        var inttotal = Integer.valueOf(totalamount.text.toString())

        tv_Leftcash.text=(intbug - inttotal).toString()+"/-left"


    }

    private fun loadrecview(rec_notes: RecyclerView) {
        rec_notes.layoutManager = LinearLayoutManager(this)

        var db_helper = note_database(this)

        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getall_Note()

        var connect_Adapter = Mynotes_Adapter(this, userlist)
        rec_notes.adapter = connect_Adapter

    }

    private fun setmonth(month: Int, currentmonth: TextView, txt_budget: TextView) {

        var budget_arraylist = arrayListOf<Int>()

        when (month) {
            0 -> {
                currentmonth.text = "Jan"
            }
            1 -> currentmonth.text = "feb"
            2 -> currentmonth.text = "Mar"
            3 -> currentmonth.text = "April"
            4 -> currentmonth.text = "May"
            5 -> currentmonth.text = "Jun"
            6 -> {
                currentmonth.text = "July"
                budget_arraylist.add(0, 3000)
                txt_budget.text = budget_arraylist.get(0).toString()
            }
            7 -> currentmonth.text = "Aug"
            8 -> currentmonth.text = "sep"
            9 -> currentmonth.text = "oct"
            10 -> currentmonth.text = "nov"
            11 -> currentmonth.text = "Dec"
        }
    }

}