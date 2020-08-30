package seedu.duke;

/**
 * A command that deletes a task.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String[] words) {
        super(words);
    }

    /**
     * Deletes the task from the list of current tasks.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @throws DukeNotSureException If the task being deleted does not exist.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeNotSureException {
        int number = Integer.parseInt(words[1]);
        if (number > ls.size()) {
            throw new DukeNotSureException("This task doesn't seem to exist? :s");
        } else {
            Task oldTask = ls.get(number - 1);
            ls.remove(number - 1);
            String thing = "Running away from your responsibilities huh. Deleted:" +
                    "\n" +
                    oldTask.getStatus().replaceAll("(?m)^", "\t") +
                    "\nNow you have " + ls.size() + " tasks in the list.";
            ui.printResult(thing);
        }
    }
}
