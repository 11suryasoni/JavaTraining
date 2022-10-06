/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This More1 Class is used to print contents from a given input file.

@Author -> suryaps (Surya prakash soni)
 */

package Day1_MoreCommand;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class More1 {

	// We use CLI, Console i/o

    public static void main(String[] data) throws FileNotFoundException {

		Console console = System.console();

            File file = new File(data[0]);
            Scanner sc=new Scanner(file);
		
		console.flush();

		long count=0;

            while (sc.hasNextLine()) 
		{
			if(count>=29)
			{	
				console.flush();
				String s=console.readLine(); 
				
				if(s.equalsIgnoreCase("q"))
				{
					console.flush();
					break;
				}
				
				System.out.println(sc.nextLine());	
				
			}
			else
            			System.out.println(sc.nextLine());
			count++;
            }
		console.flush();
            
	}
}
