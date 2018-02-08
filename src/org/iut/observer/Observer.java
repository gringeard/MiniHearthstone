/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.observer;

import java.util.ArrayList;
import org.iut.carte.Carte;
import org.iut.hero.Hero;

/**
 *
 * @author Gwen
 */
public abstract class Observer {
    protected Hero hero;
    protected int mana;
    protected ArrayList<Carte> cartesEnPile;
    protected ArrayList<Carte> cartesEnMain;
    protected ArrayList<Carte> cartesPosees;
    protected Sujet sujet;
    
    public Observer( Sujet s )
    {
        sujet = s;
        hero = s.getHero();
        sujet.ajoutObs( this );
        
    }
    
    public void update( Hero hero, int mana, ArrayList<Carte> cartesEnPile, ArrayList<Carte> cartesEnMain, ArrayList<Carte> cartesPosees  )
    {
        this.hero = hero;
        this.cartesEnPile = cartesEnPile;
        this.cartesEnMain = cartesEnMain;
        this.cartesPosees = cartesPosees;
        System.out.println(this.sujet.getNom()+"{mana=" + this.sujet.getMana() + "}");
        System.out.println( this.hero );
        System.out.println( "cartes en main : " + this.cartesEnMain );
        System.out.println( "cartes posees : " + this.cartesPosees );
        System.out.println( "cartes en pile : " + this.cartesEnPile );
    }
}
