package com.example.integradorSpring.repository;

import com.example.integradorSpring.dto.EnvioDetalleDTO;
import com.example.integradorSpring.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository <Envio, Integer> {

    @Query(value = "select * from envio where estado =:estado", nativeQuery = true)
    public List<Envio> filtrarPorEstado(@Param("estado") String tipo);
}
