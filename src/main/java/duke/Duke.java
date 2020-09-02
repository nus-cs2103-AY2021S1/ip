package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Scanner;

/**
 * Entry point of the To Do List application.
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "data/tasks.txt";

    /**
     * Constructor for Duke class, initialises objects
     * for Storage, TaskList and Ui.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Prints the welcome message, takes in and parses user input.
     */
    public void run() {
        ui.greeting();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            Parser.parseUserInput(input, ui, tasks, storage);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
