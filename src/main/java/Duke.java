import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINEDIVIDER = "    ____________________________________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        respondPicker();
    }

    // Prints out the greeting
    private static void greet() {
        prettyPrint("Hello! I'm Duke\n" +  "     What can I do for you?");
    }

    // Driver method to respond to user input
    private static void respondPicker() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> userInputCollector = new ArrayList<>();

        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            Task taskToUpdate ;

            switch (userInput.split(" ", 2)[0]) {
            case "":
                break;

            case "list":
                prettyPrint(userInputCollector);
                break;

            case "done":
                taskToUpdate = userInputCollector.get(Integer.parseInt(userInput.split(" ", 2)[1])
                        - 1);
                Task updatedTask = taskToUpdate.updateStatus(true);
                userInputCollector.set(userInputCollector.indexOf(taskToUpdate), updatedTask);
                prettyPrint("Nice! I've marked this task as done: \n" + "       " + updatedTask);
                break;

            case "todo":
                taskToUpdate = new ToDo(userInput.split(" ", 2)[1]);
                userInputCollector.add(taskToUpdate);
                prettyPrint("Got it. I've added this task: \n" +
                        "       " + taskToUpdate + "\n" +
                        "     Now you have " + userInputCollector.size() +" tasks in the list.");
                break;

            case "event":
                taskToUpdate = new Event(userInput.split("/at")[0].split(" ", 2)[1],
                        userInput.split("/at")[1]);
                userInputCollector.add(taskToUpdate);
                prettyPrint("Got it. I've added this task: \n" +
                        "       " + taskToUpdate + "\n" +
                        "     Now you have " + userInputCollector.size() + " tasks in the list.");
                break;

            case "deadline":
                taskToUpdate = new Deadline(userInput.split("/by")[0].split(" ", 2)[1],
                        userInput.split("/by")[1]);
                userInputCollector.add(taskToUpdate);
                prettyPrint("Got it. I've added this task: \n" +
                        "       " + taskToUpdate + "\n" +
                        "     Now you have " + userInputCollector.size() + " tasks in the list.");
                break;

            default:
                prettyPrint(userInput);
                taskToUpdate = new Task(userInput);
                userInputCollector.add(taskToUpdate);
                break;
            }

            userInput = scan.nextLine();
        }

        prettyPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the given string with additional wrappings
     *
     * @param string String to print
     */
    private static void prettyPrint(String string) {
        System.out.println(LINEDIVIDER + "     " + string + "\n" + LINEDIVIDER);
    }

    /**
     * Prints the given array list with additional effects
     *
     * @param array Array of strings to print
     */
    private static void prettyPrint(ArrayList array) {
        System.out.println(LINEDIVIDER);
        for (int i = 0; i < array.size(); i++) {
            System.out.println("     " + (i + 1) + "." + array.get(i));
        }
        System.out.println(LINEDIVIDER);
    }
}
