package com.example.integradorSpring.dto;

public class EnvioCreadoDTO {
    private Integer numGuia;
    private String estado = "ENTREGADO";

    public EnvioCreadoDTO(Integer numGuia, String estado) {
        this.numGuia = numGuia;
        this.estado = estado;
    }

    public Integer getNumGuia() {
        return numGuia;
    }

    public String getEstado() {
        return estado;
    }
}
