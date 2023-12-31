package poker;

import java.util.Arrays;

/* The PokerHandEvaluator class represents methods of the types of combinations
 * a hand have at a certain time.
 * 
 * The class have public and private methods and one instance variable.
 * The private methods are made to avoid redundancy in creating the
 * public methods.
 * 
 * Private methods in this class:
 * 
 * sortHand, to sort the cards
 * distinctValues, to get distinct values of integers in an array
 * removeDuplicateElem, remove duplicates elements in an array
 * pairValues, get the values of pair in the array
 * threesValues, get the values of three of a kind in the array
 * calculateMostDuplicates, gives an integer of the values that is most
 * duplicated in the array.
 * 
 * The public methods are the usual hand combinations in poker.
 * 
 * Public method contains:
 * 
 * hasPair, check if there's a pair in the hand.
 * hasTwoPair, check if there's two distinct pair in the hand.
 * hasThreeOfAKind, check if there's three similar value card in the hand.
 * hasStraight, check if there'a straight in the hand.
 * hasFlush, check if there's a flush in the hand.
 * hasFullHouse, check if there's a full house in the hand.
 * hasFourOfAKind, check if there's four of a kind in the hand.
 * hasStraightFlush, check if there's a straight flush in the hand.
 */
public class PokerHandEvaluator {
	//instance variable
	final static int MAX_HAND = 5;

	/* PRIVATE METHODS */

   /* 
	* Sort cards ascending by value. Does a double for-loop to iterate the card
	* one by one. A temporary card is created to be swapped.
	*
	* If the current in the current index is bigger than the card after, 
	* the value is swapped between the current index and the after index.
    * 
	* The method modified the current cards array.
	*/
	private static void sortHand(Card[] cards) {
		for (int index = 0; index < cards.length; index++) {
			for (int afterIndex = index + 1; afterIndex < cards.length; 
			afterIndex++) {
				Card temp = new Card(1, 0);
				if (cards[index].getValue() > cards[afterIndex].getValue()) {
					temp = cards[index];
					cards[index] = cards[afterIndex];
					cards[afterIndex] = temp;
				}
			}
		}
	}

	/* 
	 * This method return distinct values of an array. First the array is 
	 * sorted in ascending order. The we check if there's any zero in the arr
	 * and keep track of them. Then, we remove the zero by creating a new array
	 * which length is equal to the current length minus the total zeros.
	 *  
	 * Before returning the array, we remove all duplicate elements in the array
	 * by calling the removeDuplicateElem method.
	 * 
	 */
	private static int[] distinctValues(int[] arr) {
		// sort array in distinc order ascending order
		Arrays.sort(arr);

		// count zero if there's zero in the array
		int zeroCounter = 0;
		for (int counter = 0; counter < arr.length; counter++) {
			if (arr[counter] == 0) {
				zeroCounter++;
			}
		}
		// remove zero
		int[] filtered = new int[arr.length - zeroCounter];
		for (int index = 0; index < filtered.length; index++) {
			filtered[index] = arr[index + zeroCounter];
		}

		// check if elem is different
		filtered = removeDuplicateElem(filtered);

		return filtered;
	}

	/* 
	 * This array remove duplicates elements in an integer array.
	 * It first creates a temporary array of same length, even if the arr
	 * passed is empty.
	 * 
	 * In a for loop, we compare each adjacent elements until the second
	 * last element.
	 * 
	 * If the element is not equal to each other, add the elements to the 
	 * temp array.
	 * The last element is then added to the temp array.
	 *  
	 * Then, a new result array is created, and all value of the temp
	 * array is passed down to the result array.
	 * 
	 * The result array is then returned.
	 * (used in distinctValues method)
	 */
	private static int[] removeDuplicateElem(int[] arr) {
		int[] temp = new int[arr.length];
		int outCounter = 0;

		// if arr passed is empty, populate all elements with zero
		if (arr.length == 0) {
			arr = new int[MAX_HAND];
			temp = new int[MAX_HAND];
		}

		//if elem is not equal to each other, add the elem to the temp array.
		for (int index = 0; index < arr.length - 1; index++) {
			if (arr[index] != arr[index + 1]) {
				temp[outCounter] = arr[index];
				outCounter++;
			}
		}

		temp[outCounter++] = arr[arr.length - 1];

		int[] result = new int[outCounter];

		for (int index = 0; index < result.length; index++) {
			result[index] = temp[index];
		}

		return result;
	}
	
	/* 
	 * The method gets what values of pair is contained in a hand.
	 * Example:
	 *  we have 2 pair, and we need to determine what values of the pair is.
	 *  
	 * {Ace of spades, ace of diamonds, 2 diamonds, 2 spades, 3 clubs}
	 * 
	 * This method will tell us that the values of the pair are aces and spades.
	 * 
	 * METHOD EXPLANATION:
	 * A value, integer array and a counter is first initialised.
	 * 
	 * After sorting the array, we check if the adjacent cards is similar
	 * to the current card in a for-loop. If so, we add the card's value into
	 * the value array.
	 * 
	 * Then, we remove the duplicates in the value array before returning it.
	 * 
	 * (Used in fullHouse method)
	 */
	private static int[] pairValues(Card[] cards) {
		int[] value = new int[cards.length];
		int counter = 0;
		sortHand(cards);
		if (hasPair(cards)) {
			for (int index = 1; index < cards.length; index++) {
				if (cards[index - 1].getValue() == cards[index].getValue()) {
					value[counter++] = cards[index].getValue();
				}
			}
		}

		value = distinctValues(value);
		return value;
	}

	/* 
	 * The method gets what three of a kind value is contained in a hand.
	 * Example:
	 *  we have three of akind, and we need to determine what value
	 *  of the three of a kind is.
	 *  
	 * {Ace of spades, ace of diamonds, ace of hearts, 2 spades, 3 clubs}
	 * 
	 * This method will tell us that the values of the three of a kind is 
	 * an ace.
	 * 
	 * METHOD EXPLANATION:
	 * Initialize elements, maxCount and simlarCount to 1.
	 * 
	 * In looping the cards array, we check if the elements are similar to 
	 * adjacent elements, if so, add the counter. 
	 * If the counter is bigger than the maxCount, update the maxCount and 
	 * update the element by storing the last recent value. 
	 * 
	 * After then, reset the similarCount.
	 * 
	 * We then return the element, which is the card value that has
	 * the highest frequency (maxCount).
	 * (Used in fullHouse method)
	 */
	private static int threesValues(Card[] cards) {
		int element = 0, maxCount = 1, similarCount = 1;
		for (int index = 1; index < cards.length; index++) {
			// count the successive elements as long as they are same
			if (cards[index].getValue()== cards[index - 1].getValue())
			similarCount++;
			else {
				// compare the count with max_count
				if (similarCount > maxCount) {
					// update if count is greater
					maxCount = similarCount;
					element = cards[index - 1].getValue();
				}
				// reset count to 1
				similarCount = 1;
			}
		}

		if (similarCount > maxCount) {
			maxCount = similarCount;
			element = cards[cards.length - 1].getValue();
		}

		return element;	
	}

	/* 
	 * This method calculates the most duplicates in an array.
	 * 
	 * We loop through the array, and check if the adjacent elements are the
	 * same, if so, add the counter.
	 * 
	 * We'll update the maxCount if counter is bigger than maxCount.
	 * Then we reset the count before starting another iteration.
	 * 
	 * This method returns the maxCount, which is the highest total duplicates 
	 * of an element in an array.
	*/
	private static int calculateMostDuplicates(int[] arr) {
		int maxCount=1, count=1;
		for(int index=1; index<arr.length; index++){
            //count the successive elements as long as they are same
            if(arr[index] == arr[index-1])
                count++;
            else{
                //compare the count with maxCount
                if(count>maxCount){
                    //update if count is greater
                    maxCount = count;
                } 
                //reset count to 1
                count =1;
            }
        }

		if (count > maxCount){  
            maxCount = count;
        }
		return maxCount;
	}
	
	/* PUBLIC METHODS */

	/* 
	 * The method checks if there's a pair in the cards array.
	 * In a for-loop, we check adjacent elements if they are similar to 
	 * each other. If so, we set the isPair boolean from true to false.
	 * 
	 * Return isPair boolean condition.
	 * 
	 * 
	*/
	public static boolean hasPair(Card[] cards) {
		boolean isPair = false;
		int len = cards.length;

		for (int index = 0; index < len; index++) {
			for (int innerIndex = index + 1; innerIndex < len; innerIndex++) {
				if (cards[index].getValue() == cards[innerIndex].getValue()) {
					isPair = true;
				}
			}
		}
		return isPair;
	}

	/* 
	 * The method check if there's two distinct pair in the cards.
	 * First, it sorts the cards array by sortHand method.
	 * 
	 * An int array is initialized to store values of similar cards.
	 * 
	 * We then iterate through the whole cards array and find if any
	 * cards are similar to each other, if so, the value of these cards are 
	 * added to the pairTypes array.
	 * 
	 * We then call the distinctValues method to check the distint values
	 * in the pairTypes array and the values are return to res array.
	 * 
	 * The method returns true if the length of the res array is equal to 2,
	 * which means that the array has two distinct pairs.
	 */
	public static boolean hasTwoPair(Card[] cards) {
		// sort according to value
		sortHand(cards);
		int index = 1, counter = 0;
		int[] pairTypes = new int[cards.length];
		// iterate all, add value o pairtypes array
		while (index < cards.length) {
			if (cards[index].getValue() == cards[index - 1].getValue()) {
				pairTypes[counter++] = cards[index].getValue();
			}
			index++;
		}
		// returns total different values
		int[] res = distinctValues(pairTypes);

		return (res.length == 2);
	}

	/* 
	 * The method checks if there is three of a kind in the cards.
	 * 
	 * After sorting the array, we loop throught the entire cards array.
	 * Each of the cards values are added to the values array, which is an
	 * integer array.
	 * 
	 * Then, the calculateMostDuplicates method is invoked to check for 
	 * duplicates in the values array and the value is assigned to the answer.
	 * 
	 * If the answer is bigger than 3, then the cards array has three of a kind.
	 * 
	 */
	public static boolean hasThreeOfAKind(Card[] cards) {
		sortHand(cards);
		int[] values = new int[cards.length];
		for (int index = 0; index < cards.length; index++) {
			values[index] = cards[index].getValue();
		}

		int answer = calculateMostDuplicates(values);
		return (answer >= 3);
	}
		
	/* 
	 * Method checks if there's a straight in the cards.
	 * 
	 * We first SORTED the array & initialized straightCounter to 1, and
	 * a straigh boolean as false.
	 * 
	 * There are 3 main cases of aces here, which is the low end (Ace until 5),
	 * the high end ace (10,J,Q,K,A), and other straight not containing Ace
	 * in the middle.
	 * 
	 * We first have to find the ace in the array, if there's an ace, set
	 * the boolean ace from false to right.
	 * 
	 * If the ace card is the first, then we have to check each adjacent cards
	 * value. If each of their value has a difference of 1, which means that
	 * they are consecutive cards, add the straightCounter.
	 * If the straightCounter equals to 5, that means the cards is a straight.
	 * If it's a straight, then reset the straightCounter.
	 * 
	 * If the ace is in the deck, and the last card is a King, we should check 
	 * the cards after the ace, and check if they are subsequent card by 
	 * checking the difference in the value. If the difference in value is 1, 
	 * increment the straightCounter. 
	 * If the straightCounter is equal to 4, this meant that the deck is a 
	 * straight, as there's an Ace, and the last card is the King, and 
	 * the cards after the ace up to the king were all subsequent cards.
	 * 
	 * 
	 * If there's no ace in the deck, check subsequent cards if all of their
	 * value difference is 1. If so, increment the straightCounter. It is a
	 * straight card if the straightCounter is equal to 5.
	 * 
	 */
	public static boolean hasStraight(Card[] cards) {
		sortHand(cards);
		int straightCounter = 1;
		boolean straight = false;

		//find ace
		boolean ace = false;
		for (int index = 0; index < cards.length; index++) {
			if (cards[index].getValue() == 1) {
				ace = true;
			}
		}

		//if starts at ace,ace as low end
		if (cards[0].getValue() == 1) {
			for (int index = 1; index < cards.length; index++) {
				if (cards[index].getValue() - cards[index-1].getValue() == 1) {
					straightCounter++;
				}
			}
			if (straightCounter == 5) {
				straight = true;
			}
			//reset
			straightCounter = 1;
		} 

		//if ends with K and there's an ace in the hand, ace as high end
		if (cards[cards.length-1].getValue() == 13 && ace) {
			for (int index = 2; index < cards.length; index++) {
				if (cards[index].getValue() - cards[index-1].getValue() == 1) {
					straightCounter++;
					if (straightCounter == 4) {
						straight = true;
					}
				}
			}
			straightCounter = 1;			
		} 

		//if no ace
		if (!ace) {
			for (int index = 1; index < cards.length; index++) {
				if (cards[index].getValue() - cards[index-1].getValue() == 1) {
					straightCounter++;
					if (straightCounter == 5) {
						straight = true;
					}
				}
			}
		}

		return straight;
	}

	/* 
	 * The method check if there's a flush in the cards.
	 * After sorting the cards, we initialize a counter to 1/
	 * We check in each consecutive cards if they have the same suit, and if
	 * so, we will increment the counter.
	 * 
	 * The method returns true if the counter is equal to 5.
	 */
	public static boolean hasFlush(Card[] cards) {
		sortHand(cards);
		int suitCounter = 1;
		for (int index = 1; index < cards.length; index++) {
			if (cards[index - 1].getSuit() == cards[index].getSuit()) {
				suitCounter++;
			}
		}
		return (suitCounter == 5);
	}

	/* 
	 * The method checks if there's a full house in the cards.
	 * We first initialized a fullHouse boolean as false and sort the array.
	 * 
	 * Then, we check if there's a pair and a three of a kind the cards array.
	 * If so, we check the values of the three of a kind and the values of the
	 * pair. The values of the three of a kind is stored in threes int while
	 * the values of pair is stored in the pairs array.
	 * 
	 * Next, throughtout the pairs array, the value of the pairs array
	 * should not be the same as the value of the threes. 
	 * If so, the fullHouse boolean is set to true and vice versa.
	 * 
	 * (this is because the value of 
	 * a three of a kind and the pair cannot be the same in the cards to 
	 * be considered a full house)
	 * 
	 * The methods returns the fullHouse boolean.
	 */
	public static boolean hasFullHouse(Card[] cards) {
		boolean fullHouse = false;
		sortHand(cards);
		if (hasPair(cards) && hasThreeOfAKind(cards)) {
			int threes = threesValues(cards);
			int[] pairs = pairValues(cards);

			
			for (int index = 0; index < pairs.length; index++) {
				if (pairs[index] != threes) {
					fullHouse = true;
				}
			}
		}

		return fullHouse;
	}
	
	/* 
	 * The method checks if there's a four of a kind in the cards array.
	 * An array of integer is initialized.
	 * 
	 * After sorting the cards, we loop through them and assigned all the cards
	 * value to the arr array.
	 * 
	 * Then, we invoke the calculateMostDuplicates method to check
	 * the most values that are duplicates in the cards and assign to the
	 * mostDuplicateCard int.
	 * 
	 * Returns true if the mostDuplicateCard == 4.
	 */
	public static boolean hasFourOfAKind(Card[] cards) {
		sortHand(cards);
		int[] arr = new int[cards.length];
		for (int index = 0; index < cards.length; index++) {
			arr[index] = cards[index].getValue();
		}

		int mostDuplicateCard = calculateMostDuplicates(arr);
		return (mostDuplicateCard == 4);
	}

	/* 
	 * Checks if there's a straight flush in the cards.
	 * Return true if the cards has a straight AND a flush.
	 */
	public static boolean hasStraightFlush(Card[] cards) {
		return (hasStraight(cards) && hasFlush(cards));
	}
}
