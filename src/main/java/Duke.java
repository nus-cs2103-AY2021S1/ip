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
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + ". " + ls.get(i));
                }
                printLine();
                continue;
            }

            if (input.contains("done")) {
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

            printLine();
            System.out.println("added: " + input);
            ls.add(new Task(input));
            printLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();

    }
}
