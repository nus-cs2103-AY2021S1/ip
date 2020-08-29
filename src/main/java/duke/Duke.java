package duke;

import duke.Command.Command;

import duke.Exception.DukeException;

import duke.Parser.Parser;

import duke.Task.TaskList;

import duke.Ui.Message;
import duke.Ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this(Storage.STORAGE_FILEPATH);
    }

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(Message.MESSAGE_LOADING_ERROR);
            this.tasks = new TaskList();
        }
    }

    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke("./data/duke.txt").run();
    }

    private void run() {
        ui.printResponse(Message.MESSAGE_WELCOME);
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.printResponse(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printResponse(Message.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generate a response to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return Message.showError(e.getMessage());
        }
    }
}