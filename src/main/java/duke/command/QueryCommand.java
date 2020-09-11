package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import java.util.ArrayList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] lines = tasks.getTasks();

        if (commandType.equals(CommandType.LIST)) {
            if (lines.length > 0) {
                return ui.listNumberedTasks(lines);
            }
            return ui.getNoTasksMessage();
        }

        ArrayList<String> finds = new ArrayList<>();
        for (String line : lines) {
            if (line.toLowerCase().contains(String.format(".*%s.*", searchQuery))) {
                finds.add(line);
            }
        }

        if (finds.size() == 0) {
            return ui.getNoFoundTasksMessage(searchQuery);
        }

        return ui.listNumberedFoundTasks(finds.toArray(new String[1]));
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
