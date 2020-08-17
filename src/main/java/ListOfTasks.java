import java.util.List;

public class ListOfTasks {
    public static void showList(List<Task> tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }
}
