import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager {
    static List tasks = new ArrayList<>();
    static int completedTasks = 0;
    static int uncompletedTasks = 0;

    public static String addTask(String string) {
        Task task = null;
        if (string.substring(0,5).equals("todo ")) {
            task = new ToDo(string);
        } else if (string.substring(0, 9).equals("deadline ")) {
            task = new Deadline(string);
        } else if (string.substring(0, 6).equals("event ")) {
            task = new Event(string);
        }
        TaskManager.tasks.add(task);
        TaskManager.uncompletedTasks++;

        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have added this task to your list: \n    ")
                .append(task).append("\n")
                .append("you have [")
                .append(TaskManager.uncompletedTasks).append("] uncompleted task(s) and [")
                .append(TaskManager.completedTasks).append("] completed task(s)");
        return sb.toString();
    }

    public static String returnList() {
        Iterator i = tasks.iterator();
        StringBuilder output = new StringBuilder();
        int counter = 1;
        output.append(counter + ". ").append(i.next());
        while (i.hasNext()) {
            counter ++;
            output.append("\n").append(counter + ". ").append(i.next());
        }
        return output.toString();
    }

    public static String markAsDone(int i) {
        Task task = (Task) TaskManager.tasks.get(i-1);
        task.markAsDone();
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have marked this task as done: \n    ").append(task);

        TaskManager.uncompletedTasks--; TaskManager.completedTasks++;

        if (TaskManager.uncompletedTasks == 0) {
            sb.append("\n").append("woohoo! you have no more uncompleted tasks left");
        } else {
            sb.append("\n").append("you have [")
                    .append(TaskManager.uncompletedTasks).append("] uncompleted task(s) and [")
                    .append(TaskManager.completedTasks).append("] completed task(s)");
        }

        return sb.toString();
    }
}
