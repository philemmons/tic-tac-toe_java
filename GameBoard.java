/**
 * Title: TicTacToe 
 * Abstract: Contains GameBoard.class 
 * Author: Phillip T. Emmons
 * Date: 9-9-16
 * CST338 Tues/Thurs 4-6pm 
**/

public class GameBoard{

	public static final int NUMBER_OF_CELLS = 9;
    
    private GameCell[] boardCells;//It is using an array of GameCells as it provides more than enough Object functions
    
    //Constructor.
    public GameBoard(){
        boardCells = new GameCell[NUMBER_OF_CELLS];
        for( int i= 0; i< NUMBER_OF_CELLS; i++ ){
            boardCells[i] = new GameCell(i);//
        }
    }
    //A copy construct also used in debugging the computer's loop preformance.
    //I was surprised by the lack of a deep copy.
    //public GameBoard( GameBoard aCopy ){
    //	 this.boardCells = aCopy.boardCells; 
    //}
    
    public void drawBoard(){
        //Display the board to console.
        for( int i= 0; i < NUMBER_OF_CELLS; i++){
            boardCells[i].drawCell();
            if( ( i< NUMBER_OF_CELLS )&&( (i+1)%3 != 0 ) ){
                System.out.print("|");               
            }else if( i== 2 || i== 5) {
                 System.out.println("\n-----------");
            }
        }
        System.out.println("\n");
    }
    
    public void clearBoard(){
        for( int i= 0; i< NUMBER_OF_CELLS; i++ ){
            boardCells[i].empty();//Reset the value of the cells.
        }
    }
    //Is the board filled? 
    public boolean tieGame( GameCell[] boardCells ){
        for( int i= 0; i< NUMBER_OF_CELLS; i++ ){
            if ( boardCells[i].equals( ' ' ) ){
                return false; 
            }
       }
       return true;
    }
    
    //Checks all possible victory conditions by the player and the computer.
    //tL is theLetter user: X or O
    public boolean winGame( char tL ){
        if( ((boardCells[0].getData() == tL) && (boardCells[1].getData() == tL) && (boardCells[2].getData() == tL)) ||
            ((boardCells[3].getData() == tL) && (boardCells[4].getData() == tL) && (boardCells[5].getData() == tL)) ||
            ((boardCells[6].getData() == tL) && (boardCells[7].getData() == tL) && (boardCells[8].getData() == tL)) ||
            ((boardCells[0].getData() == tL) && (boardCells[3].getData() == tL) && (boardCells[6].getData() == tL)) ||
            ((boardCells[1].getData() == tL) && (boardCells[4].getData() == tL) && (boardCells[7].getData() == tL)) ||
            ((boardCells[2].getData() == tL) && (boardCells[5].getData() == tL) && (boardCells[8].getData() == tL)) ||
            ((boardCells[0].getData() == tL) && (boardCells[4].getData() == tL) && (boardCells[8].getData() == tL)) ||
            ((boardCells[2].getData() == tL) && (boardCells[4].getData() == tL) && (boardCells[6].getData() == tL)) ){
             return true;
        }else {
            return false;
        }
    }
    //Places the X or O in the array at the specific index.
    public void move( char tL, int selectCell ){
        boardCells[ selectCell ].setData( tL );
    }
    //This checks the cell to see if it is available and prevents overwriting each players previous turn.
    public boolean cellOpen( int selectCell, char playerLetter ){
        char test = boardCells[ selectCell ].getData();
        if( (test == ' ') && (test != playerLetter)){
                return true;
        }else{
            return false;
        }
    }
    //The GameBoard class accesses the arrays and to invoke the GameCell methods.  
    public GameCell[] getBoardCells(){
        return this.boardCells;
    }
    //It removes the computer placement during the win and block examination.
    public void clearCell( int index){
        boardCells[ index ].empty();
    }
      
}//EOF
