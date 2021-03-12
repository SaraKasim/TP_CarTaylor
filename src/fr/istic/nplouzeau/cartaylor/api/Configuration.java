package fr.istic.nplouzeau.cartaylor.api;


import java.util.Set;

/**
 * 
 * a public type that is used to verify/configure the selection of car parts  
 */

public interface Configuration {

	/**
	 * Returns True iff the rules of incompatibilities and requirements are respected
	 */
	
    boolean isValid();
    
	/**
	 * Returns True iff a PartType is chosen for each Category
	 */    
    
    boolean isComplete(); 
    
	/**
	 * Returns a set of all the selected Parts
	 */
    
    Set<PartType> getSelectedParts();

    /**
     * Let's the client select the a part type 
     * @param chosenPart: the part type selected be the client
     */
    void selectPart(PartType chosenPart);

    /**
     * Returns the part selected for a specific category  
     * @param category: the selected category
     */
    
    PartType getSelectionForCategory(Category category);

    /**
     * Let's the client unselect a part type from a specific category
     * @param categoryToClear: the category to remove the selected part from
     */
    void unselectPartType(Category categoryToClear);

    /**
     * Clears all the selected parts
     */
    
    void clear();

}
