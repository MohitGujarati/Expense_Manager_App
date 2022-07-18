package mohit.dev.expensemanager.View

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
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
        var btn_addbudget=findViewById<ExtendedFloatingActionButton>(R.id.btn_addbudget)


        var history_prgarray= ArrayList<Int>()
        var budget_amount=ArrayList<Int>()

        var monthpos = 0


        sp_history.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                monthpos = position
                if (budget_amount.isEmpty()==false){
                    setmonth(position, tv_monthbudget,budget_amount)
                }else{
                    for (i in 0..15){
                        budget_amount.add(i,10)
                    }
                }


                //  amountleft(txt_budget, totalamount, tv_Leftcash)

                Log.d("spinnerdata", "${"$position =" + sp_history.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        btn_addbudget.setOnClickListener {
            var d = Dialog(this)
            d.setContentView(R.layout.dialog_budget)
            d.window?.setBackgroundDrawable(ColorDrawable(0))
            d.setCancelable(true)

            var amt_jan=d.findViewById<EditText>(R.id.amt_jan)
            var amt_feb=d.findViewById<EditText>(R.id.amt_feb)
            var amt_march=d.findViewById<EditText>(R.id.amt_march)
            var amt_april=d.findViewById<EditText>(R.id.amt_april)
            var amt_may=d.findViewById<EditText>(R.id.amt_may)
            var amt_june=d.findViewById<EditText>(R.id.amt_jane)
            var amt_july=d.findViewById<EditText>(R.id.amt_july)
            var amt_aug=d.findViewById<EditText>(R.id.amt_aug)
            var amt_sep=d.findViewById<EditText>(R.id.amt_sep)
            var amt_oct=d.findViewById<EditText>(R.id.amt_oct)
            var amt_nov=d.findViewById<EditText>(R.id.amt_nov)
            var amt_dec=d.findViewById<EditText>(R.id.amt_dec)

            var btn_save=d.findViewById<Button>(R.id.btn_save)

            btn_save.setOnClickListener {

                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
                budget_amount.add(0,Integer.valueOf(amt_jan.text.toString()))
                budget_amount.add(1,Integer.valueOf(amt_feb.text.toString()))
                budget_amount.add(2,Integer.valueOf(amt_march.text.toString()))
                budget_amount.add(3,Integer.valueOf(amt_april.text.toString()))
                budget_amount.add(4,Integer.valueOf(amt_may.text.toString()))
                budget_amount.add(5,Integer.valueOf(amt_june.text.toString()))
                budget_amount.add(6,Integer.valueOf(amt_july.text.toString()))
                budget_amount.add(7,Integer.valueOf(amt_aug.text.toString()))
                budget_amount.add(8,Integer.valueOf(amt_sep.text.toString()))
                budget_amount.add(9,Integer.valueOf(amt_oct.text.toString()))
                budget_amount.add(10,Integer.valueOf(amt_nov.text.toString()))
                budget_amount.add(11,Integer.valueOf(amt_dec.text.toString()))

                for (i in 0 until budget_amount.size) {
                    Log.d("budarr", "${budget_amount[i]}")
                }



                d.dismiss()

            }

                d.show()
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

    private fun setmonth(month: Int, txt_budget: TextView, budget_amount: ArrayList<Int>) {

        var budget_arraylist = arrayListOf<Int>()


        for (i in 0 until budget_amount.size){

            budget_arraylist.add(i,budget_amount[i])
        }

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