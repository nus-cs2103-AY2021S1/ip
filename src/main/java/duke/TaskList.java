package duke;

import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskType;
import task.Task;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskList = new ArrayList<>();

    public static void add(TaskType type, String taskName) throws IOException {
        try {
            Todo todo = new Todo(taskName);
            taskList.add(todo);
            Storage.addToList(TaskType.TODO, 0, taskName, "");
            Ui.userMessage("Got it. I've added the task:\n" + todo.toString());
            Ui.userMessage("Now you have " + taskList.size() + " tasks in the list.");
        } catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

    public static void add(TaskType type, String taskName, String time) throws IOException {
        try {
            if (type == TaskType.DEADLINE) {
                Deadline deadline = new Deadline(taskName, time);
                taskList.add(deadline);
                Storage.addToList(TaskType.DEADLINE, 0, taskName, time);
                Ui.userMessage("Got it. I've added the task:\n" + deadline.toString());
            } else {
                Event event = new Event(taskName, time);
                taskList.add(event);
                Storage.addToList(TaskType.EVENT, 0, taskName, time);
                Ui.userMessage("Got it. I've added the task:\n" + event.toString());
            }
            Ui.userMessage("Now you have " + taskList.size() + " tasks in the list.");
        } catch (IOException e) {
            throw new IOException("File access failed");
        }

    }

    public static List<Task> getList() {
        return taskList;
    }

    public static void setDone(int index) throws InvalidParameterException, IOException {
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        Storage.setDone(index - 1);
        taskList.get(index - 1).setDone();
        Ui.userMessage("Well done! I've marked this as done:");
        Ui.userMessage(taskList.get(index - 1).toString());
    }

    public static void delete(int index) throws InvalidParameterException, IOException {
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        Storage.delete(index - 1);
        Ui.userMessage("Noted! I've removed this task:");
        Ui.userMessage(taskList.get(index - 1).toString());
        taskList.remove(index - 1);
        Ui.userMessage("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printList() throws IOException {
        try {
            int n = taskList.size();
            if (n == 0) { // empty or uninitialized
                taskList = Storage.readList();
                n = taskList.size();
                if (n == 0) {
                    Ui.userMessage("There is no task in the list :)");
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(i).append(". ").append(taskList.get(i - 1).toString());
                if (i != n) {
                    sb.append("\n");
                }
            }
            Ui.userMessage(sb.toString());
        } catch (IOException e) {
            Ui.fileError();
        }
    }

}
