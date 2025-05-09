package com.jceco.inventario_api.services.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jceco.inventario_api.dto.MovimentacaoDTO;
import com.jceco.inventario_api.dto.MovimentacaoProdutoDTO;

import com.jceco.inventario_api.entities.Fornecedor;
import com.jceco.inventario_api.entities.Movimentacao;
import com.jceco.inventario_api.entities.MovimentacaoProduto;
import com.jceco.inventario_api.entities.Product;
import com.jceco.inventario_api.entities.enums.TipoMovimentacao;
import com.jceco.inventario_api.entities.pk.ProductPk;
import com.jceco.inventario_api.exceptions.DataBaseException;
import com.jceco.inventario_api.exceptions.ResourceNotFoundException;
import com.jceco.inventario_api.repositories.MovimentacaoRepository;
import com.jceco.inventario_api.repositories.ProductRepository;
import com.jceco.inventario_api.services.MovimentacaoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService{

	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	

	
	
	

	public MovimentacaoServiceImpl(MovimentacaoRepository repository, ProductRepository productRepository) {
		super();
		this.repository = repository;
		this.productRepository = productRepository;
	}

	@Override
	public List<MovimentacaoDTO> findAll() {
		return repository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public MovimentacaoDTO findById(Long id) {
		Movimentacao mov = repository.findAll().stream()
				.filter(x -> x.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		
		return toDTO(mov);
	}

	@Override
	public List<MovimentacaoDTO> findByProductId(Long idProd) {
	    List<Movimentacao> movs = repository.findByProduto_Id_Id(idProd);
	    return movs.stream().map(this::toDTO).collect(Collectors.toList());
	}



	@Override
	public MovimentacaoDTO insert(MovimentacaoDTO dto) {

	    Movimentacao movimentacao = new Movimentacao();
	    movimentacao.setDescricao(dto.getDescricao());
	    movimentacao.setQuantidade(dto.getQuantidade());
	    movimentacao.setTipo(TipoMovimentacao.valueOf(dto.getTipo()));
	    movimentacao.setData(LocalDate.now());


	    List<MovimentacaoProduto> produtos = dto.getProdutos().stream()
	        .map(produtoDTO -> {


	            ProductPk pk = new ProductPk(produtoDTO.getProdutoId(), produtoDTO.getFornecedorId());
	            Product product = productRepository.findById(pk)
	                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));


	            Fornecedor fornecedor = product.getFornecedor(); 


	            MovimentacaoProduto movProd = new MovimentacaoProduto();
	            movProd.setProduto(product); 
	            movProd.setQuantidade(produtoDTO.getQuantidade());


	            return movProd;
	        })
	        .collect(Collectors.toList());


	    movimentacao.setProdutos(produtos);


	    movimentacao = repository.save(movimentacao);

	    return toDTO(movimentacao);
	}








	@Override
	public MovimentacaoDTO update(Long id, MovimentacaoDTO dto) {
		
		try {
			Movimentacao mov = repository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException());
			
			dto.setDescricao(mov.getDescricao());
			dto.setData(mov.getData());
			dto.setQuantidade(dto.getQuantidade());
			dto.setTipo(dto.getTipo());
			
			mov = repository.save(mov);
			
			return toDTO(mov);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}

	@Override
	public MovimentacaoDTO patch(Long id, MovimentacaoDTO dto) {
		
		Movimentacao mov = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		if(mov.getDescricao() != null) dto.setDescricao(mov.getDescricao());
		if(mov.getData() != null) dto.setData(mov.getData());
		if(mov.getQuantidade() != null) dto.setQuantidade(dto.getQuantidade());
		if(mov.getTipo() != null) dto.setTipo(dto.getTipo());
		
		mov = repository.save(mov);
		
		return toDTO(mov);
	}

	@Override
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
		
		repository.deleteById(id);
		
	}
	
	private MovimentacaoDTO toDTO(Movimentacao mov) {

	    List<MovimentacaoProdutoDTO> produtosDTO = mov.getProdutos().stream()
	        .map(produto -> new MovimentacaoProdutoDTO(
	            produto.getProduto().getId().getId(), 
	            produto.getQuantidade(),               
	            produto.getProduto().getDescricao(), 
	            produto.getProduto().getFornecedor() != null ? produto.getProduto().getFornecedor().getId() : null  
	        ))
	        .collect(Collectors.toList());

	    return new MovimentacaoDTO(
	        mov.getId(),                            
	        mov.getDescricao(),                     
	        mov.getQuantidade(),                    
	        mov.getTipo() != null ? mov.getTipo().name() : null, 
	        mov.getData(),                          
	        produtosDTO                             
	    );
	}






	
}
