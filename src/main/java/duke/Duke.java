package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Entry point of the To Do List application.
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private String filePath = "tasks.txt";

    /**
     * Constructor for Duke class, initialises objects
     * for Storage, TaskList and Ui.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
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
        assert ui != null & tasks != null
                & storage != null;
        ui.greeting();
        Scanner scanner = new Scanner(System.in);
        Command command;

        do {
            String input = scanner.nextLine();
            command = parser.parseUserInput(input);
            CommandResult commandResult = command.execute(tasks, storage);
            ui.printMessage(commandResult.getMessageToUser().split("\n"));
        } while (!(command instanceof ExitCommand));
    }

    /**
     * Processes user input from the GUI version of the app and
     * returns a response.
     * @param input User input.
     * @return Response from parsing user input.
     */
    public String getResponse(String input) {
        Command command = parser.parseUserInput(input);
        CommandResult commandResult = command.execute(tasks, storage);
        return "Duke heard: \n" + commandResult.getMessageToUser();
    }

}
