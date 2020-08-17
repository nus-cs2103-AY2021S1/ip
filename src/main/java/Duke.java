import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "______________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] storage = new Task[100];
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println("Beep Boop! Hello there!\n" + "What can I do for you?");
        System.out.println(divider);

        /* Takes in user inputs. Program terminates when the String "bye" is entered.
        Program stores user inputs as Tasks and returns the list when the String "list" is entered.
        When "done xx" is entered, Task xx in the list is marked as done.
         */
        int pointer = 1;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(divider);

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (Integer i = 1; i < pointer; i++) {
                    System.out.println(i.toString() + ". [" + storage[i - 1].getStatusIcon() + "] " + storage[i - 1].description);
                }
            } else if (input.contains("done")) {
                int taskToMark = Integer.parseInt(input.substring(5)) - 1;
                storage[taskToMark].markAsDone();
                System.out.println("Task Accomplished! I've marked this task as done:");
                System.out.println("[" + storage[taskToMark].getStatusIcon() + "] " + storage[taskToMark].description);
            } else {
                System.out.println("Added: " + input);
                storage[pointer - 1] = new Task(input);
                pointer++;
            }

            System.out.println(divider);
            input = sc.nextLine();
        }

        System.out.println(divider);
        System.out.println("Goodbye, have a nice day :D");
        System.out.println(divider);
    }
}
