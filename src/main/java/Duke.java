import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Duke {

    boolean running = false;
    ArrayList<Task> memory = new ArrayList<>();

    boolean isRunning() {
        return running;
    }

    void setRunningState(boolean bool) {
        running = bool;
    }

    void indent() {
        System.out.print("    ");
    }

    boolean stringIsInt(String string) {
        try {
            parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    void greet() {
        indent();
        System.out.println("Hello! I'm Duke.");
        indent();
        System.out.println("How can I help you today?");
    }

    void printLine() {
        indent();
        System.out.println("-------------------------------------------------------------");
    }

    void opener() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();

        greet();

        printLine();
    }

    void farewell() {
        printLine();

        indent();
        System.out.println("Goodbye! Hope to see you again soon!");

        printLine();
    }

    void list() {
        printLine();

        indent();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < memory.size(); i++) {
            indent();
            Task currentTask = memory.get(i);
            System.out.println((i + 1) + ". " + currentTask.toString());
        }

        printLine();
    }

    void done(int index) {
        try {
            Task task = memory.get(index - 1);
            task.markAsDone();
            printDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            printLine();

            indent();
            System.out.println("No such task!");

            printLine();
        }
    }

    void unknownCommandMessage() {
        printLine();

        indent();
        System.out.println("Unknown command");

        printLine();
    }


    void addToMemory(Task task) {
        memory.add(task);
    }


    void printAddMessage(Task task) {
        printLine();

        indent();
        System.out.println("Got it. I've added this task:");

        indent();
        System.out.println(task.toString());

        indent();
        System.out.println("Now you have " + memory.size() + " tasks in the list.");

        printLine();
    }

    void printDoneMessage(Task task) {
        printLine();

        indent();
        System.out.println("Nice! I've marked this task as done:");

        indent();
        System.out.println(task.toString());

        printLine();
    }

    String stringArrayToString(String[] arr, int startIndex, int endIndex) {
        try {
            if (endIndex > startIndex) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = startIndex; i < endIndex - 1; i++) {
                    stringBuilder.append(arr[i] + " ");
                }
                stringBuilder.append(arr[endIndex - 1]);
                return stringBuilder.toString();
            } else {
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    void checkAndPrint(String string) {
        String[] splitString = string.split("\\s+");
        if (string.equals("bye")) {
            exit();
        } else if (string.equals("list")) {
            list();
        } else if (splitString.length == 2 &&
                splitString[0].equals("done") && stringIsInt(splitString[1])) {
            int index = parseInt(splitString[1]);
            done(index);
        } else if (splitString[0].equals("todo")) {
            todo(splitString);
        } else if (splitString[0].equals("deadline")) {
            deadline(splitString);
        } else if (splitString[0].equals("event")) {
            event(splitString);
        } else {
            unknownCommandMessage();
        }
    }

    // Takes the input as a string array, adds a new deadline to list, then prints the message
    void deadline(String[] stringArray) {
        int indexOfBy = -1;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("/by")) {
                indexOfBy = i;
                break;
            }
        }

        String by = stringArrayToString(stringArray, indexOfBy + 1, stringArray.length);
        String description = stringArrayToString(stringArray, 1, indexOfBy);
        Deadline deadline = new Deadline(description, by);
        addToMemory(deadline);
        printAddMessage(deadline);
    }

    // Takes the input as a string array, then adds a new todo to list, then prints the message
    void todo(String[] stringArray) {
        String description = stringArrayToString(stringArray,
                1, stringArray.length);
        ToDo todo = new ToDo(description);
        addToMemory(todo);
        printAddMessage(todo);
    }

    // Takes input as a string array, then adds a new event to list, then prints the message
    void event(String[] stringArray) {
        int indexOfAt = -1;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("/at")) {
                indexOfAt = i;
                break;
            }
        }

        String at = stringArrayToString(stringArray, indexOfAt + 1, stringArray.length);
        String description = stringArrayToString(stringArray, 1, indexOfAt);
        Event event = new Event(description, at);
        addToMemory(event);
        printAddMessage(event);
    }


    void exit() {
        running = false;
        farewell();
    }



    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setRunningState(true);
        Scanner sc = new Scanner(System.in);

        duke.opener();

        while (duke.isRunning()) {
            String str = sc.nextLine();
            duke.checkAndPrint(str);
        }
    }
}
