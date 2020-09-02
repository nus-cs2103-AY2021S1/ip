package duke;

import duke.tasks.Task;

public class Duke {
    static final String SAVE_FILE = "save.txt";

    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(SAVE_FILE);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showException(e);
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {

        ParsedCommand command;

        try {
            command = Parser.parseNextCommand(input);
        } catch (DukeException e) {
            return Ui.showException(e);
        }

        switch (command.getType()) {
        case "bye":
            storage.save(tasks);
            return Ui.showExit();
        case "list":
            return Ui.showList(tasks);
        case "done":
            Task doneTask = tasks.done(command.getIndex());
            return Ui.showDone(doneTask);
        case "delete":
            Task deletedTask = tasks.delete(command.getIndex());
            return Ui.showDelete(deletedTask);
        case "todo":
        case "deadline":
        case "event":
            try {
                Task addedTask = command.toTask();
                tasks.addTask(addedTask);
                return Ui.showAddTask(addedTask);
            } catch (DukeException e) {
                return Ui.showException(e);
            }
        case "find":
            TaskList foundTasks = tasks.find(command.getName());
            return Ui.showFound(foundTasks);
        default:
            return Ui.showException(new DukeException("Duke did not receive a matching command type."));
        }

    }
}
