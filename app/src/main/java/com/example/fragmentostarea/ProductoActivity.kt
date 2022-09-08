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

    fun regresar(view: View?){

        // vamos a usar un intent para almacenar datos
        // OJO - aquí usamos el constructor vacío
        val intent = Intent()

        // ponemos valores en intent
        /*
        intent.putExtra("resultadoNombre", entrada.text.toString())
        intent.putExtra("resultadoCalificacion", 80)

        // muy importante si estamos escuchando el resultado
        setResult(Activity.RESULT_OK, intent)
        */

        // con esto se termina ejecución de esta actividad
        finish()

        // IMPORTANTE: NO hay que crear la actividad anterior de vuelta
    }
}