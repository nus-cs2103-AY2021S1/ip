package duke;

import duke.commands.Command;
import duke.commands.CommandOutput;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;

import duke.parser.CommandLineInterfaceParser;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.Messages;
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
        ui.print(Messages.WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        String userInput;
        while (!isExit) {
            try {
                if (scanner.hasNextLine()) {
                    userInput = scanner.nextLine();
                } else {
                    userInput = "Bye";
                }
                CommandOutput output = getCommandExecutionOutput(userInput);
                ui.print(output.getCommandOutput());
                isExit = output.isExit();
            } catch (IllegalArgumentException e) {
                ui.print(Colour.convertTextToRed(Messages.INVALID_COMMAND_INPUT_MESSAGE));
            }
        }
        System.exit(0);
    }

    public CommandOutput getCommandExecutionOutput(String userCommand) {
        try {
            Command parsedUserCommand = CommandLineInterfaceParser.parse(userCommand);
            CommandOutput commandOutput = parsedUserCommand.executeCommand(taskManager, storage);
            return commandOutput;
        } catch (DukeException e) {
            return new CommandOutput(e.getMessage(), false);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}



