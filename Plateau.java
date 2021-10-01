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

	public void ajouteMinesAlea(){
		
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
}