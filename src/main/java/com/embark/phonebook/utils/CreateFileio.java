package com.embark.phonebook.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateFileio
{
	public void create()
	{
		try
		{
			
			File f =new File("SpecialCharacter.txt");
		
			File f1 = new File("A.txt");
			File f2 = new File("B.txt");
			File f3 = new File("C.txt");
			File f4 = new File("D.txt");
			File f5 = new File("E.txt");
			File f6 = new File("F.txt");
			File f7 = new File("G.txt");
			File f8 = new File("H.txt");
			File f9 = new File("I.txt");
			File f10 = new File("J.txt");
			File f11 = new File("K.txt");
			File f12 = new File("L.txt");
			File f13 = new File("M.txt");
			File f14 = new File("N.txt");
			File f15 = new File("O.txt");
			File f16 = new File("P.txt");
			File f17 = new File("Q.txt");
			File f18 = new File("R.txt");
			File f19 = new File("S.txt");
			File f20 = new File("T.txt");
			File f21 = new File("U.txt");
			File f22 = new File("V.txt");
			File f23 = new File("W.txt");
			File f24 = new File("X.txt");
			File f25 = new File("Y.txt");
			File f26 = new File("Z.txt");
	
			f.createNewFile();
			f1.createNewFile();
			f2.createNewFile();
			f3.createNewFile();
			f4.createNewFile();
			f5.createNewFile();
			f6.createNewFile();
			f7.createNewFile();
			f8.createNewFile();
			f9.createNewFile();
			f10.createNewFile();
			f11.createNewFile();
			f12.createNewFile();
			f13.createNewFile();
			f14.createNewFile();
			f15.createNewFile();
			f16.createNewFile();
			f17.createNewFile();
			f18.createNewFile();
			f19.createNewFile();
			f20.createNewFile();
			f21.createNewFile();
			f22.createNewFile();
			f23.createNewFile();
			f24.createNewFile();
			f25.createNewFile();
			f26.createNewFile();			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		}
	}
}
	
