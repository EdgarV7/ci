package com.example.fragmentostarea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fragmentostarea.R


private const val NOMBRE = "nombreData"
private const val EDAD = "edadData"

/**
 * A simple [Fragment] subclass.
 * Use the [PerritoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var nombre: String? = null
    private var edad: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString(NOMBRE)
            edad = it.getInt(EDAD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_personal_data, container, false)
        view.findViewById<TextView>(R.id.nombreData).apply{
            text = nombre
        }
        view.findViewById<TextView>(R.id.edadData).apply{
            text = edad.toString()
        }

        return view
    }

    companion object {

        //método esstático para cración de instancias
        //PROBLEMA - los fragmentos están obligados a tener un constructor
        //default (sin argumentos)
        //esto se vuelve problema cuando necesitamos argumentos!

        //factory
        //
        @JvmStatic
        fun newInstance(nombre: String, edad: Int) : PersonalDataFragment {
            val perrito = PersonalDataFragment()
            val datos = Bundle()
            datos.putString(NOMBRE,nombre)
            datos.putInt(EDAD,edad)
            perrito.arguments = datos
            return perrito
        }
        /*
            PerritoFragment().apply {
                arguments = Bundle().apply {
                    putString(NOMBRE, nombre)
                    putInt(EDAD, edad)
                }
            }
         */
    }
}