package dude.command;

import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.util.Ui;

import dude.task.Task;

import java.util.ArrayList;

/**
 * The command gets the items in the tasklist that contains the keyword.
 */

public class FindCommand extends Command {
    private String keyWord;

    public FindCommand(String action, String keyWord) {
        super(action);
        this.keyWord = keyWord;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        ArrayList<Task> searchList = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyWord)) {
                searchList.add(task);
            }
        }
        Command command = new ListCommand("list");
        command.execute(new TaskList(searchList), ui, storage);
        if (searchList.size() == 0) {
            throw new CommandException("The keyword you searched for does not result in any results.");
        }
    }
}
