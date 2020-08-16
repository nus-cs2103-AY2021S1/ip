import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> tasks; // a list of tasks

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void markTaskAsDone(int index) {
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + tasks.get(index - 1));
    }

    public void addTask(String content, String status, String time) {
        Task newTask = new Task(content, status, time);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        int size = tasks.size();
        System.out.println(String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task")));
    }

    public void addTask(String content, String status) {
        Task newTask = new Task(content, status);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        int size = tasks.size();
        System.out.println(String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task")));

    }

    public void deleteTask(int index) throws DukeException {
        int size = tasks.size();
        if (index > size) {
            throw new DukeException("☹ OOPS!!! Seems the index you provided is not in the list.");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println(String.format("Now you have %s tasks in the list.", tasks.size()));
    }
}
