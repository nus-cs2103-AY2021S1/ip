package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        // For CLI
        taskList.find(keyword);
        // For GUI
        ArrayList<Task> matchingTasks = taskList.findForGui(keyword);
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        str.append(taskListToString(matchingTasks));
        return new CommandResult(str.toString());
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
