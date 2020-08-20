import java.util.Scanner;

public class Duke {

    public static String line = "===================================================";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        StringBuilder description;
        String date;
        String type;
        Task[] tasks = new Task[100];
        int number = 0;

        printPart("Hello! I'm Duke\n" + "What can I do for you?");
        type = s.next();

        while(!type.equals("bye")) {
            try {
                switch (type) {
                    case "list":
                        System.out.println(line);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < number; i++) {
                            System.out.println(String.format("  %d. ", i + 1) + tasks[i].toString());
                        }
                        System.out.println(line + "\n");
                        break;

                    case "todo":
                        input = s.nextLine();
                        if (!(input.length() > 1)) {
                            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            tasks[number] = new Todo(input);
                            printPart("Got it. I've added this task:\n"
                                    + "  " + tasks[number].toString()
                                    + "\nNow you have " + (number + 1) + " tasks in the list.");
                            number++;
                        }
                        break;

                    case "deadline":
                        description = new StringBuilder();
                        input = s.next();
                        while (input.charAt(0) != '/') {
                            description.append(input).append(" ");
                            input = s.next();
                        }
                        date = s.nextLine().substring(1);
                        tasks[number] = new Deadline(description.toString(), date);
                        printPart("Got it. I've added this task:\n"
                                + "  " + tasks[number].toString()
                                + "\nNow you have " + (number + 1) + " tasks in the list.");
                        number++;
                        break;

                    case "event":
                        description = new StringBuilder();
                        input = s.next();
                        while (input.charAt(0) != '/') {
                            description.append(input).append(" ");
                            input = s.next();
                        }
                        date = s.nextLine().substring(1);
                        tasks[number] = new Event(description.toString(), date);
                        printPart("Got it. I've added this task:\n"
                                + "  " + tasks[number].toString()
                                + "\nNow you have " + (number + 1) + " tasks in the list.");
                        number++;
                        break;

                    case "done":
                        input = s.nextLine();
                        int n = Integer.parseInt(input.substring(1));
                        tasks[n - 1].markAsDone();
                        printPart("Nice! I've marked this task as done:\n" + "  " + tasks[n - 1].toString());
                        break;

                    default:
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printPart(e.getMessage());
            }

            type = s.next();
        }

        printPart("Bye. Hope to see you again soon!");
    }

    public static void printPart(String str) {
        System.out.println(line);
        System.out.println(str);
        System.out.println(line + "\n");
    }
}
