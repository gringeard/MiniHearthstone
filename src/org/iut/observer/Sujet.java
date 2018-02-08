/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer;

import java.util.ArrayList;
import org.iut.carte.Carte;
import org.iut.hero.Hero;
import org.iut.observer.stateJoueur.EtatJoueur;
import org.iut.observer.stateJoueur.EtatJoueurDebut;
import org.iut.observer.stateJoueur.EtatJoueurDebuterTour;
import org.iut.observer.stateJoueur.EtatJoueurFinirTour;
import org.iut.observer.stateJoueur.EtatJoueurJouer;
import org.iut.observer.stateJoueur.EtatJoueurPiocher;
import org.iut.services.Service;

/**
 *
 * @author Gwen
 */
public class Sujet {
    protected String nom;
    protected Hero hero;
    protected int mana;
    protected boolean first;
    protected ArrayList<Carte> cartesEnPile;
    protected ArrayList<Carte> cartesEnMain;
    protected ArrayList<Carte> cartesPosees;
    protected ArrayList<Observer> obs;
    
    private EtatJoueur etatJoueurDebut;
    private EtatJoueur etatJoueurJouer;
    private EtatJoueur etatJoueurPiocher;
    private EtatJoueur etatJoueurDebuterTour;
    private EtatJoueur etatJoueurFinirTour;

    protected EtatJoueur etatJoueurCourant;

    public Sujet(String nom, int mana) {
        this.nom = nom;
        this.mana = mana;
        this.first = false;
        cartesEnMain = new ArrayList<>();
        cartesPosees = new ArrayList<>();
        obs = new ArrayList<>();
        
        etatJoueurDebut = new EtatJoueurDebut(this);
        etatJoueurJouer = new EtatJoueurJouer(this);
        etatJoueurPiocher = new EtatJoueurPiocher(this);
        etatJoueurDebuterTour = new EtatJoueurDebuterTour(this);
        etatJoueurFinirTour = new EtatJoueurFinirTour(this);
        
        etatJoueurCourant = etatJoueurDebut;
    }
    
  public void ajoutObs( Observer o ) { obs.add( o ); }
  
  public void supprObs( Observer o ) { obs.remove( o ); }
  
  private void notifier()
  {
    for( Observer o : obs )
      o.update( hero, mana, cartesEnPile, cartesEnMain, cartesPosees );
  }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public ArrayList<Carte> getCartesEnPile() {
        return cartesEnPile;
    }

    public void setCartesEnPile(ArrayList<Carte> cartesEnPile) {
        this.cartesEnPile = cartesEnPile;
        notifier();
    }

    public ArrayList<Carte> getCartesEnMain() {
        return cartesEnMain;
    }

    public void setCartesEnMain(ArrayList<Carte> cartesEnMain) {
        this.cartesEnMain = cartesEnMain;
    }

    public ArrayList<Carte> getCartesPosees() {
        return cartesPosees;
    }

    public void setCartesPosees(ArrayList<Carte> cartesPosees) {
        this.cartesPosees = cartesPosees;
    }
    
    public void addCartesEnMain(Carte carte){
        this.cartesEnMain.add(carte);
    }
    
    public void piocherCarteAleatoirement(){
        int indice = (int) (Math.random() * this.cartesEnPile.size());
        Carte nouvelleCarte = this.cartesEnPile.get(indice);
        nouvelleCarte.changerEtatEnMain();
        this.addCartesEnMain(nouvelleCarte);
    }
    
    //Transitions
    public void piocher(){
        etatJoueurCourant.piocher();
        notifier();
    }
    
    public void jouer(){
        etatJoueurCourant.piocher();
        notifier();
    }
    
    public void attaquer(){
        etatJoueurCourant.attaquer();
    }
    
    public void jouerCarte(){
        etatJoueurCourant.jouerCarte();
    }
    
    public void lancerActionSpeciale(){
        etatJoueurCourant.lancerActionSpeciale();
    }
    
    public void debuterTour(){
        etatJoueurCourant.debuterTour();
        notifier();
    }
    
    public void finirTour(){
        etatJoueurCourant.finirTour();
    }
    
    // Actions
    public void afficherMessage()
    {
        etatJoueurCourant.afficherMessage();
    }
  
    public void changerEtatJoueurJouer()
    {
        etatJoueurCourant = etatJoueurJouer;
        afficherMessage();
    }
    
    public void changerEtatJoueurPiocher()
    {
        etatJoueurCourant = etatJoueurPiocher;
        afficherMessage();
    }
    
    public void changerEtatJoueurDebuterTour()
    {
        etatJoueurCourant = etatJoueurDebuterTour;
        afficherMessage();
    }
    
    public void changerEtatJoueurFinirTour()
    {
        etatJoueurCourant = etatJoueurFinirTour;
        afficherMessage();
    }
  
  
}
