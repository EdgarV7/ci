package com.example.fragmentostarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ProductoActivity : AppCompatActivity() {

    private lateinit var nombreView : TextView
    private lateinit var precioView : TextView
    private lateinit var cantidadView : TextView
    private lateinit var descripcionView : TextView
    private lateinit var colorView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        // prueba de datos enviados
        //Toast.makeText(this, intent.getStringExtra("nombre"), Toast.LENGTH_SHORT).show()
        nombreView = findViewById(R.id.textViewNombre)
        precioView = findViewById(R.id.textViewPrecio)
        cantidadView = findViewById(R.id.textViewCantidad)
        descripcionView = findViewById(R.id.textViewDescripcion)
        colorView = findViewById(R.id.textViewColor)

        nombreView.text = intent.getStringExtra("nombre")
        precioView.text = intent.getStringExtra("precio")
        cantidadView.text = intent.getStringExtra("cantidad")
        descripcionView.text = intent.getStringExtra("descripcion")
        colorView.text = intent.getStringExtra("color")
    }
}