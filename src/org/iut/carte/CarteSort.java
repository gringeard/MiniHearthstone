package org.iut.carte;

import org.iut.carte.state.Etat;
import org.iut.carte.state.EtatLancerSort;

public class CarteSort extends Carte {
    
    private Etat etatLancerSort_;
    
    public Effet effet_;

    public CarteSort(String nom, int mana, Effet effet) {
        super(nom, mana);
        etatLancerSort_ = new EtatLancerSort(this);
        effet_ = effet;
    }

    //Transitions
    public void lancerSort(){
        etatCourant_.lancerSort();
    }
    
    //Actions
    public void changerEtatLancerSort(){
        etatCourant_ = etatLancerSort_;
        afficherMessage();
    }
    
    public Effet getEffet() {
        return effet_;
    }

    public void setEffet(Effet effet) {
        this.effet_ = effet;
    }
    
    @Override
    public String toString() {
        return super.toString() + " effet=" + effet_;
    }
        
        
	
	
}
