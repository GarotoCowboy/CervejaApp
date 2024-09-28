package br.com.pedro.cervejaapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var spinnerMain:Spinner
    lateinit var scrollCerveja:ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerMain = findViewById(R.id.spinner_main)
        scrollCerveja = findViewById(R.id.view_cervejas)

        val beerSize = resources.getStringArray(R.array.BeerSize)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,beerSize)

        spinnerMain.adapter = adapter

        spinnerMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                Toast.makeText(this@MainActivity,"Selected Text = "+beerSize[position],Toast.LENGTH_LONG).show()
                    val selectedBeer = spinnerMain.selectedItem.toString()
                updateScrollView(selectedBeer)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                Toast.makeText(this@MainActivity,"Text not selected = " ,Toast.LENGTH_LONG).show()
            }

        }
    }

    fun updateScrollView(beerType:String){
        var linearLayoutBeerList = findViewById<LinearLayout>(R.id.linerLayoutList)
        linearLayoutBeerList.removeAllViews()

        var beerList = when(beerType.toLowerCase()){
            "larger" -> listOf("Heineken","Corona","Budweiser")
            "ipa" -> listOf("Goose Ipa","Dogfish Head","Lagunitas Ipa")
            "amber" -> listOf("Fat Tire Amber Ale","Alaskan Amber","Dos Equis Amber")
            "pilsen"-> listOf("Pilsen Urquell","Stella Artois","Beck's")
            "bock" -> listOf("Shiner Bock","Paulaner Salvator","Eisenbahn Dunkell")
            "weiss" -> listOf("Weihenstephaner Hefeweissbier","Franziskaner Weissbier", "Paulaner Weissbier")
            else -> emptyList()
        }

        for(beer in beerList){
            val textView = TextView(this)
            textView.text = beer
            linearLayoutBeerList.addView(textView)
        }

    }
}