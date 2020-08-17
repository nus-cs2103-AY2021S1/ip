import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static ArrayList<Task> list = new ArrayList<>();

    public static void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void reply(String input) {
        if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < list.size() + 1; i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        } else if (input.startsWith("done ") || input.equals("done")) {
            if (input.length() > 5) {
                String item = input.substring(5);
                markAsDone(item);
            } else {
                System.out.println("Which task do you want to mark as done?");
            }
        } else {
            addTask(input);
        }
    }

    public static void addTask(String input) {
        Task task;
        if (input.startsWith("todo ")) {
            String name = input.substring(5);
            task = new ToDo(name);
        } else if (input.startsWith("deadline ")) {
            String details = input.substring(9);
            String[] split = details.split("/by ");
            String name = split[0];
            String by = split[1];
            task = new Deadline(name, by);
        } else if (input.startsWith("event ")) {
            String details = input.substring(6);
            String[] split = input.substring(6).split("/at ");
            String name = split[0];
            String duration = split[1];
            task = new Event(name, duration);
        } else {
            System.out.println("Please specify type of task");
            return;
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (list.size() > 1) {
            System.out.println("Now you have " + list.size() + " tasks in your list");
        } else {
            System.out.println("Now you have " + list.size() + " task in your list");
        }
    }

    public static void markAsDone(String item) {
        int index = Integer.parseInt(item) - 1;
        if (index > -1 && index < list.size()) {
            list.get(index).done();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index));
        } else {
            System.out.println("This task is not in your list");
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            reply(input);
            input = sc.nextLine();
        }
        exit();
    }
}
