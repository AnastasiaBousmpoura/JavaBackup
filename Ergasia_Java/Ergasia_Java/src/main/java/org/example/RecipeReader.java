package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author olga Periexei tin main method
 */
public class RecipeReader {
	public static int persons = 1;

	List<String> quantities = new ArrayList<>();
	List<String> units = new ArrayList<>();
	List<String> ingredients = new ArrayList<>();
	List<String> cookware = new ArrayList<>();
	List<String> times = new ArrayList<>();
	List<String> timeUnits = new ArrayList<>();
	// Λίστα για την αποθήκευση των βημάτων
	List<String> steps = new ArrayList<>();



	public static void main(String[] args) { // Pairneis thn main kai thn epekthneis mazi me ths dimitras

		if (args.length < 1) {
			System.out.println("Error Parameters\n");
			return;
		}
		if (args[0].equals("-list")) {
			System.out.println("ths Dimitras");
			return;

		} else {//diko mou kommati

			if (args.length > 1)
				persons = Integer.parseInt(args[1]);
			System.out.println("Reading from the recipe file " + args[0]); //args[0] einai to orisma toy xrhsth(arxeio) pou dinei sto terminal
			Scanner inputStream = null;

			// RecipeReader instance
			RecipeReader reader = new RecipeReader();
			AllParsers parser = new AllParsers(); // parser instance

			try {
				inputStream = new Scanner(new FileInputStream(args[0]));
			} catch (FileNotFoundException e) {
				System.out.println("File was not found or could not be opened.");
				System.exit(0);
			}
			RecipeSteps recipeSteps = new RecipeSteps();

			// Λίστα για την αποθήκευση των βημάτων ths syntaghs
			List<String> steps = new ArrayList<>();

			// Read and parse the file data
			while (inputStream.hasNextLine()) {
				// mpaino sthn testnow kai dhmiourgei tis listes gia posothtew,units,ylika,xronous,monades metrhshs . to kanei grammh grammh
				String line = inputStream.nextLine();
				parser.parseIngredient(line, reader.ingredients);
				parser.parseCookware(line, reader.cookware);
				parser.parseQuantities(line, reader.quantities);
				parser.parseUnits(line, reader.units);
				parser.parseTimes(line, reader.times);
				parser.parseTimeUnits(line, reader.timeUnits);

				recipeSteps.parseSteps(line, steps);
			}

			inputStream.close();


			// Convert quantities to doubles
			List<Double> convertedQuantities = AllConverters.convertQuantitiesToDoubles(reader.quantities);


			// Create QuantUnitConversion instance
			QuantUnitConversion conversion = new QuantUnitConversion(reader.ingredients, convertedQuantities,
					reader.units);

			// Process the recipe sth klash Testnow
			conversion.processRecipe(reader.ingredients, convertedQuantities, reader.units);

			//metatroph tvn xronon se integers gia na mporv na metatreco
			List<Integer> convertedTimes = AllConverters.convertTimesToIntegers(reader.times);





			FinalPrint finalPrint = new FinalPrint(conversion.getUniqueIngredients(), conversion.getFinalQuantities(),
					conversion.getFinalUnits(), conversion.getOtherQuantities(), conversion.getOtherUnits(), reader.cookware

			);


			System.out.println();
			// ektyposh olvn ylikvn,posothtvn, skeyh
			finalPrint.printResults();

			 // Κλήση της μεθόδου για τη μετατροπή tvn xronvn kai gia ektypvsh synolikhs oras

	        AllConverters.convertTimesToHoursAndMinutes(convertedTimes,reader.timeUnits);


			System.out.println();
			// Εκτύπωση των βημάτων ths syntaghs
			recipeSteps.printSteps(steps);

		}
	}
}
