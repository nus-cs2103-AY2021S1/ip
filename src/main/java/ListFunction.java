import java.util.ArrayList;
import java.util.List;

public class ListFunction {
    private static List<Task> taskList = new ArrayList<>();

    public static void add(int type, String taskName) {
        Todo todo = new Todo(taskName);
        taskList.add(todo);
        System.out.println("Got it. I've added the task:\n" + todo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void add(int type, String taskName, String time) {
        System.out.println("Got it. I've added the task:");
        if (type == 2) {
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

    public static void setDone(int index) {
        taskList.get(index - 1).setDone();
        System.out.println("Well done! I've marked this as done:");
        System.out.println(taskList.get(index - 1));
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
