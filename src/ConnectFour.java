/**
 * ConnectFour
 * Creates a Connect Four game that can be played by computer or human
 * @author Azhar Hussain
 * @author Alec Cobban
 * 3/6/16
 */
public class ConnectFour {

    /**
     * Makes the player which will be playing the game
     * @param view view of the game board that is depicted
     * @param player name of player
     * @return player objectfor the game
     */
    public static Player makePlayer(Connect4View view, String player){

        // request user to enter the name of the player
        String playerName = view.getAnswer("Enter the name of the " + player + " player." +
                "\n(Include 'Computer' in the name of a computer player)");

        // if the player included the word computer in the name
        if (playerName.contains("Computer")){

            // ask how deep the computer should look ahead for moves
            int depth = view.getIntAnswer("How far should I look ahead");

            // create new computer player
            return new ComputerConnectFourPlayer(playerName, depth);
        } else {

            // create new human player
            return new HumanConnectFourPlayer(playerName);
        }
    }

    /**
     * Main method to test the game
     */
    public static void main(String args[]){

        // create player array which holds two players
        Player[] players = new Player[2];

        // creates a view to display the game
        Connect4View view = new Connect4ViewGraphical();

        // assign position to each player
        players[0] = makePlayer(view, "First");
        players[1] = makePlayer(view, "Second");

        // create a new game state which initializes an empty board
        ConnectFourGame state = new ConnectFourGame(0, players, view);

        // display the game view to show user
        view.display(state);

        // while the game is not done
        while (!state.gameIsOver()){

            // ask player what move they wish to make
            int move = state.getPlayerToMove().getMove(state, view);

            // what do it if the move is not valid
            while (!state.isValidMove(move)) {
                move = state.getPlayerToMove().getMove(state, view);
            }

            // make the move on the game state
            state.makeMove(move);

            // display the updated game state
            view.display(state);

            // switch players to the opposite player
            state.switchPlayer();
        }

        // if the game has been won
        if (state.isWinner()) {

            // switch players again to switch back to winning player
            state.switchPlayer();

            // let the players know that a winner exists
            view.reportToUser(players[state.getPlayerNum()].getName() + " is the winner!");
        } else if (state.isFull()){

            // if the game board is full and no winner has been picked, then the game is a stalemate
            view.reportToUser("Looks like a stalemate!");
        }
    }
}
