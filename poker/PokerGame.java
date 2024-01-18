 package poker;

 import javax.swing.JButton;
 
 public class PokerGame {
    public static final int NUM_PLAYERS = 6;
    public static final int CARDS_PER_PLAYER = 2;
    private Player[] players;
    private Card[] tableCards;
    private Deck deck;
    private int state;
    public static final int PRE_DEAL = 0;
    public static final int PRE_FLOP = 1;
    public static final int FLOP = 2;
    public static final int TURN = 3;
    public static final int RIVER = 4;
 
    public PokerGame() {
       this.initializeGame();
       new PokerGUI(this);
    }
 
    private void initializeGame() {
       this.deck = new Deck();
       this.players = new Player[6];
 
       for(int i = 0; i < 6; ++i) {
          this.players[i] = new Player();
       }
 
       this.tableCards = new Card[0];
       this.state = 0;
    }
 
    public Deck getDeck() {
       return new Deck(this.deck);
    }
 
    public Player[] getPlayers() {
       Player[] p = new Player[this.players.length];
 
       for(int i = 0; i < p.length; ++i) {
          p[i] = new Player(this.players[i]);
       }
 
       return p;
    }
 
    public Card[] getTableCards() {
       Card[] c = new Card[this.tableCards.length];
 
       for(int i = 0; i < c.length; ++i) {
          c[i] = this.tableCards[i];
       }
 
       return c;
    }
 
    public void doShuffle1() {
       this.deck.shuffle();
    }
 
    public void doCutDeck(int position) {
       this.deck.cut(position);
    }
 
    public void proceedToNextStep(JButton buttonToUpdate) {
       switch(this.state) {
       case 0:
          this.dealPlayerCards();
          this.state = 1;
          buttonToUpdate.setText("Flop");
          break;
       case 1:
          this.dealFlop();
          this.state = 2;
          buttonToUpdate.setText("Turn");
          break;
       case 2:
          this.dealTurn();
          this.state = 3;
          buttonToUpdate.setText("River");
          break;
       case 3:
          this.dealRiver();
          this.state = 4;
          buttonToUpdate.setText("Restart");
          break;
       case 4:
          this.initializeGame();
          buttonToUpdate.setText("Deal");
          this.state = 0;
          break;
       default:
          throw new RuntimeException("Unknown state.");
       }
 
    }
 
    public void dealPlayerCards() {
       for(int i = 0; i < 6; ++i) {
          this.players[i].setHand(this.deck.deal(2));
       }
 
    }
 
    public void dealFlop() {
       this.tableCards = this.deck.deal(3);
    }
 
    public void dealTurn() {
       Card[] turn = this.deck.deal(1);
       Card[] newTableCards = new Card[4];
 
       for(int i = 0; i < 3; ++i) {
          newTableCards[i] = this.tableCards[i];
       }
 
       newTableCards[3] = turn[0];
       this.tableCards = newTableCards;
    }
 
    public void dealRiver() {
       Card[] river = this.deck.deal(1);
       Card[] newTableCards = new Card[5];
 
       for(int i = 0; i < 4; ++i) {
          newTableCards[i] = this.tableCards[i];
       }
 
       newTableCards[4] = river[0];
       this.tableCards = newTableCards;
    }
 }
 