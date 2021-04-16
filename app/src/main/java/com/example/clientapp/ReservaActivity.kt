package com.example.clientapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import java.util.*


class ReservaActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var btn_timePicker: Button
    private lateinit var btn_horaPicker: Button

    private lateinit var et_date: EditText
    private lateinit var et_time:EditText
    var dia = 0
    var mes = 0
    var anio = 0
    var hora = 0
    var minutos = 0

    var guardarDia = 0
    var guardarMes = 0
    var guardaranio = 0
    var guardarHora = 0
    var guardarMinutos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        btn_timePicker = findViewById<Button>(R.id.btn_fecha)
        btn_horaPicker = findViewById<Button>(R.id.btn_hora)
        et_date = findViewById<EditText>(R.id.date)
        et_time =findViewById<EditText>(R.id.time)
        pickDate();
    }



    private fun getDateCalendar(){
        val cal: Calendar = Calendar.getInstance()
        dia = cal.get(Calendar.DAY_OF_MONTH)
        mes = cal.get(Calendar.MONTH)
        anio = cal.get(Calendar.YEAR)
    }

    private fun getDateTimer(){
        val cal: Calendar = Calendar.getInstance()
        hora = cal.get(Calendar.HOUR)
        minutos = cal.get(Calendar.MINUTE)
    }



    private fun pickDate(){
        btn_timePicker.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(this,R.style.date_picker,this,anio,mes,dia).show()
        }
        btn_horaPicker.setOnClickListener{
            getDateTimer()
            TimePickerDialog(this,R.style.TimePicker, this,hora,minutos,true).show();
        }


    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        guardarDia = dayOfMonth
        guardarMes =month
        guardaranio = year
        getDateCalendar()
        et_date.setText("$guardarDia/$guardarMes/$guardaranio")
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        guardarHora = hourOfDay
        guardarMinutos = minute
        getDateTimer()
        et_time.setText("$guardarHora:$guardarMinutos")
    }


}