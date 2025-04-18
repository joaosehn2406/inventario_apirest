package com.jceco.inventario_api.dto;

public class FornecedorDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;

    public FornecedorDTO() {}

    public FornecedorDTO(Long id, String nome, String email, String telefone, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnpj = cnpj;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}
