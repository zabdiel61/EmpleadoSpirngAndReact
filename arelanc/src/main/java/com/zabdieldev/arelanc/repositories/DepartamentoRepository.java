package com.zabdieldev.arelanc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zabdieldev.arelanc.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    public Optional<Departamento> findByNombre(String nombre);
}
