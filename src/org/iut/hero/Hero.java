package org.iut.hero;

import org.iut.actionspeciale.ActionSpeciale;

public abstract class Hero {
	
	private int pdv;
	private int mana;
	private int armure;
	private ActionSpeciale sp;
	
	public Hero(int pdv, int mana, ActionSpeciale sp) {
		this.pdv = pdv;
		this.mana = mana;
		this.sp = sp;
	}
	
	
	
}
