/**
 * Title: TicTacToe 
 * Abstract: Contains Player.class
 * Author: Phillip T. Emmons
 * Date: 9-9-16
 * CST338 Tues/Thurs 4-6pm 
**/

import java.util.Scanner;

public class Player
{
    private char playerLetter;// I selected char because it is immutable.
    private int userPick;//This integer is passed to the computer to anticipate the user's first move
    
    Scanner keyboard = new Scanner(System.in);
    
    //Constructor.
    //Default values to help prevent null point exception during debugging.
    public Player() {
        playerLetter = 'X';
        userPick = 0;
    }
    //These are used by the goFirst method.
    public void setPlayerLetter( char choice ){
        this.playerLetter = choice;
    }
    
    public char getPlayerLetter(){
        return this.playerLetter;
    }
    //Provides first turn board location to the computer.
    public int getUserPick(){
    	return this.userPick;
    }
    //This will continue until the user enter's the correct values.
    //Is it occupied?
    public void playerTurn( GameBoard board ){
        boolean userInput = false;
        do{ 
            System.out.print("\nPick your spot: ");
            this.userPick = keyboard.nextInt();
            keyboard.nextLine();//Collects the newline character out of the input stream.
            this.userPick --;
            if( userPick >= 0 && userPick <= 8 && 
                board.cellOpen( userPick, playerLetter ) ){
                board.move( playerLetter, userPick);
                userInput = true;
            }else{
                System.out.println( "Illegal move!");
            }
        } while(!userInput );
    }
       
}//EOF
