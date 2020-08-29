import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.util.Scanner;

/**
 * Main class to run Focus.
 */
public class Duke {
    /**
     * Storage created for user.
     */
    private final Storage storage;
    /**
     * Task list created for user.
     */
    private final TaskList taskList;
    /**
     * UI created to interact with user.
     */
    public static UI ui;

    /**
     * Creates Focus to set up the things needed.
     */
    public Duke() {
        ui = new UI();
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveTextFile()) {
            taskList = new TaskList(storage.loadData());
        } else {
            taskList = new TaskList();
        }
    }

    /**
     * Runs Focus.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetUser();
        String name = sc.nextLine();
        ui.addressUser(name);

        boolean exit = false;
        String input;
        while (!exit) {
            input = sc.nextLine();
            Command command = Parser.parse(input);
            exit = command.isExit();
            ui.printDivider();
            try {
                command.execute(input, taskList, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                if (!exit) {
                    ui.printDividerWithSpacing();
                }
            }
        }
        ui.exitFocus();
        sc.close();
    }

    /**
     * Gets response from Focus based on user's input.
     * @param input User's input.
     * @return Focus' response.
     */
    public String getResponse(String input) {
        boolean exit;
        Command command = Parser.parse(input);
        exit = command.isExit();
        if (exit) {
            return ui.exitFocus();
        }
        try {
            return command.execute(input, taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Entry point for Focus to start.
     * @param args Args.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}