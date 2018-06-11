/**
 * Title: TicTacToe 
 * Abstract: Contains Computer.class - It is the most challenging portion of the assignment. 
 * 	If I had more time I would increase the cell grouping based on the player's actions. 
 * 	Also, simplifying the redundant lines of code into private methods would make it more readable.
 * 	There are six predefined options which increase the play ability. 9! is reduced to 5! or 6! 
 * Author: Phillip T. Emmons
 * Date: 9-9-16
 * CST338 Tues/Thurs 4-6pm 
**/

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    
    private char computerLetter; //Either X or O depending on the user's input.
    private int computerPick = 0;//Computer selection is increased by one and is sent to the GameBoard for display.
    private char playerLetter;   //Extracted value from the user to be used in the win block phases.
    private int userPick = 0;	 //This is used for the first and second move of the game only.
    
    private ArrayList<Integer> corner = new ArrayList<Integer>();//It has the append method to create the side and corner groups.
    private ArrayList<Integer> side = new ArrayList<Integer>();
    private Random index = new Random();//It randomly selects unused board cells that are remaining in the group
    
    private boolean compTurn = true;// A value is needed to prevent multiple turns in a row.

    //Constructor.
    public Computer(){  
        computerLetter = 'O';
    }
    //Allows the gofirst method to change the default value.
    public void setComputerLetter( char choice ){
        this.computerLetter = choice;  
    }
    
    public char getComputerLetter(){
        return this.computerLetter;
    }
    //
    public void computerTurn( GameBoard board, Player phil, int turnCounter ){
    	playerLetter = phil.getPlayerLetter();
    	userPick = phil.getUserPick();
    	
    	//Can it win? It iterates over the board, placing it X or O in the cell, and it is checking for a possible win.
    	//If the win is not found, then the X or O is removed before continuing to the next available cell.
        for( int i= 0; ( i< 9 ) && (compTurn); i++){
            if( board.cellOpen( i, playerLetter ) ){
                board.move( computerLetter, i);
                if( board.winGame( computerLetter ) ){
                    board.move( computerLetter, i);
                    computerPick = i;
                    compTurn = false; 
                }else{
                    board.clearCell( i );
                }
                
            }
            
        }
        //Block a player's win by the same algorithm as above.
        for( int i= 0; ( i< 9 ) && (compTurn); i++){
            if( board.cellOpen( i, playerLetter ) ){
                board.move( playerLetter, i);
                if( board.winGame( playerLetter ) ){
                    board.move( computerLetter, i );
                    computerPick = i;
                    compTurn = false;
                }else{
                    board.clearCell( i);
                }
            } 
        }
        
        //A set of defensive cells are used after the first player's choice.
        //The sets are described by Wikipedia.
        //These are the edges set if the player selects a corner cell.
        if( ( turnCounter == 1 )  && 
        	( userPick == 0 || userPick == 2 ||  userPick == 6 || userPick == 8 ) ){
        	while( compTurn ){
    	        if( board.cellOpen( 1, playerLetter ) ){
    	            side.add( 1 );
    	        }
    	        if( board.cellOpen( 3, playerLetter ) ){
    	            side.add( 3 );
    	        }
    	        if( board.cellOpen( 5, playerLetter ) ){
    	           side.add( 5 );
    	        }
    	        if( board.cellOpen( 7,playerLetter ) ){
    	            side.add( 7 );
    	        }
    	        if( side.size() != 0 ){
    	           int value = index.nextInt( side.size() );
    	           board.move( computerLetter, side.get( value ) );
    	           computerPick = side.get(value);
    	           compTurn = false;
    	           side.clear();
    	        }
            }//End while loop
        }
        
        //A center move by the computer if the player selects an edge.
        if( ( turnCounter == 1 )  && 
            ( userPick == 1 || userPick == 3 ||  userPick == 5 || userPick == 7 ) ){
        	if( board.cellOpen( 4, playerLetter ) && compTurn ){
     	       board.move( computerLetter, 4 );
     	       computerPick = 4;
     	       compTurn = false;
            }
        }
              
        //If the player selects the center, then the corners are suggested.
        //Also, the corner set is suggested as the follow up move for the prior two opening moves by the player.
        while(compTurn){
	        if( board.cellOpen( 0, playerLetter ) ){
	            corner.add( 0 );
	        }
	        if( board.cellOpen( 2, playerLetter ) ){
	            corner.add( 2 );
	        }
	        if( board.cellOpen( 6, playerLetter ) ){
	           corner.add( 6 );
	        }
	        if( board.cellOpen( 8, playerLetter ) ){
	            corner.add( 8 );
	        }
	        if( corner.size() != 0 ){
	           int value = index.nextInt( corner.size() ); 
	           board.move( computerLetter, corner.get( value ) );
	           computerPick = corner.get( value );
	           compTurn = false;
	           corner.clear();
	        }
        }//End while loop.
        
       
       //After all the corners are used, the center is suggested before the remaining edges.
       if( board.cellOpen( 4, playerLetter ) && compTurn ){
	       board.move( computerLetter, 4 );
	       computerPick = 4;
	       compTurn = false;
        }
        
       //Rinse and repeat with this last set until the end of the game.
        while( compTurn ){
	        if( board.cellOpen( 1, playerLetter ) ){
	            side.add( 1 );
	        }
	        if( board.cellOpen( 3, playerLetter ) ){
	            side.add( 3 );
	        }
	        if( board.cellOpen( 5, playerLetter ) ){
	           side.add( 5 );
	        }
	        if( board.cellOpen( 7,playerLetter ) ){
	            side.add( 7 );
	        }
	        if( side.size() != 0 ){
	           int value = index.nextInt( side.size() );
	           board.move( computerLetter, side.get( value ) );
	           computerPick = side.get(value);
	           compTurn = false;
	           side.clear();
	        }
        }  
        //Display to console and reset the compTurn value for the next turn.
        System.out.println("OK! Hal picks: "+ (computerPick+1) +"\n" );
        compTurn = true;
    }
    
}//EOF