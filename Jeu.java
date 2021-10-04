public class Jeu{
	Joueur joueur;
	Plateau plateau;

	public Jeu(Joueur j, Plateau p){
		this.joueur = j;
		this.plateau = p;
	}

	public void jouer(){ // [TODO] rejouer? 
		do{
			this.plateau.afficheCourant();
			System.out.println("\n");
			char a = this.joueur.demanderAction();
			System.out.println("\n");
			int c[] = this.joueur.demanderCoordonnes();
			System.out.println("\n");
			if(a=='r'){
				this.plateau.revelerCase(c[0], c[1]);
			}
			else if (a=='d'){
				this.plateau.drapeauCase(c[0], c[1]);
			}
		}while(this.plateau.jeuPerdu()==false && this.plateau.jeuGagne() == false);
		if(this.plateau.jeuPerdu()){
			this.plateau.afficheTout();
			System.out.println("\nVous avez perdu!");
		}
		else if(this.plateau.jeuGagne()) {
			this.plateau.afficheTout();
			System.out.println("\nVous avez gagne!");
		}
	}
}