package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

/**
 * A command dealing with listing tasks from the task-list.
 */
public class ListCommand extends Command {
    private CommandType commandType;

    /**
     * Constructs a new ListCommand object of the specified CommandType.
     *
     * @param commandType The type of the ListCommand.
     */
    public ListCommand(CommandType commandType) {
        this.commandType = commandType;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] lines = tasks.getTasks();
        if (lines.length > 0) {
            ui.listNumberedTasks(lines);
        } else {
            ui.setNoTasksMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return commandType.toString();
    }
}

