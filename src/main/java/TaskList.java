import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static List<Task> taskList = new ArrayList<>();

    TaskList() {
        taskList = new ArrayList<>();
    }

    public static String removeTask (int idx) {
        Task task = taskList.get(idx);
        taskList.remove(idx);
        return task.toString();
    }

    public static Task getTask(int idx) {
        return taskList.get(idx);
    }

    public static String addTaskHelper(Duke.TaskType taskType, String description) {
        Task task = null;
        String[] splitReadLine = description.split("/", 2 );
        String[] content;
        if (splitReadLine.length == 2) content = splitReadLine[1].split(" ", 2);
        else content = null;
        if (splitReadLine.length == 2) {
            switch (taskType) {
                case TODOS:
                    task = new ToDos(splitReadLine[0].trim(), content[1]);
                    taskList.add(task);
                    break;
                case DEADLINE:
                    task = new Deadline(splitReadLine[0].trim(), content[1]);
                    taskList.add(task);
                    break;
                case EVENT:
                    task = new Event(splitReadLine[0].trim(), content[1]);
                    taskList.add(task);
                    break;
            }
        } else {
            switch (taskType) {
                case TODOS:
                    task = new ToDos(splitReadLine[0].trim());
                    taskList.add(task);
                    break;
                case DEADLINE:
                    task = new Deadline(splitReadLine[0].trim());
                    taskList.add(task);
                    break;
                case EVENT:
                    task = new Event(splitReadLine[0].trim());
                    taskList.add(task);
                    break;
            }
        }

        return "Got it, here yur task bij\n" +
                task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public static Task addTask(Duke.TaskType taskType, String description, String deadline) {
        Task task = null;
        switch (taskType) {
            case TODOS:
                task = new ToDos(description, deadline);
                taskList.add(task);
                break;
            case DEADLINE:
                task = new Deadline(description, deadline);
                taskList.add(task);
                break;
            case EVENT:
                task = new Event(description, deadline);
                taskList.add(task);
                break;
        }
        return task;
    }

    public static Task addTask(Duke.TaskType taskType, String description) {
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
        return task;
    }

    public static int getSize() {
        return taskList.size();
    }

    public static void saveTasks() {
        if (getSize() > 0) {
            WriteFile writer = new WriteFile("data/duke.txt");
            writer.reset();
            writer.write(taskList.get(0).toPrint());
            for (int i = 1; i < getSize(); i++) {
                writer.lineBreak();
                writer.write(taskList.get(i).toPrint());
            }
        }
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
