import java.util.*;
import java.io.*;

public class TodoManager {
    List<Task> lst = new ArrayList<>();
    File file;

    TodoManager() {
        try {
            file = new File("data.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                Scanner sc = new Scanner(file);
                String type, desc, by, at;
                int status;
                while (sc.hasNext()) {
                    type = sc.nextLine();
                    desc = sc.nextLine();
                    status = Integer.parseInt(sc.nextLine());
                    if (type.equals("todo"))
                        lst.add(new Todo(desc));
                    else if (type.equals("deadline")) {
                        by = sc.nextLine();
                        lst.add(new Deadline(desc, by));
                    } else if (type.equals("event")) {
                        at = sc.nextLine();
                        lst.add(new Event(desc, at));
                    }
                    if (status == 1) lst.get(lst.size() - 1).markDone();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred when interacting with file.");
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : lst) {
                writer.write(task.toDisk() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when write to file");
            e.printStackTrace();
        }

    }

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