package com.nijfactory;

import java.util.Scanner;

public class Core {
	final private int v_nombreTour;
	final private int v_nombreCase;
	final private String[][] v_listeCouleur;
	private int v_tourJoue;
	public IO_Message v_msg;
	public boolean v_nouvellePartie;
	public Combinaison combSecrete;
	public Combinaison combPropose;
	/** Constructeur de la classe IO_Message*
	 * 	
	 * @param nombreTour
	 * @param nombreCase
	 * @param listeCouleur
	 */
	Core (int nombreTour, int nombreCase, String[][] listeCouleur) {
		this.v_nombreTour = nombreTour;
		this.v_nombreCase = nombreCase;
		this.v_listeCouleur = listeCouleur;
		v_msg = new IO_Message(v_nombreTour, v_nombreCase, v_listeCouleur);
		this.v_nouvellePartie = false;
		this.combSecrete = new Combinaison(this.v_nombreCase);
		this.combPropose = new Combinaison(this.v_nombreCase);
		this.v_tourJoue = 0;
	}
	/** Programme du jeu
	 * 
	 * Message : Les differentes possibilités
	 * Définir : La combinaison secrète
	 * Boucle : Si nombre de tour est diff du nb Max de tour ou que l'utlisateur gagne
	 * 	Increment le nombre de tour joué
	 * 	Message : Nombre de tour
	 * 	Message : Combinaison secrete
	 * 	Définir : la combinaison de l'utilisateur
	 * 	Définir : Comparaison entre la combinaison secrete et celle de l'utilisateur
	 * 	Message : Resultat
	 * Message : Affiche Perdu ou Gagné
	 * Definir : Remise a zero du tour joué
	 */
	public void Jouer() {
		int[] resultat = new int[] {0,0};
		v_msg.getPossibilite();
		this.combSecrete.generate(this.v_listeCouleur);
		do {
			this.setTourJoueSuivant();
			v_msg.getTourActuel(this.v_tourJoue);
			v_msg.getAffichageCombinaison(0, combSecrete.v_combinaison);
			SaisieJeu(combPropose);
			RechercheResultat(combSecrete, combPropose, resultat);
			v_msg.getResultat(resultat);
		} while (this.v_tourJoue != this.v_nombreTour && resultat[0] != this.v_nombreCase);
		if (resultat[0] != this.v_nombreCase) {
			v_msg.getPerdre(this.v_tourJoue, combSecrete);
		}else {
			v_msg.getGagner(this.v_tourJoue);
		}
		this.getResetTourJoue();
	}
	/** Incrémente le nombre de tour joué
	 * 
	 */
	private void setTourJoueSuivant() {
		this.v_tourJoue += 1;
	}
	/** Remise a zero du nombre joué
	 * 
	 */
	private void getResetTourJoue() {
		this.v_tourJoue = 0;
	}
	/** Definit la saisie de l'utilissateur et verifie la saisie si elle comprend les lettres jouables
	 * 
	 * Boucle : La siasie doit etre conforme au couleur affichage et a la longueur de la combinaison
	 * Definir : La saisie de l'utilisateur
	 * Test : la longueur de la combinaison
	 * Test : Les couleurs possible
	 * Message : Erreur de saisie
	 * 
	 * @param comb
	 */
	private void SaisieJeu(Combinaison comb) {
		String reponse = "";
		boolean saisieNoCorrect = true, saisieExiste = true;
		Scanner scannerS = new Scanner(System.in);
		do {
			reponse = scannerS.nextLine();
			if (reponse.length()==this.v_nombreCase) {
				saisieExiste = SaisieTestExiste(reponse, comb);
				if (saisieExiste == true)  {
					saisieNoCorrect = false;			
				}
			}
			if (saisieNoCorrect) {  
				this.v_msg.getErreurSaisie();
			}
		} while (saisieNoCorrect);
	}
	/** Test la saisie avec les couleurs possibles
	 * 
	 * Boucle : Se deplace de caractere en caractere au sein de la combinaison
	 * 	Boucle : Se deplace de couleur en couleur au sein de la liste de couleur
	 * 	Définir : La valeur en masjucule dans la combinason
	 * Test : Si erreur pas la peine de continuer
	 * 
	 * @param reponse
	 * @param comb
	 * @return
	 */
	private boolean SaisieTestExiste(String reponse, Combinaison comb) {
		int i = 0, j = 0;
		boolean test = true;
		for(i=0 ; i< comb.v_combinaison.length; i++) {
			for (j=0; j< this.v_listeCouleur[0].length; j++) {
				if (String.valueOf(reponse.charAt(i)).toUpperCase().equals(this.v_listeCouleur[0][j])) {
					test = true;
					comb.v_combinaison[i]= String.valueOf(reponse.charAt(i)).toUpperCase();
					break;
				}
				else {
					test = false;
				}
			}
			if (test == true) {
			}
			else {
				break;
			}	
		}
		return test;
	}
	/** Recherche le nombre de bien et mal placé en comparant les deux combinaison
	 * 
	 * Définir : ReInitialise le param resultat
	 * Boucle : Sur chaque caractere de la combinaison secrete
	 * Test : Si True alors Bien place
	 * sinon Boucle : Sur chaque caractere de la combinaison de l'utilisateur
	 * 	Test : Si True alors Mal placé
	 * 
	 * @param combSecrete
	 * @param combPropose
	 * @param resultat : valeur [0] bien place [1] mal place
	 */
	private void RechercheResultat(
			Combinaison combSecrete,
			Combinaison combPropose, 
			int[] resultat) {
		int i = 0, j = 0;
		for (i = 0; i < resultat.length; i++) {
			resultat[i]= 0;
		}
		for (i = 0; i < combSecrete.v_combinaison.length; i++) {
			if (combSecrete.v_combinaison[i].equals(combPropose.v_combinaison[i])) {
				resultat[0] += 1;
			}
			else {
				for (j = 0; j < combPropose.v_combinaison.length; j++) {
					if (combSecrete.v_combinaison[i].equals(combPropose.v_combinaison[j])) {
						resultat[1] += 1;
						break;
					}			
				}				
			}
		}
	}
	/** Definir la variable pour rejouer
	 * 
	 * Test : Rejouer ou Pas Rejouer
	 * 
	 * @param reponse
	 */
	public void NouvellePartie(String reponse) {
		reponse = reponse.toUpperCase();
		if (reponse.contentEquals("O")) {
			this.v_nouvellePartie = true;
		}
		else {
			this.v_nouvellePartie = false;
		}
	}
}
