package mohit.dev.expensemanager.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import mohit.dev.expensemanager.R

class loadcategorydata : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_loadcategorydata, container, false)
        var rec_cat = v.findViewById<RecyclerView>(R.id.frag_rec_cat)

        var chipCpp = v.findViewById<Chip>(R.id.chipCpp);
        var chipJava = v.findViewById<Chip>(R.id.chipJava);
        var chipPython = v.findViewById<Chip>(R.id.chipPython);


        chipCpp.setOnClickListener {
            // check whether the chips is filtered by user
            // or not and give the suitable Toast message
            if (chipCpp.isChecked) {
                Toast.makeText(context, "C++ selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "C++ deselected", Toast.LENGTH_SHORT).show()
            }
        }
        chipJava.setOnClickListener {
            // check whether the chips is filtered by user
            // or not and give the suitable Toast message
            if (chipCpp.isChecked) {
                Toast.makeText(context, "C++ selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "C++ deselected", Toast.LENGTH_SHORT).show()
            }
        }
        chipPython.setOnClickListener {
            // check whether the chips is filtered by user
            // or not and give the suitable Toast message
            if (chipCpp.isChecked) {
                Toast.makeText(context, "C++ selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "C++ deselected", Toast.LENGTH_SHORT).show()
            }
        }





        return v
    }

    private fun loadfrag_cat(recCat: RecyclerView, v: View, chiptxt: String) {

    }



}