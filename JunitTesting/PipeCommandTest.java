/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This PipeMixed Class is used to mimic the linux pipe.

@Author -> suryaPs (Surya Prakash sonI)
 */
package JunitTesting;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class PipeCommandTest {

    // Declaring and Initializing Logger.
    static final Logger logg = Logger.getLogger(PipeCommandTest.class);

    /**
     * Test Case to Check NumberChecker Method.
     */
    @Test
    void numberCheckerTestCase() {
        logg.info("Number Checker Method TestCase");
        PipeCommand obj = new PipeCommand();
        assertTrue(obj.numberChecker("", 0));
        logg.debug("Number Checker -" + obj.numberChecker("", 0));
        logg.info("Number Checker Method- TestCase Passed");
    }

    /**
     * Test Case to Check StringChecker Method.
     */
    @Test
    void stringCheckerTestCase() {
        logg.info("String Checker Method TestCase");
        PipeCommand obj = new PipeCommand();
        assertTrue(obj.stringChecker(""));
        logg.debug("String Checker -" + obj.stringChecker(""));
        logg.info("String Checker Method- TestCase Passed");
    }

    /**
     * Test Case to Check Start Method NullPointerException.
     */
    @Test
    void startMethodNullPointerException() {
        logg.info("Start Method -NullPointerException TestCase");
        PipeCommand obj = new PipeCommand();
        Throwable exception = assertThrows(Exception.class, () ->
                obj.startMethod(null));
        assertEquals("Null Pointer Exception", exception.getMessage());
    }

    /**
     * Test Case to Check Start Method FileNotFoundException.
     */
    @Test
    void startMethodFileNotFoundException() {
        logg.info("Start Method -FileNotFoundException TestCase");
        PipeCommand obj = new PipeCommand();
        Throwable exception = assertThrows(Exception.class, () ->
                obj.startMethod("cat d.txt"));
        assertEquals("File Not Found", exception.getMessage());
    }

    /**
     * Test Case to Check Cat Method FileNotFoundException.
     */
    @Test
    void catMethodFileNotFoundException() {
        logg.info("Cat Method -FileNotFoundException TestCase");
        PipeCommand obj = new PipeCommand();
        Throwable exception = assertThrows(FileNotFoundException.class, () ->
                obj.cat("cat d.txt | head -1"));
        assertEquals("File Not Found", exception.getMessage());
    }

    /**
     * Test Case to Check Cat Method With Head Command.
     */
    @Test
    void catWithHeadTestCase1() {
        logg.info("Cat Method With Head TestCase 1");
        PipeCommand obj = new PipeCommand();
        try {
            String obtainedValue = obj.startMethod("cat d1.txt | head -1");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "Secondly, Science made us reach to the moon. But we never stopped there. " +
                    "It also gave us a glance at Mars. This is one of the greatest achievements. " +
                    "This was only possible with Science. These days Scientists make many satellites. " +
                    "Because of which we are using high-speed Internet. These satellites revolve around the " +
                    "earth every day and night. Even without making us aware of it.\n";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }

    /**
     * Test Case to Check Cat Method with Head And Tail Command.
     */
    @Test
    void catWithHeadAndTailTestCase2() {
        logg.info("Cat Method With Head And Tail TestCase 2");
        PipeCommand obj = new PipeCommand();
        try {
            String obtainedValue = obj.startMethod("cat d1.txt | head -1 | tail -1");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "Secondly, Science made us reach to the moon. But we never stopped there. " +
                    "It also gave us a glance at Mars. This is one of the greatest achievements. " +
                    "This was only possible with Science. These days Scientists make many satellites. " +
                    "Because of which we are using high-speed Internet. These satellites revolve around the " +
                    "earth every day and night. Even without making us aware of it.\n";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }

    /**
     * Test Case to Check Tail Method.
     */
    @Test
    void tailTestCase1() {
        logg.info("Tail Method TestCase 1");
        PipeCommand obj = new PipeCommand();
        try {
            String obtainedValue = obj.startMethod("tail -1");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "Illegal Input";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }

    /**
     * Test Case to Check Grep Method with Cat.
     */
    @Test
    void grepWithCatTestCase1() {
        logg.info("Grep Method With Cat TestCase 1");
        PipeCommand obj = new PipeCommand();
        try {
            String obtainedValue = obj.startMethod("cat d1.txt | grep open");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }

    /**
     * Test Case to Check UinQ Method with Tail Command.
     */
    @Test
    void uniQWithTailTestCase1() {
        logg.info("UinQ Method With Tail TestCase 1");
        PipeCommand obj = new PipeCommand();
        try {
            String obtainedValue = obj.startMethod("uniq open | tail -5");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "No Such Command Exist";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }

    /**
     * Test Case to Check Cat Method with WC Command.
     */
    @Test
    void catWithWcTestCase1() {
        logg.info("Cat Method With WC TestCase 1");
        PipeCommand obj = new PipeCommand();
        try {
            String obtainedValue = obj.startMethod("cat d1.txt | wc ");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "2 94 539";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }

    /**
     * Test Case to Check Select Method at Wrong Input.
     */
    @Test
    void wrongInputTestCase1() {
        logg.info("Select Method Wrong Input TestCase 1");
        PipeCommand obj = new PipeCommand();
        try {
            logg.debug(obj.startMethod("12345"));
            String obtainedValue = obj.startMethod("12345");
            logg.debug("Obtained Value -" + obtainedValue);
            String expectedValue = "No Such Command Exist";
            logg.debug("Expected Value -" + expectedValue);
            assertEquals(expectedValue, obtainedValue);
            logg.info("Test Case Passed");
        } catch (NullPointerException e) {
            logg.error("Null Pointer Exception -" + e);
            System.out.println("Null Pointer Exception-" + e);
        } catch (IOException e) {
            logg.error("IOException -" + e);
            System.out.println("IOException -" + e);
        } catch (Exception e) {
            logg.error("Exception -" + e);
            System.out.println("Exception -" + e);
        }
    }
}