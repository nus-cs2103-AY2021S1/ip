package duke.tasklist.tasklistfilter;

import java.time.LocalDateTime;
import java.util.Calendar;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Filters the deadlines/events that will occur this week.
 */
public class WeekFilter implements TaskListFilter {
    private int currentWeek;
    private int currentYear;
    private LocalDateTime endOfWeek;

    /**
     * WeekFilter Constructor. The constructor finds the week of the year and the
     * current year.
     */
    public WeekFilter() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        currentYear = calendar.getWeekYear();
    }

    /**
     * Check if the event or deadline occur this week.
     *
     * @param task The given task.
     * @return Boolean if the task is occurs this week.
     */
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
        // Takes note of the day, month and the year of the task
        int dayOfTask = taskDate.getDayOfMonth();
        int monthOfTask = taskDate.getMonthValue() - 1;
        int yearOfTask = taskDate.getYear();

        // Find the week of the year
        Calendar currentCalendar = new Calendar.Builder().setDate(yearOfTask, monthOfTask, dayOfTask)
                .build();
        int weekOfTask = currentCalendar.get(Calendar.WEEK_OF_YEAR);

        return weekOfTask == currentWeek && yearOfTask == currentYear;
    }
}
