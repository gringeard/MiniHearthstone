package org.iut.hero;

import org.iut.actionspeciale.ActionSpeciale;

public abstract class Hero {
	
	protected int pdv_;
	protected int mana_;
	protected int armure_;
	protected ActionSpeciale sp_;
	
	public Hero() {
		pdv_ = 30;
		mana_ = 1;
                armure_ = 0;
	}
	
	
	
}
