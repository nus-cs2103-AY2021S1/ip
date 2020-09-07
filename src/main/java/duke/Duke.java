package duke;

import duke.commands.Command;
import duke.commands.CommandOutput;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;

import duke.parser.CommandLineInterfaceParser;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Ui;

import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskManager taskManager;

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



