package com.fausto_c.prototype_to_sql.model;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class PersonaDao extends Persona{

    DbHelper dbHelper;

    public PersonaDao(){

    }

    public PersonaDao(String nombres, String telefono, String correo, int edad) {
        super(nombres, telefono, correo, edad);
    }

    public void guardar(Context context){
        dbHelper = new DbHelper(context);
        String nsql = "INSERT INTO personas(nombres,telefono,correo,edad)" +
                "VALUES('"+getNombres()+"','"+getTelefono()+"','"+getCorreo()+"',"+getEdad()+")";

        dbHelper.noQuery(nsql);
        dbHelper.close();//cerramos conexión

    }

    public List<Persona> consultarDatos(Context context){

        List<Persona> listaPersonas = new ArrayList<>();

        dbHelper = new DbHelper(context);
        String sql = "SELECT * FROM personas;";
        Cursor fila = dbHelper.query(sql);
        fila.moveToFirst();

        do {

            Persona persona = new Persona();
            persona.setId(fila.getInt(0));
            persona.setNombres(fila.getString(1));
            persona.setTelefono(fila.getString(2));
            persona.setCorreo(fila.getString(3));
            persona.setEdad(fila.getInt(4));

            listaPersonas.add(persona);

        }while (fila.moveToNext());
        dbHelper.close();//cerramos conexión

        return listaPersonas;

    }

    public Persona findById(Context context,int id){
        Persona persona = new Persona();
        dbHelper = new DbHelper(context);
        String sql = "SELECT * FROM personas WHERE id="+id;
        Cursor fila = dbHelper.query(sql);
        if (fila.moveToFirst()){

            persona.setNombres(fila.getString(1));
            persona.setTelefono(fila.getString(2));
            persona.setCorreo(fila.getString(3));
            persona.setEdad(fila.getInt(4));
        }
        dbHelper.close();
        return persona;
    }

    public void actualizar(Context context, int id){
        dbHelper = new DbHelper(context);
        String nsql = "update personas set nombres= '" + getNombres() +"'" +
                ", telefono = '" + getTelefono() + "', correo = '" + getCorreo() +"'" +
                " , edad = " + getEdad() + " WHERE id = " + id;

        dbHelper.noQuery(nsql);
        dbHelper.close();//cerramos conexión

    }

    public void eliminar(Context context, int id){
        dbHelper = new DbHelper(context);
        String nsql = "DELETE FROM personas where id=" + id;
        dbHelper.noQuery(nsql);
    }
}
