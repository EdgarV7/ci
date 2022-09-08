package com.example.fragmentostarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fragmentostarea.PersonalDataFragment
import com.example.fragmentostarea.PetDataFragment

class MainActivity : AppCompatActivity() {

    lateinit var dataFragment: PersonalDataFragment
    lateinit var petFragment: PetDataFragment
    lateinit var recyclerFragment: RecyclerFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //los fragmentos se pueden agregar por medio de la GUI
        //O por medio de código

        //vamos a agreagr fragmentos programaticamente
        dataFragment = PersonalDataFragment.newInstance("Edgar",21)
        petFragment = PetDataFragment.newInstance("CHIEF",5)
        recyclerFragment = RecyclerFragment()

        //COMO SE AGREGA A GUI
        //existe el fragmentManager que es el que se encarga de operaciones
        //de administracion de fragmentos

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentContainerView,dataFragment,TAG)

        //sin esto las cosas nunca suceden
        transaction.commit()
    }

    fun swap(view: View?){
        //vamos a intercambiar fragmentos
        //obtener referencia al fragmento actualmente cargado
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG)

        if(fragmentoActual != null){
            //obtener referencia al nuevo fragmento
            //polimorfismo - un objeto de un tipo puede representar
            // a un objeto de un subtipo
            var fragmentoNuevo : Fragment = petFragment

            if(fragmentoActual == petFragment)
                fragmentoNuevo = dataFragment
            val transaction = supportFragmentManager.beginTransaction()

            //2 OPCIONES
            //1 - quitar y poner (no es lo práctico)
            /*transaction.remove(fragmentoActual)
            transaction.add(R.id.fragmentContainerView,fragmentoNuevo, TAG)*/

            //2 hacerlo con replace
            transaction.replace(R.id.fragmentContainerView,fragmentoNuevo, TAG)
            transaction.commit()
        }
    }

    companion object{
        private const val TAG = "fragmentito"
    }

    fun data(view: View?){
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG)

        if(fragmentoActual != null){
            //obtener referencia al nuevo fragmento
            //polimorfismo - un objeto de un tipo puede representar
            // a un objeto de un subtipo
            var fragmentoNuevo : Fragment = dataFragment

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragmentContainerView,fragmentoNuevo, TAG)
            transaction.commit()
    }

}
    fun pet(view: View?){
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG)

        if(fragmentoActual != null){
            //obtener referencia al nuevo fragmento
            //polimorfismo - un objeto de un tipo puede representar
            // a un objeto de un subtipo
            var fragmentoNuevo : Fragment = petFragment

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragmentContainerView,fragmentoNuevo, TAG)
            transaction.commit()
        }

    }

    fun recycler(view: View?){
        /*val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG)

        if(fragmentoActual != null){
            //obtener referencia al nuevo fragmento
            //polimorfismo - un objeto de un tipo puede representar
            // a un objeto de un subtipo
            var fragmentoNuevo : Fragment = recyclerFragment

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragmentContainerView,fragmentoNuevo, TAG)
            transaction.commit()
        }*/

        val intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)
    }
}
