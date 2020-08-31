package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Command to add <code>todo</code>, <code>deadline</code> and <code>event</code> to <code>TaskList</code>.
 */
public class AddCommand extends Command {
    private final Commands c;
    private final String description;

    /**
     * Constructor to create AddCommand object.
     *
     * @param c an enum that is the command to identify type of task added.
     * @param description specific details of the task being added.
     */
    public AddCommand(Commands c, String description) {
        this.c = c;
        this.description = description;
    }

    /**
     * Adds the task into <code>tasklist</code>.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        switch (c) {
        case TODO:
            tasklist.add(new Todo(description));
            return ("Hi there, I've added this task:\n"
                    + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                    + tasklist.getSize() + " tasks in the list.");
        case DEADLINE:
            String[] descriptions = description.split("/by ");
            tasklist.add(new Deadline(descriptions[0], descriptions[1]));
            return ("Hi there, I've added this task:\n"
                    + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                    + tasklist.getSize() + " tasks in the list.");
        case EVENT:
            String[] descriptions2 = description.split("/at ");
            tasklist.add(new Event(descriptions2[0], descriptions2[1]));
            return ("Hi there, I've added this task:\n"
                    + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                    + tasklist.getSize() + " tasks in the list.");
        default:
            throw new DukeException("invalid command.");
        }
    }
}
