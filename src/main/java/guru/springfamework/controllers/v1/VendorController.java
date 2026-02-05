package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/vendors")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<VendorListDTO> findAll() {
        return new ResponseEntity<VendorListDTO>(
                new VendorListDTO(vendorService.findAll()), HttpStatus.OK
        );
    }

    @GetMapping({"{id}"})
    public ResponseEntity<VendorDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<VendorDTO>(
                vendorService.findById(id), HttpStatus.OK
        );
    }
}
