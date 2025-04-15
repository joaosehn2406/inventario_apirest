package com.jceco.inventario_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jceco.inventario_api.dto.ProductDTO;
import com.jceco.inventario_api.entities.Categoria;
import com.jceco.inventario_api.entities.Fornecedor;
import com.jceco.inventario_api.entities.Product;
import com.jceco.inventario_api.entities.pk.ProductPk;
import com.jceco.inventario_api.exceptions.ResourceNotFoundException;
import com.jceco.inventario_api.repositories.CategoriaRepository;
import com.jceco.inventario_api.repositories.FornecedorRepository;
import com.jceco.inventario_api.repositories.ProductRepository;
import com.jceco.inventario_api.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	public ProductServiceImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<ProductDTO> findAll() {
		return repository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO findById(Long id) {
		Product p = repository.findAll().stream()
				.filter(x -> x.getId().getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		return toDTO(p);
	}

	@Override
	public ProductDTO findByCategoryId(Long categoriaId) {
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		return repository.findByCategoria(categoria).stream()
				.findFirst()
				.map(this::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum produto encontrado para a categoria"));
	}

	@Override
	public ProductDTO findByFornecedorId(Long fornecedorId) {
		Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
		return repository.findByFornecedor(fornecedor).stream()
				.findFirst()
				.map(this::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum produto encontrado para o fornecedor"));
	}

	@Override
	public ProductDTO insert(ProductDTO dto) {
		Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedor().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

		ProductPk pk = new ProductPk(dto.getId(), fornecedor.getId());

		Product entity = new Product(pk, dto.getDescricao(), dto.getQtdeEstoque(), dto.getValor(), fornecedor, categoria);

		repository.save(entity);

		return toDTO(entity);
	}

	@Override
	public ProductDTO update(Long id, ProductDTO dto) {
		Product p = repository.findAll().stream()
				.filter(x -> x.getId().getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException());
		
		p.setDescricao(dto.getDescricao());
		p.setValor(dto.getValor());
		p.setqtdeEstoque(dto.getQtdeEstoque());

		if (dto.getCategoria() != null) {
			Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
			p.setCategoria(categoria);
		}

		if (dto.getFornecedor() != null) {
			Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedor().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
			p.setFornecedor(fornecedor);
		}

		repository.save(p);
		return toDTO(p);
	}

	@Override
	public ProductDTO patch(Long id, ProductDTO dto) {
		Product p = repository.findAll().stream()
				.filter(x -> x.getId().getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException());

		if (dto.getDescricao() != null) p.setDescricao(dto.getDescricao());
		if (dto.getValor() != null) p.setValor(dto.getValor());
		if (dto.getQtdeEstoque() != null) p.setqtdeEstoque(dto.getQtdeEstoque());

		repository.save(p);

		return toDTO(p);
	}

	@Override
	public void delete(Long id) {
		Optional<Product> entity = repository.findAll().stream()
				.filter(x -> x.getId().getId().equals(id))
				.findFirst();
		
		if (entity.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
		
		repository.delete(entity.get());
	}
	
	private ProductDTO toDTO(Product product) {
		return new ProductDTO(
			product.getId().getId(),
			product.getDescricao(),
			product.getqtdeEstoque(),
			product.getValor(),
			product.getFornecedor(),
			product.getCategoria()
		);
	}
}
