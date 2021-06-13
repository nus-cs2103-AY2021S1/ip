package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends Command {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for EventCommand class.
     * @param args
     * @param tasks
     * @param storage
     */
    public EventCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            return new ErrorCommand("OOPS!!! The description of a event cannot be empty.").execute();
        }

        String[] commandParam = args[1].split("/at");

        if (commandParam.length < 2) {
            return new ErrorCommand("OOPS!!! Invalid Argument (\"/at\" String not found)").execute();
        }

        String description = commandParam[0].trim();
        String at = commandParam[1].trim();

        if (description.isBlank()) {
            return new ErrorCommand("OOPS!!! The description of a event cannot be empty.").execute();
        }

        if (at.isBlank()) {
            return new ErrorCommand("OOPS!!! The /at description of a event cannot be empty.").execute();
        }

        String dateTimeRegex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) ([1-9]|1[012]):([0-5][0-9]) [ap]m";

        if (!at.matches(dateTimeRegex)) {
            return new ErrorCommand("OOPS!!! The date-time format must be yyyy-mm-dd h:mm am/pm.").execute();
        }

        Event newEvent = new Event(description, at);

        tasks.add(newEvent, storage);
        return "Got it. I've added this task: \n\t" + newEvent + "\nNow you have " + tasks.size()
                + " tasks in the list.";
    }
}
