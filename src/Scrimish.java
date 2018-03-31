public class Scrimish {
	public void startGame() {
		Player p1 = new Player("Bob");
		Player p2 = new AutoPlayer("Dave");

		p1.setupDefault();
		p2.setupDefault();

		while(true) {
			p1.printPiles();
			p2.printPiles();

			p1.attackPlayer(p2);

			p1.printPiles();
			p2.printPiles();

			p2.attackPlayer(p1);
		}
	}
	
	public static void main(String[] args) {
		Scrimish game1 = new Scrimish();
		game1.startGame();
	}
}
