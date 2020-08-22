import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINEDIVIDER = "\t____________________________________________________________\n";
    private static final String SAVEPATH = "./src/main/data/SaveData.txt";
    private static enum AcceptedCommands {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        DONE,
        BYE,
        DELETE,
        CLEAR,
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
        ArrayList<Task> userInputCollector = DataHandler.loadTask(SAVEPATH);

        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            Task taskToUpdate;
            String[] userInputArray;

            // Splits input to command, content
            userInputArray = userInput.split(" ", 2);

            try {
                // Catch illegal commands
                checkIllegalArgument(userInputArray[0]);
                // Catch missing content
                checkMissingArgument(userInputArray);
                // Check indexing out of bounds
                checkExistingTask(userInputArray, userInputCollector.size());

                // Decide the actions
                String[] description;
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
                        description = stringSplit(userInputArray[1], "/at");
                        taskToUpdate = new Event(description[0], description[1]);
                        userInputCollector.add(taskToUpdate);
                        prettyPrint("Got it. I've added this task: \n" +
                                "\t" + taskToUpdate + "\n" +
                                "\tNow you have " + userInputCollector.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        description = stringSplit(userInputArray[1], "/by");
                        taskToUpdate = new Deadline(description[0], description[1]);
                        userInputCollector.add(taskToUpdate);
                        prettyPrint("Got it. I've added this task: \n" +
                                "\t" + taskToUpdate + "\n" +
                                "\tNow you have " + userInputCollector.size() + " tasks in the list.");
                        break;
                    case "delete":
                        taskToUpdate = userInputCollector.get(Integer.parseInt(userInputArray[1]) - 1);
                        userInputCollector.remove(taskToUpdate);
                        prettyPrint("Noted. I've removed this task: \n" +
                                "\t" + taskToUpdate + "\n" +
                                "\tNow you have " + userInputCollector.size() + " tasks in the list.");
                        break;
                    case "clear":
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                }
            } catch (DukeIllegalCommandException | DukeMissingArgumentException | DukeTaskOutOfBoundsException e) {
                System.out.println(e.toString());
            }

            try {
                DataHandler.saveTask(SAVEPATH, userInputCollector);
            } catch (IOException e) {
                System.out.println(e);
            }

            // Gets the new input
            userInput = scan.nextLine();
        }

        prettyPrint("Bye. Hope to see you again soon!");
    }

    public static String[] stringSplit(String toSplit, String split) {
        String description= toSplit.split(split)[0];
        return new String[]{description.substring(0, description.length() - 1),
                toSplit.split(split)[1]};
    }

    // Check if command user input is valid
    private static void checkIllegalArgument(String command) throws DukeIllegalCommandException {
        for (AcceptedCommands i : AcceptedCommands.values()) {
            if (command.equalsIgnoreCase(i.name())) {
                return;
            }
        }

        throw new DukeIllegalCommandException();
    }

    // Check if command of user missing arguments
    private static void checkMissingArgument(String[] command) throws DukeMissingArgumentException {
        if (!(command[0].equalsIgnoreCase(AcceptedCommands.LIST.name())
                || command[0].equalsIgnoreCase(AcceptedCommands.BYE.name())
                || command[0].equalsIgnoreCase(AcceptedCommands.CLEAR.name()))
                        && (command.length == 1)) {
            throw new DukeMissingArgumentException(command[0]);
        }
    }

    // Check if trying to access Tasks index out of the list
    private static void checkExistingTask(String[] command, int max) throws DukeTaskOutOfBoundsException {
        if ((command[0].equalsIgnoreCase(AcceptedCommands.DONE.name()))
                || command[0].equalsIgnoreCase(AcceptedCommands.DELETE.name())) {
            if ((Integer.parseInt(command[1]) < 1) || (Integer.parseInt(command[1]) >= (max + 1))) {
                throw new DukeTaskOutOfBoundsException(command[0]);
            }
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
