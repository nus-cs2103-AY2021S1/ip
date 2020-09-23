package chatbot.commands;

import java.util.ArrayList;
import java.util.Comparator;

import chatbot.data.Task;
import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

/**
 * A command that sorts and returns user tasks chronologically.
 */

public class SortCommand extends Command {

    private final Comparator<Task> comparator;

    public SortCommand(Comparator<Task> comp) {
        this.comparator = comp;
    }

    public Comparator<Task> getComparator() {
        return comparator;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.retrieveTasksByOrder(comparator);
        return ui.list(tasks);
    }
}
