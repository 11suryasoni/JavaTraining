/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.


This Wc Class is used to print No. of lines,words,characters/size,file name from a given input file.

@Author -> suryaps (Surya prakash soni)
 */


package Day1_Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Move {
	public static void main(String []args)throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("enter the command");

		String s1= sc.next();
		if(s1.equalsIgnoreCase("mv")){

			System.out.println("enter the source path");
			
			String srcpath=sc.next();
			
			System.out.println("enter the destination path");

			String destpath=sc.next();			

			File src=new File(srcpath);
			File dest=new File(destpath);

			Files.copy(src.toPath(),dest.toPath());

			String deletepath=srcpath;
		
			File f = new File(deletepath);
					f.delete();
					System.out.println("File moved successfull");
		}
		else
		{	
			System.out.println("Command doesnot match");
		}
		return;
	}
} 
