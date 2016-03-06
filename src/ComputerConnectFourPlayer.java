import java.lang.reflect.Array;

/**
 * Created by azharhussain on 2/28/16.
 */
public class ComputerConnectFourPlayer extends Player{

    private int depth;

    public ComputerConnectFourPlayer(String name, int lookAhead){
        super(name);
        depth = lookAhead;

    }

    public String getName(){
        return super.getName();
    }

    public int getMove(Connect4State state, Connect4View view){
        int move = pickMove(state, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE, view).getMove();
        view.reportMove(move, getName());

        return move;
    }

    public int staticEvaluation(Connect4State state, int move){
        char [][] board = state.getBoard();
        int row = 0;
        int bestMove = 0;

        if (state.isValidMove(move)){
            for (int i = 0; board[i][move] != '.'; i++){
                row = i;
            }

            // to the right
            if (move <= 5 && board[row][move] == board[row][move+1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
                if (move <= 4 && board[row][move] == board[row][move+2]){
                    if (200 > bestMove){
                        bestMove = 200;
                    }

                    if ((move <= 3 && board[row][move] == board[row][move+3])){
                        if (1000 > bestMove){
                            bestMove = 1000;
                        }
                    }
                }
            }

            // to the left
            if (move >= 1 && board[row][move] == board[row][move-1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
                if (move >= 2 && board[row][move] == board[row][move-2]){
                    if (200 > bestMove){
                        bestMove = 200;
                    }

                    if ((move >= 3 && board[row][move] == board[row][move-3])){
                        if (1000 > bestMove){
                            bestMove = 1000;
                        }
                    }
                }
            }

            // build up
            if (row >= 1 && row <= 3 && board[row][move] == board[row-1][move]){
                if (100 > bestMove){
                    bestMove = 100;
                }
                }
            if (row >= 2 && board[row][move] == board[row-2][move] && board[row][move] == board[row-1][move]){
                if (200 > bestMove){
                    bestMove = 200;
                }
            }
            if ((row >= 3 && board[row][move] == board[row-3][move] && board[row][move] == board[row-2][move] && board[row][move] == board[row-1][move])){
                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            if ((row == 5 && board[row][move] == board[row-3][move] && board[row][move] == board[row-2][move] && board[row][move] == board[row-1][move])){
                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            // up diag right
            if (row <= 4 && move <= 5 && row >= 2 && board[row][move] == board[row+1][move+1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }
            if (row <= 3 && move <= 4 && row >= 1 && board[row][move] == board[row+2][move+2] && board[row][move] == board[row+1][move+1]){
                if (200 > bestMove){
                    bestMove = 200;
                }
            }

            if ((row <= 2 && move <= 3 && row >= 0 && board[row][move] == board[row+3][move+3] && board[row][move] == board[row+2][move+2] && board[row][move] == board[row+1][move+1])){
                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            // up diag left
            if (row <= 4 && move >= 1 && board[row][move] == board[row+1][move-1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }
            if (row <= 3 && move >= 2 && board[row][move] == board[row+2][move-2] && board[row][move] == board[row+1][move-1]){
                if (200 > bestMove){
                    bestMove = 200;
                }
            }
            if ((row <= 2 && move >= 3 && board[row][move] == board[row+3][move-3] && board[row][move] == board[row+2][move-2] && board[row][move] == board[row+1][move-1])){
                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }

            //down diag right
            if (row >= 1 && move <= 5 && board[row][move] == board[row-1][move+1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }
            if (row >=2  && move <= 4 && board[row][move] == board[row-2][move+2] && board[row][move] == board[row-1][move+1]){
                if (200 > bestMove){
                    bestMove = 200;
                }
            }
            if ((row >= 3 && move <= 3 && board[row][move] == board[row-3][move+3] && board[row][move] == board[row-2][move+2] && board[row][move] == board[row-1][move+1])){
                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }
            //down diag left
            if (row >= 1 && move >= 1 && board[row][move] == board[row-1][move-1]){
                if (100 > bestMove){
                    bestMove = 100;
                }
            }
            if (row >=2  && move >= 2 && board[row][move] == board[row-2][move-2] && board[row][move] == board[row-1][move-1]){
                if (200 > bestMove){
                    bestMove = 200;
                }
            }
            if ((row >= 3 && move >= 3 && board[row][move] == board[row-3][move-3] && board[row][move] == board[row-2][move-2] && board[row][move] == board[row-1][move-1])){
                if (1000 > bestMove){
                    bestMove = 1000;
                }
            }
        }

        return bestMove;
    }


    public char[][] copyBoard(char[][] original){
        char[][] copy = original.clone();
        for (int i = 0; i < copy.length; i++){
            copy[i] = original[i].clone();
            for (int j = 0; j < copy[i].length; j++){
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }



    public ConnectFourMove pickMove(Connect4State state, int depth, int low, int high, Connect4View view){
        ConnectFourMove currentMove;
        ConnectFourMove bestMove = new ConnectFourMove(-Integer.MAX_VALUE, 0);

        int playerToMove = state.getPlayerNum();

            for (int j = 0; j < 6; j++) {
                ConnectFourGame copy = new ConnectFourGame(playerToMove, state.getPlayers(), view);
                copy.setBoard(copyBoard(state.getBoard()));
                if (copy.isValidMove(j)){

                    copy.makeMove(j);
                    currentMove = new ConnectFourMove(staticEvaluation(copy,j), j);
                    high += currentMove.getValue();
                    if (currentMove.getValue() > low){
                        low = currentMove.getValue();
                        bestMove = currentMove;
                    }

                    if (copy.gameIsOver()){
                        currentMove = new ConnectFourMove(staticEvaluation(copy,j), j);
                        return currentMove;
                    }

                    else if (depth > 0){
                        copy.switchPlayer();
                        currentMove = pickMove(copy, depth - 1, -high, -low, view);
                        currentMove.setValue(-currentMove.getValue());
                        currentMove.setMove(j);
                    } else {
                        currentMove = new ConnectFourMove(staticEvaluation(copy, j), j);
                    }

                    if (currentMove.getValue() > bestMove.getValue()){
                        bestMove = currentMove;
                        low = Math.max(low, bestMove.getValue());
                    }
                }
            }


        return bestMove;
    }
}