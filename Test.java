public class Test{
	public static void main(String args[]){
		// display_test();
		// display_mines_test();
		test_adjance();

	}


	public static void display_test(){
		System.out.println("----------#1 Display Test----------");
		Plateau p = new Plateau(5, 5, 1);
		Plateau.helper_display(p.getEtats());
		System.out.println();
		System.out.println("-----------------------------------");
	}
	public static void display_mines_test(){
		System.out.println("----------#2 Display Mines Test----------");
		Plateau p = new Plateau(2, 2, 4);
		Plateau.helper_display(p.getMines());
		System.out.println();
		System.out.println();
		p.ajouteMinesAlea();
		Plateau.helper_display(p.getMines());
		System.out.println();
		System.out.println("-----------------------------------");
	}

	public static void test_adjance(){
		System.out.println("----------#3 Test Adjance----------");
		Plateau p = new Plateau(3, 3, 2);
		p.ajouteMinesAlea();
		Plateau.helper_display(p.getMines());
		System.out.println();
		System.out.println();
		Plateau.helper_display(p.getAdja());
		System.out.println();
		System.out.println();
		p.calculeAdjacence();
		Plateau.helper_display(p.getAdja());
		System.out.println();
		System.out.println("-----------------------------------");
	}
}