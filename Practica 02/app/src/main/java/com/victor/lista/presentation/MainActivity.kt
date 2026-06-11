package com.victor.lista.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.AppScaffold

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val listaProductos = listOf(
                Producto(1, "Smartwatch", 29.99),
                Producto(2, "Laptop", 899.99),
                Producto(3, "Audífonos Bluetooth", 59.99),
                Producto(4, "Teclado Mecánico", 79.99),
                Producto(5, "Mouse Inalámbrico", 24.99),
                Producto(6, "Monitor 24 Pulgadas", 189.99),
                Producto(7, "Disco SSD 1TB", 99.99),
                Producto(8, "Cámara Web HD", 39.99),
                Producto(9, "Bocina Portátil", 49.99),
                Producto(10, "Tablet", 249.99)
            )

            PantallaListaProductos(listaProductos)
        }
    }
}

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double
)

@Composable
fun PantallaListaProductos(productos: List<Producto>) {

    val estadoLista = rememberScalingLazyListState()

    AppScaffold {

        ScalingLazyColumn(
            state = estadoLista,
            modifier = Modifier
        ) {

            items(productos) { producto ->
                Text("${producto.nombre} - $${producto.precio}")
            }
        }
    }
}