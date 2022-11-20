/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

@Author -> suryaPs (Surya prakash sonI)
 */
package com.azuga.springbootmysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// This class is pojo, Entity Class for Injecting Dependency.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ApiInfoBase")
public class MuseumApi {

    // ID is the primary key attribute.
    @Id
    @GeneratedValue
    private int objectId;
    private boolean isHighlight;
    private String accessionNumber;
    private String accessionYear;
    private String isPublicDomain;
    private String primaryImage;
    private String primaryImageSmall;
    private String additionalImages;
    private String constituents;
    private String department;
    private String objectName;
    private String title;
    private String culture;
    private String period;
    private String dynasty;
    private String reign;
    private String portfolio;
    private String artistRole;
    private String artistPrefix;
    private String artistDisplayName;
    private String artistDisplayBio;
    private String artistSuffix;
    private String artistAlphaSort;
    private String artistNationality;
    private String artistBeginDate;
    private String artistEndDate;
    private String artistGender;
    private String artistWikidata_URL;
    private String artistULAN_URL;
    private String objectDate;
    private String objectBeginDate;
    private String objectEndDate;
    private String medium;
    private String dimensions;
    private String measurements;
    private String creditLine;
    private String geographyType;
    private String city;
    private String state;
    private String county;
    private String country;
    private String region;
    private String subregion;
    private String locale;
    private String locus;
    private String excavation;
    private String river;
    private String classification;
    private String rightsAndReproduction;
    private String linkResource;
    private String metadataDate;
    private String repository;
    private String objectURL;
    private String tags;
    private String objectWikidata_URL;
    private String isTimelineWork;
    private String GalleryNumber;
}