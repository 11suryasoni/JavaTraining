/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.


This Cat with WC Command Class is used to print contents of a given input file in String format.

@Author -> suryaps (Surya prakash soni)
 */

package Day4_PipeCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cat {

	/**
	 * This method converts the file content to string and returns it.
	 */
	public static String cat_fun(String path) throws Exception
	{
		String data = "";
   		 data = new String(Files.readAllBytes(Path.of(path)));
   		 return data;
	}

	public static void main(String[] args) throws Exception{
		try {
			
			String[] s= new String[4];
			s[0]=args[0];			//This is file path					
			s[1]=args[1];			//pipe
			s[2]=args[2];			//This is WC command 
			
			if(args.length>3)
				s[3]=args[3];		//WC with options like -l,-w,-c etc
			else 
				s[3]="";

			String line= cat_fun(args[0]);
			String []arr=line.split("\n");
		
			long wordcount=0,linecount=0,maxword=0;
			linecount= arr.length;
	
			String[] b=line.split(" ");
			wordcount=b.length;


			if(args.length==2)
			{
				switch(s[0])
				{
					case "-l" : {		System.out.print(linecount == 1 ? "1 " : linecount+" ");
								System.out.println(Path.of(args[1]).getFileName());
						    }
						    break;	
					case "-w" : {		System.out.print(wordcount+" ");
								System.out.println(Path.of(args[1]).getFileName());
						    }
						    break;	
					case "-c" :
					case "-m" : {		System.out.print(Files.size(Path.of(args[1]))+" ");
								System.out.println(Path.of(args[1]).getFileName());
						    }
						    break;
					case "-L" : {		System.out.print(maxword+" ");
								System.out.println(Path.of(args[1]).getFileName());
						    }
						    break;	
					
					
					default: System.out.println("Command Not Match!");
				}
			}
			else
			{
				
				System.out.print(linecount == 1 ? "1 " : linecount+" ");
				System.out.print(wordcount+" ");
				System.out.print(Files.size(Path.of(args[0]))+" ");
				System.out.println(Path.of(args[0]).getFileName());
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

