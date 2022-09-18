/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Ls-new Class is used to replicate the ls of linux.

@Author -> suryaps (Surya prakash soni)
 */


package Day2_LsCommand;

import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.nio.file.*;

// We can use Color for console text using enum Color as : Color.Black;

    enum Color1 {
        //Color end string, color reset
        RESET("\033[0m"),

        // Regular Colors. Normal color, no bold, background color etc.
        BLACK("\033[0;30m"),    // BLACK
        RED("\033[0;31m"),      // RED
        GREEN("\033[0;32m"),    // GREEN
        YELLOW("\033[0;33m"),   // YELLOW
        BLUE("\033[0;34m"),     // BLUE
        MAGENTA("\033[0;35m"),  // MAGENTA
        CYAN("\033[0;36m"),     // CYAN
        WHITE("\033[0;37m"),    // WHITE

        // Bold
        BLACK_BOLD("\033[1;30m"),   // BLACK
        RED_BOLD("\033[1;31m"),     // RED
        GREEN_BOLD("\033[1;32m"),   // GREEN
        YELLOW_BOLD("\033[1;33m"),  // YELLOW
        BLUE_BOLD("\033[1;34m"),    // BLUE
        MAGENTA_BOLD("\033[1;35m"), // MAGENTA
        CYAN_BOLD("\033[1;36m"),    // CYAN
        WHITE_BOLD("\033[1;37m"),   // WHITE

        // Underline
        BLACK_UNDERLINED("\033[4;30m"),     // BLACK
        RED_UNDERLINED("\033[4;31m"),       // RED
        GREEN_UNDERLINED("\033[4;32m"),     // GREEN
        YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
        BLUE_UNDERLINED("\033[4;34m"),      // BLUE
        MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
        CYAN_UNDERLINED("\033[4;36m"),      // CYAN
        WHITE_UNDERLINED("\033[4;37m"),     // WHITE

        // Background
        BLACK_BACKGROUND("\033[40m"),   // BLACK
        RED_BACKGROUND("\033[41m"),     // RED
        GREEN_BACKGROUND("\033[42m"),   // GREEN
        YELLOW_BACKGROUND("\033[43m"),  // YELLOW
        BLUE_BACKGROUND("\033[44m"),    // BLUE
        MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
        CYAN_BACKGROUND("\033[46m"),    // CYAN
        WHITE_BACKGROUND("\033[47m"),   // WHITE

        // High Intensity
        BLACK_BRIGHT("\033[0;90m"),     // BLACK
        RED_BRIGHT("\033[0;91m"),       // RED
        GREEN_BRIGHT("\033[0;92m"),     // GREEN
        YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
        BLUE_BRIGHT("\033[0;94m"),      // BLUE
        MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
        CYAN_BRIGHT("\033[0;96m"),      // CYAN
        WHITE_BRIGHT("\033[0;97m"),     // WHITE

        // Bold High Intensity
        BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
        RED_BOLD_BRIGHT("\033[1;91m"),      // RED
        GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
        YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
        BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
        MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
        CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
        WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

        // High Intensity backgrounds
        BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     // BLACK
        RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
        GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
        YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
        BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
        MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
        CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
        WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE

        private final String code;

        Color1(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

// Class Ls have all Options of linux ls commands.

    public class Lsnew {

    /***
     * It sorts the file by modification time, showing the last edited file first.
     * @param path If Path input by user then set to that, otherwise take default path.
     */

    public static void ls_t(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);

            Map<Long,String> mp = new TreeMap<>();

            String[] s=f.list();
            int i=0;

            for(File obj:f.listFiles())
            {
                mp.put(obj.lastModified(),s[i]);
                i++;
            }
            for (Map.Entry<Long, String> me : mp.entrySet())
            {
                System.out.print(me.getValue()+ ":  ");

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                System.out.println(sdf.format(me.getKey()));

            }
        }

        /***
         * It sorts Descending order the file by modification time, showing the last edited file last.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_T(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);

            Map<Long,String> mp = new TreeMap<>(Collections.reverseOrder());

            for(File obj:f.listFiles())
            {
                mp.put(obj.lastModified(),obj.getName());

            }

            for (Map.Entry<Long, String> me : mp.entrySet())
            {
                System.out.print(me.getValue()+ ":  ");

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                System.out.println(sdf.format(me.getKey()));

            }
        }

        /***
         * Recognizing the file type by the color in which it gets displayed is a kind in classification of file.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_color(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);
            String[] arr=f.list();

            System.out.print(Color1.BLUE);
            for(int i=0;i<arr.length;i++)
                System.out.println(arr[i]);

        }

        /***
         *  Display One File Per Line, Non hidden.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_1(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);
            String[] arr=f.list();

            for(int i=0;i<arr.length;i++)
                System.out.println(arr[i]);
        }

        /***
         * To show all the hidden files in the directory. Hidden files in Unix starts with ".", in its file name.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_a(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);
            for (File f1 : f.listFiles()) {
                if (f1.isHidden())
                    System.out.println(f1);
            }

        }

        /***
         * It is used to print the files ordered by name in ascending order.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_X(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);
            String[] arr = f.list();
            Arrays.sort(arr);
            for(String s : arr)
            {
                if(s.charAt(0)!='.')
                    System.out.println(s);
            }

        }

        /***
         * To show hidden files long listing information about the file/directory.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_la(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);

            try {

                SimpleDateFormat pdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                for (File f1 : f.listFiles()) {
                    int i=0;
                    if(f1.isDirectory())
                    {	String[] arr=f1.list();
                        i=arr.length;
                    }
                    else
                        i=1;
                    Path p = Path.of(f1.getPath());
                    PosixFileAttributes ats = Files.readAttributes(p, PosixFileAttributes.class);
                    System.out.print(PosixFilePermissions.toString(ats.permissions()) + " ");

                    System.out.print(i+" ");


                    System.out.print(ats.owner().getName() + " ");
                    System.out.print(ats.group().getName() + " ");
                    System.out.print(ats.size() / 1024 + "kb ");
                    System.out.print(pdf.format(f1.lastModified()) + " ");
                    System.out.print(f1.getName());
                    System.out.println();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /***
         * This command show long listing information about the file/directory.
         * @param path If Path input by user then set to that, otherwise take default path.
         */

        public static void ls_l(String path)
        {
            if(path.equalsIgnoreCase(""))
                path=System.getProperty("user.dir");
            File f = new File(path);

            try {

                SimpleDateFormat pdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                for (File f1 : f.listFiles()) {
                    if(!(f1.isHidden()))
                    {
                        int i=0;
                        if(f1.isDirectory())
                        {	String[] arr=f1.list();
                            i=arr.length;
                        }
                        else
                            i=1;
                        Path p = Path.of(f1.getPath());
                        PosixFileAttributes ats = Files.readAttributes(p, PosixFileAttributes.class);
                        System.out.print(PosixFilePermissions.toString(ats.permissions()) + " ");

                        System.out.print(i+" ");


                        System.out.print(ats.owner().getName() + " ");
                        System.out.print(ats.group().getName() + " ");
                        System.out.print(ats.size() / 1024 + "kb ");
                        System.out.print(pdf.format(f1.lastModified()) + " ");
                        System.out.print(f1.getName());
                        System.out.println();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //Manual

        public static void man(){

            System.out.print("ls-t - It sorts the file by modification time, showing the last edited file first. head -1 picks up this first file. ");
            System.out.print("ls-1 - Display One File Per Line ");
            System.out.print("ls-a - To show all the hidden files in the directory, use ‘-a option’. Hidden files in Unix starts with ‘.’ in its file name");
            System.out.print("ls-T - It sorts Descending order the file by modification time, showing the last edited file last.");
            System.out.print("ls - l is a Linux shell command that show long listing information about the file/directory.");
            System.out.print("ls--color - Recognizing the file type by the color in which it gets displayed is an another kind in classification of file.");
            System.out.print("ls-la - To show hidden files long listing information. ");
            System.out.print("ls-X - It is used to print the files ordered by name in ascending order");

        }

        /*** We take inputs from command line as : java ls -t,l,T,etc.
         * Or as : java ls -t path
         * @param args Command line arguments
         */

        public static void main(String []args)
        {
            if(args[0].equalsIgnoreCase("man"))
                man();

            String[] s= new String[2];
            s[0]=args[0];

            if(args.length==1)
                s[1]="";
            else
                s[1]=args[1];

            switch(s[0])
            {
                case "-t" : ls_t(s[1]);
                    break;
                case "-1" :
                case "ls"   :
                    ls_1(s[1]);
                    break;
                case "-a" : ls_a(s[1]);
                    break;
                case "-T" : ls_T(s[1]);
                    break;
                case "--color"   :  ls_color(s[1]);
                    break;
                case "-l" : ls_l(s[1]);
                    break;
                case "-X" : ls_X(s[1]);
                    break;
                case "-la" : ls_la(s[1]);
                    break;

                default: System.out.println("Command Not Match!");
            }

        }

    }

