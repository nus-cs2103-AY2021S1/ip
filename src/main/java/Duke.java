import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n"
                            + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            input = sc.next();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
