package com.jceco.inventario_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.jceco.inventario_api.dto.MovimentacaoDTO;
import com.jceco.inventario_api.entities.Movimentacao;
import com.jceco.inventario_api.entities.Product;
import com.jceco.inventario_api.entities.enums.TipoMovimentacao;
import com.jceco.inventario_api.exceptions.ResourceNotFoundException;
import com.jceco.inventario_api.repositories.MovimentacaoRepository;
import com.jceco.inventario_api.repositories.ProductRepository;
import com.jceco.inventario_api.services.MovimentacaoService;

import jakarta.persistence.EntityNotFoundException;

public class MovimentacaoServiceImpl implements MovimentacaoService{

	private MovimentacaoRepository repository;
	
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
				.orElseThrow(() -> new EntityNotFoundException());
		
		
		return toDTO(mov);
	}

	@Override
	public List<MovimentacaoDTO> findByProductId(Long idProd) {
	    List<Movimentacao> movs = repository.findByProduto_Id_Id(idProd);
	    return movs.stream().map(this::toDTO).collect(Collectors.toList());
	}



	@Override
	public MovimentacaoDTO insert(MovimentacaoDTO dto) {
		
		Product product = productRepository.findAll().stream()
				.filter(p -> p.getId().getId().equals(dto.getProdutoId()))
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException());
		
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao(dto.getDescricao());
		movimentacao.setQuantidade(dto.getQuantidade());
		movimentacao.setTipo(TipoMovimentacao.valueOf(dto.getTipo()));
		movimentacao.setProduto(product);
		
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
		
		repository.deleteById(id);
		
	}
	
	private MovimentacaoDTO toDTO(Movimentacao mov) {
	    return new MovimentacaoDTO(
	        mov.getId(),
	        mov.getDescricao(),
	        mov.getQuantidade(),
	        mov.getTipo() != null ? mov.getTipo().name() : null,
	        mov.getData(),
	        mov.getProduto() != null ? mov.getProduto().getId().getId() : null,
	        mov.getProduto() != null ? mov.getProduto().getDescricao() : null
	    );
	}

	
}
