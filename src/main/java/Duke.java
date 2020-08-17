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
                String task = input.substring(5);
                markAsDone(task);
            } else {
                System.out.println("Which task do you want to mark as done?");
            }
        } else {
            addTask(input);
        }
    }

    public static void addTask(String input) {
        list.add(new Task(input));
        System.out.println("added: " + input);
    }

    public static void markAsDone(String task) {
        int index = Integer.parseInt(task) - 1;
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
