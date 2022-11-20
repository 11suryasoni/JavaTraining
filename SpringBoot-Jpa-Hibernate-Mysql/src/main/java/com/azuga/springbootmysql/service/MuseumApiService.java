/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

@Author -> suryaPs (Surya prakash sonI)
 */
package com.azuga.springbootmysql.service;

import com.azuga.springbootmysql.entity.MuseumApi;
import com.azuga.springbootmysql.repository.MuseumApiRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// This is Service Layer class.
@Service
public class MuseumApiService {
    static final Logger logger = LogManager.getLogger(MuseumApiService.class);

    // Injecting Dependency from Dao Layer.(Service -> DAO)
    @Autowired
    private MuseumApiRepository repository;


    // This method is to get elements from Dao layer.
    public List<MuseumApi> get() {
        logger.debug("JPA Find Method Invoked, Data -> " + repository.findAll());
        return repository.findAll();
    }


    // This method is to get element by ID from Dao layer.
    public MuseumApi getById(int id) {
        logger.debug("JPA FindByID Method Invoked, Data -> " + repository.findById(id).orElse(null));
        return repository.findById(id).orElse(null);
    }


    // This method is to save/transfer elements to Dao layer.
    public MuseumApi save(MuseumApi museumApi) {
        logger.debug("JPA Save Method Invoked, Data -> " + repository.save(museumApi));
        return repository.save(museumApi);
    }


    // This method is to update/transfer elements to Dao layer.
    public void delete(Integer id) {
        logger.debug("JPA Delete Method Invoked, Data -> " + (id));
        repository.deleteById(id);
    }


    // This method is to delete/transfer elements to Dao layer.
    public MuseumApi update(MuseumApi m) {
        logger.debug("JPA Save Method Invoked, Data -> " + repository.save(m));
        repository.save(m);
        return m;
    }
}