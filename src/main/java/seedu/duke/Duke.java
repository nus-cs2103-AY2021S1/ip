package seedu.duke;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.image.Image;



/**
 * The Duke program implements an application that simulates a smart task manager with the name Duke.
 * The program takes in user inputs with various formats and process them as tasks to be finished.
 *
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    //private ScrollPane scrollPane;
    //private VBox dialogContainer;
    //private TextField userInput;
    //private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/jiji.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/totoro.png"));

    /**
     * Constructor of the class.
     * Returns a new Duke object.
     * @param filePath The path or intended path of the data management file
     * @throws IOException If data management file fails to be created or accessed.
     * @throws DukeException If other errors occur.
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructor with no arguments.
     * @throws IOException
     * @throws DukeException
     */
    public Duke() throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage("/Users/chengjiyuqing/Desktop/Year 2 Sem 1/CS2103T/ip/src/main/java/seedu/duke/todo.txt");
        tasks = new TaskList(storage.load());
    }




    /**
     * Runs the Duke program.
     * Prompt user to key in their input with a welcome message
     * @throws DukeException If user input is of incorrect format or other unidentified error occurs
     * @throws IOException If fails to take user input or create/access data management file
     */

    public String welcomeMessage() {
        //welcoming message
        String logo = " ____           _        \n"
                + "|   __  \\_    _|  |  ___ \n"
                + "|  |   |   |  |  |  |  |/ / _ \\\n"
                + "|  |__|   |  |_|  |    <  __/\n"
                + "|_____/\\___/|_|\\_\\__|\n";
        logo = logo + ("Hello! Duke at your service. Please name your request."
                + "\nAll dates should be in the form of YYYY-MM-DD: ");
        return logo;
        // Take user Input
        //ui.takeUserInput(storage);

    }


    /*
     * Creates a new Duke object and runs the program
     * @param args
     * @throws DukeException
     * @throws IOException
     */

    //public static void main(String[] args) throws DukeException, IOException {
    //    new Duke("/Users/chengjiyuqing/Desktop/Year 2 Sem 1/CS2103T/ip/src/main/java/seedu/duke/todo.txt").run();

    //}
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws IOException, DukeException {
        return Parser.parseInput(input,
                new Storage("/Users/chengjiyuqing/Desktop/Year 2 Sem 1/CS2103T/ip/src/main/java/seedu/duke/todo.txt"));
    }
}
