import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initial greeting, prompt user for commands
        String logo = "     ___   ____    __    ____  _______     _______.  ______   .___  ___.  _______          ______\n"
                + "    /   \\  \\   \\  /  \\  /   / |   ____|   /       | /  __  \\  |   \\/   | |   ____|        /  __  \\\n"
                + "   /  ^  \\  \\   \\/    \\/   /  |  |__     |   (----`|  |  |  | |  \\  /  | |  |__    ______|  |  |  |\n"
                + "  /  /_\\  \\  \\            /   |   __|     \\   \\    |  |  |  | |  |\\/|  | |   __|  |______|  |  |  |\n"
                + " /  _____  \\  \\    /\\    /    |  |____.----)   |   |  `--'  | |  |  |  | |  |____        |  `--'  |\n"
                + "/__/     \\__\\  \\__/  \\__/     |_______|_______/     \\______/  |__|  |__| |_______|        \\______/\n";
        System.out.println(logo);
        Printer.printGeneralChatWindow("Greetings! I'm Awesome-O.", "What can I do for you?");

        // Assuming no more than 100 tasks
        final int taskCapacity = 100;

        // Initialise a fixed array of tasks to store
        List<Task> tasks = new ArrayList<>(taskCapacity);

        // Start scanning for user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Loop continues echoing input until input == "bye"
        while (!input.equals("bye")) {
            // Tag refers to the command to perform
            String tag = input.split(" ")[0];

            try {
                switch (tag) {
                    case "list":
                        Printer.printTasksChatWindow(tasks);
                        break;

                    case "done":
                        Printer.printDoneTaskChatWindow(completeTask(input, tasks));
                        break;

                    case "delete":
                        Printer.printDeleteTaskChatWindow(deleteTask(input, tasks), tasks.size());
                        break;

                    case "todo":
                    case "event":
                    case "deadline":
                        Task toAdd = addTask(tag, input, tasks);
                        Printer.printAddTaskChatWindow(toAdd, tasks.size());
                        break;

                    default:
                        throw new DukeUnknownInputException(input);
                }
            } catch (DukeUnknownInputException e) {
                Printer.printGeneralChatWindow("ERROR: Unknown input! Try again.");
            } catch (IndexOutOfBoundsException e) {
                Printer.printGeneralChatWindow("ERROR: Invalid list number input!");
            } catch (DukeInvalidTaskDescriptionException e){
                Printer.printGeneralChatWindow("ERROR: The description of a task cannot be empty!");
            } catch (DukeInvalidTaskDurationException e) {
                Printer.printGeneralChatWindow("ERROR: Please specify a date/time for this task!");
            } finally {
                input = sc.nextLine();
            }
        }

        // Print goodbye chat window
        Printer.printGeneralChatWindow("Thank you for talking to Awesome-O.", "Have a nice day. Goodbye!");
        System.out.println(logo);
    }

    private static Task completeTask(String input, List<Task> tasks) {
        // Obtain index within list of tasks
        int index = Integer.parseInt(input.substring(5)) - 1;
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    private static Task deleteTask(String input, List<Task> tasks) {
        // Obtain index within list of tasks
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    private static Task addTask(String tag, String input, List<Task> tasks)
            throws DukeInvalidTaskDescriptionException,
                   DukeInvalidEventDurationException,
                   DukeInvalidDeadlineDurationException {
        Task toAdd = null;
        try {
            switch (tag) {
                case "todo":
                    String toDoText = input.substring(5);
                    ToDo toDo = new ToDo(toDoText);
                    tasks.add(toDo);
                    toAdd = toDo;
                    break;

                case "event":
                    String[] eventText = input.substring(6).split(" /at ");
                    String eventDescription = eventText[0];

                    if (eventText.length == 1) {
                        throw new DukeInvalidEventDurationException(input);
                    }

                    String eventAt = eventText[1];
                    Event event = new Event(eventDescription, eventAt);
                    tasks.add(event);
                    toAdd = event;
                    break;

                case "deadline":
                    String[] deadlineText = input.substring(9).split(" /by ");
                    String deadlineDescription = deadlineText[0];

                    if (deadlineText.length == 1) {
                        throw new DukeInvalidDeadlineDurationException(input);
                    }

                    String deadlineBy = deadlineText[1];
                    Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                    tasks.add(deadline);
                    toAdd = deadline;
                    break;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidTaskDescriptionException(input);
        }
        return toAdd;
    } 
}
