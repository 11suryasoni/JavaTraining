/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Pipemixed Class is used to mimic the linux pipe.

@Author -> suryaps (Surya Prakash Soni)
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

public class Pipemixed {

    static final Logger logg = Logger.getLogger(Pipemixed.class);

    // Answer stored in "answer" variable is input to next pipe command.
    static String answer="";
    // Storing Default Path.
    static String path = System.getProperty("user.dir");

    /**
     * To initialize the answer variable.
     */

    public Pipemixed(String answer) {
        Pipemixed.answer = answer;
    }

    /**
     * This method Verify the input number with head and tail commands.
     */

    public static boolean numberChecker(String storedvalue, int num)
    {
        if(num==0 || storedvalue.equals("") || storedvalue.equals("Illegal Input")) {

            logg.error("INPUT ERROR -numberChecker");
            return true;
        }
        return false;
    }

    /**
     * This method Verify the String with all other commands except head and tail.
     */

    public static boolean stringChecker(String storedvalue)
    {
        if(storedvalue.equals("") || storedvalue.equals("Illegal Input")) {
            logg.error("INPUT ERROR -stringChecker");
            return true;
        }
        return false;
    }


    /**
     * Previous answer becomes input for head with -n.
     */

    public static String head(String storedvalue, int num)
    {
        logg.info("STORED VALUE - "+ storedvalue);
        logg.info("HEAD INPUT NUMBER - "+num);

        if(numberChecker(storedvalue, num)) {
            logg.error("HEAD INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedvalue.split("\n\r|\n|\r");

        if(num>arr.length)
            num= arr.length;
        for (int j = 0; j < num; j++) {
            data.append(arr[j]).append("\n");
            logg.debug("HEAD - DATA - "+arr[j]);
        }
        logg.info("HEAD -RETURNED");
        return data.toString();
    }

    /**
     * Previous answer becomes input for tail with -n.
     */

    public static String tail(String storedvalue, int num)
    {
        logg.info("STORED VALUE - "+ storedvalue);
        logg.info("TAIL INPUT NUMBER - "+num);

        if(numberChecker(storedvalue, num)) {
            logg.error("TAIL INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data= new StringBuilder();
        String []reverse= storedvalue.split("\n\r|\n|\r");
        int length=reverse.length;

        if(num>reverse.length)
            num= reverse.length;
        for(int j=length-num; j<length; j++) {
            data.append(reverse[j]).append("\n");
            logg.debug("Tail Data - "+reverse[j]);
        }
        logg.info("Tail -RETURNED");
        return data.toString();
    }

    /**
     * Search file in pwd and return contents in form of String.
     */

    public static String cat(String filename){
        String data = null;
        try {
            path += "/" + filename;
            logg.info("CAT COMMAND FILE PATH- " + path);
            data = new String(Files.readAllBytes(Path.of(path)));
            logg.debug("DATA -" + data);
            logg.info("CAT -RETURNED");
        } catch (Exception e) {
            logg.error("CAT INPUT ERROR - "+e);
        }
        return data;
    }

    /**
     * List files in pwd.
     */

    public static String ls()
    {
        logg.warn("SYSTEM DEFAULT PATH - "+path);
        File f = new File(path);
        String[] arr =f.list();

        StringBuilder data= new StringBuilder();
        for(int i = 0; i< Objects.requireNonNull(arr).length; i++) {
            if(arr[i].charAt(0)!='.')
                data.append(arr[i]).append("\n");
            logg.debug("LS -DATA - "+arr[i]);
        }
        logg.info("LS -RETURNED");
        return data.toString();
    }

    /**
     * Filter searches for a particular input, and displays all lines that contain that pattern.
     */

    public static String grep(String storedvalue,String str)
    {
        logg.info("STORED VALUE -"+storedvalue);
        if(stringChecker(storedvalue)) {
            logg.error("GREP INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedvalue.split("\n\r|\n|\r");

        for (String s : arr) {
            if (s.contains(str))
                data.append(s).append("\n");
            logg.debug("GREP -APPEND - "+s);
        }
        logg.info("GREP -RETURNED");
        return data.toString();
    }

    /**
     * Filters out the repeated lines in answer variable.
     */

    public static String uniq(String storedvalue)
    {
        logg.info("STORED VALUE- "+storedvalue);
        if(stringChecker(storedvalue)) {
            logg.error("UNIQ INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedvalue.split("\n\r|\n|\r");

        data.append(arr[0]).append("\n");
        for(int i=1;i<arr.length-1;i++) {
            if(arr[i].equals(arr[i-1]))
                continue;
            else
                data.append(arr[i]).append("\n");
            logg.debug("UNIQ -APPEND - "+arr[i]);
        }
        logg.info("UNIQ -RETURNED");
        return data.toString();
    }

    /**
     * Number of lines, word count, byte/characters count of answer variable.
     */

    public static String wc(String storedvalue)
    {
        logg.info("STORED VALUE - "+storedvalue);
        if(stringChecker(storedvalue)) {
            logg.error("WC INPUT ERROR");
            return "Illegal Input";
        }
        long wordcount,linecount,characters;
        characters=0;
        String[] arr = storedvalue.split("\n");
        linecount=arr.length;

        arr=storedvalue.split(" ");
        wordcount=arr.length;

        for(int i = 0; i < storedvalue.length(); i++) {
                characters++;
            logg.debug("WC - DATA - "+characters);
        }
        String data="";
        data=data+linecount+" "+wordcount+" "+characters;

        logg.info("WC -RETURNED");
        return data;
    }

    /**
     * Sorts the contents of answer variable, line by line.
     */

    public static String mysort(String storedvalue)
    {
        logg.info("STORED VALUE - "+storedvalue);
        if(stringChecker(storedvalue)) {
            logg.error("SORT INPUT ERROR");
            return "Illegal Input";
        }
        StringBuilder data = new StringBuilder();
        String[] arr = storedvalue.split("\n\r|\n|\r");

        Arrays.sort(arr, Comparator.comparingInt(str -> str.charAt(0)));

        for (String s : arr) {
            data.append(s).append("\n");
            logg.debug("SORT - "+s);
        }
        logg.info("SORT -RETURNED");
        return data.toString();
    }

    /**
     * Select is used to call respective methods.
     */

    public static void select(String str) throws Exception {

        logg.info("SELECT METHOD - "+str);
        String[] mylist = str.split("\\s");
        logg.debug("SWITCH INPUT - "+ Arrays.toString(mylist));

        switch(mylist[0])
        {
            case "cat": {   answer="";
                            answer += cat(mylist[1]);
            }
                break;
            case "head":{   String storedvalue =answer;
                            int num=Character.getNumericValue(mylist[1].charAt(1));
                            answer="";
                            answer+=head(storedvalue,num);
            }
                break;
            case "tail": {   String storedvalue =answer;
                             int num=Character.getNumericValue(mylist[1].charAt(1));
                             answer="";
                             answer+=tail(storedvalue,num);
            }
                break;
            case "ls": {    answer="";
                            answer+=ls();
            }
                break;
            case "wc": {    String storedvalue=answer;
                            answer="";
                            answer+=wc(storedvalue);
            }
                break;
            case "sort": {   String storedvalue=answer;
                             answer="";
                             answer+=mysort(storedvalue);
            }
                break;
            case "uniq": {   String storedvalue=answer;
                             answer="";
                             answer+=uniq(storedvalue);
            }
                break;
            case "grep":{   String storedvalue=answer;
                            answer="";
                            answer += grep(storedvalue,mylist[1]);
            }
                break;
        }
        logg.info("SELECT -RETURNED");
    }


    public static void main(String[] args)  {

        try {
            logg.info("MULTI-PIPE PROGRAM INVOKED");
            logg.info("INITIALIZING AND DECLARING STRING \"ANSWER\" VARIABLE TO STORE RESULT OF EACH COMMAND");
            logg.warn("INPUT FORM CLI...");
            String[] l = args[0].split("\\|");
            ArrayList<String> myList = new ArrayList<>();

            logg.debug("STORING AND SPLITTING INPUT - "+myList);

        /* First: Split the input from pipe " | " .
           ltrim: Remove all the left spaces.
           rtrim: Remove all right spaces.
           Center: Replace all middle spaces by single space.
        */
            logg.info("INPUT MANIPULATION : ");
            for (String s : l) {
                String ltrim = s.replaceAll("^\\s+", "");
                String rtrim = ltrim.replaceAll("\\s+$", "");
                String center = rtrim.replaceAll("\\s+", " ");
                myList.add(center);
                logg.debug("Data - "+center);
            }
            // Evaluate all commands one by one.

            logg.info("CALLING METHODS ONE BY ONE");
            int i = 0;
            while (i < myList.size()) {
                select(myList.get(i));
                i++;
                logg.debug("EXECUTING -PIPE - "+myList.get(i));
            }
            logg.info("ANSWER SYSTEM OUT :");
            System.out.println(answer);
            logg.debug("ANSWER - "+answer);
            logg.info("All INPUT COMMANDS EXECUTED SUCCESSFULLY");
        }
        catch (Exception e){
            logg.error("INPUT ERROR - main - "+e);
        }
    }
}