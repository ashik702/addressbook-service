package com.example.addressbookservice.service;

import com.example.addressbookservice.entity.AddressBook;
import com.example.addressbookservice.repository.AddressbookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@Slf4j
public class AddressbookService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AddressbookRepository addressbookRepository;

    public AddressBook saveAddress(AddressBook addressBook) {
        return addressbookRepository.save(addressBook);
    }

    public List<AddressBook> saveAlladdress(List<AddressBook> addressBooklist) {
        log.info("Inside save all data");
        return addressbookRepository.saveAll(addressBooklist);
    }

    public AddressBook findById(Long addressbookId) {
        return addressbookRepository.findByAddressId(addressbookId);
    }

    public List<AddressBook> fetchAllMatchAddress(String firstName, String lastName, String street, String city, String state, int zipcode) {

        List<AddressBook> addressBookList = new ArrayList<>();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AddressBook> criteriaQuery = criteriaBuilder.createQuery(AddressBook.class);
            Root<AddressBook> root = criteriaQuery.from(AddressBook.class);
            List<Predicate> predicates = new ArrayList<>();



                if (firstName != null) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), "%" + firstName.toUpperCase() + "%"));
                }
                if (lastName != null) {
                    log.info("Inside lastName");
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("lastName")), "%"+lastName.toUpperCase()+"%"));
                }
                if (street != null) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("street")), "%" + street.toUpperCase() + "%"));
                }
                if (city != null) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("city")), "%" + city.toUpperCase() + "%"));
                }
                if (state != null) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("state")), "%" + state.toUpperCase() + "%"));
                }
                if (zipcode != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("zipcode"), zipcode));

                }

            Predicate [] predicatesarr = predicates.toArray(new Predicate[predicates.size()]);

            criteriaQuery.select(root).where(predicatesarr);
            TypedQuery<AddressBook> query = entityManager.createQuery(criteriaQuery);

            addressBookList= query.getResultList();

        }catch(Exception ex){
           log.info( ex.getMessage());
        }
        return addressBookList;

    }
}
