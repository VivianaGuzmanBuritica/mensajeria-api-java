package com.example.integradorSpring.Model;

public class Cliente extends Usuario {
    public Cliente(Integer cedula, String nombre, String apellido, String celular, String email, String dirResidencia, String ciudad) {
        super(cedula, nombre, apellido, celular, email, dirResidencia, ciudad);
    }

}
