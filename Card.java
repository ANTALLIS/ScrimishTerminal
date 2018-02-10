import java.util.*;

public class Card {
	private String name;
	private char id;
	
	public Card(char set_id) {
		setId(set_id);
	}
	
	public void setId(char set_id) {
		switch(set_id) {
			case '1':
				name = "Dagger"
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
}
