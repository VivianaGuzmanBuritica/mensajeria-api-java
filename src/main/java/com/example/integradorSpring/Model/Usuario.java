package com.example.integradorSpring.Model;

public class Usuario {
    private Integer cedula;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private String dirResidencia;
    private String ciudad;

    public Usuario(Integer cedula, String nombre, String apellido, String celular, String email, String dirResidencia, String ciudad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.email = email;
        this.dirResidencia = dirResidencia;
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", dirResidencia='" + dirResidencia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
