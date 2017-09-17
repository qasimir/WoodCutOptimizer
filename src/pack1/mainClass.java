package pack1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Hello, and welcome to the wood cutter optimiser");
		System.out.println("please enter the length of standard wood cut");
		double prefab = Double.parseDouble(scnr.nextLine());
		System.out.println("please enter the lengths to be cut. Type \'f\' to finish and run the optimization");
		
		LinkedList<Double> lengths = new LinkedList<Double>();
		String input = scnr.nextLine();
		
		while (!input.equals("f")) {
			if (prefab<Double.parseDouble(input)){
				System.out.println("error, cannot input a length greater than the prefab length. Try again");
			}
			else {
				lengths.add(Double.parseDouble(input));
			}
			System.out.println("please enter the lengths you need to cut. Type \'f\' to finish and run the optimization");
			input = scnr.nextLine();
			
		}
		
//		double prefab = 8;
//		//LinkedList<Double> lengths = new LinkedList<Double>(Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0));
//		LinkedList<Double> lengths = new LinkedList<Double>(Arrays.asList(2.0, 4.0, 6.0, 7.0, 3.0, 5.0));

		Begin begin = new Begin(prefab, lengths); 
		
	}

}


