import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ListFunction {
    private static final List<Task> taskList = new ArrayList<>();

    public static void add(TaskType type, String taskName) {
        Todo todo = new Todo(taskName);
        taskList.add(todo);
        System.out.println("Got it. I've added the task:\n" + todo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void add(TaskType type, String taskName, String time) {
        System.out.println("Got it. I've added the task:");
        if (type == TaskType.DEADLINE) {
            Deadline deadline = new Deadline(taskName, time);
            taskList.add(deadline);
            System.out.println(deadline.toString());
        }
        else {
            Event event = new Event(taskName, time);
            taskList.add(event);
            System.out.println(event.toString());
        }
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static List<Task> getList() {
        return taskList;
    }

    public static void setDone(int index) throws InvalidParameterException {
        if (index > taskList.size() || index <= 0)
            throw new InvalidParameterException("Out of bound");
        taskList.get(index - 1).setDone();
        System.out.println("Well done! I've marked this as done:");
        System.out.println(taskList.get(index - 1));
    }

    public static void delete(int index) throws InvalidParameterException {
        if (index > taskList.size() || index <= 0)
            throw new InvalidParameterException("Out of bound");
        System.out.println("Noted! I've removed this task:");
        System.out.println(taskList.get(index - 1));
        taskList.remove(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printList() {
        int n = taskList.size();
        if (n == 0)
            System.out.println("There is no task in the list :)");
        else {
            for (int i = 1; i <= n; i++)
                System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
    }

}
