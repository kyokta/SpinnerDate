package com.example.app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import com.example.app.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kota = resources.getStringArray(R.array.kota)

        with(binding) {
            // Spinner keberangkatan dan tujuan
            val cityAdapter = ArrayAdapter(this@MainActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, kota)

            cityAdapter.setDropDownViewResource(
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

            txtKeberangkatan.adapter = cityAdapter
            txtTujuan.adapter = cityAdapter

            // Tanggal
            btnTanggal.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(this@MainActivity, DatePickerDialog.OnDateSetListener {
                        _, year, monthOfYear, dayOfMonth -> txtTanggal.setText("" + day + "/" + month + "/" + year)
                }, year, month, day)
                dpd.show()
            }

            // Waktu
            btnWaktu.setOnClickListener {
                val mTimePicker: TimePickerDialog
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
                val minute = mcurrentTime.get(Calendar.MINUTE)

                mTimePicker = TimePickerDialog(this@MainActivity, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        txtWaktu.setText(String.format("%d.%d", hourOfDay, minute))
                    }
                }, hour, minute, false)
                mTimePicker.show()
            }

            // Pemesanan
            btnPesan.setOnClickListener {
                val keberangkatan = txtKeberangkatan.selectedItem.toString()
                val tujuan = txtTujuan.selectedItem.toString()
                val tanggal = txtTanggal.text
                val waktu = txtWaktu.text

                Toast.makeText(this@MainActivity,
                    "Tiket " + keberangkatan + " > " + tujuan + " (" + tanggal + " : " + waktu + ") berhasil dibuat!",
                    Toast.LENGTH_SHORT).show()}
        }
    }
}