package duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles interaction with user.
 * This class prompts user to input their message and react accordingly.
 */
public class Ui {
    Scanner sc;
    //Object to store the list
    ArrayList<Task> itemList = new ArrayList<>();
    //create file
    String path = "out/todo.txt";

    /**
     * Constructor of the class.
     * Creates a new Ui object with a Scanner.
     * @throws DukeException For invalid user inputs.
     * @throws IOException For file errors.
     */
    public Ui() throws DukeException, IOException {
        this.sc = new Scanner(System.in);
    }



    /**
     * Handles user input.
     * Passes valid user input to Parser class for further process.
     * @param storage A Storage object that handles data storage.
     * @throws IOException
     * @throws DukeException
     */
    public void takeUserInput(Storage storage) throws IOException, DukeException {
        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();
            //exit
            if (userMessage.equals("bye")) {
                System.out.println("Bye! Nice serving you. Hope to see you again soon! :D");
                break;
            }
            Parser.parseInput(userMessage, storage);
        }
    }

}
