package com.example.integradorSpring.Model;

public class Empleado extends Usuario{
    private double antiguedad;
    private String rhSangre;
    private String tipo;

    public Empleado(Integer cedula, String nombre, String apellido, String celular, String email, String dirResidencia, String ciudad, double antiguedad, String rhSangre, String tipo) {
        super(cedula, nombre, apellido, celular, email, dirResidencia, ciudad);
        this.antiguedad = antiguedad;
        this.rhSangre = rhSangre;
        this.tipo = tipo;
    }


}
