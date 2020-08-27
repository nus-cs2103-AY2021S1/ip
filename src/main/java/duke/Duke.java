package duke;

import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        this.ui = new Ui();
    }

    /**
     * Runs and serves user.
     */
    public void run() {
        ui.printHello();
        String command = ui.getCommand();

        while (!command.equals("bye")) {
            try {
                String[] split = command.split(" ", 2);

                switch (split[0]) {
                case "list":
                    ui.printTasks(tasks, false);
                    break;
                case "done":
                    tasks.markTaskAsDone(Integer.parseInt(split[1]) - 1);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    tasks.addTask(command);
                    break;
                case "delete":
                    tasks.deleteTask(Integer.parseInt(split[1]) - 1);
                    break;
                case "find":
                    ui.printTasks(tasks.findTask(split[1]), true);

                    break;
                default:
                    throw new DukeException("\tApologies! I do not understand what that means :')");
                }

                command = ui.getCommand();
                storage.saveTasks(tasks.getTasks());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        ui.printBye();
    }
}
