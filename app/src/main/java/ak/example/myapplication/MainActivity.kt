package ak.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import android.util.*
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dateselected: TextView?=null
    private var totalminutes: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonpick: Button = findViewById(R.id.buttonpick)
        dateselected= findViewById(R.id.selecteddate)
        totalminutes=findViewById(R.id.displayminutes)
        buttonpick.setOnClickListener {
            clickDatePicker()
        }
    }


    private fun clickDatePicker() {
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selectedday ->
                Toast.makeText(this,"Datepicker Works",Toast.LENGTH_LONG).show()

                val selecteddate="$selectedday/${selectedmonth+1}/$selectedyear"
                dateselected?.text=selecteddate
                val cal=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val dat=cal.parse(selecteddate)
                val selectminutes=dat.time/60000
                val currentdate=cal.parse(cal.format(System.currentTimeMillis()))
                val currentdateinminutes=currentdate.time/60000
                val diffminutes=currentdateinminutes-selectminutes
                totalminutes?.text= diffminutes.toString()

            },
            year,
            month,
            day
        ).show()
    }
}