package com.isaranchuk.phones.repostitory;

import com.isaranchuk.phones.domain.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void shouldFindAllPhones() throws Exception {
        // given
        Phone phone = new Phone();
        phone.setPhoneId(1L);

        this.entityManager.persist(phone);

        // when
        List<Phone> phones = phoneRepository.findAll();

        // then
        assertThat(phones).hasSize(1);
        assertThat(phones.get(0).getPhoneId()).isEqualTo(phone.getPhoneId());
    }

}