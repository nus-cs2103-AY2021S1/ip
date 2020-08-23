package main.java.duke;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class QueryOnTasks {
    
    public List<Task> filterByDate(List<Task> list, String date) {
        return list.stream().filter(task -> (task.getType().equals("Deadline") || task.getType().equals("Event")) && task.getLocalDate().equals(LocalDate.parse(date))).collect(Collectors.toList());
    }
    
    public List<Task> filterByMonth(List<Task> list, String month) {
        return list.stream().filter(task -> (task.getType().equals("Deadline") || task.getType().equals("Event")) && Integer.toString(task.getLocalDate().getMonthValue()).equals(month)).collect(Collectors.toList());
    }

    public List<Task> filterByYear(List<Task> list, String year) {
        return list.stream().filter(task -> (task.getType().equals("Deadline") || task.getType().equals("Event")) && Integer.toString(task.getLocalDate().getYear()).equals(year)).collect(Collectors.toList());
    }
}
