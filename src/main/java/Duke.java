import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "_____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] task = new Task[100];
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println("Beep Boop! Hello there!\n" + "What can I do for you?");
        System.out.println(divider);

        /* Takes in user inputs. Program terminates when the String "bye" is entered.
        Program stores user inputs as Tasks and returns the list when the String "list" is entered.
        Tasks are categorised into "todo", "deadline" (to specify "by") and "event"  (to specify "at").
        When "done xx" is entered, Task xx in the list is marked as done.
         */
        int pointer = 1;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(divider);

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < pointer; i++) {
                    System.out.println(i + ". " + task[i - 1]);
                }
            } else if (input.startsWith("done")) {
                int taskToMark = Integer.parseInt(input.substring(5)) - 1;
                task[taskToMark].markAsDone();
                System.out.println("Task Accomplished! I've marked this task as done:");
                System.out.println(task[taskToMark]);
            } else {
                System.out.println("Got it! I've added this task:");
                if (input.startsWith("todo")) {
                    task[pointer - 1] = new ToDo(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    int endIndex = input.indexOf("/by") - 1;
                    task[pointer - 1] = new Deadline(input.substring(9, endIndex), input.substring(endIndex + 5));
                } else {
                    int endIndex = input.indexOf("/at") - 1;
                    task[pointer - 1] = new Event(input.substring(6, endIndex), input.substring(endIndex + 5));
                }
                System.out.println(task[pointer - 1]);
                System.out.println("Now you have " + pointer + " tasks in your list.");
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
