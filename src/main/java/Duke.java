import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Serves as a chat bot. Duke can keep a record of user's inputs as a list of
 * tasks, mark them as completed when they are done, and show the user the list
 * of tasks upon request.
 */
public class Duke {

    /** Star Bot's logo shown upon start up */
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

    /** Divider that delineates Star Bot's replies */
    private static String divider =
            "------------------------------------------------------\n";

    /** Stores user's tasks */
    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(logo + "\nHello, I'm Star Bot! What can I do for " +
                "you?\nSay \"bye\" to exit.\n");

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) { // Exits the program
                System.out.println(botReply("Goodbye, see you again soon!"));
                System.exit(0);
            } else if (line.equals("list")) { // Lists out task list
                System.out.println(botReply(printList()));
            } else if (line.length() > 4 && line.substring(0,4)
                    .equals("done")) { // Done with a task
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                Task completedTask = taskList.get(taskIndex);
                completedTask.markAsDone();
                System.out.println(botReply("Nice! I've marked this task as " +
                        "done:\n" + completedTask.toString()));
            } else { // Add task to task list
                taskList.add(new Task(line, taskList.size() + 1));
                System.out.println(botReply("added: " + line));
            }
        }
    }

    /** Standardises the look of Star Bot's replies by wrapping it in
     * dividers */
    private static String botReply(String reply) {
        return divider + reply + "\n" + divider;
    }

    /** Formats the task list to be shown to the user */
    private static String printList() {
        String result = "";
        for (Task task : taskList) {
            result += task + "\n";
        }
        return result.substring(0,result.length() - 1);
    }
}
