import java.util.Scanner;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        //initializing game variables
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        char[][] gameboard = new char[12][14];

        generateGameboard(gameboard);
        printGameboard(gameboard);
        placeFriendlyShips(gameboard, input);
        printGameboard(gameboard);
        placeEnemyShips(gameboard, random);

        printGameboard(gameboard);

        int winner = playGame(gameboard, input, random);
        if(winner == 1)
            System.out.println("Congrats player 1!");
        else
            System.out.println("The machines have won this time");
        printGameboard(gameboard);

    } //end main

    static void generateGameboard(char[][] board) {

        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
            {
                if(i == 0 || i == 11)
                {
                    if(j > 1 && j < 12)
                    {
                        int a = j - 2;
                        board[i][j] = (char)(a+'0');
                    }
                    else
                        board[i][j] = ' ';
                } // end IF end of board

                else
                {
                    if(j == 0 || j == 13)
                    {
                        int a = i - 1;
                        board[i][j] = (char) (a + '0');
                    }
                    else if(j == 1 || j == 12)
                        board[i][j] = '|';
                    else
                        board[i][j] = ' ';
                } // end ELSE rest of board

            } // end inner for
    } //end fillGameboard

    static void printGameboard(char[][] board) {

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if( (i > 0 && i < 11) && (j > 1 && j < 12)) //if on board
                    if(board[i][j] == '1')
                        System.out.print('@');
                    else if(board[i][j] == '2')
                        System.out.print(' ');
                    else
                        System.out.print(board[i][j]);
                else
                    System.out.print(board[i][j]);
            }

            System.out.println();
        }

    } // end printGameboard

    static void placeFriendlyShips(char[][] board, Scanner input) {
        int numShipsLeft = 5;
        int x = 0, y = 0;
        boolean validX = false;
        boolean validY = false;
        while(numShipsLeft > 0)
        {

            while(validX == false)
            {
                System.out.print("Enter X coordinate for ship " +numShipsLeft+": ");
                x = input.nextInt();
                if(x < 0 || x > 9)
                    System.out.println("Invalid X coordinate. Please choose a number between 0 and 9");
                else
                    validX = true;
            } // end while ValidX

            while(validY == false)
            {
                System.out.print("Enter Y coordinate for ship "+numShipsLeft+":  ");
                y = input.nextInt();
                if(y < 0 || y > 9)
                    System.out.println("Invalid Y coordinate. Please choose a number between 0 and 9");
                else
                    validY = true;
            } // end while ValidY

            if(placeOnBoard(board, x, y, true) )
                numShipsLeft--;

            else
                System.out.println("Space already has a ship! Please enter another location.");

            validX = false;
            validY = false;

        } // end while
    } // end placeFriendlyShips

    static boolean placeOnBoard(char[][] board, int x, int y, boolean player1) {
        char placeValue = board[x+1][y+2];
        if(placeValue == '1' || placeValue == '2')
            return false;
        else {
            if (player1)
                board[x + 1][y + 2] = '1';
            else
                board[x + 1][y + 2] = '2';
        }
        return true;
    } // end placeOnBoard

    static void placeEnemyShips(char[][] board, Random random) {
        int numShipsLeft = 5;
        int x = 0, y = 0;
        while(numShipsLeft > 0) {
            x = random.nextInt(10);
            y = random.nextInt(10);

            if (placeOnBoard(board, x, y, false)) {
                System.out.println(numShipsLeft + ". ship DEPLOYED");
                numShipsLeft--;
            }
        }
    }// end placeEnemyShips

    static int playGame(char[][] board, Scanner input, Random random) {
        int playerShipsLeft = 5, computerShipsLeft = 5, x = 0, y = 0;
        boolean validX = false, validY = false, invalidGuess = true, invalidComputerGuess = true;
        char placeValue;


        while(playerShipsLeft > 0 && computerShipsLeft > 0)
        {

            System.out.println("YOUR TURN");
            while(invalidGuess) { // player's turn to make a guess

                while (validX == false) {
                    System.out.print("Enter X coordinate: ");
                    x = input.nextInt();
                    if (x < 0 || x > 9)
                        System.out.println("Invalid X coordinate. Please choose a number between 0 and 9");
                    else
                        validX = true;
                } // end while ValidX

                while (validY == false) {
                    System.out.print("Enter Y coordinate:  ");
                    y = input.nextInt();
                    if (y < 0 || y > 9)
                        System.out.println("Invalid Y coordinate. Please choose a number between 0 and 9");
                    else
                        validY = true;
                } // end while ValidY

                placeValue = board[x+1][y+2];

                if(placeValue == '1' || placeValue == '2' || placeValue == ' ')
                {
                    if(placeValue == '1')
                    {
                        System.out.println("Oh no! You sunk your own ship :(");
                        board[x+1][y+2] = 'X';
                        playerShipsLeft--;
                    }
                    else if(placeValue == '2')
                    {
                        System.out.println("Boom! You sunk the ship!");
                        board[x+1][y+2] = '!';
                        computerShipsLeft--;

                        if(computerShipsLeft == 0)// win condition reached before computer turn
                            return 1;
                    }
                    else
                    {
                        System.out.println("Sorry, you missed");
                        board[x+1][y+2] = '-';
                    }
                    invalidGuess = false;
                } //end if valid

                else {
                    validX = false;
                    validY = false;
                    System.out.println("Invalid guess. Try again");
                }
            }// end while invalidGuess for player

            System.out.println("COMPUTER'S TURN");
            while(invalidComputerGuess)
            {
                // Computer's turn to guess
                x = random.nextInt(10);
                y = random.nextInt(10);
                placeValue = board[x+1][y+2];

                if(placeValue == '1' || placeValue == '2' || placeValue == ' ')
                {
                    if(placeValue == '1')
                    {
                        System.out.println("The Computer sunk one of your ships!");
                        board[x+1][y+2] = 'X';
                        playerShipsLeft--;
                    }
                    else if(placeValue == '2')
                    {
                        System.out.println("The Computer sunk one of its own ships.");
                        board[x+1][y+2] = '!';
                        computerShipsLeft--;
                    }
                    else
                    {
                        System.out.println("Computer missed");
                        board[x+1][y+2] = '-';
                    }
                    printGameboard(board);
                    invalidComputerGuess = false;
                } //end if valid
            }

            // reset for next round
            validX = false;
            validY = false;
            invalidGuess = true;
            invalidComputerGuess = true;
        } //end while ships more than 0

        if(playerShipsLeft == 0)
            return 2;
        return 1;
    } //end playGame


} // end class
