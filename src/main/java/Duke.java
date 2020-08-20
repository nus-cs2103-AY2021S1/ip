import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\n\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n\n");
        String input = sc.next();

        while (!input.equals(("bye"))) {
            System.out.println("\n" + input + "\n");
            input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            }
        }
    }
}
