package duke;

import command.Command;
import command.CommandResult;
import exception.InvalidUsageException;
import exception.StorageException;
import exception.UnknownCommandException;
import ui.Ui;

/**
 * Main class for the Duke application
 */
public class Duke {
    // current task list
    private TaskList taskList;
    // storage file
    private final Storage storage;
    // text ui interface
    private final Ui ui;

    /**
     * Constructor for Duke application
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to start the application
     */
    public void start() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            ui.buildChatFence();

            // handle commands
            try {
                Command command = Parser.parseCommand(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (InvalidUsageException | UnknownCommandException ex) {
                ui.print(ex.getMessage());
            } finally {
                ui.buildChatFence();
            }
        }
    }

    public String getResponse(String input) {
        CommandResult response;
        try {
            Command command = Parser.parseCommand(input);
            response = command.execute(taskList, ui, storage);
        } catch (InvalidUsageException | UnknownCommandException ex) {
            ui.print(ex.getMessage());
            response = new CommandResult(ex.getMessage());
        } finally {
            ui.buildChatFence();
        }
        return response.getResult();
    }
}
