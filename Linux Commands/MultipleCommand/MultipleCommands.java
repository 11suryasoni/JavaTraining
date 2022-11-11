/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.


This Wc Class is used to print No. of lines,words,characters/size,file name from a given input file.

@Author -> suryaps (Surya prakash soni)
 */

package Day1_Task;


import java.io.*;

public class MultipleCommands {

    public static void mkdir(String path) {
        File f = new File(path);
        if (f.mkdir())
            System.out.println("File created successfully");
        else
            System.out.println("Error in creating file");
    }

    public static void rmdir(String path) {
        File f = new File(path);
        String[] arr = f.list();
        assert arr != null;
        if (arr.length > 0)
            System.out.println("Cannot be Deleted,it contains some files");
        else {
            f.delete();
            System.out.println("File deleted successfull");
        }
    }

    public static void touch(String path) {
        try {
            File f = new File(path);
            if (!f.exists() && f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else
                System.out.println("File already exists");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void remFile(String path) {
        File f = new File(path);
        if (f.delete())
            System.out.println("File Deleted successfully");
        else
            System.out.println("Error in deleting file or File doesnot exist");
    }


    public static void touch_am(String path) {
        File f = new File(path);
        if (f.exists()) {
            f.setLastModified(System.currentTimeMillis());
            System.out.println("time changed");
        } else {
            touch(path);
            f.setLastModified(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {

        switch (args[0]) {

            case "touch":
                touch(args[1]);
                break;
            case "touch-a":
            case "touch-m": {
                touch_am(args[1]);
            }
            break;
            case "mkdir":
                mkdir(args[1]);
                break;
            case "rmdir":
                rmdir(args[1]);
                break;
            case "remFile":
                remFile(args[1]);
                break;
            default:
                System.out.println("command doesn't match");
        }
    }
}