package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * 
 * A public type that handles the configuration of the project
 *
 */

public interface Configurator {

	/**
	 * 
	 * @return A set of all the available categories
	 */
	
    Set<Category> getCategories();
    
	/** 
	 * returns all the available variants from a specific category
	 * @param category: the chosen category
	 * @return A set of all the available variants in the category
	 */    

    Set<PartType> getVariants(Category category);

    /**
     * @return the used configuration 
     */
    
    Configuration getConfiguration();

    /**
     * @return the used CompatibilityChecker 
     */
    
    CompatibilityChecker getCompatibilityChecker();

}
