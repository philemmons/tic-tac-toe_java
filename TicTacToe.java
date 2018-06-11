/**
 * Title: TicTacToe
 * Abstract: Text based TTT program that uses classes. The Limited AI
 * 	does choose from a group of board cells based on the user's first move.
 * 	It also is an opportunity to create multiple classes.
 * Author: Phillip T. Emmons
 * Date: 9-9-16
 * CST338 Tues/Thurs 4-6pm 
 **/

import java.util.Scanner;

public class TicTacToe{ 
	//This is the active portion of the program.
    private GameBoard hashMark;
    //The game players
    private Computer hal;
    private Player phil;

    private boolean inPlay;//Determines if the game is over.
    private int turnCounter = 0;// Determines the computers first group of moves within the computer class.
    private String turn;//Alternates between players.
    private String answer;// Holds the user's input to go first or not.
    
    private static Scanner keyboard = new Scanner(System.in);
    
    //Constructor.Everything happens inside the object.
    public TicTacToe()
    {
        hashMark = new GameBoard();
        
        hal = new Computer();
        phil = new Player();
        
        inPlay = true;
        turn = "player";
        
        goFirst( hal, phil );

        while(inPlay){//If player is first, program continues, otherwise it goes to computer's turn.
            if( turn.equals( "player" )) {
            	turnCounter++;
                hashMark.drawBoard();//Live Board displayed every turn.
                phil.playerTurn( hashMark );
                //Did player win the game?
                if( hashMark.winGame( phil.getPlayerLetter()  )) {
                    hashMark.drawBoard();
                    System.out.println( "  You won!" );
                    inPlay = false;
                //Is it a tie game?    
                }else if( hashMark.tieGame( hashMark.getBoardCells() ) ){
                    hashMark.drawBoard();
                    System.out.println( "  Tie game!" );
                    inPlay = false;
                    
                }else {
                    turn = "computer";//End of player's turn
                }
            }else {//Begins computer'turn
                hashMark.drawBoard();
                hal.computerTurn( hashMark, phil, turnCounter );
                //Did AI win?
                if( hashMark.winGame( hal.getComputerLetter()  )) {
                    hashMark.drawBoard();
                    System.out.println( " Hal won!" );
                    inPlay = false; 
                //Is it a draw?    
                }else if( hashMark.tieGame( hashMark.getBoardCells() ) ){
                    hashMark.drawBoard();
                    System.out.println( " Tie game!" );
                    inPlay = false; 
                    
                }else {
                    turn = "player";//end of turn
                }       
            }
        }    
    }
    //All the work is done in the TTT object.
    public static void main( String[] args){
        System.out.println("  ==== Welcome to Tic-Tac-Toe Game ==== ");
        new TicTacToe();
        System.out.println("  Good game!");;  
    }
    //Local method called within an object. Is this called an instantiated method much like a variable?
    public void goFirst( Computer xerox, Player phil ){
        System.out.print("Do you want to start first? (Y/N) ");
        answer = keyboard.nextLine();
        //Match the input regardless of caps.
        if( answer.toUpperCase().equals( "N" ) ){
            phil.setPlayerLetter( 'O' );
            xerox.setComputerLetter( 'X' );
            turn = "computer";
        }
        System.out.println("OK! Your character is '" + phil.getPlayerLetter() +
            "' and the computer character is '" + xerox.getComputerLetter() + "' \n");
    }
    
}//EOF
