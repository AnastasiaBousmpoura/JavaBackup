package org.example;

import java.util.List;


public class AllParsers {
	
	
	public void parseIngredient(String line, List<String> ingredients) {
		
	    try {
	        int start = 0;
	        while ((start = line.indexOf("@", start)) != -1) {
	            int end = -1;

	            // Βρίσκουμε τις θέσεις των διαφόρων χαρακτήρων
	            int spaceIndex = line.indexOf(" ", start);
	            int braceIndex = line.indexOf("{", start);
	            int hashIndex  = line.indexOf("#", start);
	            int tildeIndex = line.indexOf("~", start);

	            if (braceIndex != -1) { 
	                // Υπάρχει '{' μετά το '@'
	                if (spaceIndex != -1) { 
	                    // Υπάρχει και κενό μετά το '@'
	                    if (spaceIndex > braceIndex) {
	                        // Αν το '{' είναι πριν το κενό, παίρνουμε το '{'
	                        end = braceIndex;
	                    } else if (((hashIndex == -1 || braceIndex < hashIndex) 
	                                && (tildeIndex == -1 || braceIndex < tildeIndex)) 
	                               && braceIndex > spaceIndex) {
	                        // Αν το '{' είναι πριν από '#' και '~' αλλά μετά το κενό
	                        end = braceIndex;
	                    } else {
	                        // Αλλιώς παίρνουμε το κενό
	                        end = spaceIndex;
	                    }
	                } else {
	                    // Δεν υπάρχει κενό, κοιτάμε μόνο το '{'
	                    end = braceIndex;
	                }
	            } else if (spaceIndex != -1) {
	                // Δεν υπάρχει '{', κοιτάμε μόνο το κενό
	                end = spaceIndex;
	            }

	            // Αν δεν βρήκαμε κανένα από τα παραπάνω, αγνοούμε αυτή τη γραμμή
	            if (end == -1) {
	                break;
	            }

	            // Εξασφαλίζουμε ότι το `end` είναι μεγαλύτερο από το `start`
	            if (end > start) {
	                String ingredient = line.substring(start + 1, end);
	                ingredients.add(ingredient);
	                start = end;
 // Αναβάθμιση του `start` για την επόμενη αναζήτηση
	            }
	        }
	    } catch (StringIndexOutOfBoundsException e) {
	        System.out.println("Error: String index out of bounds while parsing ingredients.");
	    }
	}

	
	
	
	
	
	public void parseCookware(String line, List<String> cookware) {
	    try {
	        if (line.contains("#")) {
	            int start = line.indexOf("#") + 1;
	            int end = line.length(); // Αρχικά υποθέτουμε ότι το τέλος είναι το τέλος της γραμμής.

	            int spaceIndex = line.indexOf(" ", start);
	            int braceIndex = line.indexOf("{", start);
	            int tildeIndex = line.indexOf("~", start);
	            int atIndex = line.indexOf("@", start);
	            int kommaIndex = line.indexOf(",", start);

	            // Ελέγχει αν υπάρχει { (δηλαδή braceIndex != -1) και αν εμφανίζεται πριν από το πρώτο διάστημα.
	            if (braceIndex != -1 && (braceIndex < spaceIndex)) {
	                // Αν υπάρχει '{' και είτε δεν υπάρχει διάστημα, είτε το '{' είναι πριν το διάστημα
	                end = braceIndex;
	            } else if (kommaIndex != -1 && (kommaIndex < spaceIndex)) {
	                // Αν υπάρχει ',' και είτε δεν υπάρχει διάστημα, είτε το ',' είναι πριν το διάστημα
	                end = kommaIndex;
	            } else if (spaceIndex != -1) {
	                if (braceIndex != -1 && (braceIndex > spaceIndex)) {
	                    if (tildeIndex != -1 && braceIndex > tildeIndex) {
	                        end = spaceIndex;
	                    } else {
	                        end = braceIndex;
	                    }
	                } else if (braceIndex == -1 && (tildeIndex == -1 || spaceIndex < tildeIndex)
	                        && (atIndex == -1 || spaceIndex < atIndex)) {
	                    // Αν υπάρχει διάστημα, αλλά δεν υπάρχει '~' ή '@' πριν από αυτό
	                    end = spaceIndex;
	                }
	            }

	            // Εξασφαλίζουμε ότι το `start` και το `end` είναι εντός των ορίων της γραμμής
	            if (start >= 0 && end <= line.length() && start < end) {
	                String utensil = line.substring(start, end);
	                if (!utensil.isEmpty()) {
	                    cookware.add(utensil);
	                }
	            } else {
	                System.out.println("Invalid indices: startKey = " + start + ", endKey = " + end);
	            }
	        }
	    } catch (StringIndexOutOfBoundsException e) {
	        System.out.println("Error: String index out of bounds while parsing cookware.");
	    }
	}



	

	public void parseQuantities(String line,List<String> quantities) {
		
	    try {
	    	int start = 0;
	    	 int  end=-1;
	        while ((start = line.indexOf("@", start)) != -1) {
	        	boolean correct=false;
	              end=-1;
	            // Βρίσκουμε τις θέσεις των διαφόρων χαρακτήρων
	            
	            int leftBraceIndex = line.indexOf("{", start);
	            int rightBraceIndex = line.indexOf("}", start);
	            int hashIndex = line.indexOf("#", start);
	            int tildeIndex = line.indexOf("~", start);
	            int percentIndex=line.indexOf("%", start);
	            
	            if (leftBraceIndex != -1) {
                     if((hashIndex!=-1 && leftBraceIndex < hashIndex )
                    || (tildeIndex != -1 && leftBraceIndex < tildeIndex)|| (hashIndex==-1) && (tildeIndex == -1)) { 
                    	
	                // Υπάρχει '{' μετά το '@'
                    	
	                   if ((percentIndex != -1)&& ((hashIndex!=-1 && percentIndex < hashIndex )
	                        || (tildeIndex != -1 && percentIndex < tildeIndex)|| (hashIndex==-1 && tildeIndex==-1))) {
	                		start=leftBraceIndex;
	                		end=percentIndex;
	                		
	                }else {
	                	start=leftBraceIndex;
	                	end=rightBraceIndex;
	                }
	                }
	            
	            }
	            
	            if((leftBraceIndex==-1 && rightBraceIndex==-1)||(hashIndex!=-1 && leftBraceIndex > hashIndex )
                        || (tildeIndex != -1 && leftBraceIndex < tildeIndex)) {
	            	  String quantity = "0";
	            	    quantities.add(quantity);

	            	correct=true;
            	}

	            // Αν δεν βρήκαμε κανένα από τα παραπάνω, αγνοούμε αυτή τη γραμμή
	            if (end == -1) {
	                break;
	            }

	            if (end > start) {
	            
	            	 if(correct==false) {
	            		
	                String quantity = line.substring(start + 1, end);
	                quantities.add(quantity);
	                // Αναβάθμιση του `start` για την επόμενη αναζήτηση
	                
	            	}            	
	            }
	          start=end;
	        }
	    } catch (StringIndexOutOfBoundsException e) {
	        System.out.println("Error: String index out of bounds while parsing quantities.");
	    }
	}
	


	
	
	

public void parseUnits(String line,List<String> units) {
	
   try {
	   int start = 0;
	  
  	 
      while ((start = line.indexOf("@", start)) != -1) {
      	boolean noUnit=false;
      	 boolean correct=false;
           int end=-1;
          // Βρίσκουμε τις θέσεις των διαφόρων χαρακτήρων
          
          int leftBraceIndex = line.indexOf("{", start);
          int rightBraceIndex = line.indexOf("}", start);
          int hashIndex = line.indexOf("#", start);
          int tildeIndex = line.indexOf("~", start);
          int percentIndex=line.indexOf("%", start);
          
          
          if (leftBraceIndex != -1) {
               if((hashIndex!=-1 && leftBraceIndex < hashIndex )
              || (tildeIndex != -1 && leftBraceIndex < tildeIndex)|| (hashIndex==-1) && (tildeIndex == -1)) { 
              	
              // Υπάρχει '{' μετά το '@'
              	
                 if ((percentIndex != -1)&& ((hashIndex!=-1 && percentIndex < hashIndex )
                      || (tildeIndex != -1 && percentIndex < tildeIndex)|| (hashIndex==-1 && tildeIndex==-1))) {
              		
              		start=percentIndex;
              		end=rightBraceIndex;
              }else {
              	start=leftBraceIndex;
              	end=rightBraceIndex;
              	 noUnit=true;
              }
          }
          }
          
         if(leftBraceIndex==-1||((hashIndex!=-1 && percentIndex < hashIndex )
                      || (tildeIndex != -1 && percentIndex < tildeIndex))) {
        	 String unit = " ";
   		  units.add(unit);
         }
          // Αν δεν βρήκαμε κανένα από τα παραπάνω, αγνοούμε αυτή τη γραμμή
          if (end == -1) {
              break;
          }
         

          if (end > start) {
        	  if(noUnit!=true && !correct) {
              String unit = line.substring(start + 1, end);
              units.add(unit);
             
              // Αναβάθμιση του `start` για την επόμενη αναζήτηση
              
        	  }else if(noUnit){
        		  String unit = " ";
        		  units.add(unit);
        	  }
        	  
          }
          start=end;
          
        }
    } catch (StringIndexOutOfBoundsException e) {
        System.out.println("Error: String index out of bounds while parsing units.");
    }
}




public void parseTimes(String line, List<String> Times) {
    try {
        int start = 0;

        while ((start = line.indexOf("~", start)) != -1) {
            int leftBraceIndex = line.indexOf("{", start);
            int percentIndex = line.indexOf("%", leftBraceIndex);
            int rightBraceIndex = line.indexOf("}", percentIndex);

            // Εξασφαλίζουμε ότι υπάρχουν και τα '{', '%', '}'
            if (leftBraceIndex != -1 && percentIndex != -1 && rightBraceIndex != -1 &&
                leftBraceIndex < percentIndex && percentIndex < rightBraceIndex) {

                // Εξάγουμε το κομμάτι ανάμεσα στο '{' και '%'
                String time = line.substring(leftBraceIndex + 1, percentIndex);

                // Προσθέτουμε το time στη λίστα αν δεν είναι κενό
                if (!time.isEmpty()) {
                    Times.add(time);
                }

                // Ανανεώνουμε το start για την επόμενη αναζήτηση
                start = rightBraceIndex + 1;
            } else {
                // Αν δεν βρεθεί έγκυρη δομή, τερματίζουμε τη διαδικασία
                break;
            }
        }
    } catch (StringIndexOutOfBoundsException e) {
        System.out.println("Error: String index out of bounds while parsing times.");
    }
}

   
   
   
   
   
public void parseTimeUnits(String line, List<String> timeUnits) {
    try {
        int start = 0;

        while ((start = line.indexOf("~", start)) != -1) {
            

            // Εντοπίζουμε τις θέσεις των χαρακτήρων
            int leftBraceIndex = line.indexOf("{", start);
            int rightBraceIndex = line.indexOf("}", start);
            int percentIndex = line.indexOf("%", start);

            // Βεβαιωνόμαστε ότι υπάρχουν τα απαιτούμενα σύμβολα
            if (leftBraceIndex != -1 && rightBraceIndex != -1 && percentIndex != -1 &&
                leftBraceIndex < percentIndex && percentIndex < rightBraceIndex) {

                // Εξάγουμε το περιεχόμενο μετά το '%' μέχρι το '}'
                String timeUnit = line.substring(percentIndex + 1, rightBraceIndex);

                // Προσθέτουμε το αποτέλεσμα αν δεν είναι κενό
                if (!timeUnit.isEmpty()) {
                    timeUnits.add(timeUnit);
                }

                // Αλλάζουμε το start για την επόμενη αναζήτηση
                start = rightBraceIndex + 1;
            } else {
                // Αν δεν βρεθεί έγκυρη δομή, τερματίζουμε
                break;
            }
        }
    } catch (StringIndexOutOfBoundsException e) {
        System.out.println("Error: String index out of bounds while parsing time units.");
    }
}

}

