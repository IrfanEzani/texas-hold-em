 package poker;

 public class Player {
    private Card[] hand;
 
    public Player() {
       this.hand = new Card[0];
    }
 
    public Player(Player x) {
       this.hand = new Card[x.hand.length];
 
       for(int i = 0; i < x.hand.length; ++i) {
          this.hand[i] = x.hand[i];
       }
 
    }
 
    public void setHand(Card[] cards) {
       this.hand = cards;
    }
 
    public Card[] getHand() {
       return this.hand;
    }
 }
 