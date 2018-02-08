/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer;

import java.util.ArrayList;
import org.iut.carte.Carte;
import org.iut.hero.Hero;
import org.iut.services.Service;

/**
 *
 * @author Gwen
 */
public class Sujet {
    protected String nom;
    protected Hero hero;
    protected int mana;
    protected ArrayList<Carte> cartesEnPile;
    protected ArrayList<Carte> cartesEnMain;
    protected ArrayList<Carte> cartesPosees;
    protected ArrayList<Observer> obs;

    public Sujet(String nom, int mana) {
        this.nom = nom;
        this.mana = mana;
        this.cartesEnPile = Service.getCartesCommunes();
        cartesEnMain = new ArrayList<>();
        cartesPosees = new ArrayList<>();
        obs = new ArrayList<>();
    }
    
  public void ajoutObs( Observer o ) { obs.add( o ); }
  
  public void supprObs( Observer o ) { obs.remove( o ); }
  
  private void notifier()
  {
    for( Observer o : obs )
      o.update( hero, mana, cartesEnMain, cartesPosees );
  }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        notifier();
    }

    public String getNom() {
        return nom;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    
    
  
  
}
