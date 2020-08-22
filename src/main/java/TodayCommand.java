import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class TodayCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        LocalDateTime now = LocalDate.now().atStartOfDay();
        ArrayList<Task> tasksToday = new ArrayList<>();
        taskList.getTasks().forEach(task -> {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getAt().isSameDate(new DukeDateTime(now, false))) {
                    tasksToday.add(event);
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().isSameDate(new DukeDateTime(now, false))) {
                    tasksToday.add(deadline);
                }
            }
        });
        // todo: sort tasks
        ui.show(tasksTodayString(tasksToday));
    }

    private String tasksTodayString(ArrayList<Task> tasks) {
        StringBuilder builder = new StringBuilder("\t Here are your tasks today:\n");
        int i = 1;
        for (Task task : tasks) {
            builder.append("\t ").append(i).append(". ").append(task.toString());
            if (i != tasks.size()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
