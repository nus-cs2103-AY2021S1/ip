import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "How can I help you?");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                String command = sc.nextLine();
                String[] commandArr = command.split(" ", 2);
                Task task;
                String[] strings;
                int index;
                if (commandArr[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                switch (commandArr[0]) {
                    case "todo":
                        if (commandArr.length == 1 || commandArr[1].strip().equals("")) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new ToDos(commandArr[1]);
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        if (commandArr.length == 1 || commandArr[1].strip().equals("")) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        strings = commandArr[1].split("/by");
                        task = new Deadlines(strings[0].strip(), strings[1].strip());
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    case "event":
                        if (commandArr.length == 1 || commandArr[1].strip().equals("")) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                        }
                        strings = commandArr[1].split("/at");
                        task = new Events(strings[0].strip(), strings[1].strip());
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    case "done":
                        index = Integer.parseInt(commandArr[1]) - 1;
                        tasks.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n " + tasks.get(index));
                        break;
                    case "delete":
                        index = Integer.parseInt(commandArr[1]) - 1;
                        Task removedTask = tasks.remove(index);
                        System.out.println("Noted. I've removed this task:\n " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}