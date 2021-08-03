package com.example.addressbookservice.repository;

import com.example.addressbookservice.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressbookRepository extends JpaRepository<AddressBook,Long> {

    AddressBook findByAddressId(Long addressbookId);
}
