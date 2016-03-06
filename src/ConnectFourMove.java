/**
 * ConnectFourMove
 * This is a class that defines a move type with value and position
 * for purposes of static evaluation.
 * @author Alec Cobban
 * @author Azhar Hussain
 * Last edited 3/6/16
 */

public class ConnectFourMove {
	//the value of the move from a static eval
    private int value; 
  //the location of a move (column
    private int move; 

    /**
     * Constructor for a ConnectFourMove
     * assigns the instance variables value and column
     * @param val the value of the move
     * @param mov the column of the move
     */
    public ConnectFourMove(int val, int mov){
        //the value of a move
    		value = val; 
        //the column that the move is played in
        move = mov;
    }
    /**
     * Getter function on instance variable value
     * @return the value of the move
     */
    public int getValue() {
        return value;
    }
    /**
     * Getter function on the instance variable move
     * @return the column that the move was played in
     */
    public int getMove() {
        return move;
    }
    /**
     * Setter for value instance variable
     * @param value the value of the move
     */
    public void setValue(int value) {
        this.value = value;
    }
    /**
     * Setter for move instance variable
     * @param move the column a move is in
     */
    public void setMove(int move) {
        this.move = move;
    }
}
