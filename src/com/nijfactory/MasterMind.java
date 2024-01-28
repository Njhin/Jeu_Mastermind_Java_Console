package com.nijfactory;

import java.util.Scanner;

public class MasterMind {
	/** Variable Globale
	 * 
	 *
	 */
	final static String[][] COULEUR_LISTE = new String[][] {
		{"J","B","R","V","O","N"},{"Jaune","Bleu","Rouge","Vert","Orange","Noir"}};
	final static int MAX_TOURS = 12;
	final static int NOMBRE_CASE = 4;
	
	/** Programme principale
	 *  
	 * Message : le nom du jeu
	 * Lance la boucle pour rejouer
	 * 	Message : les regles
	 * 	Lance le jeu
	 * 	Message : Demande à rejouer
	 *  Definir : Variable pour Rejouer grace à la Saisie de l'utilisateur
	 * Message : Dire au revoir
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Core masterMind = new Core(MAX_TOURS, NOMBRE_CASE, COULEUR_LISTE);
		masterMind.v_msg.getNomDuJeu();
		do {
			masterMind.v_msg.getRegle();
			masterMind.Jouer();
			masterMind.v_msg.getRejouer();
			masterMind.NouvellePartie(scanner.nextLine());
		} while(masterMind.v_nouvellePartie);
		masterMind.v_msg.getEpilogue();	
		scanner.close();
	}
}
