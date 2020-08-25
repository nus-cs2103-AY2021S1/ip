package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.tasks.*;

import java.time.LocalDate;
import java.util.ArrayList;

/** Represents the command that shows the deadlines/events occurring on a specific date inputted. */
public class DateCommand extends Command {

    /** The inputted date. */
    public LocalDate queryDate;

    /** Constructor.
     *
     * @param queryDate The inputted date.
     */
    public DateCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    /** Prints out the list of tasks that are occurring on the inputted date in Duke format.
     *
     * @param tasks The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the list of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks.lst) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.date.equals(queryDate)) {
                    tasksOnDate.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.date.equals(queryDate)) {
                    tasksOnDate.add(event);
                }
            }
        }
        ui.formatShowTasksOnDate(tasksOnDate, queryDate);
    }
}
