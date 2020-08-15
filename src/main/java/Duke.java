import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String[] tasks = new String[100];
    public static int numberOfTasks = 0;

    public static void printTasks() {
        System.out.println(
                "    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.format("     %d. %s\n", i + 1, tasks[i]);
        }
        System.out.println(
                "    ____________________________________________________________\n");
    }

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
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");

                running = false;
            } else if (input.equals("list")) {
                printTasks();
            } else {
                tasks[numberOfTasks] = input;
                numberOfTasks++;

                System.out.println(
                        "    ____________________________________________________________\n" +
                        String.format("     added:" + input + "\n") +
                        "    ____________________________________________________________\n");
            }
        }
    }
}
