import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // Initialise strings to separate messages from Duke
        // and commands from CLI
        String servantSpeak = "Duke:\n";
        String masterSpeak = "Your Command Sire:";

        // Introduction at the beginning of the chat
        System.out.println(servantSpeak
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");

        // Initialise the Scanner object to get input from user
        Scanner myObj = new Scanner(System.in);
        String input;

        // Initialise ArrayList to store tasks from user
        ArrayList<Task> userTasks = new ArrayList<Task>();

        // Start chat
        do {
            // Get input from user
            System.out.println(masterSpeak);
            input = myObj.nextLine();
            System.out.println();

            // If user inputs "bye" in any case, end the chat
            if (input.equals("bye")) {
                System.out.println(servantSpeak
                        + "    It was a pleasure to serve you my Liege.\n"
                        + "    Till next time.");
                break;
            }

            // If user marks "done" mark task as isDone = true
            if (input.contains("done ")) {
                // Get the index stated after "done" by parsing the string
                int index = Integer.parseInt(input.substring(5)) - 1;

                // Try catch block in case task was not found
                try {
                    userTasks.get(index).setDone();
                    System.out.println(servantSpeak
                            + "    As you wish Sire. I have marked this task as done:\n"
                            + "       [" + userTasks.get(index).getStatusIcon() + "] "
                            + userTasks.get(index).getDescription());
                } catch (IndexOutOfBoundsException err) {
                    System.out.println(servantSpeak
                            + "    There seems to be an error your Grace. "
                            + "That task cannot be found!");
                }
                System.out.println();
                continue;
            }

            // If user requests for list, display list
            if (input.equals("list")) {
                int count = 1;
                System.out.println(servantSpeak
                        + "    Here are your tasks your Majesty:");
                for (Task i : userTasks) {
                    System.out.println("    "
                            + count + ". "
                            + "[" + i.getStatusIcon() + "] "
                            + i.getDescription());
                    count++;
                }
                System.out.println();
                continue;
            }

            // Default: Add input from user into the ArrayList
            userTasks.add(new Task(input));
            System.out.println(servantSpeak
                    + "    I have added the task: " + input + "\n");
        } while (!input.equals("bye"));

    }
}
