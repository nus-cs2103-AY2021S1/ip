package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.io.IOException;

public class Duke {
    private TaskManager taskManager;
    private Ui ui;
    private boolean isExit;


    public Duke() {
        this.ui = new Ui();
        try {
            this.taskManager = new TaskManager();
        } catch (DukeException | IOException exception) {
            System.out.println(exception.getMessage());
            this.taskManager = null;
        }
    }

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

    private void listTasks(Duke duke) {
        String message = MessageManager.getListMessage(taskManager);
        ui.sendMessage(message);
    }

}
