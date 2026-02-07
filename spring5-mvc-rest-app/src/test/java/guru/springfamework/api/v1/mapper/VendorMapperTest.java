package guru.springfamework.api.v1.mapper;

import guru.springfamework.domain.Vendor;
import guru.springfamework.model.VendorDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {
    public static final String VENDOR_NAME = "Fresh Fruits from Ecuador Ltd.";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {
        // Given
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR_NAME);

        // When
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // Then
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendor() {
        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR_NAME);

        // When
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // Then
        assertEquals(VENDOR_NAME, vendor.getName());
    }
}