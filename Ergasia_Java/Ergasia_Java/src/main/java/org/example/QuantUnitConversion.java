package org.example;

import java.util.ArrayList;
import java.util.List;
/**
 * ftiaxnei tous 4 pinakew  finalQuantities,otherQuantities,otherUnits kai 1 lista
 *  uniqueIngredients kai pairnei tis listew units,quantities,ingredients apo thn testnow
 */
public class QuantUnitConversion {
   
    private List<Double> quantities;
    private List<String> uniqueIngredients;  // List of unique ingredients
    private double[] finalQuantities;       // Total quantities converted to grams/ml
    private String[] finalUnits;            // units to finalQuantities posothtes poy exoun monada metrhshw metatrecimh
    private double[] otherQuantities;       // Quantities gia tis monades metrhshs px koupa ,preza
    private String[] otherUnits;            // monades metrhshs px koupa,preza...

    public QuantUnitConversion(List<String> ingredients, List<Double> quantities, List<String> units) {
        this.quantities = quantities;
        this.uniqueIngredients = new ArrayList<>();
        this.finalQuantities = new double[0];
        this.finalUnits = new String[0];
        this.otherQuantities = new double[0];
        this.otherUnits = new String[0];
    }

    public void processRecipe(List<String> ingredients, List<Double> convertedQuantities, List<String> units) {
        // Extract unique ingredients
        for (String ingredient : ingredients) {
            if (!uniqueIngredients.contains(ingredient)) {
                uniqueIngredients.add(ingredient);
            }
        }

        // initialize arrays me bash to plhthos ton unique ingredients
        finalQuantities = new double[uniqueIngredients.size()];
        finalUnits = new String[uniqueIngredients.size()];
        otherQuantities = new double[uniqueIngredients.size()];
        otherUnits = new String[uniqueIngredients.size()];

        for (int i = 0; i < uniqueIngredients.size(); i++) {
            finalQuantities[i] = 0.0;
            finalUnits[i] = "";
            otherQuantities[i] = 0.0;
            otherUnits[i] = "";
        }

        // Process quantities and units
        for (int i = 0; i < uniqueIngredients.size(); i++) {
            String uniqueIngredient = uniqueIngredients.get(i);
            for (int index = 0; index < ingredients.size(); index++) {
                if (ingredients.get(index).equals(uniqueIngredient)) {
                    double quantity = quantities.get(index);
                    String unit = units.get(index);

                    if (unit.equals("gr") || unit.equals("kg")) {
                        finalQuantities[i] += AllConverters.convertToGrams(quantity, unit)*RecipeReader.persons;
                        finalUnits[i] = "gr";
                    } else if (unit.equals("ml") || unit.equals("lt")) {
                        finalQuantities[i] +=  AllConverters.convertToMl(quantity, unit)*RecipeReader.persons;
                        finalUnits[i] = "ml";
                    } else if (unit.isEmpty()) { 
                        finalQuantities[i] += quantity*RecipeReader.persons;
                        finalUnits[i] = "";
                    } else {
                        otherQuantities[i] += quantity*RecipeReader.persons;
                        otherUnits[i] = unit;
                    }
                }
            }
        }
    }
    
    //getters gia oti xreiazetai
    

    public List<Double> getQuantities() {
		return quantities;
	}

	public List<String> getUniqueIngredients() {
        return new ArrayList<>(uniqueIngredients);
    }

    public double[] getFinalQuantities() {
        return finalQuantities;
    }

    public String[] getFinalUnits() {
        return finalUnits;
    }

    public double[] getOtherQuantities() {
        return otherQuantities;
    }

    public String[] getOtherUnits() {
        return otherUnits;
    }

    
}

