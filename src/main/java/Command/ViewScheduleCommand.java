package Command;

import Tasks.TaskManager;
import Tasks.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ViewScheduleCommand extends Command {
    public static ArrayList<task> sortByDateTime(ArrayList<task> store) {
        ArrayList<task> sorted = new ArrayList<>();
        // add sorting function for date and time
    }
    public static String execute2(LocalDate date) {
        ArrayList<task> filtered = FilterDateCommand.filter(date);
        ArrayList<task> sorted = sortByDateTime(filtered);
        String result = "Here are your tasks for " + date.toString() + System.lineSeparator();
        int count = 1;
        for (task t : sorted) {
            result += "    " + count + ". " + TaskManager.read(t) + System.lineSeparator();
            count++;
        }
        return result;
    }
}
