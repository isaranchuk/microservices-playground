package com.isaranchuk.phones.api;

import com.isaranchuk.phones.repostitory.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PhoneController {

    private final PhoneRepository phoneRepository;

    public PhoneController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/v1/phones")
    public ResponseEntity<GetPhonesResponse> phones() {
        log.info("Getting phones");
        GetPhonesResponse body = new GetPhonesResponse(phoneRepository.findAll());
        return ResponseEntity.ok(body);
    }
}
