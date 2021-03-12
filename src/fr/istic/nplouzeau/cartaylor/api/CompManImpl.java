package fr.istic.nplouzeau.cartaylor.api;

import java.util.*;

public class CompManImpl implements CompatibilityManager {
	
	Map<PartType,Set<PartType>> incomp;
	Map<PartType,Set<PartType>> req;
	
	public CompManImpl (Map<PartType,Set<PartType>> i, Map<PartType,Set<PartType>>r) {
		this.incomp = i;
		this.req = r;
	}
	
	public Set<PartType> getIncompatibilities(PartType reference){
		if (incomp.containsKey(reference)) {
			return Collections.unmodifiableSet(incomp.get(reference));
		} else
			return new HashSet<PartType>();
	}

	public Set<PartType> getRequirements(PartType reference) {
		if (req.containsKey(reference)) {
			return Collections.unmodifiableSet(req.get(reference));
		} else
			return new HashSet<PartType>();
	}
	
    // the target parts shouldn't be required 
	public void addIncompatibilities(PartType reference, Set<PartType> target) {
		for (PartType p : target) {

			if (req.containsKey(reference)) {
				if (!(req.get(reference).contains(p))) {
					if (incomp.containsKey(reference)) {
						incomp.get(reference).add(p);
					} else {
						incomp.put(reference, target);
					}
				}
			} else {
				if (incomp.containsKey(reference)) {
					incomp.get(reference).add(p);
				} else {
					incomp.put(reference, target);
				}
			}
		}
	}
	
	public void removeIncompatibility(PartType reference, PartType target) {
		if (incomp.containsKey(reference)) {
			if (incomp.get(reference).contains(target)) {
				incomp.get(reference).remove(target);
			}
		}
	}
	
    // the required parts shouldn't be incompatible
	public void addRequirements(PartType reference, Set<PartType> target) {
		Set<PartType> refSet = new HashSet<PartType>();
		for (PartType p : target) {
			if (incomp.containsKey(reference)) {
				if (!(incomp.get(reference).contains(p))) {
					if (req.containsKey(reference)) {
						req.get(reference).add(p);	
					} else {
						req.put(reference, target);	
					}
					
					if(req.containsKey(p)) {
						req.get(p).add(reference);
					} else {
						refSet.add(reference);
						req.put(p, refSet);
					}
				}
			} else {
				if (req.containsKey(reference)) {
					req.get(reference).add(p);	
				} else {
					req.put(reference, target);	
				}
				if(req.containsKey(p)) {
					req.get(p).add(reference);
				} else {
					refSet.add(reference);
					req.put(p, refSet);
				}
			}
		}
	}

    public void removeRequirement(PartType reference, PartType target) {
    	if (req.containsKey(reference)) {
			if (req.get(reference).contains(target)) {
				req.get(reference).remove(target);
			}
		}
    	if (req.containsKey(target)) {
			if (req.get(target).contains(reference)) {
				req.get(target).remove(reference);
			}
		}
    }
    
}
