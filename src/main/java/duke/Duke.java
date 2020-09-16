package duke;

import duke.commands.Command;
import duke.commands.CommandHandler;
import duke.tasks.TaskManager;

/**
 * Main class that will oversee the running of the program.
 */
public class Duke {
    private Ui ui;
    private TaskManager taskManager;
    private CommandHandler commandHandler;
    private Command cmd;

    Duke() {
        this.ui = new Ui();
        this.commandHandler = new CommandHandler();
        try {
            this.taskManager = new Storage().load(new TaskManager());
        } catch (DukeException e) {
            System.out.println("OOPS error loading storage: " + e.getMessage());
        }
    }

    /**
     * Returns a response depending on the user input.
     * @param input user input.
     * @return the response.
     */
    public String getResponse(String input) {
        // If a command has not been set yet or if the ongoing command has been completed
        if (cmd == null || cmd.isDone()) {
            Command newCmd = commandHandler.parseCommand(input);
            newCmd.init(taskManager, ui);
            setCommand(newCmd);
            return newCmd.toString();
        } else {
            try {
                cmd.execute(input);
                return cmd.toString();
            } catch (DukeException e) {
                return "OOPS! Something went wrong while executing:\n" + e.getMessage();
            }
        }
    }

    private void setCommand(Command cmd) {
        this.cmd = cmd;
    }
}
