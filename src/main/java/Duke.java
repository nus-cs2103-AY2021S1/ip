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
                Task task;
                if (in.contains("todo ")) {
                    task = new Todo(in.replace("todo ", ""));
                } else if (in.contains("deadline ")) {
                    String[] split = in.replace("deadline ", "").split(" /by ");
                    task = new Deadline(split[0], split[1]);
                } else {
                    String[] split = in.replace("event ", "").split(" /at ");
                    task = new Event(split[0], split[1]);
                }
                storage.add(task);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + task);
                System.out.println("\tNow you have " + storage.size() + " tasks in the list.");

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

class Todo extends Task {
    Todo(String task) {
        super(task);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    String deadline;

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}

class Event extends Task {
    String duration;

    Event(String task, String duration) {
        super(task);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }
}