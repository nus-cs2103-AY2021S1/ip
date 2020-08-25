import java.util.*;

public class TodoManager {
    List<Task> lst = new ArrayList<>();

    private void addTodo(String desc) {
        desc = desc.trim();
        if (desc.isEmpty())
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        lst.add(new Todo(desc));
    }

    private void addDeadline(String desc) {
        desc = desc.trim();
        if (desc.isEmpty())
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for deadline task");
        String newDesc = desc.substring(0, desc.indexOf('/') - 1);
        String time = desc.substring(desc.indexOf('/') + 4);
        lst.add(new Deadline(newDesc, time));

    }

    public void addEvent(String desc) {
        desc = desc.trim();
        if (desc.isEmpty())
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for event task");
        String newDesc = desc.substring(0, desc.indexOf('/') - 1);
        String time = desc.substring(desc.indexOf('/') + 4);
        lst.add(new Event(newDesc, time));
    }

    public void addTask(String command) {
        if (command.startsWith("todo")) addTodo(command.substring(4));
        else if (command.startsWith("deadline")) addDeadline(command.substring(8));
        else if (command.startsWith("event")) addEvent(command.substring(5));
        System.out.println(Helper.horiLine);
        System.out.println("Got it. I've added this task: ");
        System.out.println(lst.get(lst.size() - 1));
        System.out.println(String.format("Now you have %d tasks in the list.", lst.size()));
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
    public void delete(String command) {
        int num = Integer.parseInt(command) - 1;
        Task cur = lst.remove(num);
        System.out.println(Helper.horiLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println(cur);
        System.out.println(String.format("Now you have %d tasks in the list.", lst.size()));
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