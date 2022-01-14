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

        List<String> ls = new ArrayList<>();

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
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + ". " + ls.get(i));
                }
                printLine();
                continue;
            }

            printLine();
            System.out.println("added: " + input);
            ls.add(input);
            printLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();

    }
}
