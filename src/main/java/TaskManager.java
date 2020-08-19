import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager {
    static List tasks = new ArrayList<>();
    static int completedTasks = 0;
    static int uncompletedTasks = 0;

    public static String numberOfTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("you have [")
                .append(TaskManager.uncompletedTasks).append("] uncompleted task(s) and [")
                .append(TaskManager.completedTasks).append("] completed task(s)");
        return sb.toString();
    }

    public static String addTask(String string) {
        Task task = null;
        if (string.substring(0, 5).equals("todo ")) {
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
                .append(TaskManager.numberOfTasks());
        return sb.toString();
    }

    public static String returnList() {
        Iterator i = tasks.iterator();
        StringBuilder output = new StringBuilder();
        output.append("here are the tasks in your list:\n");
        int counter = 1;
        output.append(counter + ". ").append(i.next());
        while (i.hasNext()) {
            counter++;
            output.append("\n").append(counter + ". ").append(i.next());
        }
        return output.toString();
    }

    public static String markAsDone(int i) {
        Task task = (Task) TaskManager.tasks.get(i - 1);
        task.markAsDone();
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have marked this task as done: \n    ").append(task);

        TaskManager.uncompletedTasks--;
        TaskManager.completedTasks++;

        if (TaskManager.uncompletedTasks == 0) {
            sb.append("\n").append("woohoo! you have no more uncompleted tasks left");
        } else {
            sb.append("\n").append(TaskManager.numberOfTasks());
        }

        return sb.toString();
    }

    public static String deleteTask(int i) {
        Task task = (Task) TaskManager.tasks.remove(i - 1);

        StringBuilder sb = new StringBuilder();
        sb.append("sure thing. i have removed this task: \n    ")
                .append(task).append("\n");

        if (task.isDone()) {
            TaskManager.completedTasks--;
        } else {
            TaskManager.uncompletedTasks--;
        }

        sb.append(TaskManager.numberOfTasks());
        return sb.toString();
    }
}
