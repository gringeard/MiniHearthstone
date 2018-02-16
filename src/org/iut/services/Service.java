/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.services;

import java.util.ArrayList;
import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
import org.iut.carte.CarteSort;
import org.iut.carte.decorator.serviteur.Bonus1AttaqueAllies;
import org.iut.carte.decorator.serviteur.Charge;
import org.iut.carte.decorator.serviteur.Provocation;
import org.iut.carte.decorator.serviteur.VolDeVie;
import org.iut.carte.decorator.sort.BenedictionPuissance;
import org.iut.carte.decorator.sort.Consecration;
import org.iut.carte.decorator.sort.ExplosionArcanes;
import org.iut.carte.decorator.sort.ImageMiroir;
import org.iut.carte.decorator.sort.MaitriseBlocage;
import org.iut.carte.decorator.sort.Metamorphose;
import org.iut.carte.decorator.sort.Tourbillon;

/**
 *
 * @author Gwen
 */
public class Service {
    
    public static ArrayList<Carte> getCartesCommunes()
    {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new CarteServiteur("Sanglier brocheroc", 1, 1, 1 ));
        cartes.add(new Provocation(new CarteServiteur("Soldat du comté-de-l'or", 1, 1, 2 )));
        cartes.add(new Charge(new CarteServiteur("Chevaucheur de loup", 3, 3, 1 )));
        cartes.add(new Bonus1AttaqueAllies(new CarteServiteur("Chef de raid", 3, 2, 2 )));
        cartes.add(new CarteServiteur("Yeti noroit", 4, 4, 5 ));


        return cartes ;
    }
    
    public static ArrayList<Carte> getCartesMage()
    {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new ImageMiroir(new CarteSort("Image miroir", 1)));
        cartes.add(new ExplosionArcanes(new CarteSort("Explosion des arcanes", 2)));
        cartes.add(new Metamorphose(new CarteSort("Métamorphose", 4)));

        return cartes ;
    }
    
    public static ArrayList<Carte> getCartesGuerrier()
    {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new Tourbillon(new CarteSort("Tourbillon", 1)));
        cartes.add(new Provocation(new CarteServiteur("Avocat commis d'office", 2, 0, 7 )));
        cartes.add(new MaitriseBlocage(new CarteSort("Maîtrise du blocage", 3)));
        
        return cartes ;
    }
    
    public static ArrayList<Carte> getCartesPaladin()
    {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new Charge(new VolDeVie(new CarteServiteur("Champion frisselame", 4, 3, 2 ))));
        cartes.add(new BenedictionPuissance(new CarteSort("Bénédiction de puissance", 1)));
        cartes.add(new Consecration(new CarteSort("Consécration", 4)));
        
        return cartes ;
    }
}
