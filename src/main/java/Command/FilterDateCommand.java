package Command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;



/**
 * Represents a command that filters the task by the date.
 */
public class FilterDateCommand extends Command {

    public static ArrayList<task> filter(LocalDate date) {
        ArrayList<task> clone = TaskManager.getStore();
        ArrayList<task> filtered = new ArrayList<>();
        for (task t : clone) {
            LocalDateTime dateTime = t.getTaskDate();
            if (dateTime != null && dateTime.toLocalDate().equals(date)) {
                filtered.add(t);
            }
        }
        return filtered;
    }
    /**
     * Filters the tasks based on the input date, showing only those with that date.
     * Return the list of task matching the filter.
     *
     * @param date date to search.
     * @return String filtered list.
     * @throws ErrorExceptions wrong date format.
     */
    public static String execute2(LocalDate date) throws ErrorExceptions {
        ArrayList<task> filtered = filter(date);
        int count = 1;
        String result = "";
        result = result + "Here are your tasks on this date!";
        for (task i : filtered) {
            result = result + System.lineSeparator();
            result = result + "    " + count + ". " + TaskManager.read(i);
            count++;
        }
        return result;
    }
}
