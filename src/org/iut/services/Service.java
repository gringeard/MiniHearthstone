/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iut.services;

import java.util.LinkedList;
import org.iut.carte.Carte;
import org.iut.carte.CarteServiteur;
import org.iut.carte.decorator.Bonus1AttaqueAllies;
import org.iut.carte.decorator.Charge;
import org.iut.carte.decorator.Provocation;

/**
 *
 * @author Gwen
 */
public class Service {
    
    public static LinkedList<Carte> getCartesCommunes()
	{
		LinkedList<Carte> cartes = new LinkedList<Carte>();
		cartes.add(new CarteServiteur("Sanglier brocheroc", 1, 1, 1 ));
		cartes.add(new Provocation(new CarteServiteur("Soldat du comté-de-l'or", 1, 1, 2 )));
		cartes.add(new Charge(new CarteServiteur("Chevaucheur de loup", 3, 3, 1 )));
		cartes.add(new Bonus1AttaqueAllies(new CarteServiteur("Chef de raid", 3, 2, 2 )));
		cartes.add(new CarteServiteur("Yeti noroit", 4, 4, 5 ));
		
		
		return cartes ;
	}
}
