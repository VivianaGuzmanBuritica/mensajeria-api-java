package com.example.integradorSpring.Service.patronState;

import com.example.integradorSpring.dto.EnvioEstadoDTO;

public class EstadoEntregado implements Estado{
    @Override
    public String cambiarEstado(EnvioEstadoDTO envioEstadoDTO) {
        return "Error: El paquete ya ha sido entregado";
    }
}
