import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Create an empty list of tasks
        ArrayList<Task> tasks = new ArrayList<>();

        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String introduction = "Hello! I'm Duke\nWhat can I do for you?\n";
        String add = "added: ";

        // Print Duke's introduction
        System.out.println(divider + logo + introduction + divider);

        // Read input from input.txt
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    System.out.println(number + ". " + tasks.get(i).getText());
                }
            } else if (nextLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                tasks.add(new Task(nextLine));
                System.out.println(add + nextLine);
            }
        }
    }
}
