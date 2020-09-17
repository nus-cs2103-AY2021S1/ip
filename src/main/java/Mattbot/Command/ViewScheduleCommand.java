package Mattbot.Command;

import Mattbot.Tasks.TaskManager;
import Mattbot.Tasks.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ViewScheduleCommand extends Command {
    private static task findSmallest(ArrayList<task> list) {
        int initial = 0;
        task smallest = list.get(initial);
        for (int i = 1; i < list.size(); i++) {
            task current = list.get(i);
            LocalDateTime currentTime = current.getTaskDate();
            if (currentTime.isBefore(smallest.getTaskDate())) {
                smallest = current;
            } else {
                // do nothing as smallest is the earliest.
            }
        }
        return smallest;
    }
    public static ArrayList<task> sortByDateTime(ArrayList<task> store) {
        ArrayList<task> sorted = new ArrayList<>();
        // add sorting function for date and time
        while (!store.isEmpty()) {
            task smallest = findSmallest(store);
            store.remove(smallest);
            sorted.add(smallest);
        }
        return sorted;
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

//    public static String execute3() {
//        ArrayList<task> clone = TaskManager.getStore();
//        clone = sortByDateTime(clone);
//        String result = "Here are your tasks for in order " + System.lineSeparator();
//        int count = 1;
//        for (task t : clone) {
//            result += "    " + count + ". " + TaskManager.read(t) + System.lineSeparator();
//            count++;
//        }
//        return result;
//
//    }
}
