package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;

public interface VendorService {
    VendorListDTO findAll();
    VendorDTO findById(Long id);
    VendorDTO save(VendorDTO vendorDTO);
    VendorDTO update(Long id, VendorDTO vendorDTO);
    VendorDTO patch(Long id, VendorDTO vendorDTO);
    void delete(Long id);
}
