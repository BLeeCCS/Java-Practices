import java.lang.Math;

public class DeckOfCards
{

   public static void main(String[] args)
   {
      int numOfDecks = 2;
      Deck tuPac = new Deck(numOfDecks);
      
      for (int i = 0; i < 2 * 52; i++)
      {
         System.out.println(tuPac.dealCard());
      }
      
      numOfDecks = 1;
      tuPac.init(numOfDecks);
      tuPac.shuffle();
      
      System.out.println("After Shuffled: ");
      for (int i = 0; i < numOfDecks * 52; i++)
      {
         System.out.println(tuPac.dealCard());
      }
   }

}

class Card
{
   public static enum Suit
   {
      CLUBS, DIAMONDS, HEARTS, SPADES
   };

   public static final String[] SUIT_STRING =
   { "Clubs", "Diamonds", "Hearts", "Spades" };
   public static final char[] VALUES =
   { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };

   private char value;
   private Suit suit;
   private boolean errorFlag;

   public Card(char val, Suit s)
   {
      value = val;
      suit = s;
   }

   public Card()
   {
      value = 0;
      suit = Suit.CLUBS;
      errorFlag = true;
   }

   public String toString()
   {
      if (errorFlag)
      {
         return "invalid";
      } else
      {
         String retString = "";
         switch (suit)
         {
         case CLUBS:
            retString = value + " of Clubs";
            break;
         case DIAMONDS:
            retString = value + " of Diamonds";
            break;
         case SPADES:
            retString = value + " of Spades";
            break;
         case HEARTS:
            retString = value + " of Hearts";
            break;
         default:
            retString = "Uknown suit type";
         }
         return retString;
      }
   }

   public boolean set(char val, Suit s)
   {

      if (isValid(val, s))
      {
         value = val;
         suit = s;
         errorFlag = false;
         return true; 
         
      } else
      {
         errorFlag = true;
         return false;
      }
   }

   public char getValue()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   public boolean equals(Card card)
   {
      char otherValue = card.getValue();
      Suit otherSuit = card.getSuit();

      if (value == otherValue && suit == otherSuit)
         return true;

      return false;
   }

   private boolean isValid(char value, Suit suit)
   {
      boolean found = false;

      for (char c : VALUES)
      {
         if (value == c)
            found = true;
      }

      if (!found)
      {
         return false;
      }

      if (suit != Suit.CLUBS && suit != Suit.DIAMONDS && suit != Suit.SPADES && suit != Suit.HEARTS)
      {
         return false;
      }

      return true;
   }
}

//Andrew's work
class Deck
{
   public final int MAX_CARDS = 6 * 52;

   private static Card[] masterPack = new Card[52];

   private Card[] cards;
   // this int will point to the last element of Card[]
   private int topCard;

   // constructor that has numCards == 52
   // or 1 * 52
   Deck()
   {
      allocateMasterPack();
      int numCards = 52;
      topCard = numCards - 1;
      cards = new Card[numCards];

      for (int i = 0; i < numCards; i++)
      {
         cards[i] = masterPack[i];
      }
  
   }

   // constructor that has multiple decks in the game
   Deck(int numPacks)
   {
      allocateMasterPack();
      int numCards = numPacks * 52;
      topCard = numCards - 1;
      cards = new Card[numCards];

      for (int i = 0; i < numCards; i++)
      {
         // alocate the first 52 cards
         if (i < 52)
            cards[i] = masterPack[i];
         // seccond 52 cards
         else if (i >= 52 && i < 52 * 2)
            cards[i] = masterPack[i - 52];
         else if (i >= 52 * 2 && i < 52 * 3)
            cards[i] = masterPack[i - (52 * 2)];
         else if (i >= 52 * 3 && i < 52 * 4)
            cards[i] = masterPack[i - (52 * 3)];
         else if (i >= 52 * 4 && i < 52 * 5)
            cards[i] = masterPack[i - (52 * 4)];
         else if (i >= 52 * 5 && i < 52 * 6)
            cards[i] = masterPack[i - (52 * 5)];
         else
            System.out.println("ERROR in Constructor at i=" + i);
      }
   }
   
   public void init(int numPacks)
   {
      // if more than 6 decks, default to 6 decks
      if (numPacks > 6)
      {
         numPacks = 6;
      }
      
      int numOfCards = numPacks * 52;
      topCard = numOfCards - 1;
      
      for (int i = 1; i <= numPacks; i++)
      {
         for (int j = 0; j < 52; j++)
         {
            cards[j] = masterPack[j];
         }
      }
   }
     
   public void shuffle() 
   {
      int numOfCards = cards.length;
      
      for (int i = 0; i < numOfCards; i++)
      {
         int random = i + (int)(Math.random() * (numOfCards - i));
         Card temp = cards[random];
         cards[random] = cards[i];
         cards[i] = temp;
      }
   }
     
   public Card dealCard() 
   {
      if(topCard < 0)
      {
         Card empty = new Card();
         return empty;
      }
      
      return cards[topCard--];
   }
  
   public final Card topCardAccessor() 
   {
      if(topCard < 0)
      {
         Card empty = new Card();
         return empty;
      }
      
      return cards[topCard];
   }
  
   public final Card inspectCard(int k) 
   {
      int numOfCards = cards.length;
      
      if (k > numOfCards | --k < 0)
      {
         Card empty = new Card();
         return empty;
      }
      
      return cards[k];
   }

   // Method to add cards to MasterPack
   // only called once per program
   public static void allocateMasterPack()
   {
      if (masterPack[0] != null)
         return;
      System.out.println("Hello world!!");
      Card.Suit[] placeHolder = Card.Suit.values();
      int k = 0;

      for (int i = 0; i < 4; i++)
      {
         for (int j = 0; j < 13; j++)
         {
            masterPack[k] = new Card(Card.VALUES[j], placeHolder[i]);
            k++;
         }
      }
   }
}
