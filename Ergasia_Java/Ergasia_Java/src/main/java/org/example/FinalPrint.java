package org.example;

import java.util.List;

public class FinalPrint {

	
	private List<String> uniqueIngredients;
    private double[] finalQuantities;
    private String[] finalUnits;
    private double[] otherQuantities;
    private String[] otherUnits;
    private List<String> cookware;

    public FinalPrint(List<String> uniqueIngredients, double[] finalQuantities, String[] finalUnits,
                      double[] otherQuantities, String[] otherUnits,List<String> cookware) {
        this.uniqueIngredients = uniqueIngredients;
        this.finalQuantities = finalQuantities;
        this.finalUnits = finalUnits;
        this.otherQuantities = otherQuantities;
        this.otherUnits = otherUnits;
        this.cookware=cookware;
    }
        
        public void printResults() {
        	System.out.println("Υλικά : ");
            for (int i = 0; i < uniqueIngredients.size(); i++) {
            	
                if (finalQuantities[i] != 0.0) {
                    if (!otherUnits[i].isEmpty()) {
                        System.out.print("\t"+uniqueIngredients.get(i) + " : " + finalQuantities[i] + " " + finalUnits[i]
                                + " και " + otherQuantities[i] + " " + otherUnits[i]);
                    } else {
                        System.out.print("\t"+uniqueIngredients.get(i) + " : " + finalQuantities[i] + " " + finalUnits[i]);
                    }
                } else if (otherQuantities[i] != 0.0) {
                    System.out.print("\t"+uniqueIngredients.get(i) + " : " + otherQuantities[i] + " " + otherUnits[i]);
                } else {
                    System.out.print("\t"+uniqueIngredients.get(i));
                }
                System.out.println(); // Μεταφορά σε νέα γραμμή
            }
//            
            System.out.println("Σκεύη : ");
            
            for(String c : cookware) {
            	System.out.println("\t"+c);
            }
            
        
       
	
        }

}