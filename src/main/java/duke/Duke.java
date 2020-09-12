package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Platform;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        this("tasks.txt");
    }

    /**
     * Constructs an instance of Duke.
     *
     * @param filePath The path to save the tasks to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Runs the command-line version of the application.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet();
        boolean shouldLoop = true;
        while (shouldLoop) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parse(input);
                command.executeWithoutResponse(tasks, ui, storage);
                shouldLoop = command.shouldLoop();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Returns Duke's response to a command after executing it.
     *
     * @param input User command.
     * @return Duke's response to the command.
     */
    public String getResponse(String input) {
        String response;

        try {
            Command command = Parser.parse(input);
            response = command.executeWithResponse(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }

        return response;
    }
}
