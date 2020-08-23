import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    // Collection of user's tasks
    static List<Task> taskItems = new ArrayList<>();

    /**
     * Sends a greeting to user and indicates the INTRUBOT is active.
     */
    static void start() {
        String logo =
                "8888888 888b    888 88888888888 8888888b.  888     888 888888b.    .d88888b. 88888888888 \n" +
                        "  888   8888b   888     888     888   Y88b 888     888 888  \"88b  d88P\" \"Y88b    888     \n" +
                        "  888   88888b  888     888     888    888 888     888 888  .88P  888     888    888     \n" +
                        "  888   888Y88b 888     888     888   d88P 888     888 8888888K.  888     888    888     \n" +
                        "  888   888 Y88b888     888     8888888P\"  888     888 888  \"Y88b 888     888    888     \n" +
                        "  888   888  Y88888     888     888 T88b   888     888 888    888 888     888    888     \n" +
                        "  888   888   Y8888     888     888  T88b  Y88b. .d88P 888   d88P Y88b. .d88P    888     \n" +
                        "8888888 888    Y888     888     888   T88b  \"Y88888P\"  8888888P\"   \"Y88888P\"     888";
        printReply(replyFormatter("ITS ME: \n" + logo + "\nI want to know EVERYTHING ABOUT YOU"));
    }

    // EventHandlers
    static void handleDone(String reply) throws DukeException {
        try {
            int itemNumber = Integer.parseInt(reply.split(" ")[1]) - 1;
            Task task = taskItems.get(itemNumber);
            task.markDone();
            printReply(replyFormatter("Nice! I've marked this task as done:\n" + task.toString()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist");
        }
    }

    static void handleDelete(String reply) throws DukeException {
        try {
            int itemNumber = Integer.parseInt(reply.split(" ")[1]) - 1;
            Task task = taskItems.remove(itemNumber);
            printReply(deleteTaskReplyFormatter(task));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Cannot delete task that does not exist");
        }
    }


    /**
     * Parses user input and carry out operations on user's tasks
     */
    static void replyHandler(String reply) throws DukeException {
            String[] replyArray = reply.split(" ");
            String command = replyArray[0];
            if (command.equals("bye")) {
                printReply(replyFormatter(reply));
                System.exit(0);
            } else if (command.equals("list")) {
                printReply(replyFormatter(listFormatter(taskItems)));
            } else {
                try {
                    switch (command) {
                    case "done":
                        handleDone(reply);
                        break;
                    case "delete":
                        handleDelete(reply);
                        break;
                    case "todo":
                        Task newTodo = new ToDo(reply.substring(5));
                        taskItems.add(newTodo);
                        printReply(addTaskReplyFormatter(newTodo));
                        break;
                    case "deadline":
                        String[] taskAndTimeByArray = reply.split(" /by ");
                        String deadlineDescription = taskAndTimeByArray[0].substring(9);
                        String by = taskAndTimeByArray[1];
                        Task newDeadline = new Deadline(deadlineDescription, LocalDate.parse(by));
                        taskItems.add(newDeadline);
                        printReply(addTaskReplyFormatter(newDeadline));
                        break;
                    case "event":
                        String[] taskAndTimeAtArray = reply.split(" /at ");
                        String eventDescription = taskAndTimeAtArray[0].substring(6);
                        String at = taskAndTimeAtArray[1];
                        Task newEvent = new Event(eventDescription, LocalDate.parse(at));
                        taskItems.add(newEvent);
                        printReply(addTaskReplyFormatter(newEvent));
                        break;
                    default:
                        throw new DukeException("Invalid Command Exception");
                    }
                } catch (DateTimeParseException e) {
                   throw new DukeException(String.format(
                           "Time format has to be in the form: YYYY-MM-DD %s", e.getMessage()));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException(String.format("No arguments specified for %s", command));
                }
            }

    }

    // Formatting and UI
    static String replyFormatter(String reply) {
        String partition = "__________________________";
        return String.format(partition + "\n%s\n" + partition, reply);
    }

    static void errorReply(String reply) {
        System.out.println(replyFormatter(String.format("Something is amiss `(OCO)/ !!: %s", reply)));
    }

    static String addTaskReplyFormatter(Task task) {
        return replyFormatter(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.size()));
    }

    static String deleteTaskReplyFormatter(Task task) {
        return replyFormatter(String.format("HAI. I've deleted this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.size()));
    }

    static String listFormatter(List<Task> ls) {
        String formattedListString = "";
        for (int i = 0; i < ls.size(); i ++) {
            formattedListString+= String.format("%d. %s\n", i + 1, ls.get(i));
        }
        return formattedListString;
    }

    static void printReply(String reply) {
        System.out.println(reply);
    }

    /**
     * main driver function
     * @param args
     */
    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String reply = sc.nextLine();
            try {
                replyHandler(reply);
            } catch (DukeException duked) {
                errorReply(duked.getMessage());
            }
        }
    }
}
