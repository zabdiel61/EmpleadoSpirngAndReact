package com.zabdieldev.arelanc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String nombreDelrecurso;
    private String nombreDelCampo;
    private long valorDelCampo;

    public ResourceNotFoundException(String nombreDelrecurso, String nombreDelCampo, long valorDelCampo) {
        super(String.format("%s no encontrado con : %s : '%s'", nombreDelrecurso, nombreDelCampo, valorDelCampo));
        this.nombreDelrecurso = nombreDelrecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }

    public String getNombreDelrecurso() {
        return nombreDelrecurso;
    }

    public void setNombreDelrecurso(String nombreDelrecurso) {
        this.nombreDelrecurso = nombreDelrecurso;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }

    public long getValorDelCampo() {
        return valorDelCampo;
    }

    public void setValorDelCampo(long valorDelCampo) {
        this.valorDelCampo = valorDelCampo;
    }
}
