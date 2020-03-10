import java.util.Scanner;
import java.text.DecimalFormat;

public class StringManipulator
{
   static final int MIN_HOURS = 12;
   static final int MAX_HOURS = 20; 
   
   public static void main(String[] args)
   {
      Scanner keyInput = new Scanner(System.in);
      
      System.out.println("Please enter your first name "
         + "with first letter capitalized: ");
      String firstName = keyInput.next();
      
      System.out.println("Please enter your last name"
         + "with first letter capilazied: ");
      String lastName = keyInput.next();
      
      String fullName = firstName + " " + lastName;
      System.out.println("The full name is: " + fullName);
      
      System.out.println("The length of the full name is: " 
         + fullName.length());
      
      System.out.println("The full name in all upper case: " 
         + fullName.toUpperCase());
      
      System.out.println("The full name in all lower case: " 
         + fullName.toLowerCase());
      
      System.out.println("The minimum number of hours "
         + "you should spend on this class each week: " 
         + MIN_HOURS);
      
      System.out.println("The maximum number of hours "
         + "you should spend on this class each week: " 
         + MAX_HOURS);
      
      System.out.println("How many hours do you spend on this class "
         + "this week?");
      System.out.println("(The answer should be up to 3 decimal places)");
      double hoursSpentForClass = keyInput.nextDouble();
      
      DecimalFormat hourWithOneDecimal = new DecimalFormat("00.0");
      
      System.out.println("The hours user spent on this class for this week: ");
      System.out.print(hourWithOneDecimal.format(hoursSpentForClass));
   }
}