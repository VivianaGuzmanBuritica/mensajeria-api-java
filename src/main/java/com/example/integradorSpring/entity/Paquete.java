package com.example.integradorSpring.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@Table(name = "paquete")
@ApiModel(description = "Entidad que representa un paquete")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
     private Integer id;

    @Column(name="tipo")
    private String tipo;

    @Column(name="peso")
    private double peso;

    @Column(name="valorDeclarado")
    private double valorDeclarado;

    public Paquete(String tipo, double peso, double valorDeclarado) {
        this.tipo = tipo;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public Paquete() {
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }
}
