/**
 * ConnectFourGame
 * This class stores the information for the state of the connect four game and provides
 * various methods of interaction with the game
 * @author Alec Cobban
 * @author Azhar Hussain
 * Last edited 3/6/16
 */
public class ConnectFourGame implements Connect4State {
		// a 2d array of chars represents the board
    private char [][] board = new char[ROWS][COLS];
    //An array of two players that represents the two players
    private Player [] players;
    //the number of the player to move, initializes at 0
    private int playerToMoveNum = 0;
    //passes in the type of view being used to make the game
    private Connect4View gameView;
    
    /**
     * This is the constructor for a game of ConnectFour.
     * sets up various instance variables as well as the board
     * @param playerNum Which player goes first
     * @param player a list of players to be fed in
     * @param view the view that this object will be using to communicate with the user
     */
    public ConnectFourGame(int playerNum, Player[] player, Connect4View view){
    		//the number of the player to move is set
        playerToMoveNum = playerNum;
        //the player list is initialized
        players = player;
        //the view is fed into the class
        gameView = view;
        
        //the board is created
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '.';
            }
        }
    }
    
    /**
     * Getter method for the board
     * @return char[][] the board
     */
    public char[][] getBoard() {
        return board;
    }
    
    /**
     * Getter method for the players
     * @return Player[] a list of the players for this game
     */
    public Player[] getPlayers() {
        return players;
    }
    
    /**
     * Getter method for the player number to determine whose turn it is
     * @return int the current player's number
     */
    public int getPlayerNum() {
        return playerToMoveNum;
    }
    
    /**
     * Getter method for the player to move
     * @return player returns the player whose turn it is
     */
    public Player getPlayerToMove(){
        return players[playerToMoveNum];
    }
    
    /**
     * Setter method to edit the board
     * @param board the board to set the instance variable to
     */
    public void setBoard(char[][] board) {
        this.board = board;
    }
    
    /**
     * Returns true if move is within range, otherwise returns false.
     * @return a boolean to determine whether the move is valid
     */
    public boolean isValidMove(int col){
        if (col > 5 || col < 0){
            return false;
        }

        for (int i = 0; i < ROWS; i++){
            if (board[i][col] == (EMPTY)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Places a piece on the board in the desired column
     * @param the column that you wish to place the piece in
     */
    public void makeMove(int col){
    		//Go through all the rows for the given column
        for (int i = 0; i < ROWS; i++){
        		// if that spot is empty
            if (board[i][col] == (EMPTY)){
                
            		//if the first player
            		if (playerToMoveNum == 0){
            				//put player ones checker
                    board[i][col] = CHECKERS[0];
                    break;
                } else {
                	//or must be player two so put their checker
                    board[i][col] = CHECKERS[1];
                    break;
                }
            }
        }
    }
    
    /**
     * Returns a boolean that tells whether the entire board is full
     * @return boolean- is the entire board full
     */
    public boolean isFull(){
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
            		//if any spot is empty return false
                if (board[i][j] == EMPTY){
                    return false;
                }
            }

        }
        return true; //else return true
    }
    /**
     * determine if a spot is empty
     * @param i the row of the spot
     * @param j the column of the spot
     * @return whether or not that spot is empty
     */
    public boolean notEmpty(int i, int j){
        return board[i][j] != EMPTY;
    }
    
    /**
     * Look and see if there is a winner. Look at every spot and see if there are four in a row
     * @return true if there are four in a row otherwise return false
     */
    public boolean isWinner(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (j <= 3 && notEmpty(i,j) && board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3]){
                    return true;
                } else if (i <= 2 && notEmpty(i,j) && board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]){
                    return true;
                } else if (i <= 2 && j <= 3 &&notEmpty(i,j) && board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3]){
                    return true;
                } else if (i > 2 && j <= 3 && notEmpty(i,j) && board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && board[i][j] == board[i-3][j+3]){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Tells you whether or not the game is over
     * @return returns true if the board is full or if there's a winner, else returns false
     */
    public boolean gameIsOver(){
        if (isFull()){
            return true;
        } else if (isWinner())
            return true;
        else
            return false;
    }
    /**
     * Switch playerToMoveNum between 0 and 1.
     */
    public void switchPlayer(){
    		// if 0 then 1
        if (playerToMoveNum == 0){
            playerToMoveNum = 1;
        } else {
        		//if one then 0
            playerToMoveNum = 0;
        }
    }
}
