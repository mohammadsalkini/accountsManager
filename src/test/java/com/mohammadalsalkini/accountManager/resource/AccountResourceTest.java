package com.mohammadalsalkini.accountManager.resource;

import com.mohammadalsalkini.accountManager.AccountManagerApplication;
import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 22:59
 */


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {AccountManagerApplication.class})
@Transactional
public class AccountResourceTest {

    @Mock
    private AccountRepository accountRepository;

    AccountResource accountResource;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountResource = new AccountResource(accountRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(accountResource).build();

    }


    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


    @Test
    public void should_create_an_account_and_return_created_status() throws Exception {
        Account account = new Account(1, "My new account");

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(account)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("http://localhost/api/accounts/1")))
                .andExpect(content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void should_CreateAccount() throws Exception {
        when(accountRepository.save(any(Account.class))).thenReturn(new Account(1, "test"));

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"1\", \"name\": \"test\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/api/accounts/1"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    public void should_not_allow_others_http_methods() throws Exception {
        Account account = new Account(1, "My new account");

        mockMvc.perform(put("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(account)))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void should_GetAccount_() throws Exception {

        Account account = new Account(1, "test");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/api/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andDo(MockMvcResultHandlers.print());
    }

}