package org.example;

import java.util.List;
/**
 * 
 * @author olga
 *
 */
public class RecipeSteps {

    // Μέθοδος για την επεξεργασία μιας γραμμής και την προσθήκη του βήματος στη λίστα
    public void parseSteps(String line, List<String> steps) {
        int start = 0;
        int end;

        while (start < line.length()) {
            // Εντοπισμός αρχής βήματος (κεφαλαίο γράμμα)
            while (start < line.length() && !Character.isUpperCase(line.charAt(start))) {
                start++;
            }

            // εάν φτάσουμε στο τέλος της γραμμής, σταματάμε
            if (start >= line.length()) {
                break;
            }

            // εντοπισμός τέλους βήματος (τελεία)
            end = line.indexOf('.', start);
            if (end == -1) {
                end = line.length(); // αν δεν υπάρχει τελεία, πηγαίνουμε μέχρι το τέλος
            }

            // εξασφαλίζουμε ότι το end είναι εντός ορίων
            if (end > line.length()) {
                end = line.length();
            }

            // εξαγωγή και προσθήκη του βήματος στη λίστα
            if (start < line.length() && end <= line.length()) {
                String step = line.substring(start, end);
                if (!step.isEmpty()) {
                    steps.add(step);
                }
            }
            
            if (start == line.length()   && end <= line.length()) {
                String step = line.substring(start, end);
                if (!step.isEmpty()) {
                    steps.add(step);
                }
            }

            // ανανεωνουμε το start για την επόμενη αναζήτηση
            start = end + 1;
        }
    }

    /**
     * Methodos gia tin ektyposi olon ton bimaton
     * @param steps
     */
    public void printSteps(List<String> steps) {
    	
        System.out.println("Βήματα:");
        int stepNumber = 1;
        for (String step : steps) {
            System.out.println(stepNumber + ". " + step);
            stepNumber++;
        }
    }
}










