/*
 * Hello, there, and thank you for looking at my code, I would like to say that this was an 
 * exercise in solving a real world problem. My Father, who is a carpenter, needed a tool like 
 * this, which minimizes the length of wood wasted from a cutting non standard lengths. As there 
 * are no free tools online for solving this problem, and given that the algorithm which calculates
 * all of the possible combinations of cut lengths is complicated, I though that I would give it a 
 * go, and put it up on Github. If only for an exercise. 
 * 
 * Notes:
 * 
 * I wrote it in Java, because I didn't want to spend a long time coding a slightly faster version in 
 * C++, when the time saved will be miniscule.
 * 
 * It is not entirely optimized. Although, that is not to say I havn't made any effort to.
 * 
 * I made the choice to use linked list, as there were going to be an enormous amount of insertions and
 * deletions, and not so many look-ups. ArrayLists would be slower at this. How much slower? I don't 
 * know.
 * 
 * This is a perpetual work in progress. I make no guarentee that I will ever get around to working on 
 * this beyond what it currently is
 *  
 */


package pack1;

import java.util.LinkedList;
import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		// this contains all of the code which interacts with the user
		Scanner scnr = new Scanner(System.in);
		System.out.println("Hello, and welcome to the wood cut optimiser");
		System.out.println("please enter the length of the prefab wood");
		double prefab = Double.parseDouble(scnr.nextLine());
		System.out.println("please enter the lengths to be cut. Type \'f\' to finish and run the optimization");
		
		LinkedList<Double> lengths = new LinkedList<Double>();
		String input = scnr.nextLine();
		
		while (!input.equals("f")) {
			try{
				if (prefab<Double.parseDouble(input)){
					System.out.println("error, cannot input a length greater than the prefab length. Try again");
				}
				else {
					lengths.add(Double.parseDouble(input));
				}
			}
			catch(NumberFormatException e){
				  System.out.println("error, please enter a number, and try again");
			}
			System.out.println("Please enter a length you need to cut. Type \'f\' to finish and run the optimization");
			input = scnr.nextLine();
			
		}
		
		MinWasteFinder mwf = new MinWasteFinder(prefab, lengths); 
		
		System.out.println("best result: ");
		for (double d : mwf.getLeastCutList()) {
			System.out.print(d + ", ");
		}
		
		System.out.println("Waste: " + mwf.getMinWaste());
		scnr.close();
	}

}


