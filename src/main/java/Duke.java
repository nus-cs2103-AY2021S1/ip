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

            printBorder();

            switch (commandCheck) {
                case "list":
                    if (STORAGE.isEmpty()) {
                        System.out.println("The list is empty!");
                    } else {
                        int index = 1;
                        System.out.println("These are the tasks in your list:");
                        for (Task task : STORAGE) {
                            String str = task.toString();
                            System.out.println(index + ". " + str);
                            index++;
                        }
                    }
                    printBorder();
                    break;

                case "done":
                    String temp = command.split(" ")[1];
                    int indexOfTemp = Integer.parseInt(temp);
                    int currentTaskIndex = indexOfTemp - 1;
                    Task currentTask = STORAGE.get(currentTaskIndex);
                    STORAGE.get(currentTaskIndex).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + currentTask.toString());
                    break;

                case "todo":
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
                    break;

                case "deadline":
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
                    break;

                case "event":
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
                    break;

                default:
                    break;
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

    private static void printByeMessage() {
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
    }
}