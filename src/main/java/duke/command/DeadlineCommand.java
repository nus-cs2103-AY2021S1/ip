package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for DeadlineCommand class.
     * @param args
     * @param tasks
     * @param storage
     */
    public DeadlineCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            return new ErrorCommand("OOPS!!! The description of a deadline cannot be empty.").execute();
        }

        String[] commandParam = args[1].split("/by");

        if (commandParam.length < 2) {
            return new ErrorCommand("OOPS!!! Invalid Argument (\"/by\" String not found)").execute();
        }

        String description = commandParam[0].trim();
        String by = commandParam[1].trim();

        if (description.isBlank()) {
            return new ErrorCommand("OOPS!!! The description of a deadline cannot be empty.").execute();
        }

        if (by.isBlank()) {
            return new ErrorCommand("OOPS!!! The /by description of a deadline cannot be empty.").execute();
        }

        String dateRegex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";

        if (!by.matches(dateRegex)) {
            return new ErrorCommand("OOPS!!! The date format must be yyyy-mm-dd.").execute();
        }

        Deadline newDeadline = new Deadline(description, by);

        tasks.add(newDeadline, storage);
        return "Got it. I've added this task: \n\t" + newDeadline + "\nNow you have " + tasks.size()
                + " tasks in the list.";
    }
}
