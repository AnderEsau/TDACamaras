package com.example.appcamaras.Modelo;
/**
 * Autor: Ander Esau Hernández Jiménez
 * TDA: CamaraCCTV (hereda de DispositivoRed)
 * Propósito: Representar una cámara de seguridad conectada por cable UTP
 *            y calcular costo de cableado, diagnóstico y pérdida de señal.
 */
public class clsCamaraModelo extends clsDispositivoRed{
    // la distancia máxima permitida. También evita un StackOverflow en la recursión
    public static final int METROS_MAXIMOS = 500;

    // La calidad baja este porcentaje por cada metro de UTP (0.5%)
    private static final double PORCENTAJE_PERDIDA_POR_METRO = 0.005;

    // Atributos del TDA
    private int metrosCableUTP;
    private boolean tieneBalun;

    // Constructor con validaciones
    public clsCamaraModelo(String modelo, int metrosCableUTP) {
        super(modelo); // El padre valida el modelo
        validarMetros(metrosCableUTP);
        this.metrosCableUTP = metrosCableUTP;
        this.tieneBalun = false;
    }

    // Valida que la distancia esté en el rango permitido
    private void validarMetros(int metros) {
        if (metros < 0 || metros > METROS_MAXIMOS) {
            throw new IllegalArgumentException(
                    "Distancia fuera de rango (0 a " + METROS_MAXIMOS + " m)");
        }
    }

    //Getters y Setters
    public int getMetrosCableUTP() {
        return metrosCableUTP;
    }

    public void setMetrosCableUTP(int metrosCableUTP) {
        validarMetros(metrosCableUTP);
        this.metrosCableUTP = metrosCableUTP;
    }

    public boolean tieneBalun() {
        return tieneBalun;
    }

    //Metodos

    //Instalar balunes de video en la cámara
    public void conectarBalun() {
        this.tieneBalun = true;
    }

    //Retirar los balunes y apaga la camara (apagar() es heredado)
    public void desconectar() {
        this.tieneBalun = false;
        apagar();
    }

    //Costo total del cable segun el precio por metro
    public double calcularCostoCable(double precioPorMetro) {
        return metrosCableUTP * precioPorMetro;
    }

    //El diagnostico segun la distancia y si hay balunes
    public String diagnosticarConexion() {
        if (tieneBalun == false) {
            if (metrosCableUTP <= 50) {
                return "Conexión estable: la distancia es corta y no requiere balunes.";
            } else if (metrosCableUTP <= 300) {
                return "ALERTA: a esta distancia se necesitan balunes, habrá ruido en el DVR.";
            }
            return "ALERTA: conexión inestable. Se necesitan balunes y una distancia menor.";
        }
        // Con balunes
        if (metrosCableUTP <= 300) {
            return "Conexión estable: balunes instalados correctamente.";
        }
        return "Advertencia: aun con balunes, más de 300 m puede ser inestable.";
    }

    // Calidad final de la señal tras cierta cantidad de metros de cable
    // Inicia con 100% de calidad y usa el metodo recursivo
    public double calcularDegradacionSenal(int metros) {
        return degradar(metros, 100.0);
    }

    // Metodo recursivo
    // Caso base: si ya no quedan metros por recorrer, se devuelve la calidad acumulada.
    // Avance: se calcula cuánto se pierde en este metro, se le resta a la calidad
    //         actual, y se llama de nuevo con un metro menos (metros - 1).
    private double degradar(int metrosRestantes, double calidadActual) {
        if (metrosRestantes <= 0) {          // caso base
            return calidadActual;
        }
        // Aqui se calcula cuanto se pierde en este metro (0.5% de la calidad que se tiene ahora)
        double perdidaDeEsteMetro = calidadActual * PORCENTAJE_PERDIDA_POR_METRO;

        // La nueva calidad es la actual menos lo que se perdio en este metro
        double nuevaCalidad = calidadActual - perdidaDeEsteMetro;

        return degradar(metrosRestantes - 1, nuevaCalidad);
    }
}
