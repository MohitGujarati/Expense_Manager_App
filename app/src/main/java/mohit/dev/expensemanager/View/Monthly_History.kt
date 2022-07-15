package mohit.dev.expensemanager.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        var sp_history = findViewById<Spinner>(R.id.sp_history)
        var iv_arrow = findViewById<ImageView>(R.id.iv_arrow)
        var tv_monthbudget = findViewById<TextView>(R.id.tv_budget)

        var rechistory = findViewById<RecyclerView>(R.id.rec_history)

        var tv_HistoryExpence = findViewById<TextView>(R.id.tv_HistoryExpence)
        var tv_historyLeftcash = findViewById<TextView>(R.id.tv_historyLeftcash)


        var history_prgarray= ArrayList<Int>()

        var monthpos = 0
        sp_history.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                monthpos = position
                setmonth(position, tv_monthbudget)

                //  amountleft(txt_budget, totalamount, tv_Leftcash)

                Log.d("spinnerdata", "${"$position =" + sp_history.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


        var onclick = true

        iv_arrow.setOnClickListener {
            if (onclick == true) {
                iv_arrow.setImageResource(R.drawable.ic_arrow_up)
                loadamount(tv_HistoryExpence,monthpos,history_prgarray,tv_monthbudget)
                rechistory.visibility = View.VISIBLE
                loadhistrory(monthpos, rechistory,history_prgarray)
                if (tv_monthbudget.text != null && tv_HistoryExpence.text != null) {
                    amountleft(tv_monthbudget, tv_HistoryExpence, tv_historyLeftcash)
                } else {
                    Toast.makeText(this, "setting values to zero", Toast.LENGTH_SHORT).show()
                    tv_monthbudget.text == "0" && tv_HistoryExpence.text == "0" && tv_historyLeftcash.text == "0"
                }

                onclick = false
            } else if (onclick == false) {
                iv_arrow.setImageResource(R.drawable.ic_arrow_down)
                rechistory.visibility = View.GONE
                tv_HistoryExpence.text="0"
                tv_historyLeftcash.text="0"
                onclick = true
            }

        }
    }

    private fun loadamount(
        totalamount: TextView,
        monthpos: Int,
        history_prgarray: ArrayList<Int>,
        tv_monthbudget: TextView
    ) {
        var db_helper = note_database(this)


        var amountlist: MutableList<Int>
        amountlist = db_helper.getfilteramount(monthpos)

        var sum = 0
        for (i in 0..amountlist.size - 1) {

            var amountlistdata = sum + amountlist[i]
            sum = amountlistdata
        }
        totalamount.text = sum.toString()
        Log.d("amount_list_data", "$amountlist, sum=$sum")


        var prg=0
        for (i in 0..amountlist.size-1){
            prg =(amountlist.get(i) * 100/sum).toInt()
            Log.d("progress","$prg")
            // Log.d("progressarray","$prg")
            history_prgarray.add(prg)
            Log.d("progressarray","$history_prgarray")
        }

    }

    private fun amountleft(
        txt_budget: TextView,
        totalamount: TextView,
        tv_Leftcash: TextView,
    ) {
        var intbug = Integer.valueOf(txt_budget.text.toString())
        var inttotal = Integer.valueOf(totalamount.text.toString())

        var intleft = (intbug - inttotal)
        tv_Leftcash.setTextColor(ContextCompat.getColor(this, R.color.green));
        tv_Leftcash.text = (intbug - inttotal).toString()

        if (intleft <= 0) {
            tv_Leftcash.text = (intbug - inttotal).toString()
            tv_Leftcash.setTextColor(ContextCompat.getColor(this, R.color.red));
        }


    }

    private fun loadhistrory(
        history_month: Int,
        rechistory: RecyclerView,
        history_prgarray: ArrayList<Int>
    ) {
        rechistory.layoutManager = LinearLayoutManager(this)

        var db_helper = note_database(this)

        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getmonth(history_month)

        var connect_Adapter = Mynotes_Adapter(this, userlist,history_prgarray)
        rechistory.adapter = connect_Adapter

        Log.d("history_pg","$history_prgarray")

    }

    private fun setmonth(month: Int, txt_budget: TextView) {

        var budget_arraylist = arrayListOf<Int>()
        budget_arraylist.add(0, 0)
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

    override fun onBackPressed() {
        var i = Intent(this,User_Notes::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        overridePendingTransition(0,0)
        finish()
        startActivity(i)

    }
}