package com.rafael.crud.controllers;

import com.rafael.crud.modelo.Produto;
import com.rafael.crud.modelo.ProdutoDTO;
import com.rafael.crud.servicos.RepositorioProduto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private RepositorioProduto repositorio;
    private static final Logger logger = LoggerFactory.getLogger(ProdutosController.class);

    @GetMapping({"", "/"})
    public String mostrarTodos(Model model) {
        List<Produto> produtos = repositorio.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("produtos", produtos);
        return "produtos/index";
    }

    @GetMapping("/criar")
    public String criarPagina(Model model) {
        ProdutoDTO dto = new ProdutoDTO();
        model.addAttribute("produtoDto", dto);
        return "produtos/criarProduto";
    }

    @PostMapping("/criar")
    public String criarProduto(@Valid @ModelAttribute("produtoDto") ProdutoDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "produtos/criarProduto";
        }
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setMarca(dto.getMarca());
        produto.setCategoria(dto.getCategoria());
        repositorio.save(produto);

        return "redirect:/produtos";
    }

    @GetMapping("/editar")
    public String showEditPage(@RequestParam int id, Model model){
        try{
            Produto produto = repositorio.findById(id).get();
            model.addAttribute("produto", produto);
            ProdutoDTO dto = new ProdutoDTO();
            dto.setNome(produto.getNome());
            dto.setDescricao(produto.getDescricao());
            dto.setPreco(produto.getPreco());
            dto.setMarca(produto.getMarca());
            dto.setCategoria(produto.getCategoria());

            model.addAttribute("produtoDto", dto);


        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/produtos";
        }

        return "produtos/editarProduto";
    }

    @PostMapping("/editar")
    public String atualizarProduto(Model model, @RequestParam int id, @Valid @ModelAttribute("produtoDto") ProdutoDTO dto, BindingResult bindingResult){
        try{
            Produto produto = repositorio.findById(id).get();
            model.addAttribute("produto", produto);

            if(bindingResult.hasErrors()){
                return "produtos/editarProduto";
            }
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());
            produto.setPreco(dto.getPreco());
            produto.setMarca(dto.getMarca());
            produto.setCategoria(dto.getCategoria());

            repositorio.save(produto);


        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/produtos";
    }

    @GetMapping("/deletar")
    public String deletarProduto(@RequestParam int id){

        try{
            Produto produto = repositorio.findById(id).get();
            repositorio.delete(produto);
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/produtos";
    }


}
