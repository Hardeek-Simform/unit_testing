package com.unit.testing.registration.controller;

import com.unit.testing.registration.entity.Vendor;
import com.unit.testing.registration.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> allVendors = vendorService.getAllVendors();
        if (allVendors.isEmpty()) {
            return new ResponseEntity<>(allVendors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allVendors, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Vendor> saveVendor(@RequestBody Vendor vendor) {
        Vendor saved = vendorService.addVendor(vendor);
        if (saved == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable int id) {
        vendorService.deleteVendorById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
