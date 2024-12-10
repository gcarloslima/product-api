package br.com.edu.iftm.tspi.productapi.controllers;

import br.com.edu.iftm.tspi.productapi.models.dto.ProductDTO;
import br.com.edu.iftm.tspi.productapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<ProductDTO>> getAllPage(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategoryId(@PathVariable String categoryId) {
        return ResponseEntity.ok(productService.getByCategoryId(categoryId));
    }

    @GetMapping("/identifier/{productIdentifier}")
    public ResponseEntity<ProductDTO> findByProductIdentifier(@PathVariable String productIdentifier) {
        return ResponseEntity.ok(productService.findByProductIdentifier(productIdentifier));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
