package duke.command;

import duke.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.util.Strings;

public class CommandHandler {
    private Ui ui;
    private TaskList tasks;
    private StringBuilder log;
    private String command;
    public CommandHandler(String command, Ui ui, TaskList tasks) {
        this.command = command;
        this.ui = ui;
        this.tasks = tasks;
        this.log = new StringBuilder();
    }

    public void execute() {
        try {
            String[] split = command.split(" ", 2);

            switch (split[0]) {
            case "list":
                log.append(ui.printTasks(tasks, false));
                break;
            case "done":
                int index = Integer.parseInt(split[1]) - 1;
                tasks.markTaskAsDone(index);
                log.append(ui.printTaskAsDone(tasks.getTaskAtIndex(index)));
                break;
            case "todo":
            case "deadline":
            case "event":
                tasks.addTask(command);
                log.append(ui.printTaskAdded(tasks.getTaskAtIndex(tasks.getLength()-1)));
                break;
            case "delete":
                index = Integer.parseInt(split[1]) - 1;
                tasks.deleteTask(index);
                log.append(ui.printTaskDeleted(tasks.getTaskAtIndex(index)));
                break;
            case "find":
                log.append(ui.printTasks(tasks.findTask(split[1]), true));
                break;
            case "bye":
                log.append(ui.printBye());
                break;
            default:
                throw new DukeException(Strings.ERROR_COMMAND_FORMAT_INCORRECT);
            }
        } catch (DukeException e) {
            log.append(e.getMessage());
        }
    }

    public String getLog() {
        return log.toString();
    }
}

