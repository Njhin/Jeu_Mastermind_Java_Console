package com.nijfactory;

/** Regroupe tous les messages pour le jeu
 * 
 * @author Nij
 * */
public class IO_Message {
	/* Attributs */
	final private int v_nombreTour;
	final private int v_nombreCase;
	final private int v_nombreCouleur;
	final private String[][] v_listeCouleur;
	
	/* Méthodes */
	/** Constructeur de la classe IO_Message
	 * 
	 * @param nombreTour
	 * @param nombreCase
	 * @param listeCouleur
	 *
	 */
	IO_Message(int nombreTour, int nombreCase, String[][] listeCouleur) {
		this.v_nombreTour = nombreTour;
		this.v_nombreCase = nombreCase;
		this.v_listeCouleur = listeCouleur;
		this.v_nombreCouleur = this.v_listeCouleur[0].length;
	}
	/** Affichage le nom du jeu
	 * 
	 * @param void
	 * @return void
	 */
	public void getNomDuJeu() {
		this.getPagination(1, 1);
		System.out.println(" *     * ******* ******* ******* ******* ****** ");
		System.out.println(" **   ** *     * *          *    *       *     *");
		System.out.println(" * * * * *     * *          *    *       *     *");
		System.out.println(" *  *  * ******* *******    *    *****   ****** ");
		System.out.println(" *     * *     *       *    *    *       *   *  ");
		System.out.println(" *     * *     *       *    *    *       *    * ");
		System.out.println(" *     * *     * *******    *    ******* *     *");
		System.out.println(); 
		System.out.println("         *     * ******* *     * *****          ");
		System.out.println("         **   **    *    **    * *    *         ");
		System.out.println("         * * * *    *    * *   * *     *        ");
		System.out.println("         *  *  *    *    *  *  * *     *        ");
		System.out.println("         *     *    *    *   * * *     *        ");
		System.out.println("         *     *    *    *    ** *    *         ");
		System.out.println("         *     * ******* *     * *****          ");
		this.getPagination(1, 1);
	}
	/** Affiche les regles du jeu
	 * 
	 * @param void
	 * @return void
	 */
	public void getRegle() {
		this.getPagination(2, 1);
		System.out.println(("Règles du jeu").toUpperCase()); 
		System.out.println("Vous disposez de " + this.v_nombreTour + " essais pour trouver la combinaison secrète."); 
		System.out.println("La combinaison est constitué de " + this.v_nombreCase + " couleurs parmis les " + this.v_nombreCouleur + " couleurs possibles."); 		
		this.getPagination(2, 1);
	}
	/** Affiche une mise en page (saut de ligne ou barre de separation)
	 * 
	 * @param mode : [1] ligne vide, [2] ligne de separation
	 * @param nombre : nombre de ligne a reproduire uniquement pour le mode 1
	 * @return void
	 */
	public void getPagination(int mode, int nombre) {
		if (mode == 1) {
			for (int i = 0; i < nombre; i++ ) {
				System.out.println();			
			}			
		} 
		else if (mode == 2) {
			System.out.println("*******************************************************************************************");			
		} 
		else {
			System.out.println();			
		}
	}
	/** Affiche les differentes possiblités de jeu
	 * 
	 * @param void
	 * @return void
	 */
	public void getPossibilite () {
		String str="";
		String strTotal="";
		System.out.println(("Possibilité").toUpperCase()); 
		for (int i=0; i < this.v_listeCouleur[0].length; i++) {
			str =  this.v_listeCouleur[1][i];
			str =str.replaceAll(this.v_listeCouleur[0][i], "["+this.v_listeCouleur[0][i]+"]");
			strTotal += " " + str ;
		}
		this.getPagination(1,1);
		System.out.println(strTotal);
		this.getPagination(1,1);
	}
	/** Affiche le nombre de tour auquel on est
	 * 
	 */
	public void getTourActuel (int tourActuel) {
		System.out.println("Tour : " + tourActuel + " sur " + this.v_nombreTour);		
	}
	/** Affiche un message d'erreur pour la saisie manuelle des combinaisons.
	 * 
	 * @param void
	 * @return void
	 */
	public void getErreurSaisie() {
		System.out.println("Veuillez entrer une combinaison de " + this.v_nombreCase + " lettres cohérente");
	}
	/** Affiche le resultat de la combinaison propose.
	 * 
	 * @param resultat
	 * @return void
	 */
	public void getResultat(int[] resultat) {
		System.out.println("Vous avez " + resultat[0] + " bien placé(s) et " + resultat[1] + " mal placé(s).");
	}
	/** Affiche le message de fin du jeu : Gagner
	 * 
	 * @param nombreTours
	 * @return void
	 */
	public void getGagner(int nombreTours) {
		this.getPagination(0,1);
		this.getPagination(2,1);
		switch (nombreTours) {
		case 1:
			System.out.println("Waooouhhh en " + nombreTours + " coup, vous etes un champion.");		
			break;
		case 2:
			System.out.println("Vous etes trop fort ! Oui vous, juste en " + nombreTours + " coups.");					
			break;
		case 11:
			System.out.println("Super, mais c'etait limite vu que vous avez trouver en" + nombreTours + " coups.");					
			break;
		case 12:
			System.out.println("Comment j'ai eu peur pour vous ! Reussite en " + nombreTours + " coups.");		
			break;
		default :
			System.out.println("Bravo vous avez gagner en " + nombreTours + " coups.");			
		}	
		this.getPagination(2,1);
	}
	/** Affiche le message de fin du jeu : Perdu
	 * 
	 * @param nombreTours
	 * @return void
	 */	
	public void getPerdre(int nombreTours, Combinaison comb) {
		this.getPagination(0,1);
		this.getPagination(2,1);
		System.out.println("Vous avez Perdu, Dommage.");
		System.out.print("La combinaison secrete etait : ");
		this.getAffichageCombinaison(1, comb.v_combinaison);
		this.getPagination(2,1);
	}
	/** Affiche une question pour rejouer la partie
	 * 
	 * @param void
	 * @return void
	 */
	public void getRejouer () {
		System.out.print("Voulez vous rejouer (O/N) ? : ");
	}
	/** Affiche une phrase pour dire bye bye
	 * 
	 * @param void
	 * @return void
	 */
	public void getEpilogue() {
		this.getPagination(1,1);
		System.out.println("Au revoir.");
	}
	
	/** Affiche le resultat de la combinaison
	 * 
	 * @param mode : valeur 0 caché ; valeur 1 lisible
	 * @param combinaison
	 */
	public void getAffichageCombinaison(int mode, String[] combinaison) {
		int i = 0;
		String resultat = "";
		
		for (i=0; i< combinaison.length; i++) {
			if (mode == 0) {
				resultat += "*" + " ";			
			} else {
				resultat += combinaison[i] + " ";	
			}
		}
		System.out.println(resultat);
	}
}
