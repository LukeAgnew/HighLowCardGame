
public class PlayingCard { // begin PlayingCard data type 
	   // public constants 
	   public static final int ACE = 1, JACK = 11, KNIGHT = 12, QUEEN = 13,  
	      KING = 14; 
	   public static final int[] RANKS = {ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10,  
	      JACK, KNIGHT, QUEEN, KING}; // rank order of playing cards 
	    
	   public static final int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;    
	   public static final int[] SUITS = {SPADES, HEARTS, DIAMONDS, CLUBS}; 
	    
	   // private constant 
	   private static final char[][][] pictographs; // card pictographs 
	                                                // as surrogate pairs 
	   // rankIndex of playing card  
	   private final int rankIndex; // invariant: 0 <= rankIndex < RANKS.length() 
	    
	   // suitIndex of playing card:  
	   private final int suitIndex; // invariant: 0 <= suitIndex < SUITS.length() 

	   // initialise the playing card pictographs  
	   static { 
	      pictographs = new char [SUITS.length][RANKS.length][2]; 
	      int codePoint = 0x1F0A1; // ace of spades unicode code point 
	      for(int suitIndex = 0; suitIndex < SUITS.length; suitIndex++) { 
	         for(int rankIndex = 0; rankIndex < RANKS.length; rankIndex++ ) { 
	            pictographs[suitIndex][rankIndex][0] =  
	               Character.highSurrogate(codePoint + rankIndex); 
	            pictographs[suitIndex][rankIndex][1] =  
	               Character.lowSurrogate(codePoint + rankIndex); 
	         } 
	         codePoint = codePoint + 0x10; // ace of heart then diamonds then cluds 
	      } 
	   } 
	     
	   // construct playing card with a rank and suit;  
	   // where rankIndex corresponds to the following (ranks): 0 (ACE), 1 (2), 
	   // 2 (3), ..., 8 (9), 9 (10), 10 (JACK), 11 (KNIGHT), 12 (QUEEN), 13 (KING);   
	   // and where suitIndex corresponds to the following (suits):  0 (SPADES),  
	   // 1 (HEARTS), 2 (DIAMONDS), 3 (CLUBS). 
	   public PlayingCard(int rankIndex, int suitIndex) { 
	      this.rankIndex = (0 <= rankIndex && rankIndex < RANKS.length) ? rankIndex : 0;
	      this.suitIndex = (0 <= suitIndex && suitIndex < SUITS.length) ? suitIndex : 0;
	   } 
	     
	   // is rank of current card less than rank of given card? 
	   public boolean isRankLessThan(PlayingCard card) { 
	      return (RANKS[this.rankIndex] < RANKS[card.rankIndex]); 
	   } 

	   // is rank of current card equal to rank of given card? 
	   public  boolean isRankEqual(PlayingCard card) { 
	      return (RANKS[this.rankIndex] == RANKS[card.rankIndex]); 
	   } 
	     
	   // convert to a String value description of the card. 
	   @Override     
	   public String toString() { 
	      int rank = RANKS[rankIndex]; 
	      String rankAsString =  
	         (rank == ACE) ? "Ace" :  
	         (rank < JACK) ? ("" + rank) : 
	         (rank == JACK) ? "Jack" : 
	         (rank == KNIGHT) ? "Knight" :   
	         (rank == QUEEN) ? "Queen" :   
	         (rank == KING) ? "King" : "Error!";  

	      int suit = SUITS[suitIndex]; 
	      String suitAsString = 
	         (suit == SPADES) ? "Spades" :  
	         (suit == HEARTS) ? "Hearts" :  
	         (suit == DIAMONDS) ? "Diamonds" : 
	         (suit == CLUBS) ? "Clubs" : "Error!";  

	      return rankAsString + " of " + suitAsString; 
	   } 

	   // convert to a String value pictograph of the card. 
	   public String toPictograph() { 
	      String cardPictograph = "" +  
	         pictographs[suitIndex][rankIndex][0] +  
	         pictographs[suitIndex][rankIndex][1]; 
	  
	      return cardPictograph;  
	   } 

	   // test the PlayingCard data type by constructing a playing card of 
	   // each rank and suit and printing a pictograph of the card to the 
	   // standard output. 
	   public static void main(String[] args) { 
	      for(int suitIndex = 0; suitIndex < SUITS.length; suitIndex++) {  
	         for(int rankIndex = 0; rankIndex < RANKS.length; rankIndex++) { 
	            PlayingCard card = new PlayingCard(rankIndex, suitIndex); 
	            System.out.print(card.toPictograph() + " ");  
	         } 
	         System.out.println(); 
	      } 
	   } 

	} // end PlayingCard data type
