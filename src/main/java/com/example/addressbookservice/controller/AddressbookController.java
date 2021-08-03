package com.example.addressbookservice.controller;

import com.example.addressbookservice.entity.AddressBook;
import com.example.addressbookservice.service.AddressbookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
@Slf4j
public class AddressbookController {

    @Autowired
    private AddressbookService addressbookService;


    @ApiOperation(value = "Save single entry to database",response = AddressBook.class)
    @PostMapping("/save")
    public AddressBook saveAddress(@RequestBody AddressBook addressBook){
        return addressbookService.saveAddress(addressBook);
    }

    @ApiOperation(value = "Save multiple single entry to database",response = AddressBook.class)
    @PostMapping("/saveAll")
    public List<AddressBook> saveAllAddress(@RequestBody List<AddressBook> addressBookList){
        return addressbookService.saveAlladdress(addressBookList);
    }
    //Search based on any field in AddressBook entity
    @ApiOperation(value = "Fetch multiple entries based on parameter passed like addressbook/getAddress?lastName=smith",response = AddressBook.class)
    @GetMapping("/getAddress")
    public List<AddressBook> getAddressBookList(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zipcode", required = false, defaultValue = "0")  Integer zipcode ){


        return  addressbookService.fetchAllMatchAddress(firstName,lastName,street,city,state,zipcode);

    }

    @ApiOperation(value = "Fetch single entry using Id ",response = AddressBook.class)
    @GetMapping("/{id}")
    public AddressBook findById(@PathVariable("id") long addressbookId){
        return addressbookService.findById(addressbookId) ;
    }
}
