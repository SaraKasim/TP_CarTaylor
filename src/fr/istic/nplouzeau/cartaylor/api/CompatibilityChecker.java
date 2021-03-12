package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 *   
 * @author plouzeau, kasim
 * 
 * A public type to get the set of incompatible parts as well as the set of required parts
 */


public interface CompatibilityChecker {

	/**
	 * Returns Set of PartTypes incompatibile with the part type reference 
	 * @param reference: a partType  
	 *  
	 */
	
    Set<PartType> getIncompatibilities(PartType reference); 

	/**
	 * Returns Set of PartTypes required to be with the part type reference 
	 * @param reference: a partType  
	 *  
	 */
    
    Set<PartType> getRequirements(PartType reference);

}
