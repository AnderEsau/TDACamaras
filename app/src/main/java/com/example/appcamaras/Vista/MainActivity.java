package com.example.appcamaras.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appcamaras.Presentador.clsCamaraPresentador;
import com.example.appcamaras.R;
/**
 * Autor: Ander Esau Hernández Jiménez
 * Propósito: Mostrar la interfaz y enviar al presentador lo que escribe el usuario.
 *            No contiene lógica de negocio.
 */
public class MainActivity extends AppCompatActivity {

    // Controles de la vista
    private TextView txtTitulo;
    private EditText txtModelo;
    private EditText txtMetros;
    private CheckBox chkBalun;
    private Button btnCalcular;
    private TextView txtResultado;
    private TextView txtFirma;

    private clsCamaraPresentador presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlaza cada variable con su control del XML
        txtTitulo = findViewById(R.id.txtTitulo);
        txtModelo = findViewById(R.id.txtModelo);
        txtMetros = findViewById(R.id.txtMetros);
        chkBalun = findViewById(R.id.chkBalun);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);
        txtFirma = findViewById(R.id.txtFirma);

        // Se crea el presentador pasándole esta vista
        presenter = new clsCamaraPresentador(this);

        // Referencia al metodo, o indica que el boton calcular hara cierta accion cuando le den click
        btnCalcular.setOnClickListener(this::ejecutarAccionCalcular);
    }

    // Se ejecuta al presionar Calcular: solo entrega los datos al presentador
    private void ejecutarAccionCalcular(View v) {
        presenter.calcular(
                txtModelo.getText().toString(),
                txtMetros.getText().toString(),
                chkBalun.isChecked());
    }

    // El presentador llama este metodo para mostrar el resultado en pantalla
    public void mostrarResultado(String resultado) {
        txtResultado.setText(resultado);
    }
}