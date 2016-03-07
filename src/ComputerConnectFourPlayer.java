import java.lang.reflect.Array;

/**
 * ComputerConnectFourPlayer
 * Creates a computer player for the game connect four
 * @author Azhar Hussain
 * @author Alec Cobban
 * 3/6/16
 */
public class ComputerConnectFourPlayer extends Player{

    // depth of search in AB pruning
    private int depth;

    /**
     * Constructor creates player
     * @param name Name of player
     * @param lookAhead depth of AB search
     */
    public ComputerConnectFourPlayer(String name, int lookAhead){
        super(name);
        depth = lookAhead;

    }

    /**
     * Return the name of the player
     * @return name of the player
     */
    public String getName(){
        return super.getName();
    }

    /**
     * Gets the move the computer made
     * @param state game state being played on
     * @param view view object the game is displayed on (graphical or text)
     * @return return the column in which to play chip
     */
    public int getMove(Connect4State state, Connect4View view){

        // calls pick move, which initiates the AB pruning with neg and positive infinity values
        int move = pickMove(state, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE, view, 0).getMove();

        // report that move to the view to update display
        view.reportMove(move, getName());

        return move;
    }

    /**
     * Evaluates the position in that column to see how many points the move is worth
     * @param state game state being played on
     * @param move the column the move is made in
     * @return the column which has the highest point valuation
     */
    public int staticEvaluation(Connect4State state, int move){

        // get the game board
        char [][] board = state.getBoard();

        // start at bottom row
        int row = 0;

        // the most value for a move is currently 0
        int bestMove = 0;

        // if the move is able to be made, loop till you find the bottom empty row in the column
        if (state.isValidMove(move)){
            for (int i = 0; board[i][move] != '.'; i++){
                row = i;
            }

            // Static evaluation on the positions to the right
            // one position to the right
            if (move <= 5 && board[row][move] == board[row][move+1]){
                if (100 > bestMove){
                    bestMove = 100;
                }

                // two positions to the right
                if (move <= 4 && board[row][move] == board[row][move+2]){
                    if (200 > bestMove){
                        bestMove = 200;
                    }

                    // three positions to the right
                    if ((move <= 3 && board[row][move] == board[row][move+3])){
                        if (1000 > bestMove){
                            bestMove = 1000;
                        }
                    }
                }
            }

            // Static evaluation on the positions to the left
            // one position to the left
            if (move >= 1 && board[row][move] == board[row][move-1]){
                if (100 > bestMove){
                    bestMove = 100;
                }

                // two positions to the left
                if (move >= 2 && board[row][move] == board[row][move-2]){
                    if (200 > bestMove){
                        bestMove = 200;
                    }

                    // three positions to the left
                    if ((move >= 3 && board[row][move] == board[row][move-3])){
                        if (1000 > bestMove){
                            bestMove = 1000;
                        }
                    }
                }
            }

            // Static evaluation on the positions below
            // one position under
            if (row >= 1 && row <= 3 && board[row][move] == board[row-1][move]){
                if (100 > bestMove){
                    bestMove = 100;
                }
                }

            // Two positions under
            if (row >= 2 && board[row][move] == board[row-2][move] && board[row][move] == board[row-1][move]){
                if (200 > bestMove){
                    bestMove = 200;
                }
            }

            // Three positions under
            if ((row >= 3 && board[row][move] == board[row-3][move] && board[row][move] == board[row-2][move]
                    && board[row][move] == board[row-1][move])){

                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            // Static evaluation on the positions to the upward diagonal right
            // one position to the top right
            if (row <= 4 && move <= 5 && row >= 2 && board[row][move] == board[row+1][move+1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }

            // two positions to the top right
            if (row <= 3 && move <= 4 && row >= 1 && board[row][move] == board[row+2][move+2]
                    && board[row][move] == board[row+1][move+1]){

                if (200 > bestMove){
                    bestMove = 200;
                }
            }

            // three positions to the top right
            if ((row <= 2 && move <= 3 && row >= 0 && board[row][move] == board[row+3][move+3]
                    && board[row][move] == board[row+2][move+2] && board[row][move] == board[row+1][move+1])){

                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            // Static evaluation on the positions to the upward diagonal left
            // one position to the top left
            if (row <= 4 && move >= 1 && board[row][move] == board[row+1][move-1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }

            // two positions to the top left
            if (row <= 3 && move >= 2 && board[row][move] == board[row+2][move-2]
                    && board[row][move] == board[row+1][move-1]){

                if (200 > bestMove){
                    bestMove = 200;
                }
            }

            // three positions to the top left
            if ((row <= 2 && move >= 3 && board[row][move] == board[row+3][move-3]
                    && board[row][move] == board[row+2][move-2] && board[row][move] == board[row+1][move-1])){

                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            // Static evaluation on the positions to the downward right
            // one position to the downward right
            if (row >= 1 && move <= 5 && board[row][move] == board[row-1][move+1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }

            // two positions to the downward right
            if (row >=2  && move <= 4 && board[row][move] == board[row-2][move+2]
                    && board[row][move] == board[row-1][move+1]){

                if (200 > bestMove){
                    bestMove = 200;
                }
            }

            // three positions to the downward right
            if ((row >= 3 && move <= 3 && board[row][move] == board[row-3][move+3]
                    && board[row][move] == board[row-2][move+2] && board[row][move] == board[row-1][move+1])){

                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }


            // Static evaluation on the positions to the downward left
            // one position to the downward left
            if (row >= 1 && move >= 1 && board[row][move] == board[row-1][move-1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }

            // Static evaluation on the positions to the right
            // Two positions to the downward left
            if (row >=2  && move >= 2 && board[row][move] == board[row-2][move-2]
                    && board[row][move] == board[row-1][move-1]){

                if (200 > bestMove){
                    bestMove = 200;
                }
            }

            // three positions to the downward left
            if ((row >= 3 && move >= 3 && board[row][move] == board[row-3][move-3]
                    && board[row][move] == board[row-2][move-2] && board[row][move] == board[row-1][move-1])){

                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }
        }

        // return the best possible point value move from all these moves
        return bestMove;
    }


    /**
     * Creates a deep copy of the game board
     * @param original original 2D array of char that represents our game board
     * @return copy returns the copied game board
     */
    public char[][] copyBoard(char[][] original){

        // creates a new array of equal size
        char[][] copy = original.clone();

        // for each cell in array, clone the contained arrays
        for (int i = 0; i < copy.length; i++){
            copy[i] = original[i].clone();

            // for each cell in nested array, copy primitive char
            for (int j = 0; j < copy[i].length; j++){
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }


    /**
     * pickMove
     * @param state game state being played on
     * @param depth depth of the AB pruning look ahead
     * @param low lower bound for AB pruning
     * @param high higher bound for AB pruning
     * @param view the graphical view the game is displayed on
     *
     */
    public ConnectFourMove pickMove(Connect4State state, int depth, int low, int high, Connect4View view, int GTSValue){

        // holds the move being tested
        ConnectFourMove currentMove;

        // best possible move that can be made on game board
        ConnectFourMove bestMove = new ConnectFourMove(-Integer.MAX_VALUE, 0);
        //the player performing the GTS
        Player player = this;
        //The opposing player

        // get player who's turn it is to play
        int playerToMove = state.getPlayerNum();
            // for each column, test the point value of placing a chip there
            for (int j = 0; j <= 6; j++) {

                // make a new dummy Connect Four game
                ConnectFourGame copy = new ConnectFourGame(playerToMove, state.getPlayers(), view);

                // set the board of the game to be a deep copy of state board
                copy.setBoard(copyBoard(state.getBoard()));

                // if the move can be made in that column
                if (copy.isValidMove(j)){

                    // make move and call static evaluation on that move
                    copy.makeMove(j);
                    currentMove = new ConnectFourMove(staticEvaluation(copy,j), j);

                    for (int i = copy.getBoard().length-1; i >= 0; i--) {
                        System.out.println(copy.getBoard()[i]);
                    }
                    System.out.println();

                    if (state.getPlayerToMove() != player){
                      currentMove.setValue(-currentMove.getValue());
                      }
                      currentMove.setMove(j);
                      GTSValue += currentMove.getValue();

                    // if the game is over
                    if (copy.gameIsOver()){

                        // update current move
                        currentMove = new ConnectFourMove(staticEvaluation(copy,j), j);
                    }

                    // if depth is greater than current move
                    else if (depth > 0){

                        // switch players and recursively call pickMove with values switched for opponent player
                        copy.switchPlayer();
                        currentMove = pickMove(copy, depth - 1, -high, -low, view, GTSValue);

                    } else {
                        currentMove = new ConnectFourMove(GTSValue, j);
                    }

                    // if the current move is better than the best stored move, update it
                    if (currentMove.getValue() > bestMove.getValue()){
                        bestMove = currentMove;

                        // update the low based on the higher of the two values: low and bestMove
                        low = Math.max(low, bestMove.getValue());
                    }
                }
            }


        // return the best move since it has the highest point value
        return bestMove;
    }
}