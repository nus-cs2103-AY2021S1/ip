package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class FindCommand extends Command {
    private TaskList tasks;

    /**
     * Constructor for FindCommand class.
     * @param args
     * @param tasks
     */
    public FindCommand(String[] args, TaskList tasks) {
        super.args = args;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            return new ErrorCommand("OOPS!!! The description of a find cannot be empty.").execute();
        }

        String searchString = args[1];
        TaskList matchedTasks = tasks.find(searchString);

        if (matchedTasks.isEmpty()) {
            return new ErrorCommand("OOPS!!! There is no such task in the list.").execute();
        }

        StringBuilder concat = new StringBuilder();

        for (int i = 0; i < matchedTasks.size(); i++) {
            concat.append(String.format("\n\t%d. %s", i + 1, matchedTasks.get(i)));
        }

        return "Here are the matching tasks in your list: " + concat;
    }
}
