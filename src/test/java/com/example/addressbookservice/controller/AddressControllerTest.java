package com.example.addressbookservice.controller;


import com.example.addressbookservice.entity.AddressBook;
import com.example.addressbookservice.service.AddressbookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(AddressbookController.class)
@Slf4j
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressbookService addressbookService;

    @Test
    public void saveAddress_basic() throws Exception {
       RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addressbook/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new AddressBook("Dave","Smith","123 main st.","seattle","wa",43)))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());

    }

    @Test
    public void saveAllAddress_basic() throws Exception {

        List<AddressBook> list = new ArrayList<>();
        list.add(new AddressBook("Dave","Smith","123 main st.","seattle","wa",43));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addressbook/saveAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(list))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());

    }
    @Test
    public void findById_basic() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/addressbook/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void getAddressBookList_basic() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/addressbook/getAddress")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200,result.getResponse().getStatus());
    }

}
// log.info("Checj=k the test response "+result.getResponse().);

     /*   String json = result.getResponse().getContentAsString();
        AddressBook someClass = new ObjectMapper().readValue(json, AddressBook.class);*/
