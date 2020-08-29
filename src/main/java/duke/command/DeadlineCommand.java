package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    TaskList tasks;
    Storage storage;
    
    public DeadlineCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }
    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            return new ErrorCommand("\t☹ OOPS!!! The description of a deadline cannot be empty.").execute();
        }

        String[] commandParam = args[1].split("/by");

        if (commandParam.length < 2) {
            return new ErrorCommand("\t☹ OOPS!!! Invalid Argument (\"/by\" String not found)").execute();
        }

        String description = commandParam[0].trim();
        String by = commandParam[1].trim();

        if (description.isBlank()) {
            return new ErrorCommand("\t☹ OOPS!!! The description of a deadline cannot be empty.").execute();
        }

        if (by.isBlank()) {
            return new ErrorCommand("\t☹ OOPS!!! The /by description of a deadline cannot be empty.").execute();
        }

        if (!by.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            return new ErrorCommand("\t☹ OOPS!!! The date format must be yyyy-mm-dd.").execute();
        }

        Deadline newDeadline = new Deadline(description, by);

        tasks.add(newDeadline, storage);
        return "\tGot it. I've added this task: \n\t\t" + newDeadline + "\n\tNow you have " + tasks.size()
                + " tasks in the list.";
    }
}
