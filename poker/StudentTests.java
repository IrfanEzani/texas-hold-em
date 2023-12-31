	package poker;
	import static org.junit.Assert.*;

	import org.junit.Test;


	public class StudentTests {
		//DECK TEST
		@Test
		public void getCardAtTest() {
			Deck d = new Deck();
			//first card is ace of spade
			assertTrue(d.getCardAt(0).getValue() == 1 && 
			d.getCardAt(0).getSuit() == 0);
			//last card is king of diamond
			assertTrue(d.getCardAt(51).getValue() == 13 && 
			d.getCardAt(51).getSuit() == 3);
		}

		@Test
		public void getNumCardsTest() {
			//initial deck must have 52 cards
			Deck d = new Deck();
			assertTrue(d.getNumCards() == 52);

			//after deal, 52 - 5 = 47
			d.deal(5);
			assertTrue(d.getNumCards() == 47);
		}

		@Test
		public void shuffleTest() {
			Deck d = new Deck();
			d.shuffle();
			//second card after shuffle is Ace of Clubs
			assertTrue(d.getCardAt(1).getValue() == 1 && 
			d.getCardAt(1).getSuit() == 2);

			//second last card after shuffle is King of Heart
			assertTrue(d.getCardAt(50).getValue() == 13 && 
			d.getCardAt(50).getSuit() == 1);
		}

		@Test
		public void cutTest() {
			Deck d = new Deck();
			d.cut(4);
			//the first 4 cards should be ace 5,6,7,8 of spades
			for (int i = 0; i < 4; i++) {
				assertTrue(d.getCardAt(i).getValue() == i+5);
			}

			//the last 4 cards should be cards of spades
			for (int i = 48; i < 52; i++) {
				assertTrue(d.getCardAt(i).getSuit() == 0);
			}

			//last card should be 4 of spades
			assertTrue(d.getCardAt(51).getValue() == 4);
		}

		@Test
		public void dealTest() {
			Deck d = new Deck();
			Card[] dealt = d.deal(4);
			//deck should be less than 4
			assertTrue(d.getNumCards() == 52-4);
			//all cards in the dealt array must be the first of the original deck
			//all should be in spades, starting from Ace to 4
			for (int i = 0; i < dealt.length; i++) {
				assertTrue(dealt[i].getValue() == i+1 && dealt[i].getSuit() == 0);
			}

		}

		//POKER HAND TEST
		@Test
		public void hasPairTest() {

			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };

			// two pair of same value
			Card[] twoPairOfSameValue = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair same value unsorted
			Card[] twoPairOfSameValueUnsorted = { 
				new Card(4, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted
			Card[] fourKindsUnsorted = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(4, 1) };

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};
				
			assertTrue(PokerHandEvaluator.hasPair(onePair));
			assertTrue(PokerHandEvaluator.hasPair(onePairUnsorted));
			
			assertTrue(PokerHandEvaluator.hasPair(twoPairOfSameValue));
			assertTrue(PokerHandEvaluator.hasPair(twoPairOfSameValueUnsorted));
			assertTrue(PokerHandEvaluator.hasPair(twoPairOfDiffValue));
			assertTrue(PokerHandEvaluator.hasPair(twoPairOfDiffValueUnsorted));

			assertTrue(PokerHandEvaluator.hasPair(threeKindsWithPair));
			assertTrue(PokerHandEvaluator.hasPair(threeKindsWithPairUnsorted));
			assertTrue(PokerHandEvaluator.hasPair(threeKindsNoPair));
			assertTrue(PokerHandEvaluator.hasPair(threeKindsNoPairUnsorted));

			assertTrue(PokerHandEvaluator.hasPair(fourKinds));
			assertTrue(PokerHandEvaluator.hasPair(fourKindsUnsorted));

			assertFalse(PokerHandEvaluator.hasPair(straight));
			assertFalse(PokerHandEvaluator.hasPair(flush));
			assertFalse(PokerHandEvaluator.hasPair(straightFlush));
			assertFalse(PokerHandEvaluator.hasPair(nothing));
		}

		@Test
		public void hasTwoPairTest() {
			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };

			// two pair of same value
			Card[] twoPairOfSameValue = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair same value unsorted
			Card[] twoPairOfSameValueUnsorted = { 
				new Card(4, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted
			Card[] fourKindsUnsorted = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(3, 0), 
				new Card(4, 0) };

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};
				
			assertFalse(PokerHandEvaluator.hasTwoPair(onePair));
			assertFalse(PokerHandEvaluator.hasTwoPair(onePairUnsorted));
			
			assertFalse(PokerHandEvaluator.hasTwoPair(twoPairOfSameValue));
			assertFalse(PokerHandEvaluator.hasTwoPair(twoPairOfSameValueUnsorted));

			assertTrue(PokerHandEvaluator.hasTwoPair(twoPairOfDiffValue));
			assertTrue(PokerHandEvaluator.hasTwoPair(twoPairOfDiffValueUnsorted));

			assertTrue(PokerHandEvaluator.hasTwoPair(threeKindsWithPair));
			assertTrue(PokerHandEvaluator.hasTwoPair(threeKindsWithPairUnsorted));

			assertFalse(PokerHandEvaluator.hasTwoPair(threeKindsNoPair));
			assertFalse(PokerHandEvaluator.hasTwoPair(threeKindsNoPairUnsorted));

			assertFalse(PokerHandEvaluator.hasTwoPair(fourKinds));
			assertFalse(PokerHandEvaluator.hasTwoPair(fourKindsUnsorted));

			assertFalse(PokerHandEvaluator.hasTwoPair(straight));
			assertFalse(PokerHandEvaluator.hasTwoPair(flush));
			assertFalse(PokerHandEvaluator.hasTwoPair(straightFlush));
			assertFalse(PokerHandEvaluator.hasTwoPair(nothing));
			
		}

		@Test
		public void hasThreesTest() {
			
			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };

			// two pair of same value
			Card[] twoPairOfSameValue = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair same value unsorted
			Card[] twoPairOfSameValueUnsorted = { 
				new Card(4, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted
			Card[] fourKindsUnsorted = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(3, 1),
				new Card(4, 1), 
				new Card(4, 0) 
			};

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};
		
			assertFalse(PokerHandEvaluator.hasThreeOfAKind(onePair));
			assertFalse(PokerHandEvaluator.hasThreeOfAKind(onePairUnsorted));

			assertTrue(PokerHandEvaluator.hasThreeOfAKind(twoPairOfSameValue));
			assertTrue(PokerHandEvaluator.hasThreeOfAKind
			(twoPairOfSameValueUnsorted));

			assertFalse(PokerHandEvaluator.hasThreeOfAKind(twoPairOfDiffValue));
			assertFalse(PokerHandEvaluator.hasThreeOfAKind
			(twoPairOfDiffValueUnsorted));

			assertTrue(PokerHandEvaluator.hasThreeOfAKind(threeKindsWithPair));
			assertTrue(PokerHandEvaluator.hasThreeOfAKind
			(threeKindsWithPairUnsorted));
			assertTrue(PokerHandEvaluator.hasThreeOfAKind(threeKindsNoPair));
			assertTrue(PokerHandEvaluator.hasThreeOfAKind(threeKindsNoPairUnsorted));

			assertTrue(PokerHandEvaluator.hasThreeOfAKind(fourKinds));
			assertTrue(PokerHandEvaluator.hasThreeOfAKind(fourKindsUnsorted));

			assertFalse(PokerHandEvaluator.hasThreeOfAKind(straight));
			assertFalse(PokerHandEvaluator.hasThreeOfAKind(flush));
			assertFalse(PokerHandEvaluator.hasThreeOfAKind(straightFlush));
			assertFalse(PokerHandEvaluator.hasThreeOfAKind(nothing));
		
		}

		@Test
		public void hasStraightTest() {
			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };

			// two pair of same value
			Card[] twoPairOfSameValue = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair same value unsorted
			Card[] twoPairOfSameValueUnsorted = { 
				new Card(4, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted
			Card[] fourKindsUnsorted = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(3, 1), 
				new Card(4, 0) 
			};

			//lowest straight, ace to five
			Card[] straightLowAce = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//highest straight, ten to ace
			Card[] straightHighAce = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };

			//double ace
			Card[] doubleAce = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(1, 1) };
			
			//all other straight combination
			Card[] straightTwoToSix = { 
				new Card(6, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };

			Card[] straightThreeToSeven = { 
				new Card(6, 3), 
				new Card(7, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };

			Card[] straightFourToEight = { 
				new Card(6, 3), 
				new Card(7, 2), 
				new Card(8, 1), 
				new Card(4, 0), 
				new Card(5, 1) };

			Card[] straightFiveToNine = { 
				new Card(6, 3), 
				new Card(7, 2), 
				new Card(8, 1), 
				new Card(9, 0), 
				new Card(5, 1) };

			Card[] straightSixToTen = { 
				new Card(6, 3), 
				new Card(7, 2), 
				new Card(8, 1), 
				new Card(9, 0), 
				new Card(10, 1) };
				
			Card[] straightSevenToJack = { 
				new Card(7, 2), 
				new Card(8, 1), 
				new Card(9, 0), 
				new Card(10, 3), 
				new Card(11, 1) };
				
			Card[] straightEightToQueen = { 
				new Card(12, 2), 
				new Card(8, 1), 
				new Card(9, 0), 
				new Card(10, 3), 
				new Card(11, 1) };

			Card[] straightNineToKing = { 
				new Card(12, 2), 
				new Card(13, 1), 
				new Card(9, 0), 
				new Card(10, 3), 
				new Card(11, 1) };

			//invalid straights
			Card[] straightJackToTwo = { 
				new Card(11, 2), 
				new Card(12, 1), 
				new Card(13, 0), 
				new Card(1, 3), 
				new Card(2, 1) };

			Card[] straightQueenToThree = { 
				new Card(12, 2), 
				new Card(13, 1), 
				new Card(1, 0), 
				new Card(2, 3), 
				new Card(3, 1) };

			Card[] straightKingToFour = { 
				new Card(13, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(3, 3), 
				new Card(4, 1) };

			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};
		
			//pair,threes and fours hand combinations
			assertFalse(PokerHandEvaluator.hasStraight(onePair));
			assertFalse(PokerHandEvaluator.hasStraight(onePairUnsorted));
			assertFalse(PokerHandEvaluator.hasStraight(twoPairOfSameValue));
			assertFalse(PokerHandEvaluator.hasStraight(twoPairOfSameValueUnsorted));
			assertFalse(PokerHandEvaluator.hasStraight(twoPairOfDiffValue));
			assertFalse(PokerHandEvaluator.hasStraight(twoPairOfDiffValueUnsorted));
			assertFalse(PokerHandEvaluator.hasStraight(threeKindsNoPair));
			assertFalse(PokerHandEvaluator.hasStraight(threeKindsNoPairUnsorted));
			assertFalse(PokerHandEvaluator.hasStraight(threeKindsWithPair));
			assertFalse(PokerHandEvaluator.hasStraight(threeKindsWithPairUnsorted));
			assertFalse(PokerHandEvaluator.hasStraight(fourKinds));
			assertFalse(PokerHandEvaluator.hasStraight(fourKindsUnsorted));

			//special combinations
			assertFalse(PokerHandEvaluator.hasStraight(nothing));
			assertFalse(PokerHandEvaluator.hasStraight(flush));
			assertTrue(PokerHandEvaluator.hasStraight(straightFlush));

			//straight combinations
			assertFalse(PokerHandEvaluator.hasStraight(doubleAce));

			assertTrue(PokerHandEvaluator.hasStraight(straightLowAce));
			assertTrue(PokerHandEvaluator.hasStraight(straightHighAce));
			assertTrue(PokerHandEvaluator.hasStraight(straightTwoToSix));
			assertTrue(PokerHandEvaluator.hasStraight(straightThreeToSeven));
			assertTrue(PokerHandEvaluator.hasStraight(straightFourToEight));
			assertTrue(PokerHandEvaluator.hasStraight(straightFiveToNine));
			assertTrue(PokerHandEvaluator.hasStraight(straightSixToTen));
			assertTrue(PokerHandEvaluator.hasStraight(straightSevenToJack));
			assertTrue(PokerHandEvaluator.hasStraight(straightEightToQueen));
			assertTrue(PokerHandEvaluator.hasStraight(straightNineToKing));

			assertFalse(PokerHandEvaluator.hasStraight(straightJackToTwo));
			assertFalse(PokerHandEvaluator.hasStraight(straightQueenToThree));
			assertFalse(PokerHandEvaluator.hasStraight(straightKingToFour));

		}

		@Test
		public void hasFlushTest() {
			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted
			Card[] fourKindsUnsorted = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(4, 1) };

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush combinations
			Card[] flushSpades = { 
				new Card(1, 0), 
				new Card(3, 0), 
				new Card(5, 0), 
				new Card(7, 0), 
				new Card(9, 0) };

			Card[] flushHearts = { 
				new Card(1, 1), 
				new Card(3, 1), 
				new Card(5, 1), 
				new Card(7, 1), 
				new Card(9, 1) };

			Card[] flushClubs = { 
				new Card(1, 2), 
				new Card(3, 2), 
				new Card(5, 2), 
				new Card(7, 2), 
				new Card(9, 2) };

			Card[] flushDiamonds = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(9, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};

			//pairs, two pairs, threes and fours combinations
			assertFalse(PokerHandEvaluator.hasFlush(onePair));
			assertFalse(PokerHandEvaluator.hasFlush(onePairUnsorted));
			assertFalse(PokerHandEvaluator.hasFlush(twoPairOfDiffValue));
			assertFalse(PokerHandEvaluator.hasFlush(twoPairOfDiffValueUnsorted));
			assertFalse(PokerHandEvaluator.hasFlush(threeKindsNoPair));
			assertFalse(PokerHandEvaluator.hasFlush(threeKindsNoPairUnsorted));
			assertFalse(PokerHandEvaluator.hasFlush(threeKindsWithPair));
			assertFalse(PokerHandEvaluator.hasFlush(threeKindsWithPairUnsorted));
			assertFalse(PokerHandEvaluator.hasFlush(fourKinds));
			assertFalse(PokerHandEvaluator.hasFlush(fourKindsUnsorted));

			//special combinations
			assertFalse(PokerHandEvaluator.hasFlush(straight));
			assertFalse(PokerHandEvaluator.hasFlush(nothing));

			//flush combinations
			assertTrue(PokerHandEvaluator.hasFlush(flushSpades));
			assertTrue(PokerHandEvaluator.hasFlush(flushDiamonds));
			assertTrue(PokerHandEvaluator.hasFlush(flushClubs));
			assertTrue(PokerHandEvaluator.hasFlush(flushHearts));
			assertTrue(PokerHandEvaluator.hasFlush(straightFlush));

		}

		@Test
		public void hasFoursTest() {
			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
		
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted, one card smaller value
			Card[] fourKindsUnsorted = { 
				new Card(7, 3), 
				new Card(4, 2), 
				new Card(7, 1), 
				new Card(7, 0), 
				new Card(7, 2) };

			//four kinds unsorted, one card bigger value
			Card[] fourKindsUnsortedBigger = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 0), 
				new Card(1, 1), 
				new Card(1, 2) };

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};

			assertFalse(PokerHandEvaluator.hasFourOfAKind(onePair));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(onePairUnsorted));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(twoPairOfDiffValue));
			assertFalse(PokerHandEvaluator.hasFourOfAKind
			(twoPairOfDiffValueUnsorted));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(threeKindsNoPair));
			assertFalse(PokerHandEvaluator.hasFourOfAKind
			(threeKindsNoPairUnsorted));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(threeKindsWithPair));
			assertFalse(PokerHandEvaluator.hasFourOfAKind
			(threeKindsWithPairUnsorted));
			
			assertTrue(PokerHandEvaluator.hasFourOfAKind(fourKinds));
			assertTrue(PokerHandEvaluator.hasFourOfAKind(fourKindsUnsorted));
			assertTrue(PokerHandEvaluator.hasFourOfAKind(fourKindsUnsortedBigger));

			//special combinations
			assertFalse(PokerHandEvaluator.hasFourOfAKind(straight));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(flush));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(nothing));
			assertFalse(PokerHandEvaluator.hasFourOfAKind(straightFlush));



		}

		@Test
		public void hasFullHouseTest() {
			//one pair
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
		
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted, one card smaller value
			Card[] fourKindsUnsorted = { 
				new Card(7, 3), 
				new Card(4, 2), 
				new Card(7, 1), 
				new Card(7, 0), 
				new Card(7, 2) };

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};

			assertFalse(PokerHandEvaluator.hasFullHouse(onePair));
			assertFalse(PokerHandEvaluator.hasFullHouse(onePairUnsorted));
			assertFalse(PokerHandEvaluator.hasFullHouse(twoPairOfDiffValue));
			assertFalse(PokerHandEvaluator.hasFullHouse(twoPairOfDiffValueUnsorted));

			assertFalse(PokerHandEvaluator.hasFullHouse(threeKindsNoPair));
			assertFalse(PokerHandEvaluator.hasFullHouse(threeKindsNoPairUnsorted));

			assertTrue(PokerHandEvaluator.hasFullHouse(threeKindsWithPair));
			assertTrue(PokerHandEvaluator.hasFullHouse(threeKindsWithPairUnsorted));

			assertFalse(PokerHandEvaluator.hasFullHouse(fourKinds));
			assertFalse(PokerHandEvaluator.hasFullHouse(fourKindsUnsorted));

			//special combinations
			assertFalse(PokerHandEvaluator.hasFullHouse(straight));
			assertFalse(PokerHandEvaluator.hasFullHouse(flush));
			assertFalse(PokerHandEvaluator.hasFullHouse(straightFlush));
			assertFalse(PokerHandEvaluator.hasFullHouse(nothing));
		}

		@Test
		public void hasStraightFlush() {
			Card[] onePair = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//two pair, different values
			Card[] twoPairOfDiffValue = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
		
			
			//two pair different value unsorted
			Card[] twoPairOfDiffValueUnsorted = { 
				new Card(1, 3), 
				new Card(4, 1), 
				new Card(1, 2), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//one pair, unsorted
			Card[] onePairUnsorted = { 
				new Card(1, 3), 
				new Card(3, 2), 
				new Card(4, 1), 
				new Card(5, 0), 
				new Card(1, 1) };
			
			//three kinds without pair
			Card[] threeKindsNoPair = { 
				new Card(1, 2), 
				new Card(2, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds with a pair
			Card[] threeKindsWithPair = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };
			
			//three kinds unsorted
			Card[] threeKindsNoPairUnsorted = { 
				new Card(1, 3), 
				new Card(4, 2), 
				new Card(1, 1), 
				new Card(2, 0), 
				new Card(1, 2) };
			
			//three kinds with pair unsorted
			Card[] threeKindsWithPairUnsorted = { 
				new Card(1, 3), 
				new Card(1, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(4, 2) };

			//four kinds
			Card[] fourKinds = { 
				new Card(4, 3), 
				new Card(4, 2), 
				new Card(4, 1), 
				new Card(4, 0), 
				new Card(3, 1) };
			
			//four kinds unsorted, one card smaller value
			Card[] fourKindsUnsorted = { 
				new Card(7, 3), 
				new Card(4, 2), 
				new Card(7, 1), 
				new Card(7, 0), 
				new Card(7, 2) };

			//straight
			Card[] straight = { 
				new Card(1, 3), 
				new Card(2, 2), 
				new Card(3, 1), 
				new Card(4, 0), 
				new Card(5, 1) };
			
			//flush
			Card[] flush = { 
				new Card(1, 3), 
				new Card(3, 3), 
				new Card(5, 3), 
				new Card(7, 3), 
				new Card(10, 3) };
			
			//straight flush
			Card[] straightFlush = {
				new Card(1, 3), 
				new Card(2, 3), 
				new Card(3, 3), 
				new Card(4, 3), 
				new Card(5, 3) };

			//nothing
			Card[] nothing = {
				new Card(1, 3), 
				new Card(2, 1), 
				new Card(5, 2), 
				new Card(7, 3), 
				new Card(10, 0)
			};

			assertFalse(PokerHandEvaluator.hasStraightFlush(onePair));
			assertFalse(PokerHandEvaluator.hasStraightFlush(onePairUnsorted));
			assertFalse(PokerHandEvaluator.hasStraightFlush(twoPairOfDiffValue));
			assertFalse(PokerHandEvaluator.hasStraightFlush(twoPairOfDiffValueUnsorted));
			assertFalse(PokerHandEvaluator.hasStraightFlush(threeKindsNoPair));
			assertFalse(PokerHandEvaluator.hasStraightFlush(threeKindsNoPairUnsorted));
			assertFalse(PokerHandEvaluator.hasStraightFlush(threeKindsWithPair));
			assertFalse(PokerHandEvaluator.hasStraightFlush(threeKindsWithPairUnsorted));
			assertFalse(PokerHandEvaluator.hasStraightFlush(fourKinds));
			assertFalse(PokerHandEvaluator.hasStraightFlush(fourKindsUnsorted));

			//special combinations
			assertFalse(PokerHandEvaluator.hasStraightFlush(straight));
			assertFalse(PokerHandEvaluator.hasStraightFlush(flush));

			assertTrue(PokerHandEvaluator.hasStraightFlush(straightFlush));

			assertFalse(PokerHandEvaluator.hasStraightFlush(nothing));
		}
	}