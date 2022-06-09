package com.zabdieldev.arelanc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabdieldev.arelanc.dto.DepartamentoDTO;
import com.zabdieldev.arelanc.exceptions.ResourceNotFoundException;
import com.zabdieldev.arelanc.models.Departamento;
import com.zabdieldev.arelanc.repositories.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {
        // convertimos DTO A entidad
        Departamento departamento = mapearEntidad(departamentoDTO);

        Departamento nuevoDepartamento = departamentoRepository.save(departamento);

        // convertimos de entidad a Dto
        DepartamentoDTO departamentoRespuesta = mapearDTO(nuevoDepartamento);

        return departamentoRespuesta;
    }

    @Override
    public List<DepartamentoDTO> obtenerTodosLosDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return departamentos.stream().map(departamento -> mapearDTO(departamento)).collect(Collectors.toList());

    }

    @Override
    public DepartamentoDTO obtenerDepartamentoPorId(long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("departamento", "id", id));
        return mapearDTO(departamento);
    }

    @Override
    public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("departamento", "id", id));

        departamento.setNombre(departamentoDTO.getNombre());

        Departamento departamentoActualizado = departamentoRepository.save(departamento);
        return mapearDTO(departamentoActualizado);
    }

    @Override
    public void eliminarDepartamento(long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("departamento", "id", id));
        departamentoRepository.delete(departamento);

    }

    // convierte de entidad a DTO
    private DepartamentoDTO mapearDTO(Departamento departamento) {
        DepartamentoDTO departamentoDTO = modelMapper.map(departamento, DepartamentoDTO.class);

        return departamentoDTO;
    }

    // convierte de DTO a entidad
    private Departamento mapearEntidad(DepartamentoDTO departamentoDTO) {
        Departamento departamento = modelMapper.map(departamentoDTO, Departamento.class);

        return departamento;
    }

}
