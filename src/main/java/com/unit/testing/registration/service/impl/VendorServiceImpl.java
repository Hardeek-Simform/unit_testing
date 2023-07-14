package com.unit.testing.registration.service.impl;

import com.unit.testing.registration.entity.Vendor;
import com.unit.testing.registration.repository.VendorRepository;
import com.unit.testing.registration.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public void deleteVendorById(int id) {
        vendorRepository.deleteById(id);
    }
}
