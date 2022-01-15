import java.nio.charset.Charset;
import java.util.*;

public class Duke {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        List<Task> ls = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        while (true) {
            String input = scan.nextLine();
            if (input.startsWith("bye")) {
                break;
            }

            if (input.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + ". " + ls.get(i));
                }
                printLine();
                continue;
            }

            if (input.startsWith("mark")) {
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    ls.get(index - 1).setDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(ls.get(index - 1));
                    printLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    continue;
                }
            }
            if (input.startsWith("unmark")) {
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    ls.get(index - 1).setUndone();
                    printLine();
                    System.out.println("OK, I've marked this task as not dont yet:  ");
                    System.out.println(ls.get(index - 1));
                    printLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    continue;
                }
            }

            if (input.startsWith("todo")) {
                Task task = new Todo(input.split("todo")[1]);

                ls.add(task);
                printLine();
                System.out.println("Got it. I've added this task: ");
                System.out.println(task);
                System.out.println("Now you have " + ls.size() + " tasks in the list.");
                printLine();
                continue;
            }

            if (input.startsWith("deadline")) {
                String[] splitted = input.split("deadline")[1].split("/by");
                Deadline toAdd = new Deadline(splitted[0].trim(), splitted[1].trim());
                ls.add(toAdd);
                printLine();
                System.out.println("Got it. I've added this task: ");
                System.out.println(toAdd);
                System.out.println("Now you have " + ls.size() + " tasks in the list.");
                printLine();
                continue;
            }

            if (input.startsWith("event")) {
                String[] splitted = input.split("event")[1].split("/at");
                Event toAdd = new Event(splitted[0].trim(), splitted[1].trim());
                ls.add(toAdd);
                printLine();
                System.out.println("Got it. I've added this task: ");
                System.out.println(toAdd);
                System.out.println("Now you have " + ls.size() + " tasks in the list.");
                printLine();
                continue;
            }

        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();

    }
}
