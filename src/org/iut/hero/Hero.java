package org.iut.hero;

import org.iut.actionspeciale.ActionSpeciale;
import org.iut.cible.Cible;

public abstract class Hero implements Cible {
	
    protected int pdv_;
    protected int armure_;
    protected ActionSpeciale sp_;

    public Hero() {
            pdv_ = 30;
            armure_ = 0;
    }

    public int getPdv_() {
        return pdv_;
    }

    public void setPdv_(int pdv_) {
        this.pdv_ = pdv_;
    }

    public int getArmure_() {
        return armure_;
    }

    public void setArmure_(int armure_) {
        this.armure_ = armure_;
    }
    
    

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" + "pdv=" + pdv_ + ", armure=" + armure_ + '}';
    }

    @Override
    public void recevoirDegats(int i) {
        this.pdv_ -= i;
    }

    public void bonusArmure(int i) {
        this.armure_ += i;
    }

    public ActionSpeciale getActionSpeciale() {
        return sp_;
    }

    public void setActionSpeciale(ActionSpeciale sp_) {
        this.sp_ = sp_;
    }

}
