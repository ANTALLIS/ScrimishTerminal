public class Scrimish {
	public static void main(String args[]) {
		Player p1 = new Player("Bob");
		Player p2 = new Player("Dave");
	
		p1.setupDefault();
		p2.setupDefault();
		
		p1.printPiles();
		p2.printPiles();
		
		for(;;) {
			p1.attackPlayer(p2);
			
			p1.printPiles();
			p2.printPiles();
			
			p2.attackPlayer(p1);
					
			p1.printPiles();
			p2.printPiles();
		}
	}
}
