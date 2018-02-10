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
		System.out.print(name + " attacks " + opponent.getName());
		switch(id) {
			case 'C':
				if(opponent.getId() == 'C') {
					return 'W';
				} else {
					return 'L';
				}
			case 'A':
				if(opponent.getId() == 'S') {
					return 'r';
				} else {
					return 'w';
				}
			case 'S':
				System.out.print("  Error: Can't attack with shield card");
				return 'e';
			default:
				switch(opponent.getId()) {
					case 'S':
						return 'd';
					case 'C':
						return 'W';
					case 'A':
						return 'w';
					default:
						int op_id = ((int)opponent.getId()) - 48;
						int my_id = ((int)id) - 48;
						if(op_id == my_id) {
							return 'd';
						} else if(op_id < my_id) {
							return 'w';
						} else if(op_id > my_id) {
							return 'l';
						}
				}// END INNER SWITCH
		}// END SWITCH
		return '0';
	}// END METHOD
}// END CLASS
