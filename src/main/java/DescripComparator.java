import java.util.Comparator;

public class DescripComparator implements Comparator<Task> {

    public int compare(Task task1, Task task2) {
        return (int) (task1.getDescription().compareTo(task2.getDescription()));
    }
}
