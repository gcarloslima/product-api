package br.com.edu.iftm.tspi.productapi.models.dto;

import br.com.edu.iftm.tspi.productapi.models.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private String id;
    private String nome;

    public static CategoryDTO convert(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setNome(category.getNome());
        return dto;
    }

    public static Category convert(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setNome(dto.getNome());
        return category;
    }
}
