package poker;


/* The Deck class represents a deck of cards.  It starts off with 52 cards,
 * but as cards are dealt from the deck, the number of cards becomes smaller. 
 * This class contain:
 * Constructor
 * Copy constructor 
 * Method that returns a card at a position 
 * Method that returns the current deck length
 * A shuffle method
 * A cut method
 * A deal method
 */

public class Deck {
	//instance variables
	private Card[] cards = new Card[52];
	private final int SUIT_LEN = 4;
	private final int VALUE_LEN = 13;

	/* CONSTRUCTOR
	 * Initializes deck of card. Loops through all values of a card
	 * through the suit and create a new card with that value and suit.
	 * Then, the new card is added to the cards array by updating a counter
	 * value.
	 */
	public Deck() {
		int counter = 0;
		for (int suit = 0; suit < SUIT_LEN; suit++) {
			for (int value = 1; value <= VALUE_LEN; value++) {
				Card card = new Card(value, suit);
				cards[counter] = card;
				counter++;
			}
		}
	}

	//copy constructor
	public Deck(Deck other) {
		cards = other.cards;
	}

	//returns a card position in the cards array.
	public Card getCardAt(int position) {
		return cards[position];
	}

	//get remaining total length of cards array. returns an int.
	public int getNumCards() {
		return cards.length;
	}

	/* SHUFFLE METHOD
	 *
	 * Shuffles the deck of card. First the method checks if the deck is
	 * even or odd. After that, the middle element of array is assigned 
	 * according to the deck's condition wether if it's odd or even.
	 * 
	 * A new array, topHalf,  is created to store the first card until
	 * the middle element of the original array.
	 * 
	 * A new array, shuffled, is created to shuffle the deck. If the current
	 * index is an even value, a card from the topHalf array is added to 
	 * shuffled array. Else, a card from the original cards array is added to 
	 * the shuffled array. Both of this has a counter that is updated 
	 * everytime a new card is added to the shuffled aray.
	 * 
	 * Lastly, the cards in the shuffle array is assigned back to the 
	 * original cards array.
	*/
	public void shuffle() {
		// odd even check
		int middle;
		boolean even = (getNumCards() % 2 == 0);
		if (even) {
			middle = getNumCards() / 2;
		} else {
			middle = getNumCards() / 2 + 1;
		}

		//0-middle card is copied to topHalf
		Card[] topHalf = new Card[middle];
			for (int card = 0; card < topHalf.length; card++) {
				topHalf[card] = cards[card];
			}
		
		//shuffled according to even or odd value
		Card[] shuffled = new Card[cards.length];
		for (int cardIndex = 0, top = 0, bottom = 0; 
			cardIndex < shuffled.length; cardIndex++) {
				if (cardIndex % 2 == 0) {
					shuffled[cardIndex] = topHalf[top++];
				}
				else {
					shuffled[cardIndex] = cards[middle+(bottom++)];
				}
			cards[cardIndex] = shuffled[cardIndex];
		}
	}

	/* CUT METHOD
	 * 
	 * The method loops throught the total amount of position needed to be 
	 * shifted. 
	 * Then, a temporary card is made to store the first card in the 
	 * cards array. 
	 * Next, all consecutive elements in the cards array is shifted to the left.
	 * Lastly, the temporary card value is assigned to the last card in
	 * the cards array.
	 * 
	 * This shifting proccess loops according to the position value. 
	 * 
	*/
	public void cut(int position) {
		for (int counter = 0; counter < position; counter++) {
			Card temp = cards[0];
        	for (int innerCounter = 0; innerCounter < cards.length-1; 
			innerCounter++) {
				cards[innerCounter] = cards[innerCounter + 1];
			}
			cards[cards.length-1] = temp;
		}
	}

	/* DEAL METHOD
	 * 
	 * An array called dealt is made according to the num of cards to be dealt.
	 * Another smaller array is made to store the value of cards remaining, 
	 * after removing the cards that are dealt. 
	 * 
	 * First, the values of cards that are being dealt is copied to the 
	 * dealt array. 
	 * 
	 * Then, the cards that are left (taking the index of the cards that are
	 * after the dealt cards) are added to the smaller array.
	 * 
	 * The smaller array is then assigned to the original cards array, updating
	 * the contents of the cards array to store the remaining cards.
	 * 
	 * This method returns the dealt card array, containing the cards that are
	 * dealt.
	*/
	public Card[] deal(int numCards) {
		Card[] dealt = new Card[numCards];
		int remaining = cards.length - numCards;
		Card[] smaller = new Card[remaining];

		for (int dealtCard = 0; dealtCard < dealt.length; dealtCard++) {
			dealt[dealtCard] = cards[dealtCard];
		}

		for (int cardsLeft = 0; cardsLeft < remaining; cardsLeft++) {
			smaller[cardsLeft] = cards[cardsLeft+numCards];
		}
		cards = smaller;

		return dealt;
	}

}
