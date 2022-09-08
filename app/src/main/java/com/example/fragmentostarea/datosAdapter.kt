package com.example.fragmentostarea

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class datosAdapter(var datos : ArrayList<ArrayList<String>>, var listener : View.OnClickListener) : RecyclerView.Adapter<datosAdapter.DatosViewHolder>(){
    //1era cosa que hay que hacer
    //ViewHolder //
    //ViewHolder es similar al bindign: un objeto con referencias a los elementos de una vista

    class DatosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var texto1 : TextView
        var texto2 : TextView
        var boton : Button

        //bloque de inizacion
        //blque de código que se corre TODAS las veces que se crea un objeto
        init{
            texto1 = itemView.findViewById(R.id.rowText1)
            texto2 = itemView.findViewById(R.id.rowText2)
            boton = itemView.findViewById(R.id.rowButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.productos,parent,false)
        view.setOnClickListener(listener)

        view.setOnClickListener(listener)

        val viewHolder = DatosViewHolder(view)

        viewHolder.boton.setOnClickListener{
            Log.wtf("BOTON","HOLA")
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {
        Log.wtf("JSON-HOLD","hola puto")
        holder.texto1.text = datos[position][0]
        holder.texto2.text = datos[position][1]


    }

    override fun getItemCount(): Int {
        return datos.size
    }


}
