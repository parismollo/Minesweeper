/*
Plateau contiendra plusieurs tableaux représentant l’état du jeu, ainsi que des
fonctions pour agir sur le plateau, pour savoir si la condition de victoire est remplie,
ou si on a perdu.
*/
import java.util.Random;
public class Plateau{
	// champs
	int hauteur; 
	int largeur;

	int nbMines;
	int nbDrapeaux = 0; // initialise avec 0

	private boolean[][] mines; //  indique où sont placées les mines.
	private int[][] etats; // 0 - case cachée sans drapeau, 1 - cachée avec drapeau, 2 - révélée
	private int[][] adja; //nombre de mines adjacentes à chaque case


	// constructeurs
	public Plateau(int ha, int la, int mi){
		this.hauteur = ha;
		this.largeur = la;
		this.nbMines = verifie_nbmines(mi, ha, la);

		this.mines = new boolean[ha+2][la+2];
		this.etats = new int[ha+2][la+2];
		this.adja = new int[ha+2][la+2];

		ajouteMinesAlea();
		calculeAdjacence();


	}
	// methodes
	public static void helper_display(int t[][]){
		System.out.print("[");
		for(int i=0; i<t.length; i++){
			if (i!=0){
				System.out.print(" [");
			}else{
				System.out.print("[");
			}
			for(int j=0; j<t[i].length; j++){
				if (j == t[i].length-1){
					System.out.print(t[i][j]);
				}else{
					System.out.print(t[i][j]+", ");
				}
			}
			System.out.print("]");
			if (i!=t.length-1){
			 System.out.println();
			}
		}
		System.out.print("]");
	}
	public static void helper_display(boolean t[][]){
		System.out.print("[");
		for(int i=0; i<t.length; i++){
			if (i!=0){
				System.out.print(" [");
			}else{
				System.out.print("[");
			}
			for(int j=0; j<t[i].length; j++){
				if (j == t[i].length-1){
					System.out.print(t[i][j]);
				}else{
					System.out.print(t[i][j]+", ");
				}
			}
			System.out.print("]");
			if (i!=t.length-1){
			 System.out.println();
			}
		}
		System.out.print("]");
	}

	public int[][] getEtats(){
		return this.etats;
	}

	public boolean[][] getMines(){
		return this.mines;
	}

	public int[][] getAdja(){
		return this.adja;
	}

	private int getRandomNumber(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}

	private static int verifie_nbmines(int mines, int ha, int la){
		if (mines <= ha * la){
			return mines;
		}else {
			System.out.println("Nb de mines depasse l'autorise, donc plateau est rempli de mines");
			return ha * la;
		}
	}

	private void ajouteMinesAlea(){
		
		// 1. Ajoute mines de façon aléatoire au tableau : Il faut ajouter #'nbMines' au tableau
		// 2. Attention
		// 	- ne posez pas plusieurs fois une mine à la même case: Faut utiliser tableau de boolean de mines
		// 	- ne posez que des mines que dans l’espace de jeu: délimiter espace parcouru par (1, 1) - (h, l)
		// 	-  mise à jour tab d'adjcentes??
		// 3. Utilise NextInt

		int nb_mines_restantes = nbMines; // initialise avec le nb total de mines et réduit au fur et à mesure qui les mines sont placées.
		boolean mine_place = false; 	
		while (nb_mines_restantes != 0){
			// pendant que la mine n'est pas placé on repete le processus de trouver une position au tableau
			// 1. identifie un place au plateau aléatoire (respecte l'espace limité de 1,1 - h, l)
			// 2. vérifie si il existe une mine
			// 3. place si n'existe pas
			// 4. s'il existe, recommence processus
			do{
				int random_ligne = getRandomNumber(1, this.hauteur+1);
				int random_colonne = getRandomNumber(1, this.largeur+1);
				mine_place = (this.mines[random_ligne][random_colonne]);
				if (!mine_place){
					this.mines[random_ligne][random_colonne] = true;
					nb_mines_restantes--;
				}
			}while(mine_place);
		} 
	}

	private void calculeAdjacence(){
		for(int i=1; i<=this.hauteur; i++){
			for(int j=1; j<=this.largeur; j++){
				this.adja[i][j] = count_adj_horizontal_vertical(i, j);
				this.adja[i][j] += count_adj_diagonal(i, j);
			}
		}
	}

	private int count_adj_horizontal_vertical(int i, int j){
		int counter = 0;
		counter += (this.mines[i-1][j]) ? 1 : 0;
		counter += (this.mines[i][j+1]) ? 1 : 0;
		counter += (this.mines[i+1][j]) ? 1 : 0;
		counter += (this.mines[i][j-1]) ? 1 : 0;

		return counter;
	}

	private int count_adj_diagonal(int i, int j){
		int counter = 0;
		counter += (this.mines[i-1][j-1]) ? 1 : 0;
		counter += (this.mines[i-1][j+1]) ? 1 : 0;
		counter += (this.mines[i+1][j+1]) ? 1 : 0;
		counter += (this.mines[i+1][j-1]) ? 1 : 0;

		return counter;
	}
	// [TODO]: Fix bug on large tables mismatch
	public void afficheTout(){
		int alpha_counter = 0;
		String alpha[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		afficheToutIntroduction();
		afficheToutFirstLine();
		afficheToutContenu(alpha);
	}

	private void afficheToutContenu(String[] alpha){
		for(int i=1; i<=this.hauteur; i++){
			System.out.println();
			System.out.print(alpha[i-1]+" | ");
			for(int j=1; j<=this.largeur;j++){
				if(this.mines[i][j]){
					System.out.print(" * ");
				}else{
					System.out.print(" "+this.adja[i][j]+" ");
				}
			}
		}
	}

	private void afficheToutIntroduction(){
		System.out.println("    ********************");
		System.out.println("    * Mines  / Drapeaux *");
		System.out.println("    *    "+this.nbMines+"  /  "+this.nbDrapeaux+"       *");
		System.out.println("    ********************");	
	}

	private void afficheToutFirstLine(){
		String s = "";
		System.out.print("    ");
		for(int k=1; k<=this.largeur;k++){
			s +=" "+k+" ";
			System.out.print(" "+k+" ");
		}
		System.out.println();
		for(int i=0; i<s.length()+3;i++){
			System.out.print("-");
		}
	}

	// [TODO]: USE SWITCH CASE
	// public void revelerCase(int i, int j){
	// 	if(i>=1 && i<=this.hauteur && j>=1 && j<=this.largeur){
	// 		if (this.etats[i][j] == 1){
	// 			System.out.print("Cette case contient un drapeau!");
	// 		}
	// 		if (this.etats[i][j] == 2){
	// 			System.out.print("Cette case est deja revelee!");
	// 		}
	// 		if (this.etats[i][j] == 0){
	// 			this.etats[i][j] = 2;
	// 		}	
	// 	}else{
	// 		System.out.println("Please Enter valid cordinates!");
	// 	}
	// }
	public void revelerCase(int i, int j){
		// Check if (i, j) are in the game limits
		if (isInGame(i, j)){
			switch (this.etats[i][j]){
				case 0:
					this.etats[i][j] = 2;
					if (this.adja[i][j] == 0){
						revelerCase(i-1, j);
						revelerCase(i, j+1);
						revelerCase(i+1, j);
						revelerCase(i, j-1);
						revelerCase(i-1, j-1);
						revelerCase(i-1, j+1);
						revelerCase(i+1, j+1);
						revelerCase(i+1, j-1);
					break;
					}
				case 1:
					System.out.println("Cette case contient un drapeau!");
					break;
				case 2:
					System.out.println("Cette case est deja revelee!");
					break;
			}
		}else{
			System.out.println("Not valid (i, j)");
		}

	}
	private boolean isInGame(int i, int j){ // Check if (i, j) are valid
		if(i>=1 && i<=this.hauteur && j>=1 && j<=this.largeur){
			return true;
		}
		return false;
	}
	public void drapeauCase(int i, int j){
		switch(this.etats[i][j]){
			case 0:
				this.etats[i][j] = 1;
				this.nbDrapeaux++;
				break;
			case 1:
				this.etats[i][j] = 0;
				this.nbDrapeaux--;
				break;
			case 2:
				System.out.println("Case deja revelee");

		}	
	}

	public void afficheCourant(){
		int alpha_counter = 0;
		String alpha[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		afficheCourantIntroduction();
		afficheToutFirstLine();
		afficheCourantContenu(alpha);
	}

	private void afficheCourantContenu(String[] alpha){
		for(int i=1; i<=this.hauteur; i++){
			System.out.println();
			System.out.print(alpha[i-1]+" | ");
			for(int j=1; j<=this.largeur;j++){
				// 1. Si etat == 0 -> .
				// 2. Si  etat == 1 -> ?
				// 3. Si etat == 2 -> adja
				switch(this.etats[i][j]){
					case 0:
						System.out.print(" . ");
						break;
					case 1:
						System.out.print(" ? ");
						break;
					case 2:
						System.out.print(" "+this.adja[i][j]+" ");
				}
				// if(this.mines[i][j]){
				// 	System.out.print(" * ");
				// }else{
				// 	System.out.print(" "+this.adja[i][j]+" ");
				// }
			}
		}
	}
	private void afficheCourantIntroduction(){
		int nbMinesCourantCachee = 0;
		for(int i=1; i<=this.hauteur; i++){
			for(int j=1; j<=this.largeur; j++){
				if(this.etats[i][j] == 0 && this.mines[i][j] == true){
					nbMinesCourantCachee++;
				}
			}
		}
		System.out.println("    ********************");
		System.out.println("    * Mines / Drapeaux *");
		System.out.println("    *    "+nbMinesCourantCachee+"  /  "+this.nbDrapeaux+"       *");
		System.out.println("    ********************");	
	}
	public boolean jeuPerdu(){ 
		for(int i=1; i<this.hauteur; i++){
			for(int j=1; j<this.largeur; j++){
				if(this.mines[i][j]==true && this.etats[i][j]==2){
					return true;
				}
			}
		}
		return false;
	}

	public boolean jeuGagne(){
		for(int i=1; i<this.hauteur; i++){
			for(int j=1; j<this.largeur; j++){
				if(this.mines[i][j]==false && this.etats[i][j] != 2){
					return false;
				}
			}
		}
		return true;
	}
}