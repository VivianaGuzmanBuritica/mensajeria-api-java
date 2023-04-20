package com.example.integradorSpring.dto;

import com.example.integradorSpring.Service.EstadoEnvio;
import com.example.integradorSpring.Service.patronState.Estado;
import com.example.integradorSpring.Service.patronState.EstadoRecibido;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvioEstadoDTO {
    private static final String ESTADO_INICIAL = "RECIBIDO";
    private Estado estado;

    @Autowired
    public EnvioEstadoDTO() {
        this.estado = new EstadoRecibido();
    }


    public String cambiarEstado(){
        return estado.cambiarEstado(this);
    }


    public String getEstadoInicial() {
     //  return  EstadoEnvio.RECIBIDO.getEstado();
      return ESTADO_INICIAL;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


}
