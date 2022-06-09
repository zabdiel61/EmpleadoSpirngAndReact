package com.zabdieldev.arelanc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zabdieldev.arelanc.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByNombre(String nombre);
}
