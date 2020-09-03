package duke;

import duke.command.CommandHandler;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.command.CommandHandler;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Represents the task management app.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        this.ui = new Ui();
    }

    /**
     * Runs Duke console mode.
     */
    public void run() {
        ui.printToConsole(ui.printHello());
        String input;
        boolean isRunning = true;

        while (isRunning) {
            input = ui.getCommand();
            CommandHandler commandHandler = new CommandHandler(input, ui, tasks);

            commandHandler.execute();
            storage.saveTasks(tasks.getTasks());
            ui.printToConsole(commandHandler.getLog());

            if (input.equals("bye")) {
                isRunning = false;
            }
        }
        ui.printToConsole(ui.printBye());
    }

    /**
     * Runs Duke GUI mode.
     */
    public String getResponse(String input) {
        CommandHandler commandHandler = new CommandHandler(input, ui, tasks);
        commandHandler.execute();
        return commandHandler.getLog();
    }
}
