package mattbot.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import mattbot.errors.ErrorExceptions;
import mattbot.tasks.Task;
import mattbot.tasks.TaskManager;



/**
 * Represents a command that filters the task by the date.
 */
public class FilterDateCommand extends Command {
    /**
     * Filters the task by the user input date.
     *
     * @param date date to filter
     * @return ArrayList list of filtered tasks
     */
    public static ArrayList<Task> filter(LocalDate date) {
        ArrayList<Task> clone = TaskManager.getStore();
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task t : clone) {
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
        ArrayList<Task> filtered = filter(date);
        int count = 1;
        String result = "";
        result = result + "Here are your tasks on this date!";
        for (Task i : filtered) {
            result = result + System.lineSeparator();
            result = result + "    " + count + ". " + TaskManager.read(i);
            count++;
        }
        return result;
    }
}
