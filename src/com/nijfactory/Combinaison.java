package com.nijfactory;

public class Combinaison {
	public String[] v_combinaison;
	/** Constructeur
	 * 
	 * @param nombreCase
	 */
	Combinaison(int nombreCase) {
		this.v_combinaison = new String[nombreCase];
	}
	/** Définir la combinaison de couleur sans aucun doublon
	 * 
	 * Se deplace sur chaque case de la combinnaison
	 * Pour chaque case 
	 * 	 Si doublon
	 * 		Choisit une valeur de couleur aleatoirement
	 * 		Recherche de doublon
	 * Definir : La valeur de la couleur dans la case de la combinaison
	 * @param maListeCouleur
	 */
	public void generate(String[][] maListeCouleur) {
		int i = 0, valeur = 0;
		boolean estDoublon = false;	
		for (i = 0; i < v_combinaison.length; i++) {
			estDoublon = true;
			do {
				valeur = (int)(Math.random() * maListeCouleur[0].length);
				estDoublon = this.rechercheDoublon(maListeCouleur[0][valeur], this.v_combinaison);
			} while(estDoublon);
			v_combinaison[i] = maListeCouleur[0][valeur];
		}
	}
	/** Recherche de doublon dans la combinaison
	 * 
	 * Boucle : Se deplace sur chaque case de la combinaison
	 * si Valeur existe alors on retourne "Doublon True"
	 * sinon Valeur n'existe pas on retourne "Doublon False"
	 * 
	 * @param valeur
	 * @param combinaison
	 * @return boolean
	 */
	private boolean rechercheDoublon(String valeur, String[] combinaison) {
		boolean doublon = false;
		int i = 0;
		for (i = 0; i < combinaison.length; i++) {
			if (combinaison[i] == valeur) {
				doublon = true;
				break;
			} 
			else {
				doublon = false;
			}
		}
		return doublon;
	}
}
