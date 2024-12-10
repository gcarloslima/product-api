package br.com.edu.iftm.tspi.productapi.models.dto;

import br.com.edu.iftm.tspi.productapi.models.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String productIdentifier;
    private String nome;
    private Double preco;
    private String category;

    public static ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setProductIdentifier(product.getProductIdentifier());
        dto.setNome(product.getNome());
        dto.setPreco(product.getPreco());
        dto.setCategory(product.getCategory() != null ? product.getCategory().getId() : null);
        return dto;
    }

    public static Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductIdentifier(dto.getProductIdentifier());
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        return product;
    }
}
