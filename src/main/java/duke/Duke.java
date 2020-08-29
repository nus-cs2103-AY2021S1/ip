package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {

    private Storage store;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private Duke(Storage store, TaskList taskList, Ui ui, Parser parser) {
        this.store = store;
        this.taskList = taskList;
        this.ui = ui;
        this.parser = parser;
    }

    public static Duke startDuke() {
        Storage store = new Storage();
        TaskList taskList = new TaskList();
        Ui ui =  new Ui();
        Parser parser = new Parser();
        try {
            store.initializeStorage();
            taskList = new TaskList(store.getTasks());
        } catch (IOException e) {
            System.out.println("Error connecting to storage, actions made will not be saved");
        }
        return new Duke(store, taskList, ui, parser);
    }

    public void start() {
        ui = new Ui();
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                store.initializeStorage();
                taskList = new TaskList(store.getTasks());
                Command c = parser.parse(ui.readCommand());
                c.execute(taskList, ui, store);
                isRunning = !c.isExit();
            } catch (IOException e) {
                System.out.println("Error connecting to storage, actions made will not be saved");
            } catch (DukeException e) {
                ui.printMessage(e.getFriendlyMessage());
            }
        }
        ui.stopReading();
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(ui.readCommand());
            c.execute(taskList, ui, store);
        } catch (DukeException e) {
            return e.getFriendlyMessage();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
