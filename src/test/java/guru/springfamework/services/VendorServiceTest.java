package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.impl.VendorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceTest {
    public static final Long VENDOR_ID = 1L;
    public static final String VENDOR_NAME = "Test Vendor";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void findAll() {
        // Given
        List<Vendor> vendors = Arrays.asList(new Vendor(),  new Vendor(),  new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);

        // When
        List<VendorDTO> vendorDTOS = vendorService.findAll();

        // Then
        assertEquals(3, vendorDTOS.size());
    }

    @Test
    public void findById() {
        // Given
        Vendor vendor = new Vendor();
        vendor.setId(VENDOR_ID);
        vendor.setName(VENDOR_NAME);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));

        // When
        VendorDTO vendorDTO = vendorService.findById(VENDOR_ID);

        // Then
        assertEquals(VENDOR_ID, vendorDTO.getId());
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }

    @Test
    public void save() {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor savedVendor = new Vendor();
        savedVendor.setId(1L);
        savedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // When
        VendorDTO savedVendorDTO = vendorService.save(vendorDTO);

        // Then
        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals("/api/v1/vendor/1", savedVendorDTO.getSelfLink());
    }

    @Test
    public void update() {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor savedVendor = new Vendor();
        savedVendor.setId(1L);
        savedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // When
        VendorDTO savedVendorDTO = vendorService.update(1L, vendorDTO);

        // Then
        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals("/api/v1/vendor/1", savedVendorDTO.getSelfLink());
    }
}