package org.iut.hero;

import org.iut.actionspeciale.ActionSpeciale;

public abstract class Hero {
	
	protected int pdv_;
	protected int armure_;
	protected ActionSpeciale sp_;
	
	public Hero() {
		pdv_ = 30;
                armure_ = 0;
	}

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" + "pdv=" + pdv_ + ", armure=" + armure_ + '}';
    }
        
        
	
	
	
}
