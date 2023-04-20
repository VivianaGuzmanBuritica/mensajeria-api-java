package com.example.integradorSpring.dto;

public class EmpleadoDTO {

    private Integer cedula;

    private String nombre;

    private  String apellido;
    private String celular;
    private String email;
    private  String dirResidencia;
    private String ciudad;
    private double antiguedad;
    private String rhSangre;
    private String tipo;
    public EmpleadoDTO(Integer cedula, String nombre, String apellido, String celular, String email, String dirResidencia, String ciudad, double antiguedad, String rhSangre, String tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.email = email;
        this.dirResidencia = dirResidencia;
        this.ciudad = ciudad;
        this.antiguedad = antiguedad;
        this.rhSangre = rhSangre;
        this.tipo = tipo;
    }

    public EmpleadoDTO() {
    }

    public double getAntiguedad() {
        return antiguedad;
    }

    public String getRhSangre() {
        return rhSangre;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getDirResidencia() {
        return dirResidencia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDirResidencia(String dirResidencia) {
        this.dirResidencia = dirResidencia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setAntiguedad(double antiguedad) {
        this.antiguedad = antiguedad;
    }

    public void setRhSangre(String rhSangre) {
        this.rhSangre = rhSangre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
