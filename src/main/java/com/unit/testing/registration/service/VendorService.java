package com.unit.testing.registration.service;

import com.unit.testing.registration.entity.Vendor;

import java.util.List;

public interface VendorService {
    public List<Vendor> getAllVendors();

    public Vendor addVendor(Vendor vendor);

    public void deleteVendorById(int id);
}
