import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello, I'm Duke");
        System.out.println("I can help you keep track of all your tasks! ☆*:.｡.o(≧▽≦)o.｡.:*☆");
        System.out.println("How to add tasks to the list:");
        System.out.println("ToDo - type 'todo' followed by the description");
        System.out.println("Deadline - type 'deadline' followed by the description," +
                "then '/by', then due date");
        System.out.println("Event - type 'event' followed by the description, " +
                "then '/at', then timing");
        System.out.println("Type 'done' followed by the task number " +
                "and I'll mark it as done");
        System.out.println("Type 'list' to see the list");
        System.out.println("Type 'bye' to exit");
        String next;
        try {
            while (true) {
                next = sc.nextLine();
                if (next.equalsIgnoreCase("bye")) {
                    System.out.println("Bye! :>");
                    sc.close();
                    System.exit(0);
                } else if (next.equalsIgnoreCase("list")) {
                    if (list.size() == 0) {
                        System.out.println("List is empty");
                    } else {
                        System.out.println("Items in list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i).toString());
                        }
                    }
                } else if (next.length() >= 4 && next.substring(0, 4).equalsIgnoreCase("done")) {
                    int taskNo = Integer.parseInt(next.substring(5));
                    if (taskNo > list.size()) {
                        throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
                    } else {
                        Task completedTask = list.get(taskNo - 1);
                        completedTask.markAsDone();
                        System.out.println("Task marked complete:");
                        System.out.println(completedTask.toString());
                    }
                } else if (next.length() >= 6 && next.substring(0, 6).equalsIgnoreCase("delete")) {
                    int taskNo = Integer.parseInt(next.substring(7));
                    if (taskNo > list.size()) {
                        throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
                    } else {
                        Task deletedTask = list.remove(taskNo - 1);
                        System.out.println("Task deleted:");
                        System.out.println(deletedTask.toString());
                    }
                } else {
                    Task newTask;
                    if (next.length() >= 4 && next.substring(0, 4).equalsIgnoreCase("todo")) {
                        if (next.length() < 6) {
                            throw new DukeException("Task cannot be empty _(´ཀ`」 ∠)_");
                        } else {
                            newTask = new ToDo(next.substring(5));
                        }
                    } else if (next.length() >= 5 && next.substring(0, 5).equalsIgnoreCase("event")) {
                        if (next.length() < 7) {
                            throw new DukeException("Event cannot be empty _(´ཀ`」 ∠)_");
                        } else {
                            newTask = new Event(next.substring(6));
                        }
                    } else if (next.length() >= 8 && next.substring(0, 8).equalsIgnoreCase("deadline")) {
                        if (next.length() < 10) {
                            throw new DukeException("Deadline cannot be empty _(´ཀ`」 ∠)_");
                        } else {
                            newTask = new Deadline(next.substring(9));
                        }
                    } else {
                        throw new DukeException("I have no idea what that means ¯\\_(ツ)_/¯");
                    }
                    list.add(newTask);
                    System.out.println("Added: " + newTask.toString());
                    System.out.println("Total tasks: " + list.size());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
