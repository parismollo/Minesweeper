class Lanceur{
	public static void main(String args[]){
		System.out.println("        Minesweeper       ");
		Joueur j = new Joueur();
		// [TODO] Do while veut jouer...
		while(j.veutJouer()){
			String nom = j.demanderNom();
			j.setNom(nom);
			int c[] = j.demanderDimensions();
			int mines = j.demanderNbMines();
			Plateau p = new Plateau(c[0], c[1], mines);
			Jeu jeu = new Jeu(j, p);
			jeu.jouer();
		}
		j.finish();

	}
}