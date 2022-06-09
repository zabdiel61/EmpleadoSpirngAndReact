/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zabdieldev.arelanc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jonat
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin("*")
public class Testcontroller {
    
    @GetMapping("/all")
    public String allAccess(){
        return "contenido publico";
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasRole('CONSULTOR') or hasRole('ADMIN')")
    public String userAccess(){
        return "Contenido de usuario";
    }
    
    @GetMapping("/mod")
    @PreAuthorize("hasRole('CONSULTOR')")
    public String moderatorAccess(){
        return "Moderador Board";
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "admin Board";
    }
    
}
