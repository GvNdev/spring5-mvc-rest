package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {
    public static final String VENDOR_NAME = "VendorTest";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void findAll() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(1L);
        vendorDTO1.setName(VENDOR_NAME);

        VendorDTO vendorDTO2 = new VendorDTO();
        vendorDTO2.setId(2L);
        vendorDTO2.setName("Other");

        List<VendorDTO> vendorDTOS = Arrays.asList(vendorDTO1, vendorDTO2);

        when(vendorService.findAll()).thenReturn(vendorDTOS);

        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void findByName() throws Exception {
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(1L);
        vendorDTO1.setName(VENDOR_NAME);

        when(vendorService.findById(anyLong())).thenReturn(vendorDTO1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)));
    }

    @Test
    public void save() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        VendorDTO returnVendorDTO = new VendorDTO();
        returnVendorDTO.setName(vendorDTO.getName());
        returnVendorDTO.setSelfLink("/api/v1/vendor/1");

        when(vendorService.save(vendorDTO)).thenReturn(returnVendorDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Vendor")))
                .andExpect(jsonPath("$.self_link", equalTo("/api/v1/vendor/1")));
    }

    @Test
    public void update() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        VendorDTO returnVendorDTO = new VendorDTO();
        returnVendorDTO.setName(vendorDTO.getName());
        returnVendorDTO.setSelfLink("/api/v1/vendor/1");

        when(vendorService.update(anyLong(), any(VendorDTO.class))).thenReturn(returnVendorDTO);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor")))
                .andExpect(jsonPath("$.self_link", equalTo("/api/v1/vendor/1")));
    }

    @Test
    public void patch() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        VendorDTO returnVendorDTO = new VendorDTO();
        returnVendorDTO.setName(vendorDTO.getName());
        returnVendorDTO.setSelfLink("/api/v1/vendor/1");

        when(vendorService.patch(anyLong(), any(VendorDTO.class))).thenReturn(returnVendorDTO);

        mockMvc.perform(MockMvcRequestBuilders.patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor")))
                .andExpect(jsonPath("$.self_link", equalTo("/api/v1/vendor/1")));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).delete(anyLong());
    }
}