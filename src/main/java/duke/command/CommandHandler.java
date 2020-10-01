package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.MagicStrings;
import duke.util.Parser;

/**
 * Handles user's input as command to be executed.
 */
public class CommandHandler {
    private Ui ui;
    private TaskList tasks;
    private StringBuilder log;
    private String command;

    /**
     * Constructor
     *
     * @param command User's command input.
     * @param ui Duke's UI.
     * @param tasks Duke's Task List.
     */
    public CommandHandler(String command, Ui ui, TaskList tasks) {
        this.command = command;
        this.ui = ui;
        this.tasks = tasks;
        this.log = new StringBuilder();
    }

    /**
     * Executes user's commands and updates the activity log.
     */
    public void execute() {
        try {
            String[] split = Parser.splitCommand(command);

            switch (split[0]) {
            case "list":
                log.append(ui.printTasks(tasks, false));
                break;
            case "done":
                log.append(tasks.markTaskAsDone(command, ui));
                break;
            case "todo":
            case "deadline":
            case "event":
                log.append(tasks.addTask(command, ui));
                break;
            case "delete":
                log.append(tasks.deleteTask(command, ui));
                break;
            case "find":
                log.append(ui.printTasks(tasks.findTask(command), true));
                break;
            case "bye":
                log.append(ui.printBye());
                break;
            case "edit":
                log.append(tasks.editTask(command, ui));
                break;
            default:
                throw new DukeException(MagicStrings.ERROR_COMMAND_FORMAT_INCORRECT);
            }
        } catch (DukeException e) {
            log.append(e.getMessage());
        }

        assert log.length() != 0;
    }

    /**
     * @return Activity log.
     */
    public String getLog() {
        return log.toString();
    }
}
