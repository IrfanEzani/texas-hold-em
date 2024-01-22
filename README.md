# Texas Hold 'Em Poker Simulator

## Project Overview
Game simulation of the Texas Hold 'Em poker, integration with a Java graphical user interface and simplem implementation of the game's infrastructure.

## Implementation Details
     
   - **Key Functions:**
     - **Cut**: Splits the deck at a specified position, then swaps the positions of the two resulting sections.
     - **Shuffle**: Implemented a method to shuffle the cards, ensuring a random order for each game.
     - **Deal**: Developed a method to deal cards, which is used to distribute cards to players during the game.
     - **Evaluator**: Methods to identify various poker hands, such as a flush, straight, full house, etc.
     - Each method takes a set of 5 cards as input and returns a boolean indicating whether the hand meets the specific criteria.

![image](https://github.com/IrfanEzani/texas-hold-em/assets/59435235/e07b97b8-9924-4d3a-816f-a822d8eb3020)

## Usage Instructions
- Compile the Java classes and run the main application by `java -jar Driver.jar`
- Interact with the GUI to play the game and test different scenarios.
