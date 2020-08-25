import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {

    public LocalDate queryDate;

    public DateCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

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
