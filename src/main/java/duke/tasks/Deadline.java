package duke.tasks;

import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeInvalidTimeException;


/**
 * Deadline class which carries tasks of type Deadline and extends of the base Task class.
 */
public class Deadline extends Task {

    /**
     * Constructor for tasks of deadline type.
     *
     * @param description               Description of the activity
     * @param index                     Numbers for the indexing process of the tasks
     * @param isDone                    Task completion status
     * @throws DukeInvalidTimeException Wrong definition for the task timing
     */
    public Deadline(String description, int index, boolean isDone) throws DukeInvalidTimeException {
        super(description, index, isDone);
        super.type = TaskType.DEADLINE;
        int idx = description.indexOf('/');
        super.setTime(idx);
    }

    /**
     * Constructor for tasks of deadline type.
     *
     * @param description               Description of the activity
     * @param index                     Numbers for the indexing process of the tasks
     * @param isDone                    Task completion status
     * @param tag                       Tag of task
     * @throws DukeInvalidTimeException Wrong definition for the task timing
     */
    public Deadline(String description, int index, boolean isDone, String tag) throws DukeInvalidTimeException {
        super(description, index, isDone, tag);
        super.type = TaskType.DEADLINE;
        int idx = description.indexOf('/');
        super.setTime(idx);
    }

    /**
     * Returns the text version of task with index & date formatted to MMM d yyyy h:mm a.
     *
     * @return String representation for event objects with indexing and date conversion.
     */

    @Override
    public String getStatusWithIndex() {
        int idx = description.indexOf('/');
        String task = description.substring(0, idx);
        String end = String.format("by: %s", super.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
        String icon = super.getIcon(isDone);
        return String.format("%s. %s%s%s(%s) %s", index, super.type, icon, task, end, super.convert());
    }

    /**
     * Default toString() definition for Deadline tasks.
     *
     * @return String representation of deadline objects.
     */

    @Override
    public String toString() {
        int idx = description.indexOf('/');
        String task = description.substring(0, idx);
        String end = new StringBuilder(description.substring(idx + 1)).insert(2, ':').toString();
        String icon = super.getIcon(isDone);
        return String.format("%s%s%s(%s) %s", super.type, icon, task, end, super.convert());
    }
}
