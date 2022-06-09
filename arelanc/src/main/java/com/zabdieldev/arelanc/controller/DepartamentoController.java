package com.zabdieldev.arelanc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabdieldev.arelanc.dto.DepartamentoDTO;
import com.zabdieldev.arelanc.services.DepartamentoService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin("*")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoDTO> listarDepartamentos() {
        return departamentoService.obtenerTodosLosDepartamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> obtenerDepartamentoPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(departamentoService.obtenerDepartamentoPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartamentoDTO> guardarDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO) {
        return new ResponseEntity<>(departamentoService.crearDepartamento(departamentoDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> actualizarDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO,
            @PathVariable(name = "id") long id) {
        DepartamentoDTO departamentoRespuesta = departamentoService.actualizarDepartamento(departamentoDTO, id);
        return new ResponseEntity<>(departamentoRespuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
        departamentoService.eliminarDepartamento(id);
        return new ResponseEntity<>("Departamento eliminado exitosamente", HttpStatus.OK);
    }
}
