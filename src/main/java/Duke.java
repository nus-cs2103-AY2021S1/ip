import java.net.IDN;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String INDENT = "    ";
    private final static String horizL = INDENT +
            "____________________________________________________________";
    private final static String logo = INDENT + " ____        _        \n"
            + INDENT + "|  _ \\ _   _| | _____ \n"
            + INDENT + "| | | | | | | |/ / _ \\\n"
            + INDENT + "| |_| | |_| |   <  __/\n"
            + INDENT + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> lst = new ArrayList<>();

    private static void intro() {
        System.out.println(horizL + "\n" + logo + "\n" +
                INDENT + "Hello. I'm Duke.\n" +
                INDENT + "How can I help you?\n" + horizL);
    }

    private static void ending() {
        System.out.println("\n" + INDENT +
                "Bye! Hope to see you again soon!\n" + horizL);
    }

    private static void displayList() {
        int len = lst.size();
        int index = 1;
        System.out.println(horizL);
        for (int i = 0; i < len; i++) {
            System.out.println(INDENT + index + ". " + lst.get(i));
            index++;
        }
        System.out.println(horizL);
    }

    private static void addTask(String input) {
        Task t1 = new Task(input);
        lst.add(t1);
    }

    private static void checkTask(int ind) {
        System.out.println(horizL);
        Task t1 = lst.get(ind - 1);
        t1.checked();
        System.out.println(INDENT + "Nice! I've marked this task as done: \n" +
                 INDENT + t1 + "\n" + horizL);
    }

    public static void main(String[] args) {
        Duke.intro();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.print(horizL);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                Duke.displayList();
            } else if (input.indexOf("done") == 0) {
                int ind = Integer.parseInt(input.split(" ")[1]);
                Duke.checkTask(ind);
            } else {
                    Duke.addTask(input);
                    System.out.println(horizL);
                    System.out.println(INDENT + "Added: " + input);
                    System.out.println(horizL);
            }
        }
        Duke.ending();
    }
}
