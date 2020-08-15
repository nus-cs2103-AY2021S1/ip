import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        while(running) {
            var input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");

                running = false;
            } else {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        String.format("    " + input + "\n") +
                        "    ____________________________________________________________\n");
            }
        }
    }
}
