package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {
    public static final long VENDOR_ID = 1L;
    public static final String VENDOR_NAME = "Fresh Fruits from Ecuador Ltd.";

    VendorMapper customerMapper = VendorMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        // Given
        Vendor vendor = new Vendor();
        vendor.setId(VENDOR_ID);
        vendor.setName(VENDOR_NAME);

        // When
        VendorDTO vendorDTO = customerMapper.vendorToVendorDTO(vendor);

        // Then
        assertEquals(Long.valueOf(VENDOR_ID), vendorDTO.getId());
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }
}