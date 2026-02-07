package guru.springfamework.controllers.v1;

import guru.springfamework.model.VendorDTO;
import guru.springfamework.model.VendorListDTO;
import guru.springfamework.services.ResourceNotFoundException;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {
    @MockBean // provided by Spring Context
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc; // provided by Spring Context

    VendorDTO vendorDTO_1;
    VendorDTO vendorDTO_2;

    @Before
    public void setUp() throws Exception {
        vendorDTO_1 = new VendorDTO();
        vendorDTO_1.setName("Vendor 1");
        vendorDTO_1.setSelfLink(VendorController.BASE_URL + "/1");

        vendorDTO_2 = new VendorDTO();
        vendorDTO_2.setName("Vendor 2");
        vendorDTO_2.setSelfLink(VendorController.BASE_URL + "/2");
    }

    @Test
    public void findAll() throws Exception {
        List<VendorDTO> vendorListDTO = Arrays.asList(vendorDTO_1, vendorDTO_2);

        given(vendorService.findAll()).willReturn(vendorListDTO);

        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void findById() throws Exception {
        given(vendorService.findById(anyLong())).willReturn(vendorDTO_1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void findByIdNotFound() throws Exception {
        given(vendorService.findById(anyLong())).willThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.BASE_URL + "/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void save() throws Exception {
        given(vendorService.save(any())).willReturn(vendorDTO_1);

        mockMvc.perform(post(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())))
                .andExpect(jsonPath("$.selfLink", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void update() throws Exception {
        given(vendorService.update(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void patch() throws Exception {
        given(vendorService.patch(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(MockMvcRequestBuilders.patch(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(VendorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendorService).should().delete(anyLong());
    }
}