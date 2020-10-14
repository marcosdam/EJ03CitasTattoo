package com.marcosledesma.ej03citastattoo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marcosledesma.ej03citastattoo.modelos.Cita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditCitaActivity extends AppCompatActivity {

    // 1. DEFINIR
    private EditText txtNombre, txtApellidos, txtFechaNacimiento, txtFecha, txtFianza;
    private SeekBar sbColor, sbAutorizado;
    private Cita cita;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cita);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. ENLAZAR
        txtNombre = findViewById(R.id.txtNombreCitaEdit);
        txtApellidos = findViewById(R.id.txtApellidosCitaEdit);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimientoCitaEdit);
        txtFecha = findViewById(R.id.txtFechaCitaEdit);
        txtFianza = findViewById(R.id.txtFianzaCitaEdit);
        sbColor = findViewById(R.id.sbColorCitaEdit);
        sbAutorizado = findViewById(R.id.sbAutorizadoCitaEdit);

        //
        if (getIntent().getExtras() != null){
            cita = getIntent().getExtras().getParcelable("CITA");
            posicion = getIntent().getExtras().getInt("POS");

            if (cita != null){
                txtNombre.setText(cita.getNombre());
                txtApellidos.setText(cita.getApellidos());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtFecha.setText(simpleDateFormat.format(cita.getFechaCita()));
            }
            else {
                cita = new Cita();
                txtNombre.setHint("Nombre");
                txtFecha.setHint("Fecha");
            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().isEmpty() &&
                        !txtApellidos.getText().toString().isEmpty() &&
                        !txtFecha.getText().toString().isEmpty()){
                    cita.setNombre(txtNombre.getText().toString());
                    cita.setApellidos(txtApellidos.getText().toString());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        cita.setFechaCita(simpleDateFormat.parse(txtFecha.getText().toString()));
                        // Si tod0 va bien creo un Intent y Bundle
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("CITA", cita);
                        bundle.putInt("POS", posicion);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (ParseException e) {
                        Toast.makeText(EditCitaActivity.this, "La fecha no es correcta", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}