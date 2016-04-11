package clueGame;

public class Card {
	private String cardName;
	private CardType type;
	public Card(CardType type, String name) {
		this.cardName = name;
		this.type = type;
	}
	public String getName() {
		return cardName;
	}
	public void setName(String cardName) {
		this.cardName = cardName;
	}
	public void setType(CardType type) {
		this.type = type;
	}
	public boolean equals(Card c){
		if (this.cardName.equals(c.cardName))
			return true;
		return false;
	}
	public CardType getType() {
		return type;
	}
}
