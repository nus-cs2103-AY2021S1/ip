import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void greet() {
        String greet = "\t____________________________________________________________\n"
                + "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void add(String task) {
        String echoizer = "\t____________________________________________________________\n"
                + "\t added: %s\n"
                + "\t____________________________________________________________\n";
        tasks.add(new Task(task));
        System.out.printf((echoizer) + "%n", task);
    }

    private static void list() {
        System.out.print("\t____________________________________________________________\n");
        System.out.print("\t Here are the tasks in your list:\n");
        Task t;
        for (int i = 0; i < tasks.size(); i++) {
            t = tasks.get(i);
            System.out.printf("\t %d.[%s] %s%n", i + 1, t.getStatusIcon(), t);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static void exit() {
        String goodbye = "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";
        System.out.print(goodbye);
        System.exit(0);
    }

    private static void done(int i) {
        Task t = tasks.get(i - 1);
        t.markAsDone();
        String done = "\t____________________________________________________________\n"
                + "\t Nice! I've marked this task as done: \n"
                + "\t   [%s] %s\n"
                + "\t____________________________________________________________\n";
        System.out.printf(done, t.getStatusIcon(), t);
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from the other side of\n" + logo);

        greet();
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
            } else if (input.equals("list")) {
                list();
            } else if (input.split(" ")[0].equals("done")) {
                done(Integer.parseInt(input.split(" ")[1]));
            } else {
                add(input);
            }
        }
    }
}
