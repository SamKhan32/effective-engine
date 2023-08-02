package main;

import java.awt.Desktop;
import java.io.BufferedReader;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import objects.Printer;


import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;



public class Main implements PrintRequestAttributeSet 

  {
	public static void main(String[] args) throws InterruptedException, IOException {
	String location = "";
	String name = "";
	Scanner in = new Scanner(System.in);
	
//	File saveData = new File("C:\\eclipse2023\\PrintingProgram\\src\\res\\saveData.txt");
   // Scanner reader = new Scanner("C:\\eclipse2023\\PrintingProgram\\src\\res\\saveData.txt");
  //  FileWriter writer = new FileWriter("C:\\eclipse2023\\PrintingProgram\\src\\res\\saveData.txt");
    BufferedReader br = new BufferedReader(new FileReader("C:\\eclipse2023\\PrintingProgram\\src\\res\\saveData.txt"));     
    
    if(   br.readLine() != null    ) {
    	System.out.println("Loading Data...");
    	name = br.readLine();
    	location = br.readLine();
    	//\Thread.sleep(3000);
    	
    	System.out.println("Loading Complete.");
    }
    else {
    	System.out.println("NEW USER DETECTED");
    	System.out.println("Please insert name of printer and location. These can be found in your settings. Type the name, press enter, then type the location, and press enter");
    	name = in.nextLine();
    	location = in.nextLine();
    	Files.writeString(  Path.of(("C:\\eclipse2023\\PrintingProgram\\src\\res\\saveData.txt")), name + "\n" + location);
    }
    Printer currentPrinter = new Printer(name, location);
    

	System.out.println("Hello, this program will print out everything in the folder you specify to the printer " + currentPrinter.getName() + "\nIs this the printer you wish to print to?\nY/N");

	if (in.nextLine().equals("N") ) {
		System.out.println("Please insert name of preferred printer and location. These can be found in your settings. Type the name, press enter, then type the location, and press enter");
    	name = in.nextLine();
    	location = in.nextLine();
    	Files.writeString(  Path.of(("C:\\eclipse2023\\PrintingProgram\\src\\res\\saveData.txt")), name + "\n" + location);
	}
	System.out.println("All Done!\nNow insert the path to the folder and press enter");
	boolean isDir = false;
	File dir;
	File[] listOfFiles = new File[1];
	while(isDir == false) {
	String pathToFolder = in.nextLine();
	dir = new File(pathToFolder);
	listOfFiles = dir.listFiles();
	if(dir.isDirectory() == false) {
		System.out.println("That's not a valid path to a directory (folder). Try again and press enter.");
	}
	else {
		for(int i =0; i< listOfFiles.length;i++ ) {
			if(listOfFiles[i].isDirectory() == true) {
				System.out.println("This is a valid path to a directory (folder), but it contains a directory as well. Try again with a directory that contains only leaves (single files)");
			}
		}
		isDir = true;
	}
	
	}
	File file1 = listOfFiles[0];
	DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
	PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	
	
	
	
	PrintService service = PrintServiceLookup.lookupDefaultPrintService();
	System.out.println(service.toString());
    DocPrintJob printJob = service.createPrintJob();
    FileInputStream fis = new FileInputStream(file1.getPath());
    Doc doc = new SimpleDoc(fis, flavor, null );
    
    Desktop desk = Desktop.getDesktop();
    desk.print(file1);
    try {
		printJob.print(doc, aset);
	} catch (PrintException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
	}
    
	}

	@Override
	public Attribute get(Class<?> category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Class<?> category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Class<?> category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Attribute[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Attribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(AttributeSet attributes) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
