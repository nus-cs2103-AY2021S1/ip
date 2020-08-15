import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private final ArrayList<Task> ls;

    public Alice() {
        ls = new ArrayList<>();
        greet();
        prompt();
    }

    public boolean reply(String s) {
        if (s.equals("bye")) {
            display("Goodbye. Hope to see you again soon!");
            return false;
        }

        String[] arr = s.split(" ", 2);
        if (arr[0].equals("list")) {
            // Display task list
            print_list();
        } else if (arr[0].equals("done")) {
            // Mark as done
            try {
                int index = Integer.parseInt(arr[1]) - 1;
                ls.get(index).markAsDone();
                display("Great work! I've marked this task as done: \n    " + ls.get(index));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                display("Sorry! I could not register that. Please use a valid number");
            }
        } else if (arr.length == 2 && arr[0].equals("todo")) {
            // Add to-do
            addTask(new Todo(arr[1]));
        } else if (arr.length == 2 && arr[0].equals("deadline")) {
            String[] details = arr[1].split(" /by ", 2);
            if (details.length == 2) {
                addTask(new Deadline(details[0], details[1]));
            } else {
                display("Did you forget to include '/by'?");
            }
        } else if (arr.length == 2 && arr[0].equals("event")) {
            String[] details = arr[1].split(" /at ", 2);
            if (details.length == 2) {
                addTask(new Event(details[0], details[1]));
            } else {
                display("Did you forget to include '/at'?");
            }
        } else {
            display("Sorry! I could not register that.");
        }

        prompt();
        return true;
    }

    private void addTask(Task t) {
        ls.add(t);
        display("Roger. I've added the task to your list:\n    " + t
                + "\nNow you have " + ls.size() + " task in your list");
    }

    private void display(String s) {
        System.out.println("____________________________________________________________\n"
                + s
                + "\n____________________________________________________________");
    }

    private void print_list() {
        System.out.println("____________________________________________________________" +
                "\nHere are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(i + 1 + ". " + ls.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private void greet() {
        String logo = "\n _____  _     _____ _____  _____ \n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__  \n" +
                "|  _  || |     | | | |    |  __| \n" +
                "| | | || |_____| |_| \\__/\\| |___ \n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/ \n";

        System.out.println(logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?\n" +
                "____________________________________________________________");
    }

    private void prompt() {
        System.out.print(" > ");
    }

    public static void main(String[] args) {
        Alice alice = new Alice();
        Scanner sc = new Scanner(System.in);

        String cmd = sc.nextLine();
        while (alice.reply(cmd)) {
            cmd = sc.nextLine();
        }
    }
}
