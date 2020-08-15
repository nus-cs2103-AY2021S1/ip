import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();

    private static void greet() {
        String greet = "\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void add(String task) {
        String echoizer = "\t____________________________________________________________\n"
                + "\tadded: %s\n"
                + "\t____________________________________________________________\n";
        tasks.add(task);
        System.out.printf((echoizer) + "%n", task);
    }

    private static void list() {
        System.out.print("\t____________________________________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static void exit() {
        String goodbye = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";
        System.out.print(goodbye);
        System.exit(0);
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
            } else {
                add(input);
            }
        }
    }
}
