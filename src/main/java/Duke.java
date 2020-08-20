import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
    }

    private static void greet() {
        System.out.println("Hello! I am Wish\n" + "What can I do for you?");
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        ArrayList<Task> database = new ArrayList<>(100);
        String description;
        while (!s.equals("bye")) {
            switch (s) {
                case "list":
                    list(database);
                    break;

                case "done":
                    if (database.size() != 0) {
                        int index = sc.nextInt();
                        database.get(index - 1).markAsDone();
                        System.out.println("Nice! I have marked this task as done!");
                    } else {
                        System.out.println("There are no tasks in the database to mark as done");
                    }
                    break;

                case "todo":
                    description = sc.nextLine();
                    ToDo toDo = new ToDo(description);
                    add(database, toDo);
                    break;

                case "deadline":
                case "event":
                    String toParse = sc.nextLine();
                    if (!toParse.equals("")) {
                        try {
                            if (s.equals("deadline")) {
                                String[] parsedStrings = toParse.split("/by ");
                                Deadline deadline = new Deadline(parsedStrings[0], parsedStrings[1]);
                                add(database, deadline);
                            } else {
                                String[] parsedStrings = toParse.split("/at ");
                                Event event = new Event(parsedStrings[0], parsedStrings[1]);
                                add(database, event);
                            }
                        } catch (Exception ArrayIndexOutOfBoundsException) {
                            System.out.println("Please input " +
                                    (s.equals("deadline") ? "the deadline" : "start and end timing") +
                                    " in a valid format!");
                        }
                    } else {
                        System.out.println("Description and " +
                                (s.equals("deadline") ? "the deadline" : "the start and end time") +
                                " of the task cannot be empty");
                    }
                    break;

                default:
                    System.out.println("Oops! No such command exists. Please enter a valid command");
                    break;
            }

            s = sc.next();
        }

        System.out.println("Goodbye! All the best and see you again soon!");
    }

    private static void add(ArrayList<Task> database, Task task) {
        database.add(task);
        System.out.println("Looking good! I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + database.size() + " tasks in your list");
    }

    private static void list(ArrayList<Task> database) {
        if (database.size() == 0) {
            System.out.println("There are no tasks in the database");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + ". " + database.get(i));
        }
    }
}
