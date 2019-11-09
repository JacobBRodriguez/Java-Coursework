import java.util.Scanner;
public class FractionCalculator {

    public static void main(String args[]) {
        String operation;
        Fraction fraction1, fraction2, result;
        Scanner input = new Scanner(System.in);

        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply, and divide fractions until you type Q to quit");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");

        while(true)
        {
            operation = getOperation(input);
            if(operation.equalsIgnoreCase("Q"))
                break;
            else
            {
                fraction1 = getFraction(input);
                fraction2 = getFraction(input);
            }

            if(operation.equals("+"))
                result = fraction1.add(fraction2);
            else if(operation.equals("-"))
                result = fraction1.subtract(fraction2);
            else if(operation.equals("*"))
                result = fraction1.multiply(fraction2);
            else
                result = fraction1.divide(fraction2);
            result.toLowestTerms();
            System.out.println(result.toString());

        }//end while


    }//end main

    public static String getOperation(Scanner input) {
        boolean validInput = false;
        String userResponse = "";
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
        while(!validInput) {
            userResponse = input.next();
            if(userResponse.equals("+") || userResponse.equals("-") || userResponse.equals("*")
            || userResponse.equals("/") || userResponse.equalsIgnoreCase("Q"))
                validInput = true;
            else
                System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");

        }// end while
        return userResponse;

    }// end getOperation

    public static Fraction getFraction(Scanner input) {
        boolean validInput = false;
        String userResponse = "";
        int numerator, denominator;
        Fraction fraction;


        System.out.print("Please enter a faction (a/b) or integer (a): ");
        while(!validInput) {
            userResponse = input.next();
            if(!validFraction(userResponse))
                System.out.print("Invalid fraction. Please enter (a/b) or (a), where " +
                        "a and b are integers and b is not zero: ");
            else
                validInput = true;
        }// end while
        if(userResponse.contains("/")){
            String[] numberArgs = userResponse.split("/", 2);
            numerator = Integer.parseInt(numberArgs[0]);
            denominator = Integer.parseInt(numberArgs[1]);
            fraction = new Fraction(numerator, denominator);
        }// end if
        else {
            numerator = Integer.parseInt(userResponse);
            fraction = new Fraction(numerator);
        }
        return fraction;
    }// end getFraction

    public static boolean validFraction(String input) {

        String[] numArgs;
        //if we have negative, must be at beginning of string
        if(input.contains("-") && input.indexOf("-") != 0)
            return false;
        //if we do have negative, test if only one negative sign
        // if so, remove negative sign and parse rest
        if(input.contains("-") && input.indexOf("-") == 0)
            if (input.substring(1, input.length()).contains("-"))
                return false;
            else
                input = input.substring(1, input.length());


        //split string by / and try to parse into valid ints
        numArgs = input.split("/", 2);
        if(numArgs.length == 1) {
            try {
                Integer.parseInt(numArgs[0]);
            }
            catch(NumberFormatException nfe) {
                return false;
            }
        }//end if
        else {

            try {
                if(Integer.parseInt(numArgs[1]) == 0)
                    return false;
                Integer.parseInt(numArgs[0]);
            }
            catch(NumberFormatException nfe) {
                return false;
            }
        }//end else

        return true;

    }// end validFraction
}
