/**
  * @author Abdul
 * Main Class
 * 
 */
package com.ProgB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
	public static IntArraySeq2b s = new IntArraySeq2b(); 
	public static IntArraySeq2b cS = new IntArraySeq2b(s);
	static Scanner scanner = new Scanner(System.in);
	static String lastWord = "";

	public static void main(String[] args) {
		System.out.println("Enter file path");
		String fileName = scanner.nextLine();		
		try {
			BufferedReader read = new BufferedReader(new FileReader(fileName));
			String line = null;

			while((line = read.readLine()) != null){
				lastWord = line;
			}
			toInt(); 
			finalOutput(); 
			read.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}	

	/**
	 * Method converts string to int
	 * Method also contains switch statement to choose between sort
	 */
	public static void toInt() {
		int choice = 0;
		String [] intString = lastWord.split(" ");
		int[] ints = new int[intString.length];

		//Asking user for choice of sorting, NOT NEEDED BUT BETTER
		System.out.println("Make a choice");
		System.out.println("\n1. Insert by appearence");
		System.out.println("\n2. Insert by least to greatest");	
		choice = scanner.nextInt();
		switch(choice) {
		case 1:
			for(int i = 0; i< ints.length; i++) {
				ints[i] = Integer.parseInt(intString[i]);
				s.addAfter(ints[i]); 
			}
			cS.addAll(s);
			writeSeq(); 
			break;
		case 2:	
			for(int i = 0; i< ints.length; i++) {
				ints[i] = Integer.parseInt(intString[i]);
			}
			Arrays.sort(ints); 
			for(int i = 0; i< ints.length; i++) {
				s.addAfter(ints[i]); 
			}
			cS.addAll(s); 
			writeSeqLeastToGreatest(); 
			break;
		default: System.out.println("INVALID CHOICE PICK 1 OR 2\n");
		toInt();
		}	
	}

	/**
	 * Method writes file of sequence least to greatest
	 */
	public static void writeSeqLeastToGreatest() {
		System.out.println("\nSequence ordered least to greatest written in file ArraySeqOutput.txt \n");
		try { 
			BufferedWriter writer1 = new BufferedWriter(new FileWriter("ArraySeqOutput.txt")); 
			s.start();
			for(int j = 0; j< s.size(); j++) {
				writer1.write(s.getCurrent() + " ");
				s.advance();
			}
			writer1.close();
		}
		catch(Exception e) {
			e.getStackTrace(); 
		}
	}

	/**
	 * Method writes file of sequence by appearance
	 */
	public static void writeSeq() {
		System.out.println("\nSequence Ordered by appearence written in file intArraySeqOutput.txt. \n");
		try { 
			BufferedWriter writer = new BufferedWriter(new FileWriter("ArraySeqOutput.txt")); 
			s.start();
			for(int i = 0; i < s.size(); i ++) {
				writer.write(s.getCurrent() + " ");
				s.advance();
			} 
			writer.close(); }

		catch(Exception e) {
			e.getStackTrace(); 
		}
	}

	/**
	 * Method removes duplicates from second sequence
	 */
	public static void removeDuplicate() {
		LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
		cS.start();
		for(int i = 0; i< cS.size(); i++) {
			set.add(cS.getCurrent());
			cS.advance();
		}
		cS.start();
		System.out.println("\nFinal Output Second Sequence: \n" );
		for(Integer i: set) {
			cS.addAfter(i);
			System.out.println( cS.getCurrent() );
			cS.advance();
		}

	}

	/**
	 * Final output method
	 */
	public static void finalOutput() {
		s.start();
		System.out.println("\nFinal Output First Sequence: \n" );
		for(int i = 0; i< s.size(); i++) {
			System.out.println(s.getCurrent() );
			s.advance();
		}
		removeDuplicate();
	}

}
