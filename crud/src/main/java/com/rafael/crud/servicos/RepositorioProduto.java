package com.rafael.crud.servicos;

import com.rafael.crud.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {
}
