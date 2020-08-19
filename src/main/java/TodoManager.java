import java.util.*;

public class TodoManager {
    List<Task> lst = new ArrayList<>();
    public void addTask(String name) {
        lst.add(new Task(name));
        System.out.println(Helper.horiLine);
        System.out.println("added: " + name);
        System.out.println(Helper.horiLine);
    }
    public void markDone(String command) {
        int num = Integer.parseInt(command) - 1;
        lst.get(num).markDone();
        System.out.println(Helper.horiLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(lst.get(num));
        System.out.println(Helper.horiLine);
    }
    public void listTask() {
        System.out.println(Helper.horiLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + "." + lst.get(i));
        }
        System.out.println(Helper.horiLine);
    }

}