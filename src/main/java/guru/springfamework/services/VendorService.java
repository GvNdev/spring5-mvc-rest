package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> findAll();
    VendorDTO findById(Long id);
    VendorDTO save(VendorDTO vendorDTO);
}
