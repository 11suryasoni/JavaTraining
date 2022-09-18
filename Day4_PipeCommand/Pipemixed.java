/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Pipemixed Class is used to mimic the linux pipe.

@Author -> suryaps (Surya Prakash Soni)
 */

package Day4_PipeCommand;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Pipemixed {

    // Answer stored in "answer" variable is input to next pipe command.
    static String answer="";

    /**
     * To initialize the answer variable.
     */

    public Pipemixed(String answer) {
        Pipemixed.answer = answer;
    }

    /**
     * Previous answer becomes input for head with -n.
     */

    public static String head(String storedvalue, int num)
    {
        if(num==0 || storedvalue.equals("") || storedvalue.equals("Illegal"))
            return "Illegal";
        String data = "";
        String[] arr = storedvalue.split("\n\r|\n|\r");

        if(num>arr.length)
            num= arr.length;
        for (int j = 0; j < num; j++)
            data = data + arr[j] + "\n";
        return data;
    }

    /**
     * Previous answer becomes input for tail with -n.
     */

    public static String tail(String storedvalue, int num)
    {
        if(num==0 || storedvalue.equals("") || storedvalue.equals("Illegal"))
            return "Illegal";
        String data= "";
        String []reverse= storedvalue.split("\n\r|\n|\r");
        int length=reverse.length;

        if(num>reverse.length)
            num= reverse.length;
        for(int j=length-num; j<length; j++)
            data=data+reverse[j]+"\n";
        return data;
    }

    /**
     * Search file in pwd and return contents in form of String.
     */

    public static String cat(String filename) throws Exception {
        String path=System.getProperty("user.dir");
        path+="/"+filename;

        String data = "";
        data = new String(Files.readAllBytes(Path.of(path)));
        return data;
    }

    /**
     * List files in pwd.
     */

    public static String ls()
    {
        String path=System.getProperty("user.dir");
        File f = new File(path);
        String[] arr =f.list();

        String data="";
        for(int i=0;i<arr.length;i++) {
            if(arr[i].charAt(0)!='.')
                data = data + arr[i] + "\n";
        }
        return data;
    }

    /**
     * Filter searches for a particular input, and displays all lines that contain that pattern.
     */

    public static String grep(String storedvalue,String str)
    {
        if(storedvalue.equals("") || storedvalue.equals("Illegal"))
            return "Illegal";
        String data = "";
        String[] arr = storedvalue.split("\n\r|\n|\r");

        for(int i=0;i<arr.length;i++) {
            if(arr[i].contains(str))
                data = data + arr[i] + "\n";
        }
        return data;
    }

    /**
     * Filters out the repeated lines in answer variable.
     */

    public static String uniq(String storedvalue)
    {
        if(storedvalue.equals("") || storedvalue.equals("Illegal"))
            return "Illegal";
        String data = "";
        String[] arr = storedvalue.split("\n\r|\n|\r");

        data = data + arr[0] + "\n";
        for(int i=1;i<arr.length-1;i++) {
            if(arr[i].equals(arr[i-1]))
                continue;
            else
                data = data + arr[i] + "\n";
        }
        return data;
    }

    /**
     * Number of lines, word count, byte/characters count of answer variable.
     */

    public static String wc(String storedvalue)
    {
        if(storedvalue.equals("") || storedvalue.equals("Illegal"))
            return "Illegal";
        long wordcount,linecount,characters;
        characters=0;
        String[] arr = storedvalue.split("\n");
        linecount=arr.length;

        arr=storedvalue.split(" ");
        wordcount=arr.length;

        for(int i = 0; i < storedvalue.length(); i++) {
                characters++;
        }
        String data="";
        data=data+linecount+" "+wordcount+" "+characters;
        return data;
    }

    /**
     * Sorts the contents of answer variable, line by line.
     */

    public static String mysort(String storedvalue)
    {
        if(storedvalue.equals("") || storedvalue.equals("Illegal"))
            return "Illegal";
        String data = "";
        String[] arr = storedvalue.split("\n\r|\n|\r");

        Arrays.sort(arr,(str1,str2)-> str1.charAt(0) - str2.charAt(0));

        for(int i=0;i<arr.length;i++) {
                data = data + arr[i] + "\n";
        }
        return data;
    }

    /**
     * Select is used to call respective methods.
     */

    public static void select(String str) throws Exception {
        String[] mylist = str.split("\\s");

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
    }


    public static void main(String[] args) throws Exception {
        String[] l=args[0].split("\\|");
        ArrayList<String> myList = new ArrayList<String>();

        /* First: Split the input from pipe " | " .
           ltrim: Remove all the left spaces.
           rtrim: Remove all right spaces.
           Center: Replace all middle spaces by single space.
        */

        for(int i=0;i< l.length;i++)
        {
            String ltrim=l[i].replaceAll("^\\s+","");
            String rtrim=ltrim.replaceAll("\\s+$","");
            String center=rtrim.replaceAll("\\s+"," ");
            myList.add(center);
        }
        // Evaluate all commands one by one.

        int i=0;
        while(i<myList.size())
        {
            select(myList.get(i));
            i++;
        }
        System.out.println(answer);
    }
}