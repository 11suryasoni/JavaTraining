/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

@Author -> suryaPs (Surya prakash sonI)
 */
package com.azuga.springbootmysql.repository;

import com.azuga.springbootmysql.entity.MuseumApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This Interface is Repository (Dao layer) Which Extends JpaRepository Interface.
@Repository
public interface MuseumApiRepository extends JpaRepository<MuseumApi, Integer> {
}
