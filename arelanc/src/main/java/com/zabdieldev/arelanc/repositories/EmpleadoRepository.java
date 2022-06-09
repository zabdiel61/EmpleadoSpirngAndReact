package com.zabdieldev.arelanc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zabdieldev.arelanc.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    public Optional<Empleado> findByEmail(String email);

    public Optional<Empleado> findByUsernameOrEmail(String username, String email);

    public Optional<Empleado> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
