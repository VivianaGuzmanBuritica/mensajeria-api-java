package com.example.integradorSpring.dto;


public class EnvioDTO {
    private Integer cedulaCliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private  String dirDestino;
    private String nombreRecibe;
    private String celularRecibe;
    private double peso;
    private double valorDeclarado;

    public EnvioDTO(Integer cedulaCliente, String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreRecibe, String celularRecibe, double peso, double valorDeclarado) {
        this.cedulaCliente = cedulaCliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreRecibe = nombreRecibe;
        this.celularRecibe = celularRecibe;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public EnvioDTO() {
    }

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }



    public String getCiudadOrigen() {
        return ciudadOrigen;
    }


    public String getCiudadDestino() {
        return ciudadDestino;
    }



    public String getDirDestino() {
        return dirDestino;
    }


    public String getNombreRecibe() {
        return nombreRecibe;
    }


    public String getCelularRecibe() {
        return celularRecibe;
    }

    public double getPeso() {
        return peso;
    }


    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }

    public void setCelularRecibe(String celularRecibe) {
        this.celularRecibe = celularRecibe;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }


}
