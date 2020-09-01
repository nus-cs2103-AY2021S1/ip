package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        if (fullCommand.length() < 6) {
            throw new EmptyDescriptionException("oops! the description of 'find' cannot be empty");
        }
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(fullCommand.substring(5));
        return matchingTasksMessage(matchingTasks);
    }

    public String matchingTasksMessage(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();

        if (matchingTasks.size() == 0) {
            sb.append("there are no tasks matching the given search");
        } else {
            sb.append("here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(i+1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
