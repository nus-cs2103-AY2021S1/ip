/**
 * Main body of application Duke
 */

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises the application
     */
    private void start() {
        this.storage = new Storage("Data.txt");
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(storage, ui, tasks);
        this.storage.init();
        storage.importSavedDataToList(tasks.get());
        ui.greet();
    }

    /**
     * Sends the user input to the parser for processing
     *
     * @param input is the String input from the user
     */
    private void processInput(String input) throws DukeException {
        this.parser.processInput(input);
    }

    /**
     * Main body of application
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input;
        duke.start();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            try {
                duke.processInput(input);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
