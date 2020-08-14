import java.util.*;
import java.util.Scanner;

public class Duke {

    // method to find "/" substring index
    public static int findSlashIndex(String input, int startingIndex) {
        for (int i = startingIndex; i < input.length(); i++) {
            if (input.charAt(i) == 47) { // char index 47 is "/"
                return i;
            }
        }
        return 0; // if no slashes found
    }

    public static void main(String[] args) {
        // arraylist to store all text entered by user
        ArrayList<Task> stored = new ArrayList<>();

        // displayed once main is run, without input from user
        String greetings =
            "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________\n";

        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);

        // continuously scan for input from user until "bye" is invoked
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            System.out.println("____________________________________________________________");

            if (!input.equals("list") && !input.equals("bye")) {
                if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                    // don't add new task
                } else {
                    // add item to tasks stored (To-Do/Deadline/Event) depending on command keyword
                    if (input.length() > 4 && input.substring(0, 4).equals("todo")) { // is To-Do task
                        Todo newTodo = new Todo(input.substring(5, input.length()));
                        stored.add(newTodo);
                    } else if (input.length() > 8 && input.substring(0, 8).equals("deadline")) { // is Deadline task
                        int slashIndex = findSlashIndex(input, 9);
                        Deadline newDeadline = new Deadline(input.substring(9, slashIndex - 1), input.substring(slashIndex + 4));
                        stored.add(newDeadline);
                    } else { // is Event task
                        int slashIndex = findSlashIndex(input, 6);
                        Event newEvent = new Event(input.substring(6, slashIndex - 1), input.substring(slashIndex + 4));
                        stored.add(newEvent);
                    }
                }
            }

            // verify command to mark task as done
            if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                // get task number to mark as done
                int taskNo = Integer.parseInt(input.substring(5, input.length()));
                // verify task number exists, then mark as done
                if (taskNo - 1 < stored.size()) {
                    stored.get(taskNo - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(stored.get(taskNo - 1).toString());
                } else {
                    // task number does not exist
                    System.out.println("Sorry, this task does not exist!");
                }

            // display list of items to user when requested with "list" command
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < stored.size() + 1; i++) {
                    String item = i + "." + stored.get(i - 1).toString();
                    System.out.println(item);
                }
            } else if (input.equals("bye")) {
                // print goodbye message
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(goodbye);
                System.out.println("____________________________________________________________\n");
                // exits program
                break;
            } else {
                // inform user item has been added
                System.out.println("Got it. I've added this task:");
                System.out.println(stored.get(stored.size() - 1).toString());
                System.out.println("Now you have " + stored.size() + " tasks in the list.");
            }
            System.out.println("____________________________________________________________\n");
        }
    }
}