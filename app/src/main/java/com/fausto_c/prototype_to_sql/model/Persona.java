package com.fausto_c.prototype_to_sql.model;

public class Persona {

    private int id;
    private String nombres;
    private String telefono;
    private String correo;
    private int edad;

    public Persona() {
    }

    public Persona(String nombres, String telefono, String correo, int edad) {
        this.nombres = nombres;
        this.telefono = telefono;
        this.correo = correo;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
