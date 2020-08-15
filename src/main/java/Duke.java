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
                + "\t Got it. I've added this task: \n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n"
                + "\t____________________________________________________________\n";
        if (task.startsWith("todo")) {
            tasks.add(new ToDo(task.substring(5)));
        } else if (task.startsWith("deadline")) {
            String[] taskArr = task.substring(9).split(" /by ");
            tasks.add(new Deadline(taskArr[0], taskArr[1]));
        } else if (task.startsWith("event")) {
            String[] taskArr = task.substring(6).split(" /at ");
            tasks.add(new Event(taskArr[0], taskArr[1]));
        }
        System.out.printf((echoizer) + "%n", tasks.get(tasks.size() - 1), tasks.size());
    }

    private static void list() {
        System.out.print("\t____________________________________________________________\n");
        System.out.print("\t Here are the tasks in your list:\n");
        Task t;
        for (int i = 0; i < tasks.size(); i++) {
            t = tasks.get(i);
            System.out.printf("\t %d.%s%n", i + 1, t);
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
                + "\t   %s\n"
                + "\t____________________________________________________________\n";
        System.out.printf(done, t);
    }

    public static void main(String[] args) {
        String logo = " _____    __        \n"
                + "|     \\  |  |\n"
                + "|  |\\  \\ |  |\n"
                + "|  | \\  \\|  |\n"
                + "|__|  \\_____|\n";
        System.out.println(logo);
        greet();
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("done")) {
                done(Integer.parseInt(input.split(" ")[1]));
            } else {
                add(input);
            }
        }
    }
}
