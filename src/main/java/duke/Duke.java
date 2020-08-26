package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.commands.Storage;
import duke.ui.Ui;
import duke.exceptions.DukeException;
import duke.task.TaskList;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke() {
        storage = Storage.dbInstance();
        taskList = storage.getTaskListFromDatabase();
        ui = new Ui();
    }


    public void start() {
        ui.printWelcomeMessage();
        ui.printDatabaseTasks(taskList);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
