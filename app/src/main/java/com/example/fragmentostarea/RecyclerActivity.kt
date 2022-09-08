package com.example.fragmentostarea

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
    lateinit var datosPrueba : ArrayList<ArrayList<String>>
    lateinit var datosPrueba1 : ArrayList<String>
    lateinit var datosPrueba2 : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        //el recylcer es un widger que sirve para desplegar
        //muchos elementos con interfaz identica pero con tipos distintos

        //DATOS (abstracto) - Adapter(el traductor) - GUI (concreto)

        datos = ArrayList()
        //datos.add("Fido")
        //datos.add("Fifi")
        //datos.add("Firulais")
        //datos.add("Solovino")

        datosPrueba = ArrayList()
        datosPrueba1 = ArrayList()
        datosPrueba2 = ArrayList()
        datosPrueba1.add(0,"hola")
        datosPrueba1.add(1,"puto")
        datosPrueba2.add(0,"pito")
        datosPrueba2.add(1,"pito-doble")
        datosPrueba.add(0,datosPrueba1)
        datosPrueba.add(1,datosPrueba2)




        //JSON
        //Javascript object notation
        //- su uso es modelar información


        //los documentos pueden estar definidos por 2 tipos de contenedro
        //objetos - {}
        //arreglos - []

        //val jsonTest = "{'nombre': 'Juan', 'edad': 20}"
        //val jsonTest2 = "{'nombre': 'Pedro', 'calificaciones': [80,70,50,90]}"
        //val jsonTest3 = "{'nombre': 'Juan', 'edad': 20},{'nombre': 'Pedro', 'edad': 29},{'nombre': 'Ana', 'edad': 21}"

        //parsin - interpretación de texto con resultado en objeto de kotlin
        //parser - libreria / logica / codigo que se encarga de esta traduccion
        /*try {
            val objeto = JSONObject(jsonTest)
            Log.wtf("JSON",objeto.getString("nombre"))
            Log.wtf("JSON",objeto.getString("edad").toString())

            val objeto2 = JSONObject(jsonTest2)
            val calificaciones = objeto2.getJSONArray("calificaciones")

            Log.wtf("JSON",objeto2.getString("nombre"))
            for(i in 0 until calificaciones.length()){
                Log.wtf("json",calificaciones.getInt(i).toString())
            }

            val objeto3 = JSONArray(jsonTest3)
            for(i in 0 until objeto3.length()){
                val actual = objeto3.getJSONObject(i)
                Log.wtf("JSON",actual.getString("nombre"))
                Log.wtf("JSON",actual.getInt("edad").toString())
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }*/

        //HACIENDO REQUESTS CON VOLLEY
        //Looper
        //los requests son asíncronos
        //que diablos significa asíncrono?!

        //hagamos el queue
        queue = Volley.newRequestQueue(this)
        /*var url = "https://www.google.com"

        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Toast.makeText(this,"response: $response", Toast.LENGTH_SHORT).show()
            },
            { error ->
                Toast.makeText(this,"error: $error", Toast.LENGTH_SHORT).show()
            }
        ).apply {
            tag = TAG
        }*/

        var url = "https://raw.githubusercontent.com/TheAlphaWolf450/JSON-PRUEBA-A/main/productos.json"
        val objeto = JSONArray(url)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {response ->
                //procesar lógica del json array
                var temp : ArrayList<String>
                temp = ArrayList()
                for(i in 0 until response.length()){
                    val actual = response.getJSONObject(i)
                    temp.add(0,actual.getString("nombre"))
                    temp.add(1,actual.getString("precio"))

                    Log.wtf("JSON-REQUEST",temp[0])
                    Log.wtf("JSON-REQUEST",temp[1])

                    datos.add(i,temp)
                    Log.wtf("AQUIII",datos[i][0])
                    Log.wtf("AQUIII",datos[i][1])

                    Log.wtf("ie",i.toString())

                    /*val plataformas = actual.getJSONArray("plataformas")
                    for(j in 0 until plataformas.length()){
                        Log.wtf("JSON-REQUEST",plataformas.getString(j))
                    }*/
                }
            },
            {error ->
                Toast.makeText(this,"error: $error", Toast.LENGTH_SHORT).show()
            }
        ).apply {
            tag = TAG
        }

        val adapter = datosAdapter(datosPrueba, this)

        //ADAPTER


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

        queue.add(jsonArrayRequest)

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
    }
}