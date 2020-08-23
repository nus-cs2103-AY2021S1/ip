package duke;

import duke.tasks.Task;

public class Duke {
    static final String SAVE_FILE = "save.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        parser = new Parser(System.in);
        storage = new Storage(SAVE_FILE);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showException(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showWelcome();

        ParsedCommand command;
        while (true) {
            try {
                command = parser.parseNextCommand();
            } catch (DukeException e) {
                Ui.showException(e);
                continue;
            }

            switch (command.getType()) {
            case "bye":
                storage.save(tasks);
                Ui.showExit();
                return;
            case "list":
                Ui.showList(tasks);
                break;
            case "done":
                Task doneTask = tasks.done(command.getIndex());
                Ui.showDone(doneTask);
                break;
            case "delete":
                Task deletedTask = tasks.delete(command.getIndex());
                Ui.showDelete(deletedTask);
            case "todo":
            case "deadline":
            case "event":
                try {
                    Task addedTask = command.toTask();
                    tasks.addTask(addedTask);
                    Ui.showAddTask(addedTask);
                } catch (DukeException e) {
                    Ui.showException(e);
                }
                break;
            default:
                Ui.showException(new DukeException("Duke did not receive a matching command type."));
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
