package br.com.edu.iftm.tspi.productapi.repositories;

import br.com.edu.iftm.tspi.productapi.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
