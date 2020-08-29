package duke;

import ui.Ui;
import command.Command;
import exception.InvalidUsageException;
import exception.StorageException;
import exception.UnknownCommandException;

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
    }
    
    /**
     * Main method to start the application
     */
    public void start() {
        try {
            this.taskList = new TaskList(storage.load());
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
        } catch (StorageException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            command.execute(taskList, ui, storage);
        } catch (InvalidUsageException | UnknownCommandException ex) {
            ui.print(ex.getMessage());
        } finally {
            ui.buildChatFence();
        }
        return "Duke heard: " + input;
    }
}
