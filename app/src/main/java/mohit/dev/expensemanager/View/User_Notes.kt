package mohit.dev.expensemanager.View

import android.content.Intent
import android.os.Bundle
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




        setmonth(month,currentmonth,txt_budget)

        loadrecview(rec_notes)

        btn_addnotes.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


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
            0 -> {currentmonth.text = "Jan"}
            1 -> currentmonth.text = "feb"
            2 -> currentmonth.text = "Mar"
            3 -> currentmonth.text = "April"
            4 -> currentmonth.text = "May"
            5 -> currentmonth.text = "Jun"
            6 -> {
                currentmonth.text = "July"
                budget_arraylist.add(0, 3000)
                txt_budget.text = budget_arraylist.get(0).toString() + "/-"
            }
            7 -> currentmonth.text = "Aug"
            8 -> currentmonth.text = "sep"
            9 -> currentmonth.text = "oct"
            10 -> currentmonth.text = "nov"
            11 -> currentmonth.text = "Dec"
        }
    }


    /*
    private fun amount(v: Int, tv_totalExpence: TextView) {

        //storedata in arraylist
        var v = getIntent().getIntExtra("passed", 0)

        if (v != 0) {




/*
        var amountlist = ArrayList<Int>()

        var amount=0
        var count=v
        var main=+count


   /*
        for (i in 0..amountlist.size-1){

            var fc=amountlist[i]+count
            Log.d("finaltxt"," $fc")
            tv_totalExpence.text=fc.toString()
        }

    */
        var size=amountlist.size-1

        var countfinal=count
        var final=countfinal+amountlist[size]

        amountlist.add(0)
        amountlist.add(count)
        tv_totalExpence.text=final.toString()

 */


        var array = ArrayList<Int>()

        array.add(v)

        var data = 0

        var size=array.size-1


        Log.d("Myfinallist", "${array}")
        var finaldata =+ array[size]
        Log.d("Myfinallist", "${finaldata} passed data is $v")

        var finallist=ArrayList<Int>()

        finallist.add(finaldata)

        var finalsize=finallist.size-1

        var txt=finallist[finalsize]+ v

        Log.d("finallist2", "${txt} passed data is $v")


        }
    }

     */

}