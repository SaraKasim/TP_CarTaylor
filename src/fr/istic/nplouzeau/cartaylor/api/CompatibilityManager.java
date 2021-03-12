package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * 
 * @author kasim
 *
 */

public interface CompatibilityManager extends CompatibilityChecker {
	
	/**
	 * Add a set of Parts to the list of incompatible parts for partType reference 
	 * @param reference: a partType  
	 * @param target: a set of partTypes that are incompatible with partType reference
	 *  
	 */
    void addIncompatibilities(PartType reference, Set<PartType> target);

    /**
     * Remove a partType from the list of incompatible parts for partType reference
     * @param reference: a partType
     * @param target: the part type that is going to be removed from the list of incompatibilities
     */
    void removeIncompatibility(PartType reference, PartType target);

    /**
     * Add a set of partTypes to the list of requirements of partType reference
     * @param reference: a partType 
     * @param target: a set of partTypes
     */
    void addRequirements(PartType reference, Set<PartType> target);

    /**
     * Remove a part from the list of requirements of part reference
     * @param reference: a part  
     * @param target: the part that is going to be removed part
     */
    void removeRequirement(PartType reference, PartType target);

}
