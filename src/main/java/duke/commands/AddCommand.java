package duke.commands;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
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
     * @param c           an enum that is the command to identify type of task added.
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
     * @param storage  stores all the tasks being added so far into user's local storage.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        assert tasklist != null : "Tasklist cannot be null.";
        assert storage != null : "Storage cannot be null.";
        assert description != null : "Description cannot be null.";
        switch (c) {
        case TODO:
            tasklist.add(new Todo(description));
            return ("Hi there, I've added this task:\n"
                    + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                    + tasklist.getSize() + " tasks in the list.");
        case DEADLINE:
            String[] descriptions = description.split("/by ");
            String response = checkForClashes(tasklist);
            tasklist.add(new Deadline(descriptions[0], descriptions[1]));
            return ("Hi there, I've added this task:\n"
                    + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                    + tasklist.getSize() + " tasks in the list. \n\n"
                    + response);
        case EVENT:
            String[] descriptions2 = description.split("/at ");
            String response2 = checkForClashes(tasklist);
            tasklist.add(new Event(descriptions2[0], descriptions2[1]));
            return ("Hi there, I've added this task:\n"
                    + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                    + tasklist.getSize() + " tasks in the list. \n\n"
                    + response2);
        default:
            throw new DukeException("invalid command.");
        }
    }

    private String checkForClashes(TaskList tasklist) throws DukeException {
        String[] descriptions = description.split(" ");
        String response = "***WARNING!*** \nTasks on the same date and within an hour of added task: \n";
        boolean hasClash = false;
        List<Task> tasks = tasklist.getList();
        LocalDate date = null;
        LocalTime time = null;
        for (String i : descriptions) {
            try {
                date = LocalDate.parse(i);
            } catch (DateTimeParseException e) {
                try {
                    time = LocalTime.parse(i);
                } catch (DateTimeParseException e2) {
                    continue;
                }
            }
        }
        for (Task k : tasks) {
            if (k instanceof Deadline) {
                if (((Deadline) k).isSameDate(date)) {
                    if (((Deadline) k).isSameTime(time) || ((Deadline) k).isWithinAnHour(time)) {
                        response += tasks.indexOf(k) + ". " + k.toString() + "\n";
                        hasClash = true;
                    }
                }
            } else if (k instanceof Event) {
                if (((Event) k).isWithinAnHour(time)) {
                    if (((Event) k).isSameTime(time)) {
                        response += tasks.indexOf(k) + ". " + k.toString() + "\n";
                        hasClash = true;
                    }
                }
            }
        }
        if (hasClash) {
            return response;
        }
        return "";
    }
}
