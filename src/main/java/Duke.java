import java.util.*;
import java.util.Scanner;

public class Duke {
    // arraylist to store all text entered by user
    public static ArrayList<Task> stored = new ArrayList<>();

    public static void main(String[] args) {
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
            try {
                processInput(input);
            } catch (DukeException ex) {
                System.err.print(ex);
            }
            System.out.println("____________________________________________________________\n");
        }
    }

    // method to find "/" substring index
    public static int findSlashIndex(String input, int startingIndex) throws DukeException {
        for (int i = startingIndex; i < input.length(); i++) {
            if (input.charAt(i) == 47) { // char index 47 is "/"
                return i;
            }
        }
        // if no slashes found
        throw new DukeException("\n Please indicate: \n '/by' - for Deadline, or \n '/at' - for Event.\n");
    }

    // method to mark task as done
    public static void markTaskDone(String input) throws DukeException {
        int taskNo = Integer.parseInt(input.substring(5));
        // verify task number exists, then mark as done
        if (taskNo - 1 < stored.size()) {
            stored.get(taskNo - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(stored.get(taskNo - 1).toString());
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    // method to process user input to identify commands
    public static void processInput(String input) throws DukeException {
        if (input.equals("bye")) {
            // print goodbye message
            String goodbye = "Bye. Hope to see you again soon!";
            System.out.println(goodbye);
            System.out.println("____________________________________________________________\n");
            // exits program
            System.exit(0);

        // display list of items to user when requested with "list" command
        } else if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < stored.size() + 1; i++) {
                String item = i + "." + stored.get(i - 1).toString();
                System.out.println(item);
            }
        } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
            // don't add new task, get task number to mark as done
            try {
                markTaskDone(input);
            } catch (DukeException ex) {
                System.err.print(ex); // catch if task does not exist
            }
        } else {
            // add item to tasks stored (To-Do/Deadline/Event) depending on command keyword
            if (input.length() >= 4 && input.substring(0, 4).equals("todo")) { // is To-Do task
                if (input.length() > 4 && !Character.isWhitespace(input.charAt(4))) {
                    // is not "todo" command (e.g. "todoo")
                    throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
                } else if (input.substring(4).length() <= 1) {
                    throw new DukeException("The description of a todo cannot be empty.\n");
                } else {
                    try {
                        Todo newTodo = new Todo(input.substring(5));
                        stored.add(newTodo);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new DukeException("Invalid input - please check if there are too many redundant spaces.\n");
                    }
                }
            } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) { // is Deadline task
                if (input.length() > 8 && !Character.isWhitespace(input.charAt(8))) {
                    // is not "deadline" command (e.g. "deadlines")
                    throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
                } else if (input.substring(8).length() <= 1) {
                    throw new DukeException("The description of a deadline cannot be empty.\n");
                } else {
                    try {
                        int slashIndex = findSlashIndex(input, 9);
                        Deadline newDeadline = new Deadline(input.substring(9, slashIndex - 1), input.substring(slashIndex + 4));
                        stored.add(newDeadline);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new DukeException("Invalid input - please check if there are too many redundant spaces.\n");
                    }
                }
            } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) { // is Event task
                if (input.length() > 5 && !Character.isWhitespace(input.charAt(5))) {
                    // is not "event" command (e.g. "events")
                    throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
                } else if (input.substring(5).length() <= 1) {
                    throw new DukeException("The description of an event cannot be empty.\n");
                } else {
                    try {
                        int slashIndex = findSlashIndex(input, 6);
                        Event newEvent = new Event(input.substring(6, slashIndex - 1), input.substring(slashIndex + 4));
                        stored.add(newEvent);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new DukeException("Invalid input - please check if there are too many redundant spaces.\n");
                    }
                }
            } else { // command is invalid
                throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
            }

            // inform user item has been added
            System.out.println("Got it. I've added this task:");
            System.out.println(stored.get(stored.size() - 1).toString());
            System.out.println("Now you have " + stored.size() + " tasks in the list.");
        }
    }
}