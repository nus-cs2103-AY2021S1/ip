package duke.tasklist.tasklistfilter;

import java.time.LocalDateTime;
import java.util.Calendar;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class WeekFilter implements TaskListFilter {
    private int currentWeek;
    private int currentYear;
    private LocalDateTime endOfWeek;

    public WeekFilter() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        currentYear = calendar.getWeekYear();

    }

    public boolean filter(Task task) {
        if (task instanceof Todo) {
            return false;
        }

        // Saves the date of a deadline or an event
        LocalDateTime taskDate;
        if (task instanceof Deadline) {
            taskDate = ((Deadline) task).getDate();
        } else {
            taskDate = ((Event) task).getDate();
        }

        int dayOfTask = taskDate.getDayOfMonth();
        int monthOfTask = taskDate.getMonthValue() - 1;
        int yearOfTask = taskDate.getYear();

        // Find the week of the year
        Calendar currentCalendar = new Calendar.Builder().setDate(yearOfTask, monthOfTask, dayOfTask ).build();
        int weekOfTask = currentCalendar.get(Calendar.WEEK_OF_YEAR);

        return weekOfTask == currentWeek && yearOfTask == currentYear;
    }
}
