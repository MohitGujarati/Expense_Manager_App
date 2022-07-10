package mohit.dev.expensemanager.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
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

        var sp_history=findViewById<Spinner>(R.id.sp_history)
        var iv_arrow=findViewById<ImageView>(R.id.iv_arrow)
        var tv_amountspend=findViewById<TextView>(R.id.tv_amountspend)

        var rechistory=findViewById<RecyclerView>(R.id.rec_history)



        var monthpos=0
        sp_history.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                monthpos=position


                setmonth(position, tv_amountspend)

              //  amountleft(txt_budget, totalamount, tv_Leftcash)

                Log.d("spinnerdata","${"$position =" + sp_history.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


        var onclick=true

        iv_arrow.setOnClickListener {
        if (onclick==true){
            iv_arrow.setImageResource(R.drawable.ic_arrow_up)
            rechistory.visibility=View.VISIBLE
            loadhistrory(monthpos,rechistory)

            onclick=false
        }else if(onclick==false){
            iv_arrow.setImageResource(R.drawable.ic_arrow_down)
            rechistory.visibility=View.GONE
            onclick=true
        }

        }
    }

    private fun loadhistrory(history_month: Int,rechistory: RecyclerView) {
        rechistory.layoutManager = LinearLayoutManager(this)

        var db_helper = note_database(this)


        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getmonth(history_month)

        var connect_Adapter = Mynotes_Adapter(this, userlist)
        rechistory.adapter = connect_Adapter

    }

    private fun setmonth(month: Int, txt_budget: TextView) {

        var budget_arraylist = arrayListOf<Int>()
        budget_arraylist.add(0, 10000)
        budget_arraylist.add(1, 10000)
        budget_arraylist.add(2, 20000)
        budget_arraylist.add(3, 30000)
        budget_arraylist.add(4, 40000)
        budget_arraylist.add(5, 50000)
        budget_arraylist.add(6, 60000)
        budget_arraylist.add(7, 70000)
        budget_arraylist.add(8, 80000)
        budget_arraylist.add(9, 90000)
        budget_arraylist.add(10, 100000)
        budget_arraylist.add(11, 110000)

        when (month) {
            0 -> {

                txt_budget.text = budget_arraylist.get(0).toString()
            }
            1 -> {

                txt_budget.text = budget_arraylist.get(1).toString()
            }
            2 -> {

                txt_budget.text = budget_arraylist.get(2).toString()
            }
            3 -> {

                txt_budget.text = budget_arraylist.get(3).toString()
            }
            4 -> {

                txt_budget.text = budget_arraylist.get(4).toString()
            }
            5 -> {

                txt_budget.text = budget_arraylist.get(5).toString()
            }
            6 -> {


                txt_budget.text = budget_arraylist.get(6).toString()
            }
            7 -> {

                txt_budget.text = budget_arraylist.get(7).toString()
            }
            8 -> {

                txt_budget.text = budget_arraylist.get(8).toString()
            }
            9 -> {

                txt_budget.text = budget_arraylist.get(9).toString()
            }
            10 -> {

                txt_budget.text = budget_arraylist.get(10).toString()
            }
            11 -> {

                txt_budget.text = budget_arraylist.get(11).toString()
            }
        }


    }
}