package guru.springfamework.bootstrap;

import guru.springfamework.domain.Vendor;
import guru.springfamework.domain.Product;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private ProductRepository productRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(ProductRepository productRepository,
                     VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
        loadVendors();
    }

    private void loadProducts() {
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

        System.out.println("Product data loaded = " + productRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("First Vendor");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Second Vendor");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);

        System.out.println("Vendor data loaded = " + vendorRepository.count());
    }
}
