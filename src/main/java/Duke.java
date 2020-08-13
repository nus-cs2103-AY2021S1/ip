import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _         -----------\n"
                + "|  _ \\ _   _| | _____  \\__    __/\n"
                + "| | | | | | | |/ / _ \\    |  |\n"
                + "| |_| | |_| |   <  __/  __|  |\n"
                + "|____/ \\__,_|_|\\_\\___|  \\____/\n";
        System.out.println(logo);
        Printer.printGeneralChatWindow("Greetings! I'm Duke J.", "What can I do for you?");

        // Assuming no more than 100 tasks
        final int taskCapacity = 100;

        // Initialise a fixed array of tasks to store
        List<Task> tasks = new ArrayList<>(taskCapacity);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Loop continues echoing input until input == "bye"
        while (!input.equals("bye")) {
            switch (input.split(" ")[0]) {
                case "list":
                    Printer.printTasksChatWindow(tasks);
                    break;

                case "done":
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    Printer.printDoneTask(task);
                    break;

                default:
                    tasks.add(new Task(input));
                    Printer.printGeneralChatWindow("added: " + input);
            }
            input = sc.nextLine();
        }

        Printer.printGeneralChatWindow("Thank you for using Duke J.", "Have a nice day. Goodbye!");
    }
}
