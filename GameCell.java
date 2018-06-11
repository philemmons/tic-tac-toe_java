/**
 * Title: TicTacToe 
 * Abstract: Contains GameCell.class - Method are called by the game board class.
 * Author: Phillip T. Emmons
 * Date: 9-9-16
 * CST338 Tues/Thurs 4-6pm 
**/

public class GameCell
{
    private char data;//This is the variable used to store the computer and player's char.
    private int location =0;
    
    //Constructor.
    public GameCell( int cellLocation ){
    	this.location = cellLocation;// This is used as the cell number in the game board.
        data = ' ';
    }
    //The board is displayed after every tunr
    public void drawCell(){
        if( data == 'X' )
            System.out.print( " X " );
        else if (data == 'O' )
            System.out.print( " O " );
        else 
            System.out.print( " "+ ( location +1 ) +" " );
    
    }
    //Cells default value and the computer uses method during the win and block subroutines.
    public void empty(){
        data = ' ';
    }
    //This is used by the clearCell method
    public boolean equals( char cellContent ){
        if( Character.isWhitespace( data ) == Character.isWhitespace( cellContent ) ) {
            return true;
        } else {
            return false;
        }
    }
    //It returns the cell value: X O or ' '.
    public char getData(){
        return this.data;
    }
    
    public void setData( char playerLetter ){
        this.data = playerLetter;
    }
    //It is used during debugging.
    public int getLocation(){
    	return this.location;
    }
       
}//EOF
