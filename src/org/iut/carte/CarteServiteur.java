package org.iut.carte;

import org.iut.carte.state.Etat;
import org.iut.carte.state.EtatDors;
import org.iut.carte.state.EtatPretAAttaquer;
import org.iut.cible.Cible;

public class CarteServiteur extends Carte implements Cible {

    private Etat etatDors_;
    private Etat etatPretAAttaquer_;
    
    public int degat_;
    public int pv_;
    
    public CarteServiteur(String nom, int mana, int degat, int pv) {
        super(nom, mana);
        etatDors_ = new EtatDors(this);
        etatPretAAttaquer_ = new EtatPretAAttaquer(this);
        degat_ = degat;
        pv_ = pv;
    }
    
    @Override
    public Carte clone() throws CloneNotSupportedException {
        CarteServiteur c = (CarteServiteur) (Carte) super.clone(); 
        c.setEtatDors_(new EtatDors(c));
        c.setEtatPretAAttaquer_(new EtatPretAAttaquer(c));
        return c;
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

    public int getPdv_() {
        return pv_;
    }

    public void setPv(int pv) {
        pv_ = pv;
    }

    public void setEtatDors_(Etat etatDors_) {
        this.etatDors_ = etatDors_;
    }

    public void setEtatPretAAttaquer_(Etat etatPretAAttaquer_) {
        this.etatPretAAttaquer_ = etatPretAAttaquer_;
    }
    
    @Override
    public void recevoirDegats(int i) {
        this.pv_ -= i;
    }

    @Override
    public String toString() {
        return super.toString() + " degat=" + degat_ + " pv=" + pv_;
    }

}
