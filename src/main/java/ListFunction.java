import java.util.ArrayList;
import java.util.List;

public class ListFunction {
    private static List<Task> taskList = new ArrayList<>();

    public static void add(String taskName) {
        taskList.add(new Task(taskName));
        System.out.println("Task added: " + taskName);
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
