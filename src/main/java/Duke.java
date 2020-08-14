import java.util.*;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // arraylist to store all text entered by user
        ArrayList<Task> stored = new ArrayList<>();

        // displayed once main is run, without input from user
        String greetings =
            "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";

        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);

        // continuously scan for input from user until "bye" is invoked
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (!input.equals("list") && !input.equals("bye")) {

                if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                    // don't add new task
                } else {
                    // need to add new task
                    Task newTask = new Task(input);
                    stored.add(newTask);
                }
            }

            System.out.println("    ____________________________________________________________");

            // verify command to mark task as done
            if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                // get task number to mark as done
                int taskNo = Integer.parseInt(input.substring(5, input.length()));

                // verify task number exists, then mark as done
                if (taskNo - 1 < stored.size()) {
                    stored.get(taskNo - 1).markAsDone();

                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("    [" + stored.get(taskNo - 1).getStatusIcon() + "] " + stored.get(taskNo - 1).description);
                } else {
                    // task number does not exist
                    System.out.println("    Sorry, this task does not exist!");
                }

            // display list of items to user when requested with "list" command
            } else if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");

                for (int i = 1; i < stored.size() + 1; i++) {
                    String item = "     " + i + ".[" + stored.get(i - 1).getStatusIcon() + "] " + stored.get(i - 1).description;
                    System.out.println(item);
                }

            } else if (input.equals("bye")) {
                // print goodbye message
                String goodbye = "     Bye. Hope to see you again soon!";
                System.out.println(goodbye);
                System.out.println("    ____________________________________________________________\n");
                // exits program
                break;

            } else {
                // inform user item has been added
                String output = "     added: " + input;
                System.out.println(output);
            }

            System.out.println("    ____________________________________________________________\n");

        }
    }
}