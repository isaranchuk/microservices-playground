package com.isaranchuk.phones.repostitory;

import com.isaranchuk.phones.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
