
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private static final String indentation = "     ";

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void printAddedTask(Task task) {
        System.out.println(indentation + "Got it. I've added this task: ");
        System.out.println(indentation + "  " + task.toString());
        System.out.println(indentation + "Now you have "+ taskList.size() + " tasks in the list.");
    }

    public void printList() {
        System.out.println(indentation + "Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            String s = indentation + (i + 1) + ". " + taskList.get(i).toString();
            System.out.println(s);
        }
    }

    public void markTaskAsDone(int n) {
        System.out.println(indentation + "Nice! I've marked this task as done: ");
        Task task = taskList.get(n - 1);
        task.markAsDone();
        System.out.println(indentation + "  " + task.toString());
    }

    public void deleteTask(int n) {
        System.out.println(indentation + "Noted. I've removed this task:");
        Task task = taskList.get(n - 1);
        System.out.println(indentation + "  " + task.toString());
        taskList.remove(n - 1);
        System.out.println(indentation + "Now you have "+ taskList.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getNumOfTask() {
        return taskList.size();
    }
}
