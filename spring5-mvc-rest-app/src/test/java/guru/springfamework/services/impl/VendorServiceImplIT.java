package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Vendor;
import guru.springfamework.model.VendorDTO;
import guru.springfamework.repositories.ProductRepository;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiceImplIT {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ProductRepository productRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Vendor Data");
        System.out.println(vendorRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(productRepository, vendorRepository);
        bootstrap.run();

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void patchName() {
        String updatedName = "UpdatedVendorName";
        long id = getVendorId();

        Vendor originalVendor = vendorRepository.getOne(id);
        assertNotNull(originalVendor);

        String originalName = originalVendor.getName();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(updatedName);

        vendorService.patch(id, vendorDTO);

        Vendor updatedVendor = vendorRepository.findById(id).get();

        assertNotNull(updatedVendor);
        assertEquals(updatedName, updatedVendor.getName());
        assertThat(originalName, not(equalTo(updatedVendor.getName())));
    }

    private Long getVendorId() {
        List<Vendor> vendors = vendorRepository.findAll();
        System.out.println("Vendors found: " + vendors.size());
        return vendors.get(0).getId();
    }
}