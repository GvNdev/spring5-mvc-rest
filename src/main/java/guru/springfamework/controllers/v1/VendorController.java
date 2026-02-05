package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";

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

    @PostMapping
    public ResponseEntity<VendorDTO> save(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(
                vendorService.save(vendorDTO), HttpStatus.CREATED
        );
    }

    @PutMapping({"{id}"})
    public ResponseEntity<VendorDTO> update(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(
                vendorService.update(id, vendorDTO), HttpStatus.OK
        );
    }

    @PatchMapping({"{id}"})
    public ResponseEntity<VendorDTO> patch(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<VendorDTO>(
                vendorService.patch(id, vendorDTO), HttpStatus.OK
        );
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendorService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
