import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINEDIVIDER = "\t____________________________________________________________\n";
    private static enum AcceptedCommands {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        DONE,
        BYE,c
    }

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
        prettyPrint("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    // Driver method to respond to user input
    private static void respondPicker() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> userInputCollector = new ArrayList<>();

        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            Task taskToUpdate ;
            String[] userInputArray = new String[2];

            // Splits input to command, content
            userInputArray = userInput.split(" ", 2);

            try {
                // Catch illegal commands
                checkIllegalArgument(userInputArray[0]);
                // Catch missing content
                checkMissingArgument(userInputArray);

                // Decide the actions
                switch (userInputArray[0]) {
                    case "list":
                        prettyPrint(userInputCollector);
                        break;

                    case "done":
                        taskToUpdate = userInputCollector.get(Integer.parseInt(userInput.split(" ", 2)[1])
                                - 1);
                        Task updatedTask = taskToUpdate.updateStatus(true);
                        userInputCollector.set(userInputCollector.indexOf(taskToUpdate), updatedTask);
                        prettyPrint("Nice! I've marked this task as done: \n" + "\t" + updatedTask);
                        break;

                    case "todo":
                        taskToUpdate = new ToDo(userInputArray[1]);
                        userInputCollector.add(taskToUpdate);
                        prettyPrint("Got it. I've added this task: \n" +
                                "\t" + taskToUpdate + "\n" +
                                "\tNow you have " + userInputCollector.size() +" tasks in the list.");
                        break;

                    case "event":
                        taskToUpdate = new Event(userInputArray[1].split("/at")[0],
                                userInputArray[1].split("/at")[1]);
                        userInputCollector.add(taskToUpdate);
                        prettyPrint("Got it. I've added this task: \n" +
                                "\t" + taskToUpdate + "\n" +
                                "\tNow you have " + userInputCollector.size() + " tasks in the list.");
                        break;

                    case "deadline":
                        taskToUpdate = new Deadline(userInputArray[1].split("/by")[0],
                                userInputArray[1].split("/by")[1]);
                        userInputCollector.add(taskToUpdate);
                        prettyPrint("Got it. I've added this task: \n" +
                                "\t" + taskToUpdate + "\n" +
                                "\tNow you have " + userInputCollector.size() + " tasks in the list.");
                        break;
                }
            } catch (DukeIllegalArgument | DukeMissingArgument e) {
                System.out.println(e.toString());
            }

            userInput = scan.nextLine();
        }

        prettyPrint("Bye. Hope to see you again soon!");
    }

    // Check if command user input is valid
    private static void checkIllegalArgument(String command) throws DukeIllegalArgument {
        for (AcceptedCommands i : AcceptedCommands.values()) {
            if (command.equalsIgnoreCase(i.name())) {
                return;
            }
        }

        throw new DukeIllegalArgument();
    }

    // Check if command of user missing arguments
    private static void checkMissingArgument(String[] command) throws DukeMissingArgument {
        if (!(command[0].equalsIgnoreCase(AcceptedCommands.LIST.name()) || (command[0].equalsIgnoreCase(AcceptedCommands.BYE.name())))
                && (command.length == 1)) {
            throw new DukeMissingArgument(command[0]);
        }
    }

    /**
     * Prints the given string with additional wrappings
     *
     * @param string String to print
     */
    private static void prettyPrint(String string) {
        System.out.println(LINEDIVIDER + "\t" + string + "\n" + LINEDIVIDER);
    }

    /**
     * Prints the given array list with additional effects
     *
     * @param array Array of strings to print
     */
    private static void prettyPrint(ArrayList array) {
        System.out.println(LINEDIVIDER + "\tHere are the tasks in your list:");
        for (int i = 0; i < array.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + array.get(i));
        }
        System.out.println(LINEDIVIDER);
    }
}
