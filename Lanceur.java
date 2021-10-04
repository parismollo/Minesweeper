class Lanceur{
	public static void main(String args[]){
		System.out.println("        Minesweeper       ");
		Joueur j = new Joueur();
		String nom = j.demanderNom();
		j.setNom(nom);
		// [TODO] Do while veut jouer...
		if(j.veutJouer()){
			int c[] = j.demanderDimensions();
			int mines = j.demanderNbMines();
			Plateau p = new Plateau(c[0], c[1], mines);
			Jeu jeu = new Jeu(j, p);
			jeu.jouer();
		}else{
			j.finish();
		}

	}
}