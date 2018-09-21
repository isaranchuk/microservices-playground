package com.isaranchuk.phones.api;

import com.isaranchuk.phones.domain.Phone;
import com.isaranchuk.phones.repostitory.PhoneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneController.class)
public class PhoneControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneRepository phoneRepository;

    @Test
    public void shouldGetPhones() throws Exception {
        Phone phone = new Phone();
        phone.setPhoneId(1L);

        doReturn(Collections.singletonList(phone)).when(phoneRepository).findAll();

        this.mvc.perform(get("/v1/phones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phones", hasSize(1)))
                .andExpect(jsonPath("$.phones[0].phoneId").value(phone.getPhoneId()));
    }

}