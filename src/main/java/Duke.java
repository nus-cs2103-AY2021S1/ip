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
                            + "       " + userTasks.get(index).toString());
                } catch (IndexOutOfBoundsException err) {
                    System.out.println(servantSpeak
                            + "    There seems to be an error your Grace. "
                            + "That task cannot be found!");
                }
                System.out.println();
                continue;
            }

            // If user requests for list, display list of tasks
            if (input.equals("list")) {
                int count = 1;
                System.out.println(servantSpeak
                        + "    Here are your tasks your Majesty:");
                for (Task i : userTasks) {
                    System.out.println("    "
                            + count + ". "
                            + i.toString());
                    count++;
                }
                System.out.println();
                continue;
            }

            // Determine what kind of task it is
            Task t;
            String description;
            switch (input.toLowerCase().split(" ")[0]) {
                case "deadline":
                    System.out.println("Deadline");
                    String[] inputSplit = input.split("/by");
                    String by = inputSplit[1];
                    description = inputSplit[0].substring(8);
                    t = new Deadline(description, by);
                    userTasks.add(t);
                    System.out.println(servantSpeak
                            + "    I have added the task:\n       "
                            + t.toString() + "\n");
                    break;
                case "todo":
                    System.out.println("ToDo");
                    break;
            }
            // Default: Add input from user into the ArrayList

        } while (!input.equals("bye"));

    }
}
