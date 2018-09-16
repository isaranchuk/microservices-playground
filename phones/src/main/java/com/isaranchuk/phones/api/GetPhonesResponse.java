package com.isaranchuk.phones.api;

import com.isaranchuk.phones.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetPhonesResponse {
    private List<Phone> phones;
}
