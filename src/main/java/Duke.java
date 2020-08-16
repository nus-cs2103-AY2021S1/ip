import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Serves as a chat bot. Duke can keep a record of user's inputs as a list of
 * tasks, mark them as completed when they are done, and show the user the list
 * of tasks upon request.
 */
public class Duke {

    /**
     * Star Bot's logo shown upon start up
     */
    private static String logo = "     _______.___________.    ___      " +
            ".______     " +
            " \n" +
            "    /       |           |   /   \\     |   _  \\     \n" +
            "   |   (----`---|  |----`  /  ^  \\    |  |_)  |    \n" +
            "    \\   \\       |  |      /  /_\\  \\   |      /     \n" +
            ".----)   |      |  |     /  _____  \\  |  |\\  \\----.\n" +
            "|_______/       |__|    /__/     \\__\\ | _| `._____|\n" +
            "                                                   \n" +
            "         .______     ______   .___________.        \n" +
            "         |   _  \\   /  __  \\  |           |        \n" +
            "         |  |_)  | |  |  |  | `---|  |----`        \n" +
            "         |   _  <  |  |  |  |     |  |             \n" +
            "         |  |_)  | |  `--'  |     |  |             \n" +
            "         |______/   \\______/      |__|             \n" +
            "                                                   ";

    /**
     * Divider that delineates Star Bot's replies
     */
    private static String divider =
            "------------------------------------------------------\n";

    /**
     * Stores user's tasks
     */
    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(logo + "\nHello, I'm Star Bot! What can I do for " +
                "you?\nSay \"bye\" to exit.\n");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) { // Duke takes in user input indefinitely
            // until the user says "bye"
            String line = sc.nextLine();
            String[] splitLine = line.split(" ");
            try {
                if (line.equals("bye")) { // Exit the program
                    System.out.println(botReply("Goodbye, see you again soon!" +
                            " :)"));
                    System.exit(0);
                } else if (line.equals("list")) { // List out task list
                    System.out.println(botReply(printList()));
                } else if (splitLine[0].equals("done")) { // Done with a task
                    try {
                        if (splitLine.length != 2) { // If command is in a wrong
                            // format
                            throw new DoneWrongFormatException();
                        }
                        int taskIndex = Integer.parseInt(splitLine[1]) - 1;
                        // Index of task in the task list
                        Task completedTask = taskList.get(taskIndex);
                        completedTask.markAsDone();
                        System.out.println(botReply("Nice! I've marked this " +
                                "task as done:\n" + completedTask.toString()));
                    } catch (NumberFormatException e) { // Second argument of
                        // command was not a number, e.g. "done X"
                        throw new DoneWrongFormatException();
                    } catch (IndexOutOfBoundsException e) { // User requests
                        // for a task with an index not within the current
                        // task list
                        throw new TaskIndexOutOfBoundsException(splitLine[1]);
                    }
                } else if (splitLine[0].equals("todo")) { // Add To-Do task
                    try {
                        Task newTask = new ToDo(line.substring(5).trim());
                        taskList.add(newTask);
                        System.out.println(botReplyForAddTask(newTask));
                    } catch (IndexOutOfBoundsException | WrongFormatException e)
                    { // Command is in a wrong format
                        throw new TodoWrongFormatException();
                    }
                } else if (splitLine[0].equals("event")) { // Add Event task
                    try {
                        String[] splitLineIntoTwo = line.split("/at");
                        Task newTask = new Event(splitLineIntoTwo[0]
                                .substring(6).trim(),
                                splitLineIntoTwo[1].trim());
                        taskList.add(newTask);
                        System.out.println(botReplyForAddTask(newTask));
                    } catch (IndexOutOfBoundsException | WrongFormatException e)
                    { // Command is in a wrong format
                        throw new EventWrongFormatException();
                    }
                } else if (splitLine[0].equals("deadline")) { // Add Deadline
                    // task
                    try {
                        String[] splitLineIntoTwo = line.split("/by");
                        Task newTask = new Deadline(splitLineIntoTwo[0]
                                .substring(9).trim(),
                                splitLineIntoTwo[1].trim());
                        taskList.add(newTask);
                        System.out.println(botReplyForAddTask(newTask));
                    } catch (IndexOutOfBoundsException | WrongFormatException e)
                    { // Command is in a wrong format
                        throw new DeadlineWrongFormatException();
                    }
                } else { // Unknown command entered
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(botReply(e.defaultErrorMessage()));
            }
        }
    }

    /**
     * Standardises the look of Star Bot's replies by wrapping it in
     * dividers
     */
    private static String botReply(String reply) {
        return divider + reply + "\n" + divider;
    }

    private static String botReplyForAddTask(Task newTask) {
        return botReply("Got it. I've added this task:\n" + newTask + "\nNow " +
                "you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Formats the task list to be shown to the user
     */
    private static String printList() {
        int index = 1;
        String result = "Here are the tasks in your list:";
        for (Task task : taskList) {
            result += "\n" + index++ + "." + task;
        }
        return result;
    }
}
