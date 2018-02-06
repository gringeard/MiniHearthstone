package org.iut.carte;

import org.iut.carte.state.Etat;
import org.iut.carte.state.EtatPretAAttaquer;

public class CarteServiteur extends Carte {

    private Etat etatDors_;
    private Etat etatPretAAttaquer_;
    
    public int degat_;
    public int pv_;
    
    public CarteServiteur(String nom, int mana, int degat, int pv) {
        super(nom, mana);
        etatPretAAttaquer_ = new EtatPretAAttaquer(this);
        degat_ = degat;
        pv_ = pv;
    }
    
    //Transitions
    public void dors(){
        etatCourant_.dors();
    }
    
    public void pretAAttaquer(){
        etatCourant_.pretAAttaquer();
    }

    //Actions
    public void changerEtatDors(){
        etatCourant_ = etatDors_;
        afficherMessage();
    }
    
    public void changerEtatPretAAttaquer(){
        etatCourant_ = etatPretAAttaquer_;
        afficherMessage();
    }
    
    public int getDegat() {
        return degat_;
    }

    public void setDegat(int degat) {
        degat_ = degat;
    }

    public int getPv() {
        return pv_;
    }

    public void setPv(int pv) {
        pv_ = pv;
    }



}
