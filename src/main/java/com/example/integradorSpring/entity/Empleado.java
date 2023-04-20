package com.example.integradorSpring.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@Entity
@Table(name= "empleado")
@ApiModel(description = "Entidad que representa un empleado")
public class Empleado {

    @Id
    @Column(name = "cedula")
    private Integer cedula;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="celular")
    private String celular;

    @ApiModelProperty(value = "Direccion de correo electronico", example = "viviana123@mail.com")
    @Column(name="email")
    private String email;

    @Column(name="dirResidencia")
    private String dirResidencia;

    @Column(name="ciudad")
    private String ciudad;

    @Column (name="antiguedad")
    private double antigueadad;

    @Column(name="rhSangre")
    private String rhSangre;

    @Column(name="tipo")
    private String tipo;

    public Empleado(Integer cedula, String nombre, String apellido, String celular, String email, String dirResidencia, String ciudad, double antigueadad, String rhSangre, String tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.email = email;
        this.dirResidencia = dirResidencia;
        this.ciudad = ciudad;
        this.antigueadad = antigueadad;
        this.rhSangre = rhSangre;
        this.tipo = tipo;
    }

    public Empleado() {
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirResidencia() {
        return dirResidencia;
    }

    public void setDirResidencia(String dirResidencia) {
        this.dirResidencia = dirResidencia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getAntigueadad() {
        return antigueadad;
    }

    public void setAntigueadad(double antigueadad) {
        this.antigueadad = antigueadad;
    }

    public String getRhSangre() {
        return rhSangre;
    }

    public void setRhSangre(String rhSangre) {
        this.rhSangre = rhSangre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
