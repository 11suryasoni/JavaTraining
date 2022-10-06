/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This PipeMixed Class is used to mimic the linux pipe.

@Author -> suryaPs (Surya Prakash sonI)
 */
package JunitTesting;

import com.itextpdf.text.DocumentException;
import com.pdfunit.AssertThat;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestApiAndConvertersTest {

    // Declaring and Initializing Logger.
    static final Logger logg = Logger.getLogger(PipeCommandTest.class);

    /**
     * Test Case to check Creation of Csv File from API.
     */
    @Test
    void restApiMethodTestCase1FileCheck() {
        logg.info("RestApi Method TestCase1 Of File Creation Check");
        RestApiAndConverters obj = new RestApiAndConverters();
        try {
            String csvPath = obj.restApi("https://collectionapi.metmuseum.org/public/collection/v1/objects"
                    , "/Users/azuga/Desktop/files/newTest3.json",
                    "/Users/azuga/Desktop/files/newDATA1.csv");
            logg.debug("https://collectionapi.metmuseum.org/public/collection/v1/objects" +
                    "/Users/azuga/Desktop/files/newTest3.json" + "/Users/azuga/Desktop/files/newDATA1.csv");
            logg.info("Checking Csv File Creation");
            AssertThat.document(csvPath)
                    .and("/Users/azuga/Desktop/files/newDATA1.csv")
                    .haveSameText();
            logg.info("RestApi Method TestCase1 File Check Passed");
        } catch (Exception e) {
            logg.error("RestApi Method Error");
            System.out.println("Exception Occurred -" + e);
        }
    }

    /**
     * Test Case to check IOException.
     */
    @Test
    void restApiMethodTestCase2IOException() {
        logg.info("RestApi Method TestCase2 Of IOException Check");
        RestApiAndConverters obj = new RestApiAndConverters();
        Throwable exception = assertThrows(IOException.class, () ->
                obj.restApi("https://collectionapi.metmuseum.org/public/collection/v1/objects"
                        , "/Users/azuga/Desktop/files/newTest33333.json",
                        "/Users/azuga/Desktop/files/newDATA1.csv"));
        logg.debug("https://collectionapi.metmuseum.org/public/collection/v1/objects" +
                "/Users/azuga/Desktop/files/newTest33333.json" + "/Users/azuga/Desktop/files/newDATA1.csv");
        assertEquals("Json File Not Found", exception.getMessage());
        logg.info("RestApi Method TestCase2 IOException Passed");
    }

    /**
     * Test Case to check Url Exception.
     */
    @Test
    void restApiMethodTestCase3UrlException() {
        logg.info("RestApi Method TestCase2 Of IOException Check");
        RestApiAndConverters obj = new RestApiAndConverters();
        Throwable exception = assertThrows(IOException.class, () ->
                obj.restApi("https://collectionapi.metmuseum.org/public/cotion/v1/objects"
                        , "/Users/azuga/Desktop/files/newTest3.json",
                        "/Users/azuga/Desktop/files/newDATA1.csv"));
        logg.debug("https://collectionapi.metmuseum.org/public/cotion/v1/objects" +
                "/Users/azuga/Desktop/files/newTest3.json" + "/Users/azuga/Desktop/files/newDATA1.csv");
        assertEquals("Error in Http Request", exception.getMessage());
        logg.info("RestApi Method TestCase2 UrlException Passed");
    }

    /**
     * This TestCase Checks Existence of File.
     */
    @Test
    void fileExistTestCase() {
        logg.info("File Exists Check");
        RestApiAndConverters file = new RestApiAndConverters();
        assertTrue(file.checkFileExist("/Users/azuga/Desktop/files/test3.csv"));
        logg.debug("\"/Users/azuga/Desktop/files/test3.csv\"");
        assertTrue(file.checkFileExist("/Users/azuga/Desktop/files/Testschema3.csv"));
        logg.debug("/Users/azuga/Desktop/files/Testschema3.csv");
        logg.info("File Exist Test Case Passed");
    }

    /**
     * This TestCase Checks Pdf File using Checksum.
     */
    @Test
    void convertPdfTestCase1() {
        logg.info("Convert Pdf Check");
        RestApiAndConverters Pdf = new RestApiAndConverters();
        try {
            String pdfFilePath = Pdf.csvToPdf("/Users/azuga/Desktop/files/Testschema3.csv",
                    "/Users/azuga/Desktop/files/pdf_file1.pdf");
            logg.debug("/Users/azuga/Desktop/files/Testschema3.csv" +
                    "/Users/azuga/Desktop/files/pdf_file1.pdf");
            AssertThat.document(pdfFilePath)
                    .and("/Users/azuga/Desktop/files/pdf_file2.pdf")
                    .haveSameText();
            logg.info("Pdf Test Case 1 Passed");
        } catch (IOException e) {
            logg.error("Convert Pdf IO Error");
            System.out.println("IOEXCEPTION -" + e);
        } catch (DocumentException e) {
            logg.error("Convert Pdf Document Error");
            System.out.println("DOCUMENT EXCEPTION -" + e);
        }
    }

    /**
     * This TestCase1 Checks for Convert to Html Method.
     */
    @Test
    void convertHtmlTestCase1() {
        logg.info("Convert Html Check");
        RestApiAndConverters file = new RestApiAndConverters();
        try {
            String filePath = file.csvToHtml("/Users/azuga/Desktop/files/test3.csv",
                    "/Users/azuga/Desktop/files/html_file1.html");
            logg.debug("/Users/azuga/Desktop/files/test3.csv" +
                    "/Users/azuga/Desktop/files/html_file1.pdf");
            String checkSumCode = file.checkSum(filePath);
            assertEquals(file.checkSum("/Users/azuga/Desktop/files/html_file1.html"), checkSumCode);
            logg.info("Html Test Case 2 Passed");
        } catch (IOException e) {
            logg.error("Convert Html IO Error-" + e);
            System.out.println("IOEXCEPTION -" + e);
        } catch (NoSuchAlgorithmException e) {
            logg.error("Convert Html Document Error");
            System.out.println("NoSuchAlgorithmException -" + e);
        }
    }

    /**
     * This TestCase2 Checks for Not Equal Csv -Convert to Html Method.
     */
    @Test
    void convertHtmlTestCase2() {
        logg.info("Convert Html Check 2");
        RestApiAndConverters file = new RestApiAndConverters();
        try {
            Assertions.assertNotEquals(file.csvToHtml("/Users/azuga/Desktop/files/test3.csv",
                            "/Users/azuga/Desktop/files/html_file1.html"),
                    file.csvToHtml("/Users/azuga/Desktop/files/Testschema3.csv",
                            "/Users/azuga/Desktop/files/html_file2.html"));
            logg.debug("/Users/azuga/Desktop/files/test3.csv" +
                    "/Users/azuga/Desktop/files/html_file1.pdf");
            logg.debug("/Users/azuga/Desktop/files/Testschema3.csv" +
                    "/Users/azuga/Desktop/files/html_file2.pdf");
            logg.info("Html Test Case 2 Passed");
        } catch (IOException e) {
            logg.error("Convert Html IO Error");
            System.out.println("IOEXCEPTION -" + e);
        }
    }

    /**
     * This TestCase Checks for Convert to XML Method.
     */
    @Test
    void convertXmlTestCase() {
        logg.info("Convert Xml Check");
        RestApiAndConverters file = new RestApiAndConverters();
        String filePath = file.jsonToXml("/Users/azuga/Desktop/files/crypto.json",
                "/Users/azuga/Desktop/files/museum.xml");
        logg.debug("/Users/azuga/Desktop/files/crypto.json" +
                "/Users/azuga/Desktop/files/museum.xml");
        try {
            String checkSumCode = file.checkSum(filePath);
            assertEquals(file.checkSum("/Users/azuga/Desktop/files/museum.xml"), checkSumCode);
            logg.info("Convert to Xml Test Case Passed");
        } catch (IOException e) {
            logg.error("Convert Html IO Error-" + e);
            System.out.println("IOEXCEPTION -" + e);
        } catch (NoSuchAlgorithmException e) {
            logg.error("Convert Html Document Error");
            System.out.println("NoSuchAlgorithmException -" + e);
        }
    }

    /**
     * Test Case to Check Csv To Pdf  Method FileNotFoundException.
     */
    @Test
    public void csvToPdfThrowFileNotFoundsException() {
        logg.info("Convert To Pdf FileNotFoundException Check");
        RestApiAndConverters Pdf = new RestApiAndConverters();
        Throwable exception = assertThrows(FileNotFoundException.class, () ->
                Pdf.csvToPdf("/Users/azuga/Desktop/files/test.csv", "1"));
        assertEquals("csvFileNotFound", exception.getMessage());
        logg.info("Pdf FileNotFoundException Test Case Passed");
    }

    /**
     * Test Case to Check Csv To Pdf  Method DocumentException.
     */
    @Test
    public void csvToPdfThrowDocumentException() {
        logg.info("Convert To Pdf DocumentException Check");
        RestApiAndConverters Pdf = new RestApiAndConverters();
        Throwable exception = assertThrows(DocumentException.class, () ->
                Pdf.csvToPdf("/Users/azuga/Desktop/files/test3.csv",
                        "/Users/azuga/Desktop/files/pdf_file.pdf"));
        assertEquals("Document Not Found", exception.getMessage());
        logg.info("Pdf Document Exception Test Case Passed");
    }

    /**
     * Test Case to Check Csv To Html Method FileNotFoundException.
     */
    @Test
    public void csvToHtmlThrowFileNotFoundsException() {
        logg.info("Convert To Html ThrowFileNotFoundsException Check");
        RestApiAndConverters Pdf = new RestApiAndConverters();
        Throwable exception = assertThrows(FileNotFoundException.class, () ->
                Pdf.csvToHtml("/Users/azuga/Desktop/files/test.csv",
                        "/Users/azuga/Desktop/files/html_file1.html"));
        assertEquals("csvFileNotFound", exception.getMessage());
        logg.info("Html FileNotFoundException Test Case Passed");
    }

    /**
     * Test Case to Check Csv To Html  Method IOException.
     */
    @Test
    public void csvToHtmlThrowIOException() {
        logg.info("Convert To Html IO Exception Check");
        RestApiAndConverters Pdf = new RestApiAndConverters();
        Throwable exception = assertThrows(IOException.class, () ->
                Pdf.csvToHtml("/Users/azuga/Desktop/files/test3.csv",
                        "/Users/azZga/Desktop/files/html_file1.html"));
        assertEquals("NO SUCH FILE OR DIRECTORY", exception.getMessage());
        logg.info("Html IO Exception Test Case Passed");
    }
}