package com.example.appcamaras.Presentador;

import com.example.appcamaras.Modelo.clsCamaraModelo;
import com.example.appcamaras.Vista.MainActivity;

/**
 * Autor: Ander Esau Hernández Jiménez
 * Propósito: Intermediario entre la vista (MainActivity) y el modelo (CamaraCCTV).
 *            Recibe los datos como texto, los convierte, usa el modelo
 *            y devuelve el resultado a la vista ya formateado.
 */
public class clsCamaraPresentador {
    // Precio del metro de cable UTP en pesos (tomando en cuenta precios de bobinas en mercado libre)
    private static final double PRECIO_POR_METRO = 5.80;

    // Referencia a la vista para enviarle resultados
    private final MainActivity vista;

    public clsCamaraPresentador(MainActivity vista) {
        this.vista = vista;
    }

    // Recibe los datos en texto, ejecuta la lógica del modelo y muestra el resultado
    public void calcular(String modelo, String metrosTexto, boolean usaBalun) {
        try {
            // 1.- Convierte a número (lanza NumberFormatException si no es válido)
            int metros = Integer.parseInt(metrosTexto.trim());

            // 2.- Crea el TDA con los datos del usuario (aquí se validan)
            clsCamaraModelo camara = new clsCamaraModelo(modelo, metros);
            camara.encender();
            if (usaBalun) {
                camara.conectarBalun();
            }

            // 3.- Ejecuta la logica
            String diagnostico = camara.diagnosticarConexion();
            double costo = camara.calcularCostoCable(PRECIO_POR_METRO);
            double calidad = camara.calcularDegradacionSenal(camara.getMetrosCableUTP());

            // 4.- Da formato y envía el resultado a la vista
            String resumen = "Cámara: " + camara.getModelo() + "\n"
                    + diagnostico + "\n"
                    + "Costo del cable UTP: $" + String.format("%.2f", costo) + " MXN\n"
                    + "Calidad de imagen final: " + String.format("%.2f", calidad) + "%";
            vista.mostrarResultado(resumen);

        } catch (NumberFormatException e) {
            vista.mostrarResultado("Ingresa una distancia válida (solo números enteros).");
        } catch (IllegalArgumentException e) {
            // Mensajes de las validaciones del modelo
            vista.mostrarResultado("Error: " + e.getMessage());
        }
    }
}
