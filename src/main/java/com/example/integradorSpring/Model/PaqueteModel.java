package com.example.integradorSpring.Model;

public class PaqueteModel {

    private String tipo;
    private double peso;
    private double valorDeclarado;

    public PaqueteModel(double peso, double valorDeclarado) {

        this.tipo = asignarTipoPaquete(peso);
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                ",  tipo='" + tipo + '\'' +
                ", peso=" + peso +
                ", valorDeclarado=" + valorDeclarado +
                '}';
    }

    public String asignarTipoPaquete (Double peso){
        if(peso < 2.0){
            return "LIVIANO";
        }else if( peso > 5){
            return "GRANDE";
        }else {
            return "MEDIANO";
        }
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
}
