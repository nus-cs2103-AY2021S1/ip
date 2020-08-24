import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ListFunction {
    private static List<Task> taskList = new ArrayList<>();

    public static void add(TaskType type, String taskName) throws IOException {
        try {
            Todo todo = new Todo(taskName);
            taskList.add(todo);
            FileController.addToList(TaskType.TODO, 0, taskName, "");
            System.out.println("Got it. I've added the task:\n" + todo.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
        catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

    public static void add(TaskType type, String taskName, String time) throws IOException {
        try {
            if (type == TaskType.DEADLINE) {
                Deadline deadline = new Deadline(taskName, time);
                taskList.add(deadline);
                FileController.addToList(TaskType.DEADLINE, 0, taskName, time);
                System.out.println("Got it. I've added the task:");
                System.out.println(deadline.toString());
            } else {
                Event event = new Event(taskName, time);
                taskList.add(event);
                FileController.addToList(TaskType.EVENT, 0, taskName, time);
                System.out.println("Got it. I've added the task:");
                System.out.println(event.toString());
            }
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
        catch (IOException e) {
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
        FileController.setDone(index - 1);
        taskList.get(index - 1).setDone();
        System.out.println("Well done! I've marked this as done:");
        System.out.println(taskList.get(index - 1));
    }

    public static void delete(int index) throws InvalidParameterException, IOException {
        if (index > taskList.size() || index <= 0) {
            throw new InvalidParameterException("Out of bound");
        }
        FileController.delete(index - 1);
        System.out.println("Noted! I've removed this task:");
        System.out.println(taskList.get(index - 1));
        taskList.remove(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printList() throws IOException {
        try {
            int n = taskList.size();
            if (n == 0) { // empty or uninitialized
                taskList = FileController.readList();
                n = taskList.size();
                if (n == 0) {
                    System.out.println("There is no task in the list :)");
                    return;
                }
            }
            for (int i = 1; i <= n; i++)
                System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
        catch (IOException e) {
            System.out.println("Oops! Something went wrong :(");
        }
    }

}
