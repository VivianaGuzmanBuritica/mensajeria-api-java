package com.example.integradorSpring.Service.patronState;


import com.example.integradorSpring.Service.EstadoEnvio;
import com.example.integradorSpring.dto.EnvioEstadoDTO;
import com.example.integradorSpring.entity.Envio;

public class EstadoRecibido implements Estado{
    @Override
    public String cambiarEstado(EnvioEstadoDTO envioEstadoDTO) {
        envioEstadoDTO.setEstado(new EstadoEnRuta());
        return EstadoEnvio.EN_RUTA.getEstado();

    }
}
