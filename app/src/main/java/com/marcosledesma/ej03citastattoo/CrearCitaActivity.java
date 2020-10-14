package com.marcosledesma.ej03citastattoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.marcosledesma.ej03citastattoo.modelos.Cita;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CrearCitaActivity extends AppCompatActivity {

    private TextView txtNombre, txtApellidos, txtFechaNacimiento, txtFecha, txtFianza;
    private SeekBar sbColor, sbAutorizado;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cita);

        txtNombre = findViewById(R.id.txtNombreCitaAdd);
        txtApellidos = findViewById(R.id.txtApellidosCitaAdd);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimientoCitaAdd);
        txtFecha = findViewById(R.id.txtFechaCitaAdd);
        txtFianza = findViewById(R.id.txtFianzaCitaAdd);
        sbColor = findViewById(R.id.sbColorCitaAdd);
        sbAutorizado = findViewById(R.id.sbAutorizadoCitaAdd);
        btnGuardar = findViewById(R.id.btnCrearCitaAdd);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().isEmpty() &&
                        !txtApellidos.getText().toString().isEmpty() &&
                        !txtFechaNacimiento.getText().toString().isEmpty() &&
                        !txtFecha.getText().toString().isEmpty() &&
                        !txtFianza.getText().toString().isEmpty()){

                    // SimpleDateFormat para las 2 fechas
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Cita cita = new Cita(txtNombre.getText().toString(), txtApellidos.getText().toString(),
                                simpleDateFormat.parse(txtFechaNacimiento.getText().toString()),
                                simpleDateFormat.parse(txtFecha.getText().toString()),
                                Float.parseFloat(txtFianza.getText().toString()),
                                sbColor.isSelected(), sbAutorizado.isSelected());

                        // Creamos intent después de crear la cita
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("CITA", cita);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (ParseException e) {
                        Toast.makeText(CrearCitaActivity.this,"El formato de la fecha es incorrecto", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CrearCitaActivity.this,"Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}