import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Integer Intger;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<>();
        String in = scan.nextLine();
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + storage.get(i));
                }
            } else if (in.contains("done ")) {
                int task = Intger.parseInt(in.replace("done ", "")) - 1;
                storage.get(task).done = true;
                System.out.println("\tNice! I've marked this task as done:\n\t" + storage.get(task));
            } else {
                System.out.println("\tadded: " + in);
                storage.add(new Task(in));
            }
            in = scan.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon!");

    }
}

class Task {
    String task;
    boolean done;
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    @Override
    public String toString() {
        return done ? ("[✓] " + task) : ("[✗] " + task);
    }
}
