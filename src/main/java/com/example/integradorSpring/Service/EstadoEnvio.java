package com.example.integradorSpring.Service;

public enum EstadoEnvio {
    ENTREGADO("ENTREGADO"),
    EN_RUTA("EN_RUTA"),
    RECIBIDO("RECIBIDO");

    private String estado;
    EstadoEnvio(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
