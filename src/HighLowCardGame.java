import java.util.Scanner;
import javax.swing.JOptionPane;

public class HighLowCardGame {
	public static final int STARTING_WIN_STREAK = 0;
	public static final int FOUR_IN_A_ROW = 4;
	
	public static void main(String[] args) {	
		DeckOfCards myDeck = new DeckOfCards();
		myDeck.shuffle();
		
		int userWinStreak = STARTING_WIN_STREAK; 
		
		boolean empty = myDeck.isEmpty();
		boolean userPlaying = true;
		
		PlayingCard currentCard = myDeck.deal();
			
		String userInput;
		try
		{	
			while (userPlaying)
			{
				try
				{
					if (empty)
					{
						myDeck = new DeckOfCards();
						myDeck.shuffle();
					}
					
					if (userWinStreak < FOUR_IN_A_ROW)
					{
						userInput = JOptionPane.showInputDialog("The current card is " + currentCard.toString() + 
									"." + " Will the next card be higher, lower, or equal to the current card?");
					
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Congratulations! You have guessed four in a row and have won a game.");
						userWinStreak = STARTING_WIN_STREAK;
						myDeck = new DeckOfCards();
						myDeck.shuffle();
					
						currentCard = myDeck.deal();
						
						userInput = JOptionPane.showInputDialog(null, "The current card is " + currentCard.toString() + "." + 
														" Will the next card be higher, lower, or equal to the current card?");
					}
					
					Scanner inputScanner = new Scanner(userInput);
					
					String userGuess = inputScanner.next();
					PlayingCard previousCard = currentCard;
					
					currentCard = myDeck.deal();
					
					if (previousCard.isRankLessThan(currentCard) && userGuess.trim().equals("higher"))
					{
						userWinStreak++;
						JOptionPane.showMessageDialog(null, "Yes! You guessed right, as the next card is "
										+ currentCard.toString() + "." + "\nYour win streak = " + userWinStreak);
						
					}
					else if (!previousCard.isRankLessThan(currentCard) && userGuess.trim().equals("lower"))
					{
						userWinStreak++;
						JOptionPane.showMessageDialog(null, "Yes! You guessed right, as the next card is " + 
											currentCard.toString() + "." + "\nYour win streak = " + userWinStreak);
						
					}
					else if (userGuess.trim().equals("exit"))
					{
						userPlaying = false;
					}
					else
					{
						userWinStreak = STARTING_WIN_STREAK;
						JOptionPane.showMessageDialog(null, "Sorry, you guessed wrong, as the next card is " +
											currentCard.toString() + "." + "\nYour win streak = " + userWinStreak);
						
					}
					
					empty = myDeck.isEmpty();
						
					inputScanner.close();
					
				}
				catch (java.util.NoSuchElementException exception)
				{
					JOptionPane.showMessageDialog(null, "You must enter either 'higher', 'lower', 'equal' or"
																		+ " 'exit' if you wish to stop playing.");
				}
					
			}
		}
		catch (NullPointerException exception)
		{
			JOptionPane.showMessageDialog(null, "High Low Card Game has been cancelled.");
		}
		
	}
}
