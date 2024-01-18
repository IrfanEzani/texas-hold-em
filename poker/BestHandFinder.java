 package poker;

 import java.util.ArrayList;
 
 public class BestHandFinder {
    public static String evaluteHand(Card[] hand) {
       ArrayList<Card> cards = new ArrayList();
 
       for(int i = 0; i < hand.length; ++i) {
          if (hand[i] != null) {
             cards.add(hand[i]);
          }
       }
 
       String[] rankNames = new String[]{"Nothing", "Pair", "Two Pair", "Three of a Kind", "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush"};
       if (cards.size() < 5) {
          return "";
       } else {
          int[] index = new int[5];
          int bestFound = 0;
 
          int var10002;
          for(index[0] = 0; index[0] < cards.size() - 4; var10002 = index[0]++) {
             for(index[1] = index[0] + 1; index[1] < cards.size() - 3; var10002 = index[1]++) {
                for(index[2] = index[1] + 1; index[2] < cards.size() - 2; var10002 = index[2]++) {
                   for(index[3] = index[2] + 1; index[3] < cards.size() - 1; var10002 = index[3]++) {
                      for(index[4] = index[3] + 1; index[4] < cards.size(); var10002 = index[4]++) {
                         Card[] cardArray = new Card[5];
 
                         int handValue;
                         for(handValue = 0; handValue < 5; ++handValue) {
                            cardArray[handValue] = (Card)cards.get(index[handValue]);
                         }
 
                         handValue = evaluateFiveCardHand(cardArray);
                         if (handValue > bestFound) {
                            bestFound = handValue;
                         }
                      }
                   }
                }
             }
          }
 
          return rankNames[bestFound];
       }
    }
 
    public static int evaluateFiveCardHand(Card[] cards) {
       if (PokerHandEvaluator.hasStraight(cards) && PokerHandEvaluator.hasFlush(cards)) {
          return 8;
       } else if (PokerHandEvaluator.hasFourOfAKind(cards)) {
          return 7;
       } else if (PokerHandEvaluator.hasFullHouse(cards)) {
          return 6;
       } else if (PokerHandEvaluator.hasFlush(cards)) {
          return 5;
       } else if (PokerHandEvaluator.hasStraight(cards)) {
          return 4;
       } else if (PokerHandEvaluator.hasThreeOfAKind(cards)) {
          return 3;
       } else if (PokerHandEvaluator.hasTwoPair(cards)) {
          return 2;
       } else {
          return PokerHandEvaluator.hasPair(cards) ? 1 : 0;
       }
    }
 }
 