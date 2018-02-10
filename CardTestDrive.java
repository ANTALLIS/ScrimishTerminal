public class CardTestDrive {
	public static void main(String[] args) {
		char cards[] = {'1', '2', '3', '4', '5', '6', 'A', 'S', 'C'};
		Card new_card = new Card('1');
		System.out.println(new_card.getId());
		System.out.println(new_card.getName() + "\n");
		char result;
		Card test_card1;
		Card test_card2;

		for(char i : cards){
			for(char j : cards) {
				test_card1 = new Card(i);
				test_card2 = new Card(j);
				result = test_card1.attack(test_card2);
				switch(result) {
					case 'w':
						System.out.println(":  " + test_card1.getName() + " wins");
						break;
					case 'l':
						System.out.println(":  " + test_card1.getName() + " loses");
						break;
					case 'W':
						System.out.println(":  Player 1" + " wins");
						break;
					case 'L':
						System.out.println(":  Player 1" + " loses");
						break;
					case 'd':
						System.out.println(":  Both are discarded");
						break;
					case 'r':
						System.out.println(":  Both are returned to their piles");
						break;
					default:
						System.out.println(":  Unexpected output");
				}
			}
		}
	}
}
