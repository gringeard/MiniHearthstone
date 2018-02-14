package org.iut.carte;

import org.iut.carte.state.Etat;
import org.iut.carte.state.EtatEnMain;
import org.iut.carte.state.EtatJouee;
import org.iut.carte.state.EtatEnDefausse;
import org.iut.carte.state.EtatEnPile;

public abstract class Carte implements Cloneable {
    
    private Etat etatEnPile_;
    private Etat etatEnMain_;
    private Etat etatJouee_;
    private Etat etatEnDefausse_;

    protected Etat etatCourant_;
	
    public String nom_;
    public int mana_;
    
    public Carte(String nom, int mana) {
        etatEnPile_ = new EtatEnPile(this);
        etatEnMain_ = new EtatEnMain(this);
        etatJouee_ = new EtatJouee(this);
        etatEnDefausse_ = new EtatEnDefausse(this);
        
        etatCourant_ = etatEnPile_;
        nom_ = nom;
        mana_ = mana;
    }

    @Override
    public Carte clone() throws CloneNotSupportedException {
        Carte c = (Carte) super.clone();
        c.setEtatEnPile_(new EtatEnPile(c));
        c.setEtatEnMain_(new EtatEnMain(c));
        c.setEtatJouee_(new EtatJouee(c));
        c.setEtatEnDefausse_(new EtatEnDefausse(c));
        c.setEtatCourant_(c.getEtatEnPile_());
        return c;
    }
    
    
    
    //Transitions
    public void piocherCarte(){
        etatCourant_.piocherCarte();
    };
    
    public void jouerCarte(){
        etatCourant_.jouerCarte();
    };
    
    public void defausserCarte(){
        etatCourant_.defausserCarte();
    };
    
    // Actions
    public void afficherMessage()
    {
      etatCourant_.afficherMessage();
    }

    public void changerEtatEnMain()
    {
      etatCourant_ = etatEnMain_;
      afficherMessage();
    }
    
    public void changerEtatJouee()
    {
      etatCourant_ = etatJouee_;
      afficherMessage();
    }
    
    public void changerEtatEnDefausse()
    {
      etatCourant_ = etatEnDefausse_;
      afficherMessage();
    }
    
    public int getMana() {
        return mana_;
    }

    public String getNom_() {
        return nom_;
    }

    public void setMana(int mana) {
        mana_ = mana;
    }
        
    public void setNom_(String nom_) {
        this.nom_ = nom_;
    }    

    public Etat getEtatCourant_() {
        return etatCourant_;
    }

    public Etat getEtatEnPile_() {
        return etatEnPile_;
    }

    public Etat getEtatEnMain_() {
        return etatEnMain_;
    }

    public Etat getEtatJouee_() {
        return etatJouee_;
    }

    public Etat getEtatEnDefausse_() {
        return etatEnDefausse_;
    }

    
    
    public void setEtatEnPile_(Etat etatEnPile_) {
        this.etatEnPile_ = etatEnPile_;
    }

    public void setEtatEnMain_(Etat etatEnMain_) {
        this.etatEnMain_ = etatEnMain_;
    }

    public void setEtatJouee_(Etat etatJouee_) {
        this.etatJouee_ = etatJouee_;
    }

    public void setEtatEnDefausse_(Etat etatEnDefausse_) {
        this.etatEnDefausse_ = etatEnDefausse_;
    }

    public void setEtatCourant_(Etat etatCourant_) {
        this.etatCourant_ = etatCourant_;
    }
	
    
    @Override
    public String toString() {
        return nom_ + ": mana=" + mana_;
    }
}
