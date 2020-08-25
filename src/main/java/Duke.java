import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String line = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<>();

    public static void start() {
         String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         String start = "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
         System.out.println(line);
         System.out.println(logo);
         System.out.println(start);
         System.out.println(line);
    }

    public static void end() {
        String end = "Goodbye! Hope to see you again soon. :)";
        System.out.println(line);
        System.out.println(end);
        System.out.println(line);
    }

    public static void list() {
        if (list.isEmpty()) {
            System.out.println("You have no tasks, go out and have fun! ~");
        } else {
            System.out.println("Here is your to-do list:\n");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1).toString());
            }
        }
    }

    public static void done(String input) throws MissingNumberException {
        if (input.length() < 6) {
            throw new MissingNumberException();
        } else {
            int taskNumber = Integer.parseInt(input.substring(5));
            list.get(taskNumber - 1).completed();
            System.out.println("You've completed this task:");
            System.out.println((list.get(taskNumber - 1)).toString());
        }
    }

    public static void delete(String input) throws MissingNumberException {
        if (input.length() < 8) {
            throw new MissingNumberException();
        } else {
            int taskNumber = Integer.parseInt(input.substring(7));
            String deleted = (list.get(taskNumber - 1)).toString();
            list.remove(taskNumber - 1);
            System.out.println("Ok, this task has been kicked off your to-do list:");
            System.out.println(deleted);
            System.out.println("Now you have " + list.size() + " tasks in your list.");
        }
    }

    public static void todo(String input) throws IncompleteInputException {
        if (input.length() < 6) {
            throw new IncompleteInputException();
        } else {
            String s = input.substring(5);
            Todo todo = new Todo(s);
            list.add(todo);
            System.out.println("I've added this task:\n");
            System.out.println(todo.toString());
            System.out.println("Now you have " + list.size() + " tasks in your list.");
        }
    }

    public static void event(String input) throws IncompleteInputException {
        if (input.length() < 7 || !input.contains("at")) {
            throw new IncompleteInputException();
        } else {
            String task = input.substring(6, input.indexOf('/') - 1);
            String date = input.substring(input.lastIndexOf("at") + 3);
            Event event = new Event(task, date);
            list.add(event);
            System.out.println("I've added this task:\n");
            System.out.println(event.toString());
            System.out.println("Now you have " + list.size() + " tasks in your list.");
        }
    }

    public static void deadline(String input) throws IncompleteInputException {
        if (input.length() < 10 || !input.contains("by")) {
            throw new IncompleteInputException();
        } else {
            String task = input.substring(9, input.indexOf('/') - 1);
            String date = input.substring(input.lastIndexOf("by") + 3);
            Deadline deadline = new Deadline(task, date);
            list.add(deadline);
            System.out.println("I've added this task:\n");
            System.out.println(deadline.toString());
            System.out.println("Now you have " + list.size() + " tasks in your list.");
        }
    }

    public static void main(String[] args) {
        // Start
        start();

        // Take in inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                System.out.println(line);

                // Show list
                if (input.equals("list")) {
                    list();

                    // Add task
                } else if (input.contains("todo")) {
                    todo(input);

                    // Add event
                } else if (input.contains("event")) {
                    event(input);

                    // Add deadline
                } else if (input.contains("deadline")) {
                    deadline(input);

                    // Complete task
                } else if (input.contains("done")) {
                    done(input);

                    // Delete task
                } else if (input.contains("delete")) {
                    delete(input);

                } else {
                    throw new WrongCommandException();
                }

                System.out.println(line);
                input = sc.nextLine();

            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(line);
                input = sc.nextLine();
            }
        }

        // End
        end();
    }
}
