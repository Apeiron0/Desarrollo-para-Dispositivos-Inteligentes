package com.victor.crud_empleados

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //Esta es la Lista
    companion object {
        val listaEmpleados = ArrayList<Empleado>()
        var idContador = 1
    }

    // Variables
    private lateinit var etNombre: EditText
    private lateinit var etPuesto: EditText
    private lateinit var etSueldo: EditText
    private lateinit var etBuscarId: EditText
    private lateinit var btnAlta: Button
    private lateinit var btnBaja: Button
    private lateinit var btnEditar: Button
    private lateinit var btnBuscar: Button
    private lateinit var lvEmpleados: ListView

    private var empleadoSeleccionado: Empleado? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Emparejjar componentes
        etNombre = findViewById(R.id.etNombre)
        etPuesto = findViewById(R.id.etPuesto)
        etSueldo = findViewById(R.id.etSueldo)
        etBuscarId = findViewById(R.id.etBuscarId)
        btnAlta = findViewById(R.id.btnAlta)
        btnBaja = findViewById(R.id.btnBaja)
        btnEditar = findViewById(R.id.btnEditar)
        btnBuscar = findViewById(R.id.btnBuscar)
        lvEmpleados = findViewById(R.id.lvEmpleados)

        if (listaEmpleados.isEmpty()) {
            listaEmpleados.add(Empleado(idContador++, "Pepito Perez", "Desarrollador", 15000.0))
            listaEmpleados.add(Empleado(idContador++, "Loretta Marsupiales", "Mascota", 14000.0))
        }

        actualizarListaConsulta()

        //Altas
        btnAlta.setOnClickListener {
            val nombre = etNombre.text.toString()
            val puesto = etPuesto.text.toString()
            val sueldoStr = etSueldo.text.toString()

            if (nombre.isNotEmpty() && puesto.isNotEmpty() && sueldoStr.isNotEmpty()) {
                val nuevoEmpleado = Empleado(idContador++, nombre, puesto, sueldoStr.toDouble())
                listaEmpleados.add(nuevoEmpleado)
                limpiarCampos()
                actualizarListaConsulta()
                Toast.makeText(this, "Empleado registrado con éxito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Buscar
        btnBuscar.setOnClickListener {
            val idBuscarStr = etBuscarId.text.toString()
            if (idBuscarStr.isNotEmpty()) {
                val idBuscar = idBuscarStr.toInt()
                // Busca el ID
                empleadoSeleccionado = listaEmpleados.find { it.id == idBuscar }

                if (empleadoSeleccionado != null) {
                    // Rellenar datos
                    etNombre.setText(empleadoSeleccionado!!.nombre)
                    etPuesto.setText(empleadoSeleccionado!!.puesto)
                    etSueldo.setText(empleadoSeleccionado!!.sueldo.toString())
                    Toast.makeText(this, "Empleado encontrado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Empleado no registrado", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                }
            } else {
                Toast.makeText(this, "Ingresa un ID para buscar", Toast.LENGTH_SHORT).show()
            }
        }

        // Actualizar
        btnEditar.setOnClickListener {
            if (empleadoSeleccionado != null) {
                val nuevoNombre = etNombre.text.toString()
                val nuevoPuesto = etPuesto.text.toString()
                val nuevoSueldoStr = etSueldo.text.toString()

                if (nuevoNombre.isNotEmpty() && nuevoPuesto.isNotEmpty() && nuevoSueldoStr.isNotEmpty()) {
                    // Modifica los valores directamente en el objeto de la lista
                    empleadoSeleccionado!!.nombre = nuevoNombre
                    empleadoSeleccionado!!.puesto = nuevoPuesto
                    empleadoSeleccionado!!.sueldo = nuevoSueldoStr.toDouble()

                    limpiarCampos()
                    actualizarListaConsulta()
                    empleadoSeleccionado = null
                    Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Primero busca un empleado por su ID", Toast.LENGTH_SHORT).show()
            }
        }

        // Borrar
        btnBaja.setOnClickListener {
            if (empleadoSeleccionado != null) {
                listaEmpleados.remove(empleadoSeleccionado)
                limpiarCampos()
                actualizarListaConsulta()
                empleadoSeleccionado = null //
                Toast.makeText(this, "Empleado eliminado del sistema", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Primero busca al empleado que darás de baja", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarListaConsulta() {
        val datosFormateados = listaEmpleados.map {
            "ID: ${it.id} - ${it.nombre}\n${it.puesto} | \$${it.sueldo}"
        }
        // El adaptador vincula el arreglo de textos con el componente ListView gráfico
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datosFormateados)
        lvEmpleados.adapter = adapter
    }

    private fun limpiarCampos() {
        etNombre.text.clear()
        etPuesto.text.clear()
        etSueldo.text.clear()
        etBuscarId.text.clear()
    }
}