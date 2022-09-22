package com.testAPI.demo.controller;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.payload.request.CreateAddressRequest;
import com.testAPI.demo.payload.request.UpdateAddressRequest;
import com.testAPI.demo.payload.response.AddressResponse;
import com.testAPI.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody CreateAddressRequest createAddressRequest) throws GlobalException {
        AddressResponse addressResponse = addressService.addAddress(createAddressRequest);
        URI location = URI.create(String.format("/api/v1/address/%s", addressResponse.getAddressId()));
        return ResponseEntity.created(location).body(addressResponse);
    }

    @PutMapping
    public ResponseEntity<AddressResponse> updateAddress(@Valid @RequestBody UpdateAddressRequest updateAddressRequest) throws GlobalException {
        return ResponseEntity.ok().body(addressService.updateAddress(updateAddressRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddress(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(addressService.getAddress(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressResponse> deleteAddress(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(addressService.deleteAddress(id));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getListAddress() throws GlobalException {
        return ResponseEntity.ok().body(addressService.getListAddress());
    }
}
