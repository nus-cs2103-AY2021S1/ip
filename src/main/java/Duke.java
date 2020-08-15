import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Duke {

    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final Reader INPUTSTREAMREADER = new InputStreamReader(System.in);
    public static final BufferedReader READER = new BufferedReader(INPUTSTREAMREADER);
    public static final ArrayList<Task> STORAGE = new ArrayList<Task>();

    public static void main(String[] args) throws Exception {
        executeProgram();
    }

    private static void executeProgram() throws Exception {
        printWelcomeMessage(LOGO);
        printBorder();
        String command = READER.readLine();
        parseCommands(command);
        printByeMessage();
    }

    private static void printWelcomeMessage(String LOGO) {
        System.out.println("Hello there! My name is\n" + LOGO + "\nWhat can I do for you?");
    }

    private static void parseCommands(String command) throws Exception {
        while (command != null && !command.equals("bye")) {
            String[] userInputArray = command.split(" ");
            String commandCheck = userInputArray[0];
            Task taskToAdd;
            int numOfInput = userInputArray.length;

            try {
                switch (commandCheck) {
                    case "list":
                        if (STORAGE.isEmpty()) {
                            throw new DukeException("The list is empty.");
                        } else {
                            int index = 1;
                            System.out.println("These are the tasks in your list:");
                            for (Task task: STORAGE) {
                                String str = task.toString();
                                System.out.println(index + ". " + str);
                                index++;
                            }
                        }
                        printBorder();
                        break;

                    case "done":
                        try {
                            if (numOfInput < 2) {
                                throw new DukeException("Please specify which task you have completed.");
                            }

                            try {
                                Integer.parseInt(userInputArray[1]);
                            } catch (NumberFormatException error) {
                                throw new DukeException("Input is not a valid integer.");
                            }

                            String temp = command.split(" ")[1];
                            int indexOfTemp = Integer.parseInt(temp);
                            int currentTaskIndex = indexOfTemp - 1;

                            if (currentTaskIndex < 0 || currentTaskIndex >= STORAGE.size()) {
                                throw new DukeException("The task number" + " (" + (currentTaskIndex + 1) + ") " +
                                                        "that you have input can not be found in your list.");
                            }

                            Task currentTask = STORAGE.get(currentTaskIndex);
                            STORAGE.get(currentTaskIndex).setDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(" " + currentTask.toString());
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        printBorder();
                        break;

                    case "todo":
                        try {
                            if (numOfInput == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            StringBuilder todoString = new StringBuilder("");
                            int j = 1;
                            while (j < numOfInput) {
                                todoString.append(userInputArray[j]);
                                todoString.append(" ");
                                j++;
                            }
                            taskToAdd = new Todo(todoString.toString().trim());
                            printGotIt();
                            printTask(taskToAdd);
                            STORAGE.add(taskToAdd);
                            printTaskCount();
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    case "deadline":
                        try {
                            if (numOfInput == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }

                            if (numOfInput < 4) {
                                throw new DukeException("☹ OOPS!!! The correct way to log a deadline is: (deadline) "
                                                        + "(description) (/by) (date)");
                            }

                            StringBuilder deadlineString = new StringBuilder("");
                            StringBuilder deadlineDate = new StringBuilder("");
                            boolean checkForDate = false;
                            int m = 1;
                            while (m < numOfInput) {
                                if (userInputArray[m].equals("/by")) {
                                    checkForDate = true;
                                } else {
                                    if (checkForDate == false) {
                                        deadlineString.append(userInputArray[m]);
                                        deadlineString.append(" ");
                                    } else {
                                        deadlineDate.append(userInputArray[m]);
                                        deadlineDate.append(" ");
                                    }
                                }
                                m++;
                            }
                            taskToAdd = new Deadline(deadlineString.toString().trim(), deadlineDate.toString().trim());
                            printGotIt();
                            printTask(taskToAdd);
                            STORAGE.add(taskToAdd);
                            printTaskCount();
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    case "event":
                        try {
                            if (numOfInput == 1) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            }

                            if (numOfInput < 4) {
                                throw new DukeException("☹ OOPS!!! The correct way to log an event is: (event) " +
                                                        "(description) (/at) (date)");
                            }

                            StringBuilder eventString = new StringBuilder("");
                            StringBuilder eventDate = new StringBuilder(" ");
                            boolean checkForEvent = false;
                            int z = 1;
                            while (z < numOfInput) {
                                if (userInputArray[z].equals("/at")) {
                                    checkForEvent = true;
                                } else {
                                    if (checkForEvent == false) {
                                        eventString.append(userInputArray[z]);
                                        eventString.append(" ");
                                    } else {
                                        eventDate.append(userInputArray[z]);
                                        eventDate.append(" ");
                                    }
                                }
                                z++;
                            }
                            taskToAdd = new Event(eventString.toString().trim(), eventDate.toString().trim());
                            printGotIt();
                            printTask(taskToAdd);
                            STORAGE.add(taskToAdd);
                            printTaskCount();
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    case "delete":
                        try {
                            if (numOfInput < 2) {
                                throw new DukeException("Please specify which task you want to delete.");
                            }

                            try {
                                Integer.parseInt(userInputArray[1]);
                            } catch (NumberFormatException error) {
                                throw new DukeException("Input is not a valid integer.");
                            }

                            String tempStr = command.split(" ")[1];
                            int indexOfTempStr = Integer.parseInt(tempStr);
                            int indexToRemove = indexOfTempStr - 1;
                            int arraySize = STORAGE.size();

                            if (indexToRemove < 0 || indexToRemove >= arraySize) {
                                throw new DukeException("The task number" + " (" + (indexToRemove + 1) + ") " + "that you want to delete can not be found in your list.");
                            } else {
                                Task curr = STORAGE.get(indexToRemove);
                                printDelete();
                                System.out.println(" " + curr.toString());
                                STORAGE.remove(indexToRemove);
                                Task.totalTasks = Task.totalTasks - 1;
                                printTaskCount();
                            }
                        } catch (DukeException error) {
                            System.err.println(error.getMessage());
                        }
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what '" + command
                                                + "' means :-(");
                }
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
            command = READER.readLine();
        }
    }

    private static void printTask(Task taskToAdd) {
        System.out.println(" " + taskToAdd.toString());
    }

    private static void printGotIt() {
        System.out.println("Got it. I've added this task:");
    }

    private static void printTaskCount() {
        if (Task.totalTasks > 1) {
            System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        } else {
            System.out.println("Now you have " + Task.totalTasks + " task in the list.");
        }
        printBorder();
    }

    private static void printBorder() {
        System.out.print("---------------------------\n");
    }

    private static void printDelete() {
        System.out.println("Noted. I've removed this task:");
    }

    private static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
