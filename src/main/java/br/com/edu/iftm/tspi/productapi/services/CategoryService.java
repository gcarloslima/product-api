package br.com.edu.iftm.tspi.productapi.services;

import br.com.edu.iftm.tspi.productapi.exceptions.ResourceNotFoundException;
import br.com.edu.iftm.tspi.productapi.models.Category;
import br.com.edu.iftm.tspi.productapi.models.dto.CategoryDTO;
import br.com.edu.iftm.tspi.productapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryDTO> getAll() {
        return repository.findAll().stream().map(CategoryDTO::convert).toList();
    }

    public Page<CategoryDTO> getAllPage(Pageable pageable) {
        return repository.findAll(pageable).map(CategoryDTO::convert);
    }

    public CategoryDTO save(CategoryDTO dto) {
        Category category = CategoryDTO.convert(dto);
        return CategoryDTO.convert(repository.save(category));
    }

    public CategoryDTO update(String id, CategoryDTO dto) {
        Category category = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        category.setNome(dto.getNome());
        return CategoryDTO.convert(repository.save(category));
    }

    public void delete(String id) {
        Category category = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        repository.delete(category);
    }
}
