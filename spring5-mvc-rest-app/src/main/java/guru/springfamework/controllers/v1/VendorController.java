package guru.springfamework.controllers.v1;

import guru.springfamework.model.VendorDTO;
import guru.springfamework.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "This is my Vendor Controller")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "This will get a list of all vendors.", notes = "These are some notes about the findAll function")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO findAll() {
        VendorListDTO vendorListDTO = new VendorListDTO();
        vendorListDTO.getVendors().addAll(vendorService.findAll());
        return vendorListDTO;
    }

    @ApiOperation(value = "This will get a vendor found by id.", notes = "These are some notes about the findById function")
    @GetMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @ApiOperation(value = "This will create a new vendor.", notes = "These are some notes about the save function")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO save(@RequestBody VendorDTO vendorDTO) {
        return vendorService.save(vendorDTO);
    }

    @ApiOperation(value = "This will update an existing vendor if exist. Needed id", notes = "These are some notes about the update function")
    @PutMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO update(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.update(id, vendorDTO);
    }

    @ApiOperation(value = "This will update given properties for a specific vendor if exist. Needed id", notes = "These are some notes about the patch function")
    @PatchMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patch(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patch(id, vendorDTO);
    }

    @ApiOperation(value = "This will delete a specific vendor if exist. Needed id", notes = "These are some notes about the delete function")
    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        vendorService.delete(id);
    }
}
