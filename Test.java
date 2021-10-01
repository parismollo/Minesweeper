public class Test{
	public static void main(String args[]){
		display_test();
		display_mines_test();
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
		Plateau p = new Plateau(2, 2, 1);
		Plateau.helper_display(p.getMines());
		System.out.println();
		System.out.println();
		p.ajouteMinesAlea();
		Plateau.helper_display(p.getMines());
		System.out.println();
		System.out.println("-----------------------------------");
	}
}