package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Duke Chatbot which serves as the entry point for the whole program. The main business logic
 * is conducted by this class, which ties in functionality from all classes to run the whole programme.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    /**
     * Creates a Duke object that initialises the necessary variables for the execution of the Duke program.
     * @param filePath a relative file path giving the location to the data stored in the hard disk
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        sc = new Scanner(System.in);
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The method responsible for the main logic of the program.
     */
    public void run() {
        ui.displayGreeting();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                parser.parse(input).execute(tasks, ui);
            } catch (DukeException e) {
                System.err.println(e);
            }
            input = sc.nextLine();
        }

        ui.displayExit();

        try {
            storage.storeTaskList(tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * The starting point for the program.
     * @param args arguments to be passed to the main method. Any array can be used with no change in effect.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}