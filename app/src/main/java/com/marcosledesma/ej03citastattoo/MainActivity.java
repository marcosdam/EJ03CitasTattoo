package com.marcosledesma.ej03citastattoo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marcosledesma.ej03citastattoo.adapters.CitasAdapter;
import com.marcosledesma.ej03citastattoo.modelos.Cita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int CREAR_CITA = 1;
    private final int EDIT_CITA = 2;

    private ArrayList<Cita> listaCitas;

    private int plantillaFilas;

    private ListView listView;

    private CitasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = inicializaDatos();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearCitaActivity.class);
                startActivityForResult(intent, CREAR_CITA);
            }
        });
    }

    private FloatingActionButton inicializaDatos() {
        listaCitas = new ArrayList<>();
        plantillaFilas = R.layout.fila_cita;
        listView = findViewById(R.id.contenedorMain);
        adapter = new CitasAdapter(this, plantillaFilas, listaCitas);
        // Asociamos ListView y Adapter
        listView.setAdapter(adapter);
        // inicializaCitas();

        // OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cita cita = listaCitas.get(position);
                Intent intent = new Intent(MainActivity.this, EditCitaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("CITA", cita);
                bundle.putInt("POS", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_CITA);
            }
        });
        return findViewById(R.id.fab);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == CREAR_CITA && resultCode == RESULT_OK){
            if (data != null){
                if (data.getExtras() != null){
                    Cita cita = data.getExtras().getParcelable("CITA");
                    if (cita != null){
                        listaCitas.add(cita);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
        // Lo mismo para EDIT_NOTA, pero obteniendo tambien la posicion del intent
        if (requestCode == EDIT_CITA && resultCode == RESULT_OK){
            if (data != null){
                if (data.getExtras() != null){
                    Cita cita = data.getExtras().getParcelable("CITA");
                    // Aquí necesitamos la posición
                    int posicion = data.getExtras().getInt("POS");
                    if (cita != null){
                        listaCitas.set(posicion, cita);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}