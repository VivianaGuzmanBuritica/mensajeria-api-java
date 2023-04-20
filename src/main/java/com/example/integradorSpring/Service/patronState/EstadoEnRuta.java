package com.example.integradorSpring.Service.patronState;

import com.example.integradorSpring.Service.EstadoEnvio;
import com.example.integradorSpring.dto.EnvioEstadoDTO;

import javax.swing.*;

public class EstadoEnRuta implements Estado{
    @Override
    public String cambiarEstado(EnvioEstadoDTO envioEstadoDTO) {
       envioEstadoDTO.setEstado(new EstadoEntregado());
       return EstadoEnvio.ENTREGADO.getEstado();
       }
}
