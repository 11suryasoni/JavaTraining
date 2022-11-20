/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

@Author -> suryaPs (Surya prakash sonI)
 */
package com.azuga.springbootmysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This class is to launch spring-boot application.
@SpringBootApplication
public class SpringBootMysqlApplication {

    static final Logger logger = LogManager.getLogger(SpringBootMysqlApplication.class);

    public static void main(String[] args) {
        logger.info("Starting SpringBoot Application");
        SpringApplication.run(SpringBootMysqlApplication.class, args);
    }
}