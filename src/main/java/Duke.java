import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke.");
        System.out.println("Duke: What can I do for you?");

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("Duke: " + input);
            input = sc.nextLine();
        }
        System.out.println("Duke: Bye. Hope to see you again!");
    }
}
