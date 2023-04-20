package com.example.integradorSpring.Service.patronState;

import com.example.integradorSpring.dto.EnvioEstadoDTO;

public interface Estado {
   String cambiarEstado(EnvioEstadoDTO envioEstadoDTO);
}
