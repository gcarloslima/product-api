package br.com.edu.iftm.tspi.productapi.repositories;

import br.com.edu.iftm.tspi.productapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryId(String categoryId);
    Optional<Product> findByProductIdentifier(String productIdentifier);
}
