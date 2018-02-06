package org.iut.carte;

import org.iut.carte.state.Etat;
import org.iut.carte.state.EtatEnMain;
import org.iut.carte.state.EtatJouee;
import org.iut.carte.state.EtatEnDefausse;
import org.iut.carte.state.EtatEnPile;

public abstract class Carte {
    
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
	
	
}
