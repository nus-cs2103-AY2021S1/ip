package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import java.util.ArrayList;

public class QueryCommand extends Command {
    private CommandType commandType;
    private String searchQuery;

    public QueryCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    public QueryCommand(CommandType commandType, String searchQuery) {
        this.commandType = commandType;
        this.searchQuery = searchQuery;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] lines = tasks.getTasks();
        if (commandType.equals(CommandType.LIST)) {
            ui.showTasks(lines);
        } else {
            ArrayList<String> finds = new ArrayList<>();
            for (String line : lines) {
                if (line.toLowerCase().contains(String.format(".*%s.*", searchQuery))) {
                    finds.add(line);
                }
            }
            ui.showFoundTasks((String[]) finds.toArray());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return this.commandType.toString();
    }
}
