package com.unit.testing.registration.service.impl;

import com.unit.testing.registration.entity.User;
import com.unit.testing.registration.entity.Vendor;
import com.unit.testing.registration.repository.UserRepository;
import com.unit.testing.registration.repository.VendorRepository;
import com.unit.testing.registration.service.VendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Vendor Service:")
class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;
    private VendorService vendorService;
    private AutoCloseable autoCloseable;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        vendor = new Vendor();
        vendor.setOwnerName("owner");
        vendor.setShopName("Shop");
        vendor.setCategory("Beauty");
        vendor.setCity("city");
        vendor.setState("State");
        vendorService = new VendorServiceImpl(vendorRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @DisplayName("case : Get all vendors list")
    void testGetAllVendor() {
        mock(User.class);
        mock(UserRepository.class);
        when(vendorRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(vendor)));
        assertThat(vendorService.getAllVendors().get(0).getId()).isEqualTo(vendor.getId());
    }

    @Test
    @DisplayName("case : adding vendor")
    void testAddVendor() {
        mock(User.class);
        mock(UserRepository.class);
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.addVendor(vendor)).isEqualTo(vendor);
    }

    @Test
    @DisplayName("case: deleting vendor by id")
    void testDeleteVendorById() {
        mock(User.class);
        mock(UserRepository.class, CALLS_REAL_METHODS);
        doAnswer(CALLS_REAL_METHODS).when(vendorRepository).deleteById(1);
        vendorService.deleteVendorById(1);
        verify(vendorRepository).deleteById(1);
    }
}