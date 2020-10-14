package com.marcosledesma.ej03citastattoo.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Cita implements Parcelable {
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Date fechaCita;
    private float fianza;
    private boolean color;
    private boolean autorizado;

    public Cita() {
    }

    public Cita(String nombre, String apellidos, Date fechaNacimiento, Date fechaCita, float fianza, boolean color, boolean autorizado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCita = fechaCita;
        this.fianza = fianza;
        this.color = color;
        this.autorizado = autorizado;
    }

    protected Cita(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        // Las fechas las añadimos con TimeStamp
        fechaCita = new Date(in.readLong());
        fechaNacimiento = new Date(in.readLong());
        //
        fianza = in.readFloat();
        color = in.readByte() != 0;
        autorizado = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        // Fechas con writeLong
        dest.writeLong(fechaCita.getTime());
        dest.writeLong(fechaNacimiento.getTime());
        //
        dest.writeFloat(fianza);
        dest.writeByte((byte) (color ? 1 : 0));
        dest.writeByte((byte) (autorizado ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cita> CREATOR = new Creator<Cita>() {
        @Override
        public Cita createFromParcel(Parcel in) {
            return new Cita(in);
        }

        @Override
        public Cita[] newArray(int size) {
            return new Cita[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public float getFianza() {
        return fianza;
    }

    public void setFianza(float fianza) {
        this.fianza = fianza;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
}
