package com.example.integradorSpring.Service;

import com.example.integradorSpring.dto.PaqueteDTO;
import com.example.integradorSpring.entity.Paquete;
import com.example.integradorSpring.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteService {

    private PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }



}
