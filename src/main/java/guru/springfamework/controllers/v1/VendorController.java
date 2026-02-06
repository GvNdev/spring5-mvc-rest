package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO findAll() {
        return vendorService.findAll();
    }

    @GetMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO save(@RequestBody VendorDTO vendorDTO) {
        return vendorService.save(vendorDTO);
    }

    @PutMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO update(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.update(id, vendorDTO);
    }

    @PatchMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patch(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patch(id, vendorDTO);
    }

    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        vendorService.delete(id);
    }
}
