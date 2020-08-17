import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> store = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                int count = 0;
                for (String text : store) {
                    count++;
                    System.out.println(String.format("%d. %s", count, text));
                }
            } else {
                store.add(input);
                System.out.println("added: " + input);
            }
        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
