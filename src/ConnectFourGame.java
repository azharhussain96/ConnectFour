/**
 * Created by azharhussain on 2/27/16.
 */
public class ConnectFourGame implements Connect4State {

    private char [][] board = new char[ROWS][COLS];
    private Player [] players;
    private int playerToMoveNum = 0;
    private Connect4View gameView;

    public ConnectFourGame(int playerNum, Player[] player, Connect4View view){
        playerToMoveNum = playerNum;
        players = player;
        gameView = view;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '.';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayerNum() {
        return playerToMoveNum;
    }

    public Player getPlayerToMove(){
        return players[playerToMoveNum];
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

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

    public void makeMove(int col){
        for (int i = 0; i < ROWS; i++){
            if (board[i][col] == (EMPTY)){
                if (playerToMoveNum == 0){
                    board[i][col] = CHECKERS[0];
                    break;
                } else {
                    board[i][col] = CHECKERS[1];
                    break;
                }
            }
        }
    }

    public boolean isFull(){
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                if (board[i][j] == EMPTY){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean notEmpty(int i, int j){
        return board[i][j] != EMPTY;
    }

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

    public boolean gameIsOver(){
        if (isFull()){
            return true;
        } else if (isWinner())
            return true;
        else
            return false;
    }

    public void switchPlayer(){
        if (playerToMoveNum == 0){
            playerToMoveNum = 1;
        } else {
            playerToMoveNum = 0;
        }
    }
}
