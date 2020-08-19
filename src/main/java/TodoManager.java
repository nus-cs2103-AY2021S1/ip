import java.util.*;

public class TodoManager {
    List<Task> lst = new ArrayList<>();
    public void addTask(String name) {
        lst.add(new Task(name));
        System.out.println(Helper.horiLine);
        System.out.println("added: " + name);
        System.out.println(Helper.horiLine);
    }
    public void listTask() {
        System.out.println(Helper.horiLine);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + "." + " " + lst.get(i).name);
        }
        System.out.println(Helper.horiLine);
    }

}