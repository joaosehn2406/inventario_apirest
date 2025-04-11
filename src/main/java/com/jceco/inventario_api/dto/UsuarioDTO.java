package com.jceco.inventario_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jceco.inventario_api.entities.enums.TipoCargo;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String cargo; 

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, TipoCargo cargoEnum) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargoEnum != null ? cargoEnum.name() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @JsonIgnore
    public TipoCargo getCargoEnum() {
        if (cargo != null) {
            return TipoCargo.valueOf(cargo);
        }
        return null;
    }
}
