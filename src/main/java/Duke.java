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
            String tag = input.split(" ")[0];
            switch (tag) {
                case "list":
                    Printer.printTasksChatWindow(tasks);
                    break;

                case "done":
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    Printer.printDoneTaskChatWindow(task);
                    break;

                case "todo":
                case "event":
                case "deadline":
                    addTask(tag, input, tasks);
                    break;

                default:
                    Printer.printGeneralChatWindow("Unknown input. Try again.");
            }
            input = sc.nextLine();
        }

        Printer.printGeneralChatWindow("Thank you for using Duke J.", "Have a nice day. Goodbye!");
    }

    private static void addTask(String tag, String input, List<Task> tasks) {
        switch (tag) {
            case "todo":
                String toDoText = input.substring(5);
                ToDo toDo = new ToDo(toDoText);
                tasks.add(toDo);
                Printer.printAddTaskChatWindow(toDo, tasks.size());
                break;

            case "event":
                String[] eventText = input.substring(6).split(" /at ");
                String eventDescription = eventText[0];
                String eventAt = eventText[1];
                Event event = new Event(eventDescription, eventAt);
                tasks.add(event);
                Printer.printAddTaskChatWindow(event, tasks.size());
                break;

            case "deadline":
                String[] deadlineText = input.substring(9).split(" /by ");
                String deadlineDescription = deadlineText[0];
                String deadlineBy = deadlineText[1];
                Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                tasks.add(deadline);
                Printer.printAddTaskChatWindow(deadline, tasks.size());
                break;
        }
    }
}
