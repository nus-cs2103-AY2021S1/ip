import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        readTasks();
    }

    private static void greet() {
        String logo =
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________" +
            "\nDuke: What can i do for you?");
    }

    private static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i);
            System.out.println(i + 1 + ". " + task);
        }
        System.out.println("--------------------------" +
                "-------------------------");
    }

    private static void readTasks() {
        Scanner sc = new Scanner(System.in);
        String reply = "Duke: Added - " + "%s" + "\n--------------------------" +
                "-------------------------";
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (input.equals("list")) {
                printTasks();
            } else {
                tasks.add(input);
                System.out.println(String.format(reply, input));
            }
        }
    }
}
