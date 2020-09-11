package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import java.util.ArrayList;

/**
 * A command dealing with search queries on the tasks in the task-list.
 */
public class QueryCommand extends Command {
    private CommandType commandType;
    private String searchQuery;

    /**
     * Constructs a new QueryCommand of the specified CommandType containing the keyword for searching.
     *
     * @param commandType The type of the QueryCommand.
     * @param searchQuery The keyword to be used for searching.
     */
    public QueryCommand(CommandType commandType, String searchQuery) {
        this.commandType = commandType;
        this.searchQuery = searchQuery;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] lines = tasks.getTasks();
        ArrayList<String> finds = new ArrayList<>();
        for (String line : lines) {
            if (line.toLowerCase().contains(searchQuery)) {
                finds.add(line);
            }
        }

        if (finds.size() > 0) {
            ui.listNumberedFoundTasks(finds.toArray(new String[1]));
        } else {
            ui.setNoFoundTasksMessage(searchQuery);
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
