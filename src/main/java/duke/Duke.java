package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.io.IOException;

/**
 * Represents a Duke.
 */
public class Duke {
    private final Ui ui;
    private TaskManager taskManager;
    private boolean isExit;


    /**
     * Class constructor.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.taskManager = new TaskManager();
        } catch (DukeException | IOException exception) {
            System.out.println(exception.getMessage());
            this.taskManager = null;
        }
    }

    /**
     * Run duke.
     */
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        if (duke.taskManager == null) {
            return;
        }
        ui.sendMessage(MessageManager.getGreetMessage());

        while (!isExit) {
            String input = ui.getInput();
            Command command = Parser.parse(input);
            command.execute(taskManager, ui);
            isExit = command.isExit;
        }
    }

}
