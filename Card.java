import java.util.*;

public class Card {
	private String name;
	private char id;
	
	public Card(char set_id) {
		setId(set_id);
	}
	
	public void setId(char set_id) {
		id = set_id;
		switch(set_id) {
			case '1':
				name = "Dagger";
				break;
			case '2':
				name = "Sword";
				break;
			case '3':
				name = "Morning Star";
				break;
			case '4':
				name ="War Axe";
				break;
			case '5':
				name = "Halberd";
				break;
			case '6':
				name = "Longsword";
				break;
			case 'A':
				name = "Archer";
				break;
			case 'S':
				name = "Shield";
				break;
			case 'C':
				name = "Crown";
				break;
		}
	}
	
	public char getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public char attack(Card opponent) {
		switch(id) {
			case 'C':
				if(opponent.getId() == 'C')
					return 'W';// WIN the whole game
				else
					return 'L';// LOSE the whole game
			case 'A':
				if(opponent.getId() == 'S')
					return 'r';// Return both cards to their piles
				else
					return 'w';// Win the round
			case 'S':
				return 'e';// Error(This shouldn't happen)
			default:
				switch(opponent.getId()) {
					case 'S':
						return 'd';// Discard both cards
					case 'C':
						return 'W';
					case 'A':
						return 'w';
					default:
						int op_id = ((int)opponent.getId()) - 48;
						int my_id = ((int)id) - 48;
						if(op_id == my_id) 
							return 'd';
						else if(op_id < my_id)
							return 'w';
						else
							return 'l';// Lose the round
				}// END INNER SWITCH
		}// END SWITCH
	}// END METHOD
}// END CLASS
