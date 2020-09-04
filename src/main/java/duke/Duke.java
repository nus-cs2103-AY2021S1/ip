package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Dukenizer program implements a Task Manager application. It performs task manipulations
 * based on user commands. It consists of a TaskList object to store your tasks, a Ui object
 * to handle user interactions and a Storage object to save and retrieve tasks in a list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object from a specified filePath. If a valid .txt file containing
     * a TaskList is found, it will be loaded. Otherwise, a new TaskList object is created
     * to store the tasks.
     *
     * @param filePath Relative filepath from project source.
     */
    public Duke(String filePath) {

        //initialize User interface
        ui = new Ui();

        //Initialize Storage location
        storage = new Storage(filePath);

        //Initialize TaskList
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            storage.writeToFile(storage.getPath().toString(), "");
        }

    }


    /**
     * Main program loop until termination when "bye" is called by the user.
     */
    public void run() {

        //print greeting message
        ui.printGreeting();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printDivider();
                System.out.println();
            }
        }

    }


    public static void main(String[] args) {
        new Duke("DukenizerTaskList.txt").run();
    }

    /**
     * Returns Greeting message to user.
     *
     * @return Greeting message.
     */
    public String getGreeting() {
        return ui.printGreeting();
    }

    /**
     * Returns a String response to the user's input.
     *
     * @param input User input.
     * @return String response from program.
     */
    public String getResponse(String input) {

        String output = "";

        try {
            Command c = Parser.parse(input);
            output += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            output += ui.showError(e.getMessage());
        }

        return output;
    }
}
