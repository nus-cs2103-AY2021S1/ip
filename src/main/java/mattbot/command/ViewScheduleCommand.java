package mattbot.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import mattbot.tasks.Task;
import mattbot.tasks.TaskManager;


public class ViewScheduleCommand extends Command {
    private static Task findSmallest(ArrayList<Task> list) {
        int initial = 0;
        Task smallest = list.get(initial);
        for (int i = 1; i < list.size(); i++) {
            Task current = list.get(i);
            LocalDateTime currentTime = current.getTaskDate();
            if (currentTime.isBefore(smallest.getTaskDate())) {
                smallest = current;
            } else {
                // do nothing as smallest is the earliest.
            }
        }
        return smallest;
    }

    /**
     * Sorts the list of tasks by the order of the time.
     * @param store the input arraylist to be sorted.
     * @return ArrayList sorted arraylist of tasks.
     */
    public static ArrayList<Task> sortByDateTime(ArrayList<Task> store) {
        ArrayList<Task> sorted = new ArrayList<>();
        while (!store.isEmpty()) {
            Task smallest = findSmallest(store);
            store.remove(smallest);
            sorted.add(smallest);
        }
        return sorted;
    }

    /**
     * Executes the function of filter by date.
     * @param date date to filter by
     * @return String all the tasks filtered by the date.
     */
    public static String execute2(LocalDate date) {
        ArrayList<Task> filtered = FilterDateCommand.filter(date);
        ArrayList<Task> sorted = sortByDateTime(filtered);
        String result = "Here are your tasks for " + date.toString() + System.lineSeparator();
        int count = 1;
        for (Task t : sorted) {
            result += "    " + count + ". " + TaskManager.read(t) + System.lineSeparator();
            count++;
        }
        return result;
    }
}
