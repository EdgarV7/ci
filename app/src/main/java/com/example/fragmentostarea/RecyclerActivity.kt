package com.example.fragmentostarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var datos : ArrayList<ArrayList<String>>
    private val TAG = "request"
    private lateinit var queue : RequestQueue
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        //el recylcer es un widger que sirve para desplegar
        //muchos elementos con interfaz identica pero con tipos distintos

        //hagamos el queue
        queue = Volley.newRequestQueue(this)
    }

    override fun onStop() {
        super.onStop()

        //si hacemos stop lo ideal es detener la queue
        queue.cancelAll(TAG)
    }

    //NOTA DE ESTE MÉTODO:
    // el argumento que recibimos es el widget que mando llamar o este método
    override fun onClick(row: View) {
        //tenemos un sólo escucha para todos los rows!
        //podemos obtener la ubicación de una row referencia a la vista
        val position = recyclerView.getChildLayoutPosition(row)
        Toast.makeText(this, datos[position][0], Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ProductoActivity::class.java)

        intent.putExtra("nombre", datos[position][0])
        intent.putExtra("precio",  datos[position][1])
        intent.putExtra("cantidad", datos[position][2])
        intent.putExtra("descripcion",  datos[position][3])
        intent.putExtra("color",  datos[position][4])

        startActivity(intent)
    }

    fun cargarDatos(view: View?) {
        Toast.makeText(this, "CARGANDO DATOS, ESPERO UN MOMENTO", Toast.LENGTH_SHORT).show()
        datos = ArrayList()
        var url = "https://raw.githubusercontent.com/TheAlphaWolf450/JSON-PRUEBA-A/main/productos.json"
        
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {response ->
                //procesar lógica del json array
                for(i in 0 until response.length()) {
                    val actual = response.getJSONObject(i)
                    datos.add(i, arrayListOf(actual.getString("nombre"),actual.getString("precio"),actual.getString("cantidad"),actual.getString("descripcion"),actual.getString("color")))
                }

                //ADAPTER
                val adapter = datosAdapter(datos, this)
                //GUI
                recyclerView = findViewById(R.id.recyclerView)

                //es necesario utilizar un layout manager
                //layout manager es una clase que define como se van a desplegar
                //los items en el recyclerview

                val llm = LinearLayoutManager(this)
                llm.orientation = LinearLayoutManager.VERTICAL
                val glm = GridLayoutManager(this,3)

                //terminamos asignando al recycler view referencias a objetos necesarios
                recyclerView.layoutManager = llm
                recyclerView.adapter = adapter
            },
            {error ->
                Toast.makeText(this,"error: $error", Toast.LENGTH_SHORT).show()
            }
        ).apply {
            tag = TAG
        }

        queue.add(jsonArrayRequest)
    }
}