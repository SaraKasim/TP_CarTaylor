package fr.istic.nplouzeau.cartaylor.api;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

class ConfigurationImpl implements Configuration {
	
	private Map <Category, Set<PartType>> variants; 
	private Set<Category> cat;
	private Set<PartType> selectedParts = new  HashSet<PartType>();
	private CompatibilityChecker comp;
	private PartType part; // not initialized
	
	// Constructor 
	public ConfigurationImpl ( CompatibilityChecker c, Map <Category, Set<PartType>> variants ) {
		this.comp =c;
		this.variants = variants;
		this.cat = variants.keySet();
	}
	
	@Override 
	public Set<PartType> getSelectedParts() {
		return Collections.unmodifiableSet(selectedParts);
	}
	
	@Override // is it correct? probably, but it's dumb
	public boolean isValid() {
		boolean isComp = true;
		boolean isReq = true;
		boolean isValid = false;
		Set<PartType> reqs = new HashSet<PartType>();
		Set<PartType> incomps = new HashSet<PartType>();
		for (PartType p : selectedParts) {

			incomps = comp.getIncompatibilities(p);
			if (!incomps.isEmpty()) {
				for (PartType in : incomps) {
					isComp = !(selectedParts.contains(in));
				}
			}
		}

		for (PartType p : selectedParts) {
			reqs = comp.getRequirements(p);
			if (!reqs.isEmpty()) {
				for (PartType r : reqs) {
					isReq = (selectedParts.contains(r));
				}
			}
		}
		isValid = (isReq && isComp);
		return isValid;
	}
    
	@Override
	public boolean isComplete() {
		boolean valid = false;

		for (Category c : cat) {
			for (PartType p : selectedParts) {
				valid = (selectedParts.contains(p) && p.getCategory() == c);
			}
		}
		return valid;
	}
	
	@Override //done?
    public void selectPart(PartType chosenPart) {
    		selectedParts.add(chosenPart);
    }
	
	@Override
    public PartType getSelectionForCategory(Category category) {
		for (PartType p : selectedParts) {
			if (p.getCategory().equals(category)) {
				part= p;
			}
		}
		return part;
    }
    
	@Override 
    public void unselectPartType(Category categoryToClear) {
		for (PartType p : selectedParts) {
			if (p.getCategory().equals(categoryToClear)) {
				selectedParts.remove(p);
			}
		}
    }

	@Override // done?
    public void clear() {
    	selectedParts.clear();
    }
    
}
