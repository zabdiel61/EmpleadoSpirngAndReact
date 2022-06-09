package com.zabdieldev.arelanc.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabdieldev.arelanc.dto.LoginDTO;
import com.zabdieldev.arelanc.dto.RegistroDTO;
import com.zabdieldev.arelanc.models.Departamento;
import com.zabdieldev.arelanc.models.Empleado;
import com.zabdieldev.arelanc.models.Rol;
import com.zabdieldev.arelanc.repositories.DepartamentoRepository;
import com.zabdieldev.arelanc.repositories.EmpleadoRepository;
import com.zabdieldev.arelanc.repositories.RolRepository;
import com.zabdieldev.arelanc.security.JWTAuthResponseDTO;
import com.zabdieldev.arelanc.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // obtenemos el token del jwtProvider
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        if (empleadoRepository.existsByUsername(registroDTO.getUsername())) {
            return new ResponseEntity<>("ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if (empleadoRepository.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Empleado empleado = new Empleado();
        empleado.setNombre(registroDTO.getNombre());
        empleado.setApellido(registroDTO.getApellido());
        empleado.setDni(registroDTO.getDni());
        empleado.setDireccion(registroDTO.getDireccion());
        empleado.setUsername(registroDTO.getUsername());
        empleado.setEmail(registroDTO.getEmail());
        empleado.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Departamento departamentos = departamentoRepository.findByNombre("Contabilidad").get();
        empleado.setDepartamento(Collections.singleton(departamentos));
        Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();
        empleado.setRol(Collections.singleton(roles));

        empleadoRepository.save(empleado);

        return new ResponseEntity<>("usuario registrado exitosamente! ", HttpStatus.OK);
    }

}
