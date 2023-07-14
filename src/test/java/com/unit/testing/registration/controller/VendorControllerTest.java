package com.unit.testing.registration.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unit.testing.registration.entity.Vendor;
import com.unit.testing.registration.service.VendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendorController.class)
@DisplayName("Vendor controller:")
class VendorControllerTest {

    private final List<Vendor> vendorList = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VendorService vendorService;
    private Vendor vendor1;
    private Vendor vendor2;

    static String getJsonObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(object);
        return requestJson;
    }

    @BeforeEach
    void setUp() {
        vendor1 = new Vendor(1, "vendor1", "shop1", "bhavnagar", "gujarat", "food");
        vendor2 = new Vendor(2, "vendor2", "shop2", "ahmedabad", "gujarat", "beauty");
        vendorList.add(vendor1);
        vendorList.add(vendor2);
    }

    @AfterEach
    void tearDown() {
        vendorList.clear();
    }

    @Test
    @DisplayName("pass case : get all vendors")
    void vendorController_testGetAllVendors_serviceGetAllVendorsMethod_pass() throws Exception {
        when(vendorService.getAllVendors()).thenReturn(vendorList);
        this.mockMvc.perform(get("/v1/vendor")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    @DisplayName("fail case: get all vendors")
    void vendorController_testGetAllVendors_serviceGetAllVendorsMethod_fail() throws Exception {
        when(vendorService.getAllVendors()).thenReturn(vendorList);
        this.mockMvc.perform(get("/v1/vendor/")).andDo(print()).andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("pass case: save vendor")
    void vendorController_testSaveVendor_serviceAddVendorMethod_pass() throws Exception {
        String jsonObj = getJsonObject(vendor1);
        when(vendorService.addVendor(vendor1)).thenReturn(vendor1);
        this.mockMvc.perform(post("/v1/vendor").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("fail case: save vendor")
    void vendorController_testSaveVendor_serviceAddVendorMethod_fail() throws Exception {
        String jsonObj = getJsonObject(vendor1);
        when(vendorService.addVendor(vendor1)).thenReturn(null);
        this.mockMvc.perform(post("/v1/vendor").contentType(MediaType.APPLICATION_JSON).content(jsonObj)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("pass case: delete vendor")
    void vendorController_deleteVendor_serviceDeleteVendorById_pass() throws Exception {
        doAnswer(Answers.CALLS_REAL_METHODS).when(vendorService).deleteVendorById(1);
        mockMvc.perform(delete("/v1/vendor/1")).andDo(print()).andExpect(status().isOk());
    }
}