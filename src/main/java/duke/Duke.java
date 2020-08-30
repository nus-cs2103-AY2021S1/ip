package duke;

import duke.command.Command;
import duke.exception.InvalidInputException;
import duke.exception.InvalidFilePathException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Represents Duke class, which is the control class of Duke and
 * includes the logic of Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes storage, taskList and ui.
     */
    public Duke() {
        try {
            storage = new Storage("data/tasks.txt", "data");
        } catch (InvalidFilePathException e) {
            ui.displayError(e.getMessage());
        }
        taskList = storage.read();
        ui = new Ui(taskList);
    }

    /**
     * Starts the program by executing commands scanned from user inputs.
     */
    public void run() {
        ui.displayGreeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String userCommand = scanner.nextLine();
                Command command = Parser.parse(userCommand);
                command.execute(storage, taskList, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidInputException e) {
                ui.displayError(e.getMessage());
            }
        }
        scanner.close();
    }


    /**
     * Executes the program Duke.
     *
     * @param args user input.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }


}
