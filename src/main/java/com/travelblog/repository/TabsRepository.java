package com.travelblog.repository;

import org.springframework.data.repository.CrudRepository;
import com.travelblog.model.Tab;
import org.springframework.stereotype.Repository;

@Repository
public interface TabsRepository extends CrudRepository<Tab, Integer> {

    Iterable<Tab> findAll();

}
