/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.


This Wc Class is used to print No. of lines,words,characters/size,file name from a given input file.

@Author -> suryaps (Surya prakash soni)
 */

package Day1_Task;

import java.io.File;
import java.util.Scanner;


public class RemoveDir {
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
			System.out.println("enter the command");
		String s1= sc.next();
		if(s1.equalsIgnoreCase("rmdir")){
			System.out.println("enter the path");
			String path=sc.next();
		
			File f = new File(path);
			String arr[]=f.list();
			if(arr.length>0)
				{
					System.out.println("Cannot be Deleted,it contains some files");
					return;
				}
			else
				{
					f.delete();
					System.out.println("File deleted successfull");
				}
		}
		else
		{	
			System.out.println("Command doesnot match");
		}
		return;
	}
} 
