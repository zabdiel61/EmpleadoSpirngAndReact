package com.zabdieldev.arelanc.dto;

import com.zabdieldev.arelanc.models.Empleado;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DepartamentoDTO {

    private Long id;

    @NotEmpty
    @Size(min = 4, message = "El nombre del departamento deberia tener al menos 4 caracteres")
    private String nombre;

    private Set<Empleado> empleados;

    public DepartamentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

}
