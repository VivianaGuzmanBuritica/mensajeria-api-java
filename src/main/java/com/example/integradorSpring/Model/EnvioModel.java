package com.example.integradorSpring.Model;

public class EnvioModel {
    private String umGuia;
    private Cliente cliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private  String dirDestino;
    private String nombreRecibe;
    private String celularRecibe;
    private String horaEntrega;
    private String estado;
    private double valorEnvio;
    private PaqueteModel paquete;

    public EnvioModel(String umGuia, Cliente cliente, String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreRecibe, String celularRecibe, String horaEntrega, String estado, double valorEnvio, PaqueteModel paquete) {
        this.umGuia = umGuia;
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreRecibe = nombreRecibe;
        this.celularRecibe = celularRecibe;
        this.horaEntrega = horaEntrega;
        this.estado = estado;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }

    public EnvioModel(Cliente cliente, String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreRecibe, String celularRecibe, String horaEntrega, String estado, double valorEnvio, PaqueteModel paquete) {
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreRecibe = nombreRecibe;
        this.celularRecibe = celularRecibe;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }

    public EnvioModel() {
    }

    @Override
    public String toString() {
        return "Envio{" +
                "umGuia='" + umGuia + '\'' +
                ", cliente=" + cliente +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                ", ciudadDestino='" + ciudadDestino + '\'' +
                ", dirDestino='" + dirDestino + '\'' +
                ", nombreRecibe='" + nombreRecibe + '\'' +
                ", celularRecibe='" + celularRecibe + '\'' +
                ", horaEntrega='" + horaEntrega + '\'' +
                ", estado='" + estado + '\'' +
                ", valorEnvio=" + valorEnvio +
                ", paquete=" + paquete +
                '}';
    }


    public double calcularValorEnvio(String tipo){
        if(tipo.equals("LIVIANO")){
            return 30000;
        }else if(tipo.equals("MEDIANO")){
            return  40000;

        }else if(tipo.equals("GRANDE")){
            return 50000;
        }else {
            throw new RuntimeException("el valor ingresado no pertenece a un tamaño de paquete");
        }
    }
}
