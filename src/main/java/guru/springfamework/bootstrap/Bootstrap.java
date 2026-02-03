package guru.springfamework.bootstrap;

import guru.springfamework.domain.Product;
import guru.springfamework.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private ProductRepository productRepository;

    public Bootstrap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product banana = new Product();
        banana.setName("Banana");
        banana.setPrice(1.5);

        Product apple = new Product();
        apple.setName("Apple");
        apple.setPrice(1.75);

        Product pineapple = new Product();
        pineapple.setName("Pineapple");
        pineapple.setPrice(2.1);

        Product mango = new Product();
        mango.setName("Mango");
        mango.setPrice(1.3);

        Product pear = new Product();
        pear.setName("Pear");
        pear.setPrice(1.25);

        productRepository.save(apple);
        productRepository.save(banana);
        productRepository.save(pineapple);
        productRepository.save(mango);
        productRepository.save(pear);

        System.out.println("Data loaded = " + productRepository.count());
    }
}
