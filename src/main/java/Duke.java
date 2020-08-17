import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    List<Task> store;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.run();
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        int count = 0;
        for (Task task : store) {
            count++;
            System.out.println(String.format("%d. %s", count, task));
        }
    }

    public void doneTask(int num) {
        store.get(num - 1).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(store.get(num - 1));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        store = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            String[] splitted = input.split(" ", 2);
            String first = splitted[0];
            switch (first) {
                case "bye":
                    running = false;
                    break;
                case "list":
                    list();
                    break;
                case "done":
                    int num = Integer.parseInt(splitted[1]);
                    doneTask(num);
                    break;
                default:
                    store.add(new Task(input));
                    System.out.println("added: " + input);
            }
        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
