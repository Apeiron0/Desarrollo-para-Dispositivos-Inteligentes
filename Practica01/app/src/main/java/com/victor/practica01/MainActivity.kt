package com.victor.practica01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import  android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonAlerta1 = findViewById<Button>(R.id.Button1)
        val botonAlerta2 = findViewById<Button>(R.id.Button2)
        val botonAlerta3 = findViewById<Button>(R.id.Button3)
        val btn_datos =findViewById<Button>(R.id.btn_info)
        val nombre=findViewById<EditText>(R.id.txt_name)
        val pwd=findViewById<EditText>(R.id.txt_pwd)

        btn_datos.setOnClickListener {
            Toast.makeText(this, "Nombre: ${nombre.text.toString()}\nPassword: ${pwd.text.toString()}", Toast.LENGTH_SHORT).show()
        }

        botonAlerta1.setOnClickListener {
            mostrarAlerta("Boton 1")
        }
        botonAlerta2.setOnClickListener {
            mostrarAlerta("Boton 2")
        }
        botonAlerta3.setOnClickListener {
            mostrarAlerta("Boton 3")
        }
    }

    // 3. Creamos la función que genera y muestra el mensaje
    private fun mostrarAlerta(nombre: String) {

        val constructor = AlertDialog.Builder(this)

        constructor.setTitle("¡Alerta!")
        constructor.setMessage("Usted a presionado el boton $nombre")

        // Agregamos un botón para cerrar la alerta
        constructor.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss() // Cierra el alert
        }

        // Creamos y mostramos la alerta en pantalla
        val alerta = constructor.create()
        alerta.show()
    }
}