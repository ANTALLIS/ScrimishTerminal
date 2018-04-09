import java.awt.*;

public class PlayerTestDrive {
	public static void main(String[] args) {
//		Player player1 = new Player("Dave", 5);
		Player player2 = new Player("Steve");
		System.out.println("");
//		System.out.println(player1.getName());
		System.out.println(player2.getName());
		System.out.println("");
//		player1.printPiles();
		player2.printPiles();
		System.out.println("");
//		player1.printTopCards();
		player2.printTopCards();
	}
}
