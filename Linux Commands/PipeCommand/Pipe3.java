/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This Pipe3 Class is used to replicate the Pipe of linux.

@Author -> suryaps (Surya prakash soni)
 */

package Day4_PipeCommand;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;


public class Pipe3 {

		/**
		 * Head Function Print 'N' lines from Top
		 */

		public static String[] head(String[] s, int n)
			{
				String[] data = new String[n];

				System.arraycopy(s, 0, data, 0, n);
				return data;

			}

		/***
		 * Tail Function Print 'N' lines from Bottom.
		 */

		public static String[] tail(String[] s, int n)
			{
				String[] data = new String[n];
				String[] reverse = new String[n];
				int length=s.length;

				for(int j=0; j<n; j++)
					data[j]=s[length-1-j];

				for(int j=n-1,i=0; j>=0; j--,i++)
					reverse[i]=data[j];

				return reverse;

			}

		/***
		 * Cat Function Print text form file in String format.
		 */

		public static void cat(String path,int headvalue,int tailvalue) throws Exception
		{
			String data = "";
   		 	data = new String(Files.readAllBytes(Path.of(path)));
	   		
			String[] word =data.split("\n");
			
		   		if(headvalue==0 && tailvalue==0)
				{
						System.out.println(data);
				}
				else if(tailvalue!=0 && headvalue!=0)
				{	
					String[] f1 = head(word,headvalue);
					String[] f2 = tail(f1,tailvalue);

					for (String s : f2) System.out.println(s);
				}
				else if(tailvalue==0)
				{
					String[] f1 = head(word,headvalue);

					for (String s : f1) System.out.println(s);
				}
				else 
				{
					String f1[]= tail(word,tailvalue);

					for (String s : f1) System.out.println(s);
				}
	   		 
		}

		/***
		 * Print file's in a Column.
		 */

		public static void ls_1(int headvalue,int tailvalue)
				{
					String path=System.getProperty("user.dir");

					File f = new File(path);
					String[] arr =f.list();
					Arrays.sort(arr);

					if(headvalue==0 && tailvalue==0)
					{
						for (String s : arr) System.out.println(s);
					}
					else if(tailvalue!=0 && headvalue!=0)
					{
						String[] f1 = head(arr,headvalue);
						String[] f2 = tail(f1,tailvalue);

						for (String s : f2) System.out.println(s);
					}
					else if(tailvalue==0)
					{
						String f1[]= head(arr,headvalue);

						for (String s : f1) System.out.println(s);
					}
					else
					{
						String[] f1 = tail(arr,tailvalue);

						for (String s : f1) System.out.println(s);
					}
				}

		public static void main(String []args) throws Exception
			{
			String[] mystring = args[0].split(" ");
					
			ArrayList<String> word= new ArrayList<>(Arrays.asList(mystring));
		
			switch (mystring[0]) {
			
			case "ls":{
							if(!word.contains("|"))
								ls_1(0,0);
							
							else if(word.contains("head") && word.contains("tail"))
								{
									String hvalue=mystring[4];
									String tvalue=mystring[7];
									
									int a=Character.getNumericValue(hvalue.charAt(1));
									int b=Character.getNumericValue(tvalue.charAt(1));
									
									ls_1(a,b);
								}
							else if(word.contains("head"))
							{
								String hvalue=mystring[4];
								
								int a=Character.getNumericValue(hvalue.charAt(1));
								
								ls_1(a,0);
							}
							else
							{
								String tvalue=mystring[4];
								
								int a=Character.getNumericValue(tvalue.charAt(1));
								
								ls_1(0,a);
							}
						 }
						break;
			case "cat":{	
							if(!word.contains("|"))
								cat(mystring[1],0,0);
							
							else if(word.contains("head") && word.contains("tail"))
							{
								String hvalue=mystring[4];
								String tvalue=mystring[7];
								
								int a=Character.getNumericValue(hvalue.charAt(1));
								int b=Character.getNumericValue(tvalue.charAt(1));
								
								cat(mystring[1],a,b);
							}
							else if(word.contains("head"))
							{
								String hvalue=mystring[4];
								
								int a=Character.getNumericValue(hvalue.charAt(1));
								
								cat(mystring[1],a,0);
							}
							else
							{
								String tvalue=mystring[4];
								
								int a=Character.getNumericValue(tvalue.charAt(1));
								
								cat(mystring[1],0,a);
							}
						}
						break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + mystring[0]);
			}
			
		}
}
