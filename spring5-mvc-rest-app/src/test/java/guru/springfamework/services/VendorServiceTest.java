package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.domain.Vendor;
import guru.springfamework.model.VendorDTO;
import guru.springfamework.model.VendorListDTO;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.impl.VendorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class VendorServiceTest {
    public static final String NAME_1 = "My Vendor";
    public static final Long ID_1 = 1L;
    public static final String NAME_2 = "My Vendor";
    public static final Long ID_2 = 2L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void findAll() {
        // Given
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        // When
        List<VendorDTO> vendorListDTO = vendorService.findAll();

        // Then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.size(), is(equalTo(2)));
    }

    @Test
    public void findById() {
        // Given
        Vendor vendor = getVendor1();

        // Mockito BDD syntax
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        // When
        VendorDTO vendorDTO = vendorService.findById(1L);

        // Then
        then(vendorRepository).should(times(1)).findById(anyLong());

        // JUnit Assert that with matchers
        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void save() {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor savedVendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        // When
        VendorDTO savedVendorDTO = vendorService.save(vendorDTO);

        // Then
        // 'Should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getSelfLink(), containsString("1"));
    }

    @Test
    public void update() {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor savedVendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        // When
        VendorDTO savedVendorDTO = vendorService.update(ID_1, vendorDTO);

        // Then
        // 'Should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getSelfLink(), containsString("1"));
    }

    @Test
    public void patch() {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        Vendor savedVendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(savedVendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        // When
        VendorDTO savedVendorDTO = vendorService.patch(ID_1, vendorDTO);

    }

    @Test
    public void delete() {
        // When
        vendorRepository.deleteById(1L);

        // Then
        then(vendorRepository).should().deleteById(anyLong());
    }


    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setId(ID_1);
        vendor.setName(NAME_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setId(ID_2);
        vendor.setName(NAME_2);
        return vendor;
    }
}