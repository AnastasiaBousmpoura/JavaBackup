package org.example;

import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.example.RecipeReader.convertQuantitiesToDoubles;

public class ListOfAllTheIngredients {

    List<String> quantities = new ArrayList<>();
    List<String> units = new ArrayList<>();
    List<String> ingredients = new ArrayList<>();
    List<Ingredients> allTheIngredients = new ArrayList<>();

    Testnow testNow = new Testnow();
    Scanner inputStream = null;

    public void readFiles(String filename1, String filename2) {

        //ανοιγω το αρχειο 1
        try {
            inputStream = new Scanner(new FileInputStream(filename1));
        } catch (FileNotFoundException e) {
            System.out.println(filename1 + " was not found or could not be opened");
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            testNow.parseIngredient(line, ingredients);
            testNow.parseQuantities(line, quantities);
            testNow.parseUnits(line, units);

        }
        inputStream.close();
        List<Double> convertedQuantities = convertQuantitiesToDoubles(quantities);

        QuantUnitConversion conversion = new QuantUnitConversion(ingredients, convertedQuantities, units);

        conversion.processRecipe(ingredients, convertedQuantities, units); //exei kanei mia lista me monadika ilika,enan pinaka me posothtesmetr,ena me mon,enanxwr,enanxer

        double[] newFinalQuantities = conversion.getFinalQuantities();
        String[] newFinalUnits = conversion.getFinalUnits();
        String[] newOtherUnits = conversion.getOtherUnits();
        double[] newOtherQuantities = conversion.getOtherQuantities();

        for (int i = 0; i < conversion.getUniqueIngredients().size(); i++) {

            Ingredients finalIngredients = new Ingredients();

            finalIngredients.setName(conversion.getUniqueIngredients().get(i));
            finalIngredients.setQuantity(newFinalQuantities[i]);
            finalIngredients.setMeasurementAmount(newFinalUnits[i]);
            finalIngredients.setNoConvertibleAmount(newOtherUnits[i]);
            finalIngredients.setNoConvertibleQuantity(newOtherQuantities[i]);
            allTheIngredients.add(finalIngredients);

        }

        //ανοιγω το δευτερο αρχειο
        clearTheLists(ingredients,quantities,units);

        try {
            inputStream = new Scanner(new FileInputStream(filename2));
        } catch (FileNotFoundException e) {
            System.out.println(filename2 + " was not found or could not be opened");
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            testNow.parseIngredient(line, ingredients);
            testNow.parseQuantities(line, quantities);
            testNow.parseUnits(line, units);

        }
        inputStream.close();

        List<Double> convertedQuantities2 = convertQuantitiesToDoubles(quantities);

        QuantUnitConversion conversion2 = new QuantUnitConversion(ingredients, convertedQuantities2, units);

        conversion2.processRecipe(ingredients, convertedQuantities2, units); //exei kanei mia lista me monadika ilika,enan pinaka me posothtesmetr,ena me mon,enanxwr,enanxer

        double[] newFinalQuantities2 = conversion2.getFinalQuantities();
        String[] newFinalUnits2 = conversion2.getFinalUnits();
        String[] newOtherUnits2 = conversion2.getOtherUnits();
        double[] newOtherQuantities2 = conversion2.getOtherQuantities();
        ////apo edw kai katv einai diaforetiko
        Ingredients currentIngredient = null;
        int positionOfexistingIngr = 0;
        for (int i = 0; i < conversion2.getUniqueIngredients().size(); i++) {

            if (theIngredientExists(conversion2.getUniqueIngredients().get(i))) {
                for (Ingredients ingredient : allTheIngredients) {
                    if (conversion2.getUniqueIngredients().get(i).equalsIgnoreCase(ingredient.getName())) {
                        currentIngredient = ingredient;
                        positionOfexistingIngr = i;
                    }
                }
                if (currentIngredient == null) {
                    System.out.println("Something went wrong");
                }

                currentIngredient.setQuantity(currentIngredient.getQuantity() + newFinalQuantities2[positionOfexistingIngr]);
                currentIngredient.setNoConvertibleQuantity(currentIngredient.getNoConvertibleQuantity() + newOtherQuantities2[positionOfexistingIngr]);

            } else {
                Ingredients finalIngredients = new Ingredients();

                finalIngredients.setName(conversion2.getUniqueIngredients().get(i));
                finalIngredients.setQuantity(newFinalQuantities2[i]);
                finalIngredients.setMeasurementAmount(newFinalUnits2[i]);
                finalIngredients.setNoConvertibleAmount(newOtherUnits2[i]);
                finalIngredients.setNoConvertibleQuantity(newOtherQuantities2[i]);
                allTheIngredients.add(finalIngredients);
            }


        }


    }

    //anoigw trito
    public void readFiles(String filename1, String filename2, String filename3) {
        //ανοιγω το αρχειο 1
        try {
            inputStream = new Scanner(new FileInputStream(filename1));
        } catch (FileNotFoundException e) {
            System.out.println(filename1 + " was not found or could not be opened");
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            testNow.parseIngredient(line, ingredients);
            testNow.parseQuantities(line, quantities);
            testNow.parseUnits(line, units);

        }
        inputStream.close();

        List<Double> convertedQuantities = convertQuantitiesToDoubles(quantities);

        QuantUnitConversion conversion = new QuantUnitConversion(ingredients, convertedQuantities, units);

        conversion.processRecipe(ingredients, convertedQuantities, units); //exei kanei mia lista me monadika ilika,enan pinaka me posothtesmetr,ena me mon,enanxwr,enanxer

        double[] newFinalQuantities = conversion.getFinalQuantities();
        String[] newFinalUnits = conversion.getFinalUnits();
        String[] newOtherUnits = conversion.getOtherUnits();
        double[] newOtherQuantities = conversion.getOtherQuantities();

        for (int i = 0; i < conversion.getUniqueIngredients().size(); i++) {

            Ingredients finalIngredients = new Ingredients();

            finalIngredients.setName(conversion.getUniqueIngredients().get(i));
            finalIngredients.setQuantity(newFinalQuantities[i]);
            finalIngredients.setMeasurementAmount(newFinalUnits[i]);
            finalIngredients.setNoConvertibleAmount(newOtherUnits[i]);
            finalIngredients.setNoConvertibleQuantity(newOtherQuantities[i]);
            allTheIngredients.add(finalIngredients);

        }

        //ανοιγω το δευτερο αρχειο
        clearTheLists(ingredients,quantities,units);

        try {
            inputStream = new Scanner(new FileInputStream(filename2));
        } catch (FileNotFoundException e) {
            System.out.println(filename2 + " was not found or could not be opened");
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            testNow.parseIngredient(line, ingredients);
            testNow.parseQuantities(line, quantities);
            testNow.parseUnits(line, units);

        }
        inputStream.close();

        List<Double> convertedQuantities2 = convertQuantitiesToDoubles(quantities);

        QuantUnitConversion conversion2 = new QuantUnitConversion(ingredients, convertedQuantities2, units);

        conversion2.processRecipe(ingredients, convertedQuantities2, units); //exei kanei mia lista me monadika ilika,enan pinaka me posothtesmetr,ena me mon,enanxwr,enanxer

        double[] newFinalQuantities2 = conversion2.getFinalQuantities();
        String[] newFinalUnits2 = conversion2.getFinalUnits();
        String[] newOtherUnits2 = conversion2.getOtherUnits();
        double[] newOtherQuantities2 = conversion2.getOtherQuantities();
        ////apo edw kai katv einai diaforetiko
        Ingredients currentIngredient = null;
        int positionOfexistingIngr = 0;
        for (int i = 0; i < conversion2.getUniqueIngredients().size(); i++) {

            if (theIngredientExists(conversion2.getUniqueIngredients().get(i))) {
                for (Ingredients ingredient : allTheIngredients) {
                    if (conversion2.getUniqueIngredients().get(i).equalsIgnoreCase(ingredient.getName())) {
                        currentIngredient = ingredient;
                        positionOfexistingIngr = i;
                    }
                }
                if (currentIngredient == null) {
                    System.out.println("Something went wrong");
                }

                currentIngredient.setQuantity(currentIngredient.getQuantity() + newFinalQuantities2[positionOfexistingIngr]);
                currentIngredient.setNoConvertibleQuantity(currentIngredient.getNoConvertibleQuantity() + newOtherQuantities2[positionOfexistingIngr]);

            } else {
                Ingredients finalIngredients = new Ingredients();

                finalIngredients.setName(conversion2.getUniqueIngredients().get(i));
                finalIngredients.setQuantity(newFinalQuantities2[i]);
                finalIngredients.setMeasurementAmount(newFinalUnits2[i]);
                finalIngredients.setNoConvertibleAmount(newOtherUnits2[i]);
                finalIngredients.setNoConvertibleQuantity(newOtherQuantities2[i]);
                allTheIngredients.add(finalIngredients);
            }


        }

        //ανοιγω το trito αρχειο
        clearTheLists(ingredients,quantities,units);

        try {
            inputStream = new Scanner(new FileInputStream(filename3));
        } catch (FileNotFoundException e) {
            System.out.println(filename3 + " was not found or could not be opened");
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            testNow.parseIngredient(line, ingredients);
            testNow.parseQuantities(line, quantities);
            testNow.parseUnits(line, units);

        }
        inputStream.close();

        List<Double> convertedQuantities3 = convertQuantitiesToDoubles(quantities);

        QuantUnitConversion conversion3 = new QuantUnitConversion(ingredients, convertedQuantities3, units);

        conversion3.processRecipe(ingredients, convertedQuantities3, units); //exei kanei mia lista me monadika ilika,enan pinaka me posothtesmetr,ena me mon,enanxwr,enanxer

        double[] newFinalQuantities3 = conversion3.getFinalQuantities();
        String[] newFinalUnits3 = conversion3.getFinalUnits();
        String[] newOtherUnits3 = conversion3.getOtherUnits();
        double[] newOtherQuantities3 = conversion3.getOtherQuantities();
        ////apo edw kai katv einai diaforetiko
        currentIngredient = null;
        positionOfexistingIngr = 0;
        for (int i = 0; i < conversion3.getUniqueIngredients().size(); i++) {

            if (theIngredientExists(conversion3.getUniqueIngredients().get(i))) {
                for (Ingredients ingredient : allTheIngredients) {
                    if (conversion3.getUniqueIngredients().get(i).equalsIgnoreCase(ingredient.getName())) {
                        currentIngredient = ingredient;
                        positionOfexistingIngr = i;
                    }
                }
                if (currentIngredient == null) {
                    System.out.println("Something went wrong");
                }

                currentIngredient.setQuantity(currentIngredient.getQuantity() + newFinalQuantities3[positionOfexistingIngr]);
                currentIngredient.setNoConvertibleQuantity(currentIngredient.getNoConvertibleQuantity() + newOtherQuantities3[positionOfexistingIngr]);

            } else {
                Ingredients finalIngredients = new Ingredients();

                finalIngredients.setName(conversion3.getUniqueIngredients().get(i));
                finalIngredients.setQuantity(newFinalQuantities3[i]);
                finalIngredients.setMeasurementAmount(newFinalUnits3[i]);
                finalIngredients.setNoConvertibleAmount(newOtherUnits3[i]);
                finalIngredients.setNoConvertibleQuantity(newOtherQuantities3[i]);
                allTheIngredients.add(finalIngredients);
            }

        }

    }




    //αδιαζει τισ λιστες
    private void clearTheLists(List<String> ingredients, List<String> quantities, List<String> units) {
        ingredients.clear();
        quantities.clear();
        units.clear();
    }

    //βλεπει αμα το υλικο ειναι ειδη απο αποθηκευμενο στην λιστα
    public boolean theIngredientExists(String uliko) {
        for (Ingredients ingredient : allTheIngredients) {
            if (uliko.equals(ingredient.getName())) {
                return true;
            }
        }
        return false;
    }

    //εκτυπωνει τα υλικα
    public void printListOfIngredients() {
        System.out.println();
        for (Ingredients ingredient : allTheIngredients) {
            if(ingredient.getQuantity()!=0.0){
                if(ingredient.getNoConvertibleQuantity()!=0.0) {
                    System.out.println(ingredient.getName() + " " + ingredient.getQuantity() + " " + ingredient.getMeasurementAmount()+" "+ingredient.getNoConvertibleAmount()+" "+ingredient.getNoConvertibleQuantity());

                }else{
                    System.out.println(ingredient.getName() + " " + ingredient.getQuantity() + " " + ingredient.getMeasurementAmount());
                }
            }else{
                if(ingredient.getNoConvertibleQuantity()!=0.0) {
                    System.out.println(ingredient.getName()+" "+ingredient.getNoConvertibleAmount()+" "+ingredient.getNoConvertibleQuantity());

                }else{
                    System.out.println(ingredient.getName());
                }
            }
        }


    }
}