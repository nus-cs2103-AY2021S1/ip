import java.util.Comparator;

public class DateComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        return task1.getDueDateTime().compareTo(task2.getDueDateTime());
    }
}
