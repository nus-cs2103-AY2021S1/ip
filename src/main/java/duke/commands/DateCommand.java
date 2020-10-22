package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the command that shows the deadlines/events occurring on a specific date inputted. */
public class DateCommand extends Command {

    /** The inputted date. */
    private LocalDate queryDate;

    /** Constructs a new DateCommand object with the specified queryDate.
     *
     * @param queryDate The inputted date.
     */
    public DateCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    /** Prints out the list of tasks that are occurring on the inputted date in Duke format.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(queryDate)) {
                    tasksOnDate.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDate().equals(queryDate)) {
                    tasksOnDate.add(event);
                }
            }
        }
        setDialog(ui.formatShowTasksOnDate(tasksOnDate, queryDate));
    }
}
