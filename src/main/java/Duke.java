import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public Storage storage;

    // arraylist to store all text entered by user
    public ArrayList<Task> stored;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.stored = storage.tasks;
    }

    public static void main(String[] args) {
        Duke duke = new Duke("duke.txt");
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
            String trimmedInput = input.trim(); // get rid of trailing spaces

            System.out.println("____________________________________________________________");
            try {
                duke.processInput(trimmedInput);
            } catch (DukeException ex) {
                System.out.print(ex);
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
        throw new DukeException("Please indicate:\n'/by' - for Deadline with input date formatted as: YYYY-MM-DD, or\n'/at' - for Event.\n");
    }

    // method to mark task as done
    public void markTaskDone(String input) throws DukeException {
        int taskNo = Integer.parseInt(input.substring(5));
        // verify task number exists, then mark as done
        if (taskNo - 1 < stored.size()) {
            if (stored.get(taskNo - 1).isDone) {
                // task marked as done already
                throw new DukeException("Task is already done! :)\n");
            } else {
                stored.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(stored.get(taskNo - 1).toString());
            }
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    // method to delete task
    public void deleteTask(String input) throws DukeException {
        int taskNo = Integer.parseInt(input.substring(7));
        // verify task number exists, then delete
        if (taskNo - 1 < stored.size()) {
            Task toDelete = stored.get(taskNo - 1);
            stored.remove(taskNo - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(toDelete.toString());
            System.out.println("Now you have " + stored.size() + " tasks in the list.");
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    // method to process user input to identify commands
    public void processInput(String input) throws DukeException {
        if (input.equals("bye")) {
            // Save tasks to "duke.txt" to be loaded when Duke starts up
            storage.saveTasks(stored);

            // print goodbye message
            String goodbye = "Bye. Hope to see you again soon!";
            System.out.println(goodbye);
            System.out.println("____________________________________________________________\n");
            // exits program
            System.exit(0);

            // display list of items to user when requested with "list" command
        } else if (input.startsWith("list") && input.length() == 4) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < stored.size() + 1; i++) {
                String item = i + "." + stored.get(i - 1).toString();
                System.out.println(item);
            }
        } else if (input.startsWith("done")) {
            // don't add new task, get task number to mark as done
            try {
                markTaskDone(input);
            } catch (DukeException ex) {
                System.out.print(ex); // catch if task does not exist
            }
        } else if (input.startsWith("delete")) { // delete task
            // don't add new task, get task number to delete task
            try {
                deleteTask(input);
            } catch (DukeException ex) {
                System.out.print(ex); // catch if task does not exist
            }
        } else {
            // add item to tasks stored (To-Do/Deadline/Event) depending on command keyword
            if (input.startsWith("todo")) { // is To-Do task
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
                        throw new DukeException("Invalid input - please check if there are missing info.\n");
                    }
                }
            } else if (input.startsWith("deadline")) { // is Deadline task
                if (input.length() > 8 && !Character.isWhitespace(input.charAt(8))) {
                    // is not "deadline" command (e.g. "deadlines")
                    throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
                } else if (input.substring(8).length() <= 1) {
                    throw new DukeException("The description of a deadline cannot be empty.\n");
                } else {
                    try {
                        int slashIndex = findSlashIndex(input, 9);
                        Deadline newDeadline = new Deadline(input.substring(9, slashIndex - 1), LocalDate.parse(input.substring(slashIndex + 4)));
                        stored.add(newDeadline);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new DukeException("Invalid input - please check if there are too many redundant spaces.\n");
                    } catch (DateTimeParseException ex) {
                        throw new DukeException("Please indicate deadline date formatted as: YYYY-MM-DD.");
                    }
                }
            } else if (input.startsWith("event")) { // is Event task
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