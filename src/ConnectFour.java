/**
 * Created by azharhussain on 2/27/16.
 */
public class ConnectFour {

    public static Player makePlayer(Connect4View view, String player){
        String playerName = view.getAnswer("Enter the name of the " + player + " player." +
                "\n(Include 'Computer' in the name of a computer player)");

        if (playerName.contains("Computer")){
            int depth = view.getIntAnswer("How far should I look ahead");
            return new ComputerConnectFourPlayer(playerName, depth);
        } else {
            return new HumanConnectFourPlayer(playerName);
        }
    }


    public static void main(String args[]){
        Player[] players = new Player[2];
        Connect4View view = new ConnectFourViewText();
        ComputerConnectFourPlayer comp = new ComputerConnectFourPlayer("Computer", 4);

        players[0] = makePlayer(view, "First");
        players[1] = makePlayer(view, "Second");

        ConnectFourGame state = new ConnectFourGame(0, players, view);

        view.display(state);

        while (!state.gameIsOver()){
            int move = state.getPlayerToMove().getMove(state, view);


            while (!state.isValidMove(move)) {
                move = state.getPlayerToMove().getMove(state, view);
            }

            state.makeMove(move);
            comp.staticEvaluation(state, move);
            view.display(state);
            state.switchPlayer();
        }

        if (state.isWinner()) {
            state.switchPlayer();
            view.reportToUser(players[state.getPlayerNum()].getName() + " is the winner!");
        } else {
            view.reportToUser("Looks like a stalemate!");
        }
    }
}
