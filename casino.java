import java.util.*;
import java.lang.Math;

public class Casino
{   
   public static int getBet()
   {
      Scanner input = new Scanner(System.in);
      System.out.print("How much would you like to bet (1 - 100) or 0 to quit? ");
      int betValue = input.nextInt();
      
      while ((betValue < 0) | (betValue > 100))
      {
         System.out.print("How much would you like to bet (1 - 100) or 0 to quit? ");
         betValue = input.nextInt();
      }
      
      return betValue;
   }
   
   public static ThreeString pull()
   {
      ThreeString slotResult = new ThreeString();
      
      slotResult.setString(randString(),randString(),randString());
      
      return slotResult;
   }
   
   private static String randString()
   {
      double slotChance = Math.random() * 100;
      String slotType = "";
      
      if (slotChance <= 12.5)
      {
         double sevenOrBar = Math.random() * 100;
         
         if (sevenOrBar >= 50.0)
         {
            slotType = "7";
         }
         else
         {
            slotType = "BAR";
         }
      } 
      else if (slotChance > 12.5 & slotChance <= 25.0)
      {
         slotType = "cherries";
      } 
      else
      {
         slotType = "space";
      }
      
      return slotType;
   }
   
   public static int getPayMultiplier(ThreeString thePull)
   {
      int slotMultiplier = 0;
      
      if (thePull.getString1() == "cherries" & thePull.getString2() != "cherries")
      {
         slotMultiplier = 5;
      }
      
      if (thePull.getString1() == "cherries"
         & thePull.getString2() == "cherries"
         & thePull.getString2() != "cherries")
      {
         slotMultiplier = 15;
      }
      
      if (thePull.getString1() == "cherries" 
         & thePull.getString2() == "cherries"
         & thePull.getString3() == "cherries")
      {
         slotMultiplier = 15;
      }
      
      if (thePull.getString1() == "BAR" 
         & thePull.getString2() == "BAR"
         & thePull.getString3() == "BAR")
      {
         slotMultiplier = 50;
      }
      
      if (thePull.getString1() == "7" 
         & thePull.getString2() == "7"
         & thePull.getString3() == "7")
      {
         slotMultiplier = 100;
      }
      
      return slotMultiplier;
   }
   
   public static void display(ThreeString thePull, int winnings)
   {
      System.out.println("whirrrrrr .... and your pull is ...");
      System.out.println(thePull.toString());
      
      if (winnings > 0)
      {
         System.out.print("congratulations, you win: ");
         System.out.println(winnings);
      }
      else
      {
         System.out.println("sorry, you lose.");
      }
   }
   
   public static void main(String[] args)
   {
      int betAmount = 0;
      int winAmount = 0;
      
      do
      {
         betAmount = Casino.getBet();
         ThreeString pullString = Casino.pull();
         
         winAmount = Casino.getPayMultiplier(pullString);
         winAmount *= betAmount;
         
         display(pullString,winAmount);
         System.out.println();
         
         if (betAmount == 0)
         {
            System.out.println("\nYour total winnings were: $" + pullString.displayWinnings());
         }
         
         if (!pullString.saveWinnings(winAmount))
         {
            betAmount = 0;
            System.out.println("\nYour total winnings were: $" + pullString.displayWinnings());
         }
         
      } while (betAmount != 0);
   }

}

class ThreeString
{
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   
   private static int numPulls = 0;
   private String string1, string2, string3;
   private static int [] pullWinnings = new int [MAX_PULLS];
   
   ThreeString()
   {
      string1 = string2 = string3 = "";
   }
   
   private boolean validString(String str)
   {
      boolean valid = false;
      
      if ((str != null) & (str.length() <= MAX_LEN))
      {
         valid = true;
      }
      
      return valid;
   }
   
   public final String getString1()
   {
      return string1;
   }
   
   public final String getString2()
   {
      return string2;
   }
   
   public final String getString3()
   {
      return string3;
   }
   
   public boolean setString(String str1, String str2, String str3)
   {
      boolean success = false;
      
      if ((validString(str1)) & (validString(str2)) & (validString(str3)))
      {
         string1 = str1;
         string2 = str2;
         string3 = str3;
         success = true;
      }
      
      return success;
   }
   
   public String toString()
   {
      return string1 + "   " + string2 + "   " + string3;
   }
   
   public boolean saveWinnings(int winnings)
   {
      boolean enoughSpace = true;
      
      if (numPulls >= MAX_PULLS)
      {
         enoughSpace = false;
         return enoughSpace;
      }
      
      ThreeString.pullWinnings[numPulls] = winnings;
      numPulls++;
      
      return enoughSpace;
   }
   
   public String displayWinnings()
   {
      String total = "";
      int moneyWon = 0;
      
      System.out.println("Thanks for playing at the Casino!");
      System.out.println("Your individual winnings were:");
      
      for (int i = 0; i < numPulls; i++ )
      {
         System.out.print(pullWinnings[i] + " ");
         moneyWon += pullWinnings[i];
      }
      
      return total + moneyWon;
   }
}