package pack1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Begin {
	
	public Begin(double prefab, LinkedList<Double> lengths) {
		//first run a sanity check
		
		
		LinkedList<LinkedList<Double>> combos = getAllCombinationsOf(lengths);
		LinkedList<Double> bestList = combos.get(0);
		HashMap<LinkedList<Double>,Double> totalWastage = calculateTotalWastage(prefab, combos); 
		
		Double leastWaste = totalWastage.values().iterator().next();
		LinkedList<Double> leastCutList = new LinkedList<Double>();
		
		for (LinkedList<Double> list : totalWastage.keySet()) {
			double waste = totalWastage.get(list);
			if (waste <= leastWaste) {
				leastCutList = (LinkedList<Double>) list;
				leastWaste=waste;
			}
		}
		
//		//This is for testing purposes
//		for (LinkedList<Double> list : totalWastage.keySet()) {
//			for (double d : list) {
//				System.out.print(d);
//				System.out.print(", ");
//			}
//			System.out.println(" - waste: "+ totalWastage.get(list));
//			System.out.println();
//		}
		
		System.out.println("best result: ");
		for (double d : leastCutList) {
			System.out.print(d + ", ");
		}
		
		System.out.println("Waste: " + leastWaste);
	}


	private HashMap<LinkedList<Double>, Double> calculateTotalWastage(double prefab, LinkedList<LinkedList<Double>> combos) {
		HashMap<LinkedList<Double>,Double> returnWaste = new HashMap<LinkedList<Double>,Double>();
		// calculates wastage for each combination
		for (LinkedList<Double> list : combos) {
			
			double runningLength = 0;
			double wastage = 0;
			int listSize = list.size();
			//calculates wastage for given combination
			for (int ii = 0; ii < listSize; ii++) {
				double cutLength = list.get(ii);				
				if(ii == list.size() - 1 && cutLength + runningLength <= prefab) {
					wastage += prefab - (cutLength + runningLength);
					break;
				}
				else if(ii == list.size() - 1 && prefab < (cutLength + runningLength)) {
					wastage += prefab - runningLength;
					wastage += prefab - cutLength;

					break;
				}
				
				if (runningLength + cutLength > prefab) {
					wastage += prefab-runningLength;
					runningLength = cutLength;
				}
				else {
					runningLength+=cutLength;
				}
				
			}
			returnWaste.put(list, wastage);
		}
		return returnWaste;
	}


	private LinkedList<LinkedList<Double>> getAllCombinationsOf(LinkedList<Double> inputlengths) {
		LinkedList<LinkedList<Double>> returnlist = new LinkedList<LinkedList<Double>>();
		//base case 
		if (inputlengths.size() == 2) {
			returnlist.add(new LinkedList<Double>(Arrays.asList(inputlengths.get(0), inputlengths.get(1)) ));
			returnlist.add(new LinkedList<Double>(Arrays.asList(inputlengths.get(1), inputlengths.get(0)) ));
			return returnlist;
		}
		else if (inputlengths.size() > 2) {
			 for (int i=0; i<inputlengths.size(); i++) {
				 double firstElement = inputlengths.get(i);
				 LinkedList<LinkedList<Double>> combos = getAllCombinationsOf(selectAllBut(i, inputlengths));
				 for(LinkedList<Double> list : combos) {
					 //prepend the element
					 list.add(0, firstElement);
					 returnlist.add(list);
				 }
			 }
			 return returnlist;
		}
		//Housten, we have a problem.
		else if(inputlengths.size() == 1) {
			returnlist.add(new LinkedList<Double>(Arrays.asList(inputlengths.get(0))));
		}
		//Housten, we have a big problem!
		
		return null;
		
	}

	private LinkedList<Double> selectAllBut(int element, LinkedList<Double> lengths){
		LinkedList<Double> returnlengths =(LinkedList<Double>) lengths.clone();
		returnlengths.remove(element);
		return returnlengths;
	}
	
	
	
	
}
