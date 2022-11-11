/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Wc Class is used to print No. of lines,words,characters/size,file name from a given input file.

@Author -> suryaps (Surya prakash soni)
 */

package Day3_WcCommand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Wc {

    /***
     * We will take CLI input as:java Wc d1.txt, Wc -l,-w,-c,-m,-L d1.txt as with or without operation input.
     */

    public static void main(String[] args) {
        try {

            String[] surya = new String[2];
            surya[0] = args[0];

            // It will check that CLI input contains any Options - l,w,c,m,L or not.

            if (args.length == 2)
                surya[1] = args[1];
            else
                surya[1] = args[0];


            long wordcount, linecount, maxword;
            wordcount = linecount = maxword = 0;


            FileReader f = new FileReader(surya[1]);
            BufferedReader bf = new BufferedReader(f);
            String line;

            // This loop will Calculate all the values.

            while ((line = bf.readLine()) != null) {
                linecount++;
                String[] w = line.split(" ");
                wordcount += w.length;

                if (w.length > maxword)
                    maxword = w.length;
            }


            //   Switch case is to handle WC options.

            if (args.length == 2) {
                switch (surya[0]) {
                    case "-l": {
                        System.out.print(linecount == 1 ? "1 " : linecount + " ");
                        System.out.println(Path.of(args[1]).getFileName());
                    }
                    break;
                    case "-w": {
                        System.out.print(wordcount + " ");
                        System.out.println(Path.of(args[1]).getFileName());
                    }
                    break;
                    case "-c":
                    case "-m": {
                        System.out.print(Files.size(Path.of(args[1])) + " ");
                        System.out.println(Path.of(args[1]).getFileName());
                    }
                    break;
                    case "-L": {
                        System.out.print(maxword + " ");
                        System.out.println(Path.of(args[1]).getFileName());
                    }
                    break;

                    default:
                        System.out.println("Command Not Match!");
                }
            }

            // If CLI input not Contain any WC option then Else part executed.

            else {
                System.out.print(linecount == 1 ? "1 " : linecount + " ");
                System.out.print(wordcount + " ");
                System.out.print(Files.size(Path.of(args[0])) + " ");
                System.out.println(Path.of(args[0]).getFileName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

