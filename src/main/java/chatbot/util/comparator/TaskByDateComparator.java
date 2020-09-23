package chatbot.util.comparator;

import java.time.LocalDate;
import java.util.Comparator;

import chatbot.data.Task;

/**
 * Compares tasks by chronological order. Tasks which are not dated are preceded
 * by those which contains a date.
 */

public class TaskByDateComparator implements Comparator<Task> {

    /**
     * Compares tasks such that more recent tasks comes before older tasks.
     * @param t1 first task
     * @param t2 second task
     * @return -1 if d1 is before d2, 1 if d1 is after d2 and 0 if d1 equals d2
     */
    @Override
    public int compare(Task t1, Task t2) {
        LocalDate d1 = t1.getDate();
        LocalDate d2 = t2.getDate();
        return d1 == null || d2 == null || d1.isBefore(d2)
                ? -1
                : d1.isAfter(d2)
                    ? 1
                    : 0;
    }

    @Override
    public Comparator<Task> reversed() {
        return new TaskByDateReverseComparator();
    }
}
