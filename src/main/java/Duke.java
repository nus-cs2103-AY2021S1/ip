import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> list = new ArrayList<>();

    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listOut();
            } else if (input.contains("done")) {
                try {
                    done(input);
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else {

                try {
                    if (input.contains("todo")) {
                        handleToDo(input);
                    } else if (input.contains("deadline")) {
                        handleDeadline(input);
                    } else if (input.contains("event")) {
                        handleEvent(input);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            }
        }
    }

    public static void handleEvent(String input) throws DukeException {
        if (input.strip().length() <= 5) {
            throw new DukeException(" ☹ OOPS!!! The description of a Event cannot be empty.");
        } else if (!input.contains("/")) {
            throw new DukeException(" ☹ OOPS!!! Event requires a date.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            String[] arr = taskName.split("/");
            Event task = new Event(arr[0], arr[1].substring(arr[1].indexOf(" ") + 1));
            add(task);
        }
    }

    public static void handleDeadline(String input) throws DukeException {
        if (input.strip().length() <= 8) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!input.contains("/")) {
            throw new DukeException(" ☹ OOPS!!! Deadline requires a date.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            String[] arr = taskName.split("/");
            Deadline task = new Deadline(arr[0], arr[1].substring(arr[1].indexOf(" ") + 1));
            add(task);
        }
    }

    public static void handleToDo(String input) throws DukeException {
        if (input.strip().length() <= 4) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            ToDo task = new ToDo(taskName);
            add(task);
        }
    }

    public static void add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        list.add(task);
        System.out.printf("     Now you have %d tasks in the list.\n", list.size());
        System.out.println("    ____________________________________________________________");
    }

    public static void listOut() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s \n", i + 1, list.get(i));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void done(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter done with a number.");
        } else {
            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]);

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            task.completed();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");

            System.out.println("       " + task);
            System.out.println("    ____________________________________________________________");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        greet();

        echo();

        exit();
    }
}
