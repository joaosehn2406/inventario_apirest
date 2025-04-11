package com.jceco.inventario_api.entities;

import com.jceco.inventario_api.entities.enums.TipoCargo;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer cargo;  

    public Usuario() {}

    public Usuario(Long id, String nome, TipoCargo cargo) {
        this.id = id;
        this.nome = nome;
        setCargo(cargo);
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

    public TipoCargo getCargo() {
        return TipoCargo.valueOf(cargo);
    }

    public void setCargo(TipoCargo cargo) {
        if (cargo != null) {
            this.cargo = cargo.getCode();
        }
    }
    
    
}
