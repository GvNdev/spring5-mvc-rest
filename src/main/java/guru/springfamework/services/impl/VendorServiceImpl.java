package guru.springfamework.services.impl;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> findAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    setSelfLink(vendorDTO, vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO findById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(RuntimeException::new); // todo implement better exception handling
    }

    @Override
    public VendorDTO save(VendorDTO vendorDTO) {
        return returnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO update(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        return returnDTO(vendor);
    }

    @Override
    public VendorDTO patch(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }

            VendorDTO returnVendorDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            setSelfLink(returnVendorDTO, id);

            return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        }).orElseThrow(RuntimeException::new); // todo implement better exception handling
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO returnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        setSelfLink(savedVendorDTO, savedVendor.getId());
        return savedVendorDTO;
    }

    private static void setSelfLink(VendorDTO vendorDTO, Long id) {
        vendorDTO.setSelfLink("/api/v1/vendor/" + id);
    }
}
