package guru.springfamework.repositories;

import guru.springfamework.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 9/24/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
