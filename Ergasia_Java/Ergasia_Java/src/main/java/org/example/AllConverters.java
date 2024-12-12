package org.example;

import java.util.ArrayList;
import java.util.List;

public class AllConverters {
	

	    public static List<Integer> convertTimesToIntegers(List<String> times) {
	        List<Integer> convertedTimes = new ArrayList<>();

	            for (String time : times) {
	                int number = Integer.parseInt(time); // Μετατροπή του String σε ακέραιο
	                convertedTimes.add(number); // Προσθήκη στη λίστα
	            
	        }
	        return convertedTimes;
	    }
	    
	    

	    public static void convertTimesToHoursAndMinutes(List<Integer> convertedTimes, List<String> timeUnits) {
	        // Έλεγχος αν η λίστα timeUnits είναι κενή
	        if (timeUnits.isEmpty()) {
	            System.out.println("The list of time units is empty.");
	            return;  // Επιστροφή για να μην συνεχίσει η εκτέλεση
	        }

	        // Έλεγχος αν οι λίστες έχουν το ίδιο μέγεθος
	        if (convertedTimes.size() != timeUnits.size()) {
	            System.out.println("The lists have different sizes.");
	            return;  // Επιστροφή για να μην συνεχίσει η εκτέλεση
	        }

	        int totalTime = 0;  // Αρχικοποιούμε τη μεταβλητή totalTime σε 0

	        // Ελέγχουμε αν όλες οι μονάδες χρόνου είναι ίδιες
	        boolean allSameUnit = true;
	        String unit = timeUnits.get(0);  // Παίρνουμε την πρώτη μονάδα μέτρησης

	        // Ελέγχουμε αν όλες οι μονάδες είναι ίδιες
	        for (int i = 1; i < timeUnits.size(); i++) {
	            if (!timeUnits.get(i).equals(unit)) {
	                allSameUnit = false;  // Αν υπάρχει διαφορά, σημαίνει ότι οι μονάδες δεν είναι ίδιες
	                break;
	            }
	        }

	        // Αν όλες οι μονάδες είναι ίδιες, αθροίζουμε κατευθείαν
	        if (allSameUnit) {
	            for (int time : convertedTimes) {
	                totalTime += time;  // Προσθέτουμε το κάθε στοιχείο από τη λίστα
	            }
	        } else {
	            // Αν δεν είναι όλες οι μονάδες ίδιες, μετατρέπουμε όλες σε λεπτά και προσθέτουμε
	            for (int i = 0; i < timeUnits.size(); i++) {
	                if (timeUnits.get(i).equals("hours")) {
	                    convertedTimes.set(i, convertedTimes.get(i) * 60); // Μετατρέπουμε ώρες σε λεπτά
	                }
	                // Αν είναι ήδη σε λεπτά, δεν κάνουμε τίποτα
	            }

	            // Τώρα όλα είναι σε λεπτά, αθροίζουμε τους χρόνους
	            for (int time : convertedTimes) {
	                totalTime += time;  // Προσθέτουμε το κάθε στοιχείο από τη λίστα
	            }
	        }

	        // Υπολογισμός ωρών και λεπτών από τον συνολικό χρόνο
	        int hours = totalTime / 60;  // Υπολογισμός ωρών
	        int minutes = totalTime % 60;  // Υπολογισμός λεπτών

	        
	        System.out.println();
	        // Εμφάνιση του αποτελέσματος
	        if(hours==0) {
	        	System.out.println("Συνολική ώρα: \n"+ "\t"+ minutes +" minutes");
	        }else if(minutes==0) {
	        	System.out.println("Συνολική ώρα: \n" +"\t"+ hours + " hours ");
	        }else
	        System.out.println("Συνολική ώρα: \n" + "\t"+ hours + " hours and " + minutes + " minutes");
	    }

	


	    
	    
	
	    
	    
	
	// Method to convert quantities to doubles
		public static List<Double> convertQuantitiesToDoubles(List<String> quantities) {
			List<Double> convertedQuantities = new ArrayList<>();

			for (String quantity : quantities) {
				try {
					// Remove non-numeric characters and convert to double
					quantity = quantity.replaceAll("[^0-9.]", "");

					if (!quantity.isEmpty()) {
						double doubleValue = Double.parseDouble(quantity);
						convertedQuantities.add(doubleValue);
					} else {
						System.out.println("Invalid quantity (empty or non-numeric): " + quantity);
					}
				} catch (NumberFormatException e) {
					System.out.println("Error converting quantity to double: " + quantity);
				}
			}

			return convertedQuantities;
		}
	
	
	/**
     * Metatrepei ta kila se gr i ta gr se gr
     * @param quantity
     * @param unit
     * @return grams
     */
    public static double convertToGrams(double quantity, String unit) {
        switch (unit) {
            case "kg":
                return quantity * 1000;
            case "gr":
                return quantity;
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }

    /**
     * Pairnei ta lt kai ml kai ta epistrefei ml
     * @param quantity
     * @param unit
     * @return
     */
    public static double convertToMl(double quantity, String unit) {
        switch (unit) {
            case "lt":
                return quantity * 1000;
            case "ml":
                return quantity;
            default:
                return 0;
        }
    }
}
