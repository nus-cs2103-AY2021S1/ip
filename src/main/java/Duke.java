import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
    private static List<String> taskList = new ArrayList<>();

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
            }
            else { // Add task to task list
                taskList.add(line);
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
        int index = 1;
        for (String task : taskList) {
            if (index != 1) {
                result += "\n";
            }
            result += index++ + ". " + task;
        }
        return result;
    }
}
