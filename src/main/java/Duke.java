import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<String> taskList = new ArrayList<>();

    public static void readUserInput() {
        Scanner sc = new Scanner(System.in);
        String display = sc.nextLine();
        if (display.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (display.equals("list")) {
            for (int i = 0; i < taskList.size(); i++) {
                String item = taskList.get(i);
                System.out.println(
                        "Here are the tasks in your list: \n"
                        + (i + 1) + ". " + item + "\n");
            }
            readUserInput();
        } else {
            System.out.println("added: " + display);
            taskList.add(display);
            readUserInput();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
        System.out.println("Hello from\n" + logo + "\n" + greeting);
        readUserInput();
    }
}
