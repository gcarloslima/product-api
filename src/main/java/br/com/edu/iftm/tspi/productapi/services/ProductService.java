package br.com.edu.iftm.tspi.productapi.services;

import br.com.edu.iftm.tspi.productapi.exceptions.ResourceNotFoundException;
import br.com.edu.iftm.tspi.productapi.models.Category;
import br.com.edu.iftm.tspi.productapi.models.Product;
import br.com.edu.iftm.tspi.productapi.models.dto.ProductDTO;
import br.com.edu.iftm.tspi.productapi.repositories.CategoryRepository;
import br.com.edu.iftm.tspi.productapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(ProductDTO::convert).toList();
    }

    public ProductDTO findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ProductDTO.convert(product);
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier)
                .orElseThrow(ResourceNotFoundException::new);
        return ProductDTO.convert(product);
    }

    public List<ProductDTO> getByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId).stream().map(ProductDTO::convert).toList();
    }

    public Page<ProductDTO> getAllPage(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::convert);
    }

    public ProductDTO save(ProductDTO dto) {
        Product product = ProductDTO.convert(dto);
        Category category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(ResourceNotFoundException::new);
        product.setCategory(category);
        return ProductDTO.convert(productRepository.save(product));
    }

    public ProductDTO update(String id, ProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        if (dto.getCategory() != null) {
            Category category = categoryRepository.findById(dto.getCategory())
                    .orElseThrow(ResourceNotFoundException::new);
            product.setCategory(category);
        }
        return ProductDTO.convert(productRepository.save(product));
    }

    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        productRepository.delete(product);
    }
}
