package com.isaranchuk.phones.api;

import com.isaranchuk.phones.domain.Phone;
import com.isaranchuk.phones.repostitory.PhoneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhonesController {

    private PhoneRepository phoneRepository;

    @GetMapping("/v1/phones")
    public ResponseEntity<List<Phone>> phones() {
        return ResponseEntity.ok(phoneRepository.findAll());
    }
}