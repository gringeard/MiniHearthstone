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
import org.iut.carte.Effet;
import org.iut.carte.decorator.Bonus1AttaqueAllies;
import org.iut.carte.decorator.Charge;
import org.iut.carte.decorator.Provocation;
import org.iut.carte.decorator.VolDeVie;

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
        cartes.add(new CarteSort("Image miroir", 1, new Effet() ));
        cartes.add(new CarteSort("Explosion des arcanes", 2, new Effet() ));
        cartes.add(new CarteSort("Métamorphose", 4, new Effet() ));

        return cartes ;
    }
    
    public static ArrayList<Carte> getCartesGuerrier()
    {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new CarteSort("Tourbillon", 1, new Effet() ));
        cartes.add(new Provocation(new CarteServiteur("Avocat commis d'office", 2, 0, 7 )));
        cartes.add(new CarteSort("Maîtrise du blocage", 3, new Effet() ));
        
        return cartes ;
    }
    
    public static ArrayList<Carte> getCartesPaladin()
    {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new Charge(new VolDeVie(new CarteServiteur("Champion frisselame", 4, 3, 2 ))));
        cartes.add(new CarteSort("Bénédiction de puissance", 1, new Effet() ));
        cartes.add(new CarteSort("Consécration", 4, new Effet() ));
        
        return cartes ;
    }
}
