import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        if (task1.getDate().isBefore(task2.getDate())) {
            return -1;
        } else if (task1.getDate().equals(task2.getDate())) {
            return 0;
        } else {
            return 1;
        }
    }
}
