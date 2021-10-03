public class Test{
	public static void main(String args[]){
		display_test();
		// display_mines_test();
		// test_adjance();
		affiche_tout_test();

	}


	public static void display_test(){
		System.out.println("----------#1 Display Test----------");
		Plateau p = new Plateau(5, 5, 1);
		Plateau.helper_display(p.getEtats());
		System.out.println();
		System.out.println("-----------------------------------");
	}
	// public static void display_mines_test(){
	// 	System.out.println("----------#2 Display Mines Test----------");
	// 	Plateau p = new Plateau(2, 2, 4);
	// 	Plateau.helper_display(p.getMines());
	// 	System.out.println();
	// 	System.out.println();
	// 	p.ajouteMinesAlea();
	// 	Plateau.helper_display(p.getMines());
	// 	System.out.println();
	// 	System.out.println("-----------------------------------");
	// }

	// public static void test_adjance(){
	// 	System.out.println("----------#3 Test Adjance----------");
	// 	Plateau p = new Plateau(3, 3, 2);
	// 	p.ajouteMinesAlea();
	// 	Plateau.helper_display(p.getMines());
	// 	System.out.println();
	// 	System.out.println();
	// 	Plateau.helper_display(p.getAdja());
	// 	System.out.println();
	// 	System.out.println();
	// 	p.calculeAdjacence();
	// 	Plateau.helper_display(p.getAdja());
	// 	System.out.println();
	// 	System.out.println("-----------------------------------");
	// }

	public static void affiche_tout_test(){
		Plateau p = new Plateau(8, 8, 10);
		p.afficheTout();
		System.out.println();
		Plateau p2 = new Plateau(4, 4, 10);
		p2.afficheTout();
		System.out.println();
		Plateau p3 = new Plateau(5, 12, 10);
		p3.afficheTout();
		System.out.println();
	}
}