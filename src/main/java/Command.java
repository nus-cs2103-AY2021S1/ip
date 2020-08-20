import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class Command implements Execute {
    public static List tasks = new ArrayList<>();
    public static int completedTasks = 0;
    public static int uncompletedTasks = 0;

    public static String numberOfTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("you have [")
                .append(Command.uncompletedTasks).append("] uncompleted task(s) and [")
                .append(Command.completedTasks).append("] completed task(s)");
        return sb.toString();
    }

    public static String returnList() {
        Iterator i = tasks.iterator();
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        sb.append(counter + ". ").append(i.next());
        while (i.hasNext()) {
            counter++;
            sb.append("\n").append(counter + ". ").append(i.next());
        }
        return sb.toString();
    }
}
