package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class HelpCommand extends Command {

    /**
     * Provides the user with a list of commands available for them.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String help = "These are the available commands:\n"
                + "bye - exits the program\n\n"
                + "deadline <description> /by <due date and time in YYYY-MM-DD HH:MM format> - "
                + "adds a deadline with the given description and due date to the task list\n\n"
                + "delete <task number> - deletes the task corresponding to the number from the task list\n\n"
                + "due in <number> hours - shows a list of tasks due in the given number of hours\n\n"
                + "due in <number> days - shows a list of tasks due in the given number of days\n\n"
                + "done <task number> - marks the task corresponding to the number as done\n\n"
                + "event <description> /at <due date and time in YYYY-MM-DD HH:MM format> - "
                + "adds an event with the given description and due date to the task list\n\n"
                + "find <keyword> - shows a list of tasks containing the given keyword\n\n"
                + "help - shows this list of commands\n\n"
                + "list - shows the contents of the task list\n\n"
                + "repeat <task number> <number> <'day'/'week'/'month'/'year'> until "
                + "<date and time in YYYY-MM-DD HH:MM format> - sets the given task to "
                + "repeat with the given recurrence until the provided end date\n\n"
                + "sort - sorts the task list according to time\n\n"
                + "todo <description> - adds a todo task with the given description to the task list";
        return (help);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }
}
