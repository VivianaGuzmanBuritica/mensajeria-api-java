package com.example.integradorSpring.repository;

import com.example.integradorSpring.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepesitory extends JpaRepository<Empleado, Integer> {
}
