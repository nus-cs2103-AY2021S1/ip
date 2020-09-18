package seedu.duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles interaction with user.
 * This class prompts user to input their message and react accordingly.
 */
public class Ui {
    private Scanner sc;
    //Object to store the list
    private ArrayList<Task> itemList = new ArrayList<>();
    //create file
    private String path = "/Users/chengjiyuqing/Desktop/Year 2 Sem 1/CS2103T/ip/src/main/java/seedu/duke/todo.txt";


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
    public String takeUserInput(Storage storage) throws IOException, DukeException {
        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();
            //exit
            if (userMessage.equals("bye")) {
                return "Bye! Nice serving you. Hope to see you again soon! :D";
            }
            return Parser.parseInput(userMessage, storage);
        }
        return "";
    }
}
