import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void greet() {
        String greet = "\t____________________________________________________________\n"
                + "\t Hello! I'm Nite, the Dark Knight\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void add(String task) throws DukeException{
        String echoizer = "\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n"
                + "\t____________________________________________________________\n";
        if (task.startsWith("todo")) {
            if (task.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            tasks.add(new ToDo(task.substring(5)));
        } else if (task.startsWith("deadline")) {
            if (task.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] taskArr = task.substring(9).split(" /by ");
            try {
                tasks.add(new Deadline(taskArr[0], taskArr[1]));
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of a deadline.");
            }
        } else if (task.startsWith("event")) {
            if (task.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] taskArr = task.substring(6).split(" /at ");
            try {
                tasks.add(new Event(taskArr[0], taskArr[1]));
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of an event.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
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

    private static void done(int i) throws DukeException {
        try {
            Task t = tasks.get(i - 1);
            t.markAsDone();
            String done = "\t____________________________________________________________\n"
                    + "\t Nice! I've marked this task as done:\n"
                    + "\t   %s\n"
                    + "\t____________________________________________________________\n";
            System.out.printf((done) + "%n", t);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't complete a task that does not exist.");
        }
    }

    private static void delete(int i) throws DukeException {
        try {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            String done = "\t____________________________________________________________\n"
                    + "\t Noted. I've removed this task:\n"
                    + "\t   %s\n"
                    + "\t Now you have %d tasks in the list.\n"
                    + "\t____________________________________________________________\n";
            System.out.printf((done) + "%n", t, tasks.size());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't delete a task that does not exist.");
        }
    }

    public static void main(String[] args) {
        String logo = " _____     __   __   ________   _________\n"
                + "|     \\   |  | |  | |__    __| |   ______|\n"
                + "|  |\\  \\  |  | |  |    |  |    |  |______\n"
                + "|  | \\  \\ |  | |  |    |  |    |   ______|\n"
                + "|  |  \\  \\|  | |  |    |  |    |  |______\n"
                + "|__|   \\_____| |__|    |__|    |_________|\n";
        System.out.println(logo);
        greet();
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNext()) {
            try {
                input = sc.nextLine();

                if (input.equals("bye")) {
                    exit();
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("done")) {
                    done(Integer.parseInt(input.split(" ")[1]));
                } else if (input.startsWith("delete")) {
                    delete(Integer.parseInt(input.split(" ")[1]));
                } else {
                    add(input);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
