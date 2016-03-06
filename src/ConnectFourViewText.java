import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by azharhussain on 3/6/16.
 */
public class ConnectFourViewText implements Connect4View {
    private Scanner input;
    public ConnectFourViewText() {
        input = new Scanner(System.in);
    }

    /**
     * Displays the current board
     * @param state current state of the game
     */
    public void display (Connect4State state) {
    char[][] board = state.getBoard();
        System.out.println(state.getPlayers()[0].getName() + " = X.");
        System.out.println(state.getPlayers()[1].getName() + " = O.");
        System.out.println(" '.' represents an empty space.");

        for (int i = board.length-1; i >= 0; i--) {
            System.out.println(board[i]);
        }
        System.out.println();

    }

    /**
     * Asks the user for a move
     * The move will be in the range 0 to Connect4State.COLS-1.
     * @param state current state of the game
     * @return the number of the move that player chose
     */
    public int getUserMove(Connect4State state) {
        int column;
        char[][] board = state.getBoard();
        System.out.println();
        column = getIntAnswer(state.getPlayerToMove().getName() + " pick a column to place a piece");
        while (!state.isValidMove(column)) {
            System.out.println("Illegal move, try again.");
            column = getIntAnswer(state.getPlayerToMove().getName() + " pick a column to place a piece");
        }

        return column;
    }

    /**
     * Reports the move that a player has made.
     * The move should be in the range 0 to Connect4State.COLS-1.
     * @param chosenMove the move to be reported
     * @param name the player's name
     */
    public void reportMove (int chosenMove, String name) {
        System.out.println(name + " placed a chip in column " + chosenMove);
        System.out.println();
    }

    /**
     * Ask the user the question and return the answer as an int
     * @param question the question to ask
     * @return The depth the player chose
     */
    public int getIntAnswer (String question) {
        int answer = 0;
        boolean valid = false;

        while(!valid) {
            try {
                reportToUser(question);
                answer = input.nextInt();
                valid = true;
            } catch (InputMismatchException ex) {
                reportToUser("Not a valid integer");
                valid = false;
                input.nextLine();
                System.out.println(question + " ");
            }
        }

        input.nextLine();
        return answer;
    }


    /**
     * Convey a message to user
     * @param message the message to be reported
     */
    public void reportToUser(String message) {
        System.out.println(message);
    }

    /**
     * Ask the question and return the answer
     * @param question the question to ask
     * @return the answer to the question
     */
    public String getAnswer(String question) {
        System.out.println(question);
        return input.nextLine();
    }
}
