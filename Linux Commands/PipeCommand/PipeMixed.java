/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This PipeMixed Class is used to mimic the linux pipe.

@Author -> suryaPs (Surya Prakash sonI)
 */

package Day4_PipeCommand;

import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class PipeMixed {

    static final Logger logg = Logger.getLogger(PipeMixed.class);

    // Answer stored in "answer" variable is input to next pipe command.
    static String answer = "";
    // Storing Default Path.
    static String path = System.getProperty("user.dir");

    /**
     * To initialize the answer variable.
     */

    public PipeMixed(String answer) {
        PipeMixed.answer = answer;
    }

    /**
     * This method Verify the input number with head and tail commands.
     */

    public static boolean numberChecker(String storedValue, int num) {
        logg.info("NUMBER CHECKER INVOKED");
        logg.debug("STORED VALUE - " + storedValue);
        if (num == 0 || storedValue.equals("") || storedValue.equals("Illegal Input")) {

            logg.error("INPUT ERROR -numberChecker");
            return true;
        }
        return false;
    }

    /**
     * This method Verify the String with all other commands except head and tail.
     */

    public static boolean stringChecker(String storedValue) {
        logg.info("STRING CHECKER INVOKED");
        logg.debug("STORED VALUE - " + storedValue);
        if (storedValue.equals("") || storedValue.equals("Illegal Input")) {
            logg.error("INPUT ERROR -stringChecker");
            return true;
        }
        return false;
    }


    /**
     * Previous answer becomes input for head with -n.
     */

    public static String head(String storedValue, int num) {
        logg.info("STORED VALUE - " + storedValue);
        logg.info("HEAD INPUT NUMBER - " + num);

        if (numberChecker(storedValue, num)) {
            logg.error("HEAD INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedValue.split("\n\r|\n|\r");

        if (num > arr.length)
            num = arr.length;
        for (int j = 0; j < num; j++) {
            data.append(arr[j]).append("\n");
            logg.debug("HEAD - DATA - " + arr[j]);
        }
        logg.info("HEAD -RETURNED");
        return data.toString();
    }

    /**
     * Previous answer becomes input for tail with -n.
     */

    public static String tail(String storedValue, int num) {
        logg.info("STORED VALUE - " + storedValue);
        logg.info("TAIL INPUT NUMBER - " + num);

        if (numberChecker(storedValue, num)) {
            logg.error("TAIL INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] reverse = storedValue.split("\n\r|\n|\r");
        int length = reverse.length;

        if (num > reverse.length)
            num = reverse.length;
        for (int j = length - num; j < length; j++) {
            data.append(reverse[j]).append("\n");
            logg.debug("Tail Data - " + reverse[j]);
        }
        logg.info("Tail -RETURNED");
        return data.toString();
    }

    /**
     * Search file in pwd and return contents in form of String.
     */

    public static String cat(String filename) {
        String data = null;
        try {
            path += "/" + filename;
            logg.info("CAT COMMAND FILE PATH- " + path);
            data = new String(Files.readAllBytes(Path.of(path)));
            logg.debug("DATA -" + data);
            logg.info("CAT -RETURNED");
        } catch (Exception e) {
            logg.error("CAT INPUT ERROR - " + e);
        }
        return data;
    }

    /**
     * List files in pwd.
     */

    public static String ls() {
        logg.warn("SYSTEM DEFAULT PATH - " + path);
        File f = new File(path);
        String[] arr = f.list();

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < Objects.requireNonNull(arr).length; i++) {
            if (arr[i].charAt(0) != '.')
                data.append(arr[i]).append("\n");
            logg.debug("LS -DATA - " + arr[i]);
        }
        logg.info("LS METHOD -RETURNS");
        return data.toString();
    }

    /**
     * Filter searches for a particular input, and displays all lines that contain that pattern.
     */

    public static String grep(String storedValue, String str) {
        logg.info("PREVIOUS STORED VALUE -" + storedValue);
        if (stringChecker(storedValue)) {
            logg.error("GREP INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedValue.split("\n\r|\n|\r");

        for (String s : arr) {
            if (s.contains(str))
                data.append(s).append("\n");
            logg.debug("GREP -APPEND - " + s);
        }
        logg.info("GREP METHOD -RETURNS");
        return data.toString();
    }

    /**
     * Filters out the repeated lines in answer variable.
     */

    public static String uniQ(String storedValue) {
        logg.info("PREVIOUS STORED VALUE- " + storedValue);
        if (stringChecker(storedValue)) {
            logg.error("uniQ INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedValue.split("\n\r|\n|\r");

        data.append(arr[0]).append("\n");
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i].equals(arr[i - 1]))
                continue;
            else
                data.append(arr[i]).append("\n");
            logg.debug("uniQ -APPEND - " + arr[i]);
        }
        logg.info("uniQ METHOD -RETURNS");
        return data.toString();
    }

    /**
     * Number of lines, word count, byte/characters count of answer variable.
     */

    public static String wc(String storedValue) {
        logg.info("PREVIOUS STORED VALUE - " + storedValue);
        if (stringChecker(storedValue)) {
            logg.error("WC INPUT ERROR");
            return "Illegal Input";
        }
        long wordCount, lineCount, charCount;
        charCount = 0;
        String[] arr = storedValue.split("\n");
        lineCount = arr.length;

        arr = storedValue.split(" ");
        wordCount = arr.length;

        for (int i = 0; i < storedValue.length(); i++) {
            charCount++;
            logg.debug("WC - DATA - " + charCount);
        }
        String data = "";
        data = data + lineCount + " " + wordCount + " " + charCount;

        logg.info("WC METHOD -RETURNS");
        return data;
    }

    /**
     * Sorts the contents of answer variable, line by line.
     */

    public static String mySort(String storedValue) {
        logg.info("PREVIOUS STORED VALUE - " + storedValue);
        if (stringChecker(storedValue)) {
            logg.error("SORT INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedValue.split("\n\r|\n|\r");

        Arrays.sort(arr, Comparator.comparingInt(str -> str.charAt(0)));

        for (String s : arr) {
            data.append(s).append("\n");
            logg.debug("SORT - " + s);
        }
        logg.info("SORT METHOD -RETURNS");
        return data.toString();
    }

    /**
     * Select is used to call respective methods.
     */

    public static void select(String str) throws Exception {

        logg.info("SELECT METHOD INPUT - " + str);
        String[] myList = str.split("\\s");
        logg.debug("SWITCH INPUT - " + Arrays.toString(myList));

        switch (myList[0]) {
            case "cat": {
                answer = "";
                answer += cat(myList[1]);
            }
            break;
            case "head": {
                String storedValue = answer;
                int num = Character.getNumericValue(myList[1].charAt(1));
                answer = "";
                answer += head(storedValue, num);
            }
            break;
            case "tail": {
                String storedValue = answer;
                int num = Character.getNumericValue(myList[1].charAt(1));
                answer = "";
                answer += tail(storedValue, num);
            }
            break;
            case "ls": {
                answer = "";
                answer += ls();
            }
            break;
            case "wc": {
                String storedValue = answer;
                answer = "";
                answer += wc(storedValue);
            }
            break;
            case "sort": {
                String storedValue = answer;
                answer = "";
                answer += mySort(storedValue);
            }
            break;
            case "uniQ": {
                String storedValue = answer;
                answer = "";
                answer += uniQ(storedValue);
            }
            break;
            case "grep": {
                String storedValue = answer;
                answer = "";
                answer += grep(storedValue, myList[1]);
            }
            break;
        }
        logg.info("SELECT METHOD -RETURNS");
    }


    public static void main(String[] args) {

        try {
            logg.info("MULTI-PIPE PROGRAM INVOKED");
            logg.info("INITIALIZING AND DECLARING STRING \"ANSWER\" VARIABLE TO STORE RESULT OF EACH COMMAND");
            logg.warn("INPUT FORM CLI...");
            String[] l = args[0].split("\\|");
            ArrayList<String> myList = new ArrayList<>();

            logg.debug("STORING AND SPLITTING INPUT - " + myList);

        /* First: Split the input from pipe " | " .
           lTrim: Remove all the left spaces.
           rTrim: Remove all right spaces.
           Center: Replace all middle spaces by single space.
        */
            logg.info("INPUT MANIPULATION : ");
            for (String s : l) {
                String lTrim = s.replaceAll("^\\s+", "");
                String rTrim = lTrim.replaceAll("\\s+$", "");
                String center = rTrim.replaceAll("\\s+", " ");
                myList.add(center);
                logg.debug("Data - " + center);
            }
            // Evaluate all commands one by one.

            logg.info("CALLING METHODS ONE BY ONE");
            int i = 0;
            while (i < myList.size()) {
                select(myList.get(i));
                logg.debug("EXECUTING -PIPE - " + myList.get(i));
                i++;
            }
            System.out.println(answer);
            logg.debug("ANSWER - " + answer);
            logg.info("All INPUT COMMANDS EXECUTED SUCCESSFULLY");
        } catch (Exception e) {
            logg.error("INPUT ERROR - main - " + e);
        }
    }
}