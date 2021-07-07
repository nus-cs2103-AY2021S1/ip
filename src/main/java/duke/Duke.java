package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.commands.CommandOutput;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;

import duke.parser.CommandLineInterfaceParser;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Ui;

/**
 * It is the main class that runs the entire command line interface application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskManager taskManager;

    /**
     * Creates a new {@code Duke} object which contains the {@code TaskManager} object to handle the actions
     * with regards to the {@code Task} objects, the {@code Storage} object to store the relevant task information to
     * ensure the information persists and {@code Ui} object to format the output.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            this.taskManager = storage.load();
        } catch (InvalidFilePathException e) {
            ui.print(e.getMessage());
        } catch (StorageOperationException e) {
            ui.print(e.getMessage());
        }
    }

    /**
     * Runs the program until the user inputs a command to terminate the program.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        String userInput;
        while (!isExit) {
            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
            } else {
                userInput = "Bye";
            }
            CommandOutput output = getCommandExecutionOutput(userInput);
            ui.print(output.getCommandOutput());
            isExit = output.isExit();
        }
        scanner.close();
        System.exit(0);
    }

    public CommandOutput getCommandExecutionOutput(String userCommand) {
        try {
            Command parsedUserCommand = CommandLineInterfaceParser.parse(userCommand);
            CommandOutput commandOutput = parsedUserCommand.executeCommand(taskManager);
            storage.save(taskManager);
            return commandOutput;
        } catch (StorageOperationException e) {
            return new CommandOutput(e.getMessage(), false);
        } catch (DukeException e) {
            return new CommandOutput(e.getMessage(), false);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}



