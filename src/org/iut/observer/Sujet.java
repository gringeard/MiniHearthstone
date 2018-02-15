/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
import org.iut.cible.Cible;
import org.iut.hero.Hero;
import org.iut.observer.stateJoueur.EtatJoueur;
import org.iut.observer.stateJoueur.EtatJoueurActionSpeciale;
import org.iut.observer.stateJoueur.EtatJoueurAffronterCarte;
import org.iut.observer.stateJoueur.EtatJoueurAttaquerHero;
import org.iut.observer.stateJoueur.EtatJoueurDebut;
import org.iut.observer.stateJoueur.EtatJoueurDebuterTour;
import org.iut.observer.stateJoueur.EtatJoueurDefendreHero;
import org.iut.observer.stateJoueur.EtatJoueurEnAttente;
import org.iut.observer.stateJoueur.EtatJoueurFinirTour;
import org.iut.observer.stateJoueur.EtatJoueurJouer;
import org.iut.observer.stateJoueur.EtatJoueurJouerCarte;
import org.iut.observer.stateJoueur.EtatJoueurPiocher;
import org.iut.services.Service;

/**
 *
 * @author Gwen
 */
public abstract class Sujet {
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
    private EtatJoueur etatJoueurAttaquerHero;
    private EtatJoueur etatJoueurDefendreHero;
    private EtatJoueur etatJoueurAffronterCarte;
    private EtatJoueur etatJoueurPiocher;
    private EtatJoueur etatJoueurJouerCarte;
    private EtatJoueur etatJoueurDebuterTour;
    private EtatJoueur etatJoueurFinirTour;
    private EtatJoueur etatJoueurEnAttente;
    private EtatJoueur etatJoueurActionSpeciale;

    protected EtatJoueur etatJoueurCourant;

    public Sujet(String nom) {
        this.nom = nom;
        this.mana = 1;
        this.first = false;
        cartesEnMain = new ArrayList<>();
        cartesPosees = new ArrayList<>();
        obs = new ArrayList<>();
        
        etatJoueurDebut = new EtatJoueurDebut(this);
        etatJoueurJouer = new EtatJoueurJouer(this);
        etatJoueurAttaquerHero = new EtatJoueurAttaquerHero(this);
        etatJoueurDefendreHero = new EtatJoueurDefendreHero(this);
        etatJoueurAffronterCarte = new EtatJoueurAffronterCarte(this);
        etatJoueurPiocher = new EtatJoueurPiocher(this);
        etatJoueurJouerCarte = new EtatJoueurJouerCarte(this);
        etatJoueurDebuterTour = new EtatJoueurDebuterTour(this);
        etatJoueurFinirTour = new EtatJoueurFinirTour(this);
        etatJoueurEnAttente = new EtatJoueurEnAttente(this);
        etatJoueurActionSpeciale = new EtatJoueurActionSpeciale(this);
        
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
        try {
            int indice = (int) (Math.random() * this.cartesEnPile.size());
            Carte nouvelleCarte = (Carte) this.cartesEnPile.get(indice).clone();
            nouvelleCarte.piocherCarte();
            this.addCartesEnMain(nouvelleCarte);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void poserCarte(int index){
        Carte carteAPoser = this.cartesEnMain.get(index);
        System.out.println(carteAPoser.getEtatCourant_().getClass().getSimpleName());
        this.cartesEnMain.remove(carteAPoser);
        System.out.println(carteAPoser.getEtatCourant_().getClass().getSimpleName());
        carteAPoser.jouerCarte();
        System.out.println(carteAPoser.getEtatCourant_().getClass().getSimpleName());
        if (carteAPoser instanceof CarteServiteur){
            this.cartesPosees.add(carteAPoser);
            System.out.println(carteAPoser.getEtatCourant_().getClass().getSimpleName());
        }
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
    
    public void attaquerHero(int indexCartePourAttaquer){
        etatJoueurCourant.attaquerHero(indexCartePourAttaquer);
    }
    
    public void defendreHero(Carte c){
        etatJoueurCourant.defendreHero(c);
    }
    
    public void affronterCarte(int indexCJoueur, Carte cAdversaire){
        etatJoueurCourant.affronterCarte(indexCJoueur, cAdversaire);
        if(etatJoueurCourant == etatJoueurJouer){
            notifier();
        }
    }
    
    public void jouerCarte(int index){
        etatJoueurCourant.jouerCarte(index);
        notifier();
    }
    
    public void lancerActionSpeciale(Cible cible) {
        etatJoueurCourant.lancerActionSpeciale(cible);
    }
    
    public void debuterTour(int tour){
        etatJoueurCourant.debuterTour(tour);
        notifier();
    }
    
    public void finirTour(){
        etatJoueurCourant.finirTour();
    }
    
    public void attendreTour(){
        etatJoueurEnAttente.attendreTour();
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
    
    public void changerEtatJoueurAttaquerHero()
    {
        etatJoueurCourant = etatJoueurAttaquerHero;
        afficherMessage();
    }
    
    public void changerEtatJoueurDefendreHero()
    {
        etatJoueurCourant = etatJoueurDefendreHero;
        afficherMessage();
    }
    
    public void changerEtatJoueurAffronterCarte()
    {
    etatJoueurCourant = etatJoueurAffronterCarte;
        afficherMessage();
    }
    
    public void changerEtatJoueurPiocher()
    {
        etatJoueurCourant = etatJoueurPiocher;
        afficherMessage();
    }
    
    public void changerEtatJoueurJouerCarte()
    {
        etatJoueurCourant = etatJoueurJouerCarte;
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
  
    public void changerEtatJoueurAttendreTour()
    {
        etatJoueurCourant = etatJoueurEnAttente;
        afficherMessage();
    }

    public void invoqueCarte(CarteServiteur carteServiteur) {
        this.cartesPosees.add(carteServiteur);
    }

    public void changerEtatJoueurActionSpeciale() {
        etatJoueurCourant = etatJoueurActionSpeciale;
        afficherMessage();
    }
}
