package com.zabdieldev.arelanc.services;

import com.zabdieldev.arelanc.dto.DepartamentoDTO;

import java.util.List;

public interface DepartamentoService {
    public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO);

    public List<DepartamentoDTO> obtenerTodosLosDepartamentos();

    public DepartamentoDTO obtenerDepartamentoPorId(long id);

    public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, long id);

    public void eliminarDepartamento(long id);
}
