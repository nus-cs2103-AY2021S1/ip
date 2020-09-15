package duke.util;

import duke.task.Task;
import duke.task.TaskWithDateTime;
import duke.task.FixedDurationTaskWithDateTime;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * Comparator for comparing tasks using the date time fields.
 */
public class TaskDateTimeComparator implements Comparator<Task> {

    /**
     * Compares two tasks. The comparison follows the rules:
     *     - Task without date time will be last
     *     - Two tasks with dates will be compared
     *         - Task with earlier date will be first
     *         - If same date, time will be compared
     *             - Task without time will be last
     *             - Task with earlier time will be first
     *
     * @param t1 the first task.
     * @param t2 the second task.
     * @return the compare value.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (hasNoDateTime(t1)) {
            return (hasNoDateTime(t2) ? 0 : 1);
        }

        LocalDate date1 = getLocalDate(t1);
        LocalTime time1 = getLocalTime(t1);
        LocalDate date2 = getLocalDate(t2);
        LocalTime time2 = getLocalTime(t2);

        int compareValue = 0;
        compareValue += compareDate(date1, date2);
        if (compareValue == 0) {
            compareValue += compareTime(time1, time2);
        }
        return compareValue;
    }

    private boolean hasNoDateTime(Task t) {
        boolean isTodo = t instanceof Todo;
        boolean isTaskWithDateTime = t instanceof TaskWithDateTime;
        boolean isFixedDurationTaskWithDateTime = t instanceof FixedDurationTaskWithDateTime;
        return (isTodo || (!isTaskWithDateTime && !isFixedDurationTaskWithDateTime));
    }

    private LocalDate getLocalDate(Task t) {
        LocalDate date = null;
        if (t instanceof TaskWithDateTime) {
            TaskWithDateTime dt = (TaskWithDateTime) t;
            date = dt.getDate();
        } else if (t instanceof FixedDurationTaskWithDateTime) {
            FixedDurationTaskWithDateTime f = (FixedDurationTaskWithDateTime) t;
            date = f.getDate();
        }
        return date;
    }

    private LocalTime getLocalTime(Task t) {
        LocalTime time = null;
        if (t instanceof TaskWithDateTime) {
            TaskWithDateTime dt = (TaskWithDateTime) t;
            time = dt.getTime().orElse(null);
        } else if (t instanceof FixedDurationTaskWithDateTime) {
            FixedDurationTaskWithDateTime f = (FixedDurationTaskWithDateTime) t;
            time = f.getTime();
        }
        return time;
    }

    private int compareDate(LocalDate d1, LocalDate d2) {
        if (d1 == null) {
            return (d2 == null ? 0 : 1);
        }
        if (d2 == null) {
            return -1;
        }
        if (d1.isBefore(d2)) {
            return -1;
        } else if (d2.isBefore(d1)) {
            return 1;
        }
        return 0;
    }

    private int compareTime(LocalTime t1, LocalTime t2) {
        if (t1 == null) {
            return (t2 == null ? 0 : 1);
        }
        if (t2 == null) {
            return -1;
        }
        if (t1.isBefore(t2)) {
            return -1;
        } else if (t2.isBefore(t1)) {
            return 1;
        }
        return 0;
    }
}
