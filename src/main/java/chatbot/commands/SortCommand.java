package chatbot.commands;

import chatbot.data.Task;
import chatbot.data.TaskList;

import chatbot.storage.Storage;
import chatbot.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A command that sorts and returns user tasks chronologically.
 */

public class SortCommand extends Command {

    Comparator<Task> comparator;

    public SortCommand(Comparator<Task> comp) {
        this.comparator = comp;
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
