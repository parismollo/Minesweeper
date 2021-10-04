import java.util.Scanner;

public class Joueur{
	private String nom;
	Scanner scanResponse;

	public Joueur(){
		this.nom = "Anonyme";
		this.scanResponse = new Scanner(System.in);
	}

	public void setNom(String nom){
		this.nom = nom;
	}
	public void finish(){
		this.scanResponse.close();
	}

	public boolean veutJouer(){
		boolean veutJoeur;
		String s = demandeStr("Voulez-vous jouer (oui/non)");
		if (s.equals("oui") || s.equals("o") || s.equals("OUI") || s.equals("O")){
			return true;
		}
		else if (s.equals("non") || s.equals("n") || s.equals("NON") || s.equals("N")){
			return false;
		}else{
			return false;
		}
	}
	public String demanderNom(){
		return demandeStr("Nom du joueur?");
	}

	public int[] demanderDimensions(){
		int h = demandeInt("Hauteur du tableau?");
		int l = demandeInt("Largeur du tableau?");
		int t[] = {h, l};
		return t;
	}

	public int demanderNbMines(){
		return demandeInt("Nombre de mines?");
	}

	public char demanderAction(){
		String s = demandeStr("Voulez-vous reveler une case (r) ou placer un drapeau (d) ?");
		return s.charAt(0);
	}

	public int[] demanderCoordonnes(){
		String s = demandeStr("Indiquez les coordonnees: ");
		int t[] = new int[2];
		char alpha[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		for(int i=0; i<alpha.length; i++){
			if (alpha[i] == s.charAt(0)){
				t[0] = i+1;
			}
		}
		t[1] = Integer.parseInt(String.valueOf(s.charAt(1)));
		return t;
	}

	private String demandeStr(String q){
		System.out.println(q);
		return this.scanResponse.next();
	}

	private int demandeInt(String q){
		System.out.println(q);
		return this.scanResponse.nextInt();
	}
}