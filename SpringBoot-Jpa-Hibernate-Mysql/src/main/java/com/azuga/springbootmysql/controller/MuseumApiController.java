/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

@Author -> suryaPs (Surya prakash sonI)
 */
package com.azuga.springbootmysql.controller;

import com.azuga.springbootmysql.entity.MuseumApi;
import com.azuga.springbootmysql.service.MuseumApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This is a Controller class.
@RestController
@RequestMapping("/v1")
public class MuseumApiController {

    static final Logger logger = LogManager.getLogger(MuseumApiController.class);

    // Injecting Dependency from Service Layer to call their methods.(Controller  -> Service)
    @Autowired
    private MuseumApiService museumApiService;

    // This method is to get all elements from Service Layer.
    @GetMapping("/products")
    public List<MuseumApi> findAllProducts() {
        logger.info("Get Method Invoked");
        return museumApiService.get();
    }


    // This method is to get element by ID from Service Layer.
    @GetMapping("/productById/{id}")
    public MuseumApi findProductById(@PathVariable int id) {
        logger.info("Get Element By Id Method Invoked");
        return museumApiService.getById(id);
    }


    // This method is to save/transfer elements to Service Layer.
    @PostMapping("/addProduct")
    public MuseumApi addProduct(@RequestBody MuseumApi product) {
        logger.info("Save Method Invoked");
        return museumApiService.save(product);
    }


    // This method is to update/transfer elements to Service Layer.
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        logger.info("Delete Method Invoked");
        museumApiService.delete(id);
        return "Deleted successfully";
    }

    // This method is to delete/transfer elements to Service Layer.
    @PutMapping("/update")
    public MuseumApi update(@RequestBody MuseumApi s) {
        logger.info("Update Method Invoked");
        return museumApiService.update(s);
    }
}