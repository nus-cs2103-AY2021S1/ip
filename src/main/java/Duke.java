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
            String[] tokens = command.split(" ");
            String commandCheck = tokens[0];
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

                default:
                    System.out.println("added: " + command);
                    Task newTask = new Task(command);
                    STORAGE.add(newTask);
                    break;
            }
            command = READER.readLine();
        }
    }

    private static void printBorder() {
        System.out.print("---------------------------\n");
    }

    private static void printByeMessage() {
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
    }
}