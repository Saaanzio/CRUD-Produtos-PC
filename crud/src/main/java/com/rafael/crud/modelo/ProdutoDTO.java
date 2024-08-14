package com.rafael.crud.modelo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProdutoDTO {
    @NotEmpty(message = "Insira um valor valido")
    private String nome;
    @NotEmpty(message = "Insira um valor valido")
    private String marca;
    @NotEmpty(message = "Insira um valor valido")
    private String categoria;
    @Min(0)
    private double preco;

    @Size(min = 10, message = "A descricao deve ter ao menos 10 caracteres")
    @Size(max = 100, message = "A descricao deve ter ao maximo 100 caracteres")
    private String descricao;
    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                '}';
    }
    public @NotEmpty(message = "Insira um valor valido") String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty(message = "Insira um valor valido") String nome) {
        this.nome = nome;
    }

    public @NotEmpty(message = "Insira um valor valido") String getMarca() {
        return marca;
    }

    public void setMarca(@NotEmpty(message = "Insira um valor valido") String marca) {
        this.marca = marca;
    }

    public @NotEmpty(message = "Insira um valor valido") String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotEmpty(message = "Insira um valor valido") String categoria) {
        this.categoria = categoria;
    }

    @Min(0)
    public double getPreco() {
        return preco;
    }

    public void setPreco(@Min(0) double preco) {
        this.preco = preco;
    }

    public @Size(min = 10, message = "A descricao deve ter ao menos 10 caracteres") @Size(max = 100, message = "A descricao deve ter ao maximo 100 caracteres") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Size(min = 10, message = "A descricao deve ter ao menos 10 caracteres") @Size(max = 100, message = "A descricao deve ter ao maximo 100 caracteres") String descricao) {
        this.descricao = descricao;
    }
}
