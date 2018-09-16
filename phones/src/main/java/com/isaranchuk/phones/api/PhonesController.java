package com.isaranchuk.phones.api;

import com.isaranchuk.phones.domain.Phone;
import com.isaranchuk.phones.repostitory.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PhonesController {

    private final PhoneRepository phoneRepository;

    public PhonesController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/v1/phones")
    public ResponseEntity<GetPhonesResponse> phones() {
        log.info("Getting phones");
        GetPhonesResponse body = new GetPhonesResponse(phoneRepository.findAll());
        return ResponseEntity.ok(body);
    }
}
