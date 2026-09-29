package com.example.appcamaras.Modelo;
/**
 * Autor: Ander Esau Hernández Jiménez
 * Clase padre: DispositivoRed
 * Propósito: Definir las propiedades y el estado base de cualquier
 *            dispositivo de red. Las clases hijas heredan de ella.
 */
public class clsDispositivoRed {
    // Atributos
    private String modelo;
    private boolean activo;

    // Constructor con validacion, el modelo no puede ser nulo ni estar vacio
    public clsDispositivoRed(String modelo) {
        validarModelo(modelo);
        this.modelo = modelo.trim();
        this.activo = false; // Por defecto el dispositivo esta apagado
    }

    // Valida el modelo y lanza una excepción si no es valido
    private void validarModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío");
        }
    }

    // Getters y Setters
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        validarModelo(modelo);
        this.modelo = modelo.trim();
    }

    public boolean isActivo() {
        return activo;
    }

    // Metodos
    public void encender() {

        this.activo = true;
    }

    public void apagar() {

        this.activo = false;
    }
}
