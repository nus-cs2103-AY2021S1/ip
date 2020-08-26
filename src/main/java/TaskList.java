import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static List<Task> taskList = new ArrayList<>();

    public static String removeTask (int idx) {
        Task task = taskList.get(idx);
        taskList.remove(idx);
        return task.toString();
    }

    public static Task getTask(int idx) {
        return taskList.get(idx);
    }

    public static int getSize() {
        return taskList.size();
    }

    public static String addTask(Task.TaskType taskType, String description, String deadline) {
        Task task = null;
        switch (taskType) {
            case TODOS:
                task = new ToDos(description.trim(), deadline);
                taskList.add(task);
                break;
            case DEADLINE:
                task = new Deadline(description.trim(), deadline);
                taskList.add(task);
                break;
            case EVENT:
                task = new Event(description.trim(), deadline);
                taskList.add(task);
                break;
        }

        return "Got it, here yur task bij\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public static String addTask(Task.TaskType taskType, String description) {
        Task task = null;
        switch (taskType) {
            case TODOS:
                task = new ToDos(description);
                taskList.add(task);
                break;
            case DEADLINE:
                task = new Deadline(description);
                taskList.add(task);
                break;
            case EVENT:
                task = new Event(description);
                taskList.add(task);
                break;
        }

        return "Got it, here yur task bij\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public static String toStr() {
        String string = "";
        if (getSize() >= 1) {
            string = "1." + taskList.get(0).toString();
            for (int num = 2; num <= getSize(); num++) {
                string = string + "\n" + num + "." + taskList.get(num-1).toString();
            }
        } else {
            string = "empty";
        }
        return string;
    }
}
