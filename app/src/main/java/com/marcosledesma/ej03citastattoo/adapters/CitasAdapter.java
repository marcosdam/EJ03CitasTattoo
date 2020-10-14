package com.marcosledesma.ej03citastattoo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.marcosledesma.ej03citastattoo.R;
import com.marcosledesma.ej03citastattoo.modelos.Cita;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CitasAdapter extends ArrayAdapter<Cita> {

    /**
     * HEREDAMOS SUPER DE LA CLASE ADAPTER, CON EL CONSTRUCTOR DE ESTOS 3 ATRIBUTOS:
     * @param context -> Sirve para saber qué actividad está montando el listado
     * @param resource -> Es la plantilla para ir mostrando cada nota
     * @param objects -> Es el conjunto de elementos que voy a mostrar
     */

    // Los definimos
    private Context context;
    private int resource;
    private ArrayList<Cita> objects;

    // Modificamos el tercer parámetro para que reciba ArrayList en lugar de List
    public CitasAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Cita> objects) {
        super(context, resource, objects);
        //Inicializamos las variables
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    // Implementamos método getView y borramos la llamada al padre (return super.getView)
    /**
     *
     * @param position -> Elemento del Array en el que estoy
     * @param convertView -> El View que devuelve (filaCitas)
     * @param parent ->
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtenemos la fila
        View filaCita = LayoutInflater.from(context).inflate(resource, null);
        // La sacamos
        Cita cita = objects.get(position);

        TextView txtNombre = filaCita.findViewById(R.id.txtNombreFilaCita);
        TextView txtFecha = filaCita.findViewById(R.id.txtFechaFilaCita);

        txtNombre.setText(cita.getNombre());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha.setText(simpleDateFormat.format(cita.getFechaCita()));

        return filaCita;
    }
}
