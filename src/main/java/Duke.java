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

    void printMessage(String string) {
        String[] splitByNewLine = string.split("\\r?\\n");
        printLine();
        for (String str : splitByNewLine) {
            indent();
            System.out.println(str);
        }
        printLine();
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

        String opening = "Hello! I'm Duke.\nHow can I help you today?";
        printMessage(opening);
    }

    void farewell() {
        printMessage("Goodbye! Hope to see you again soon!");
    }

    void list() {
        if (memory.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the tasks in your list:\n");

            for (int i = 0; i < memory.size(); i++) {
                Task currentTask = memory.get(i);
                stringBuilder.append((i + 1) + ". " + currentTask.toString() + "\n");
            }

            printMessage(stringBuilder.toString());
        } else {
            printMessage("There are no tasks yet!");
        }
    }

    void done(int index) throws DukeException{
        try {
            Task task = memory.get(index - 1);
            task.markAsDone();
            printDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Sorry, I couldn't find the task.");
        }
    }

    void delete(int index) throws DukeException {
        try {
            Task task = memory.remove(index - 1);
            printMessage("Noted. I've removed this task.\n" +
                    task.toString() + "\n" +
                    "Now you have " + memory.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Sorry, I couldn't find the task.");
        }
    }

    void unknownCommandMessage() {
        printMessage("Oops! I'm sorry, I don't know what that means.");
    }


    void addToMemory(Task task) {
        memory.add(task);
    }


    void printAddMessage(Task task) {
        printMessage("Got it. I've added this task:\n" +
                task.toString() + "\n" +
                "Now you have " + memory.size() + " tasks in the list.");
    }

    void printDoneMessage(Task task) {
        printMessage("Nice! I've marked this task as done:\n" +
                task.toString());
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
        try {
            String[] splitString = string.split("\\s+");
            if (string.equals("bye")) {
                exit();
            } else if (string.equals("list")) {
                list();
            } else if (splitString.length == 2 &&
                    splitString[0].equals("done") && stringIsInt(splitString[1])) {
                int index = parseInt(splitString[1]);
                done(index);
            } else if (splitString.length == 2 &&
                    splitString[0].equals("delete") && stringIsInt(splitString[1])) {
                int index = parseInt(splitString[1]);
                delete(index);
            } else if (splitString[0].equals("todo")) {
                todo(splitString);
            } else if (splitString[0].equals("deadline")) {
                deadline(splitString);
            } else if (splitString[0].equals("event")) {
                event(splitString);
            } else {
                unknownCommandMessage();
            }
        } catch (TaskException e) {
            printMessage(e.getMessage());
        } catch (DukeException e) {
            printMessage(e.getMessage());
        }
    }

    // Takes the input as a string array, adds a new deadline to list, then prints the message
    void deadline(String[] stringArray) throws TaskException{
        int indexOfBy = -1;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("/by")) {
                indexOfBy = i;
                break;
            }
        }
        if (indexOfBy == 1 || stringArray.length == 1) {
            throw new TaskException("Oops! A deadline task needs a description");
        } else if (indexOfBy == stringArray.length - 1 || indexOfBy == -1) {
            throw new TaskException("Oops! A deadline task needs a deadline");
        } else {
            String by = stringArrayToString(stringArray, indexOfBy + 1, stringArray.length);
            String description = stringArrayToString(stringArray, 1, indexOfBy);
            Deadline deadline = new Deadline(description, by);
            addToMemory(deadline);
            printAddMessage(deadline);
        }
    }

    // Takes the input as a string array, then adds a new todo to list, then prints the message
    void todo(String[] stringArray) throws TaskException {
        if (stringArray.length == 1) {
            throw new TaskException("Oops! A todo task needs a description.");
        } else {
            String description = stringArrayToString(stringArray,
                    1, stringArray.length);
            ToDo todo = new ToDo(description);
            addToMemory(todo);
            printAddMessage(todo);
        }
    }

    // Takes input as a string array, then adds a new event to list, then prints the message
    void event(String[] stringArray) throws TaskException {
        int indexOfAt = -1;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("/at")) {
                indexOfAt = i;
                break;
            }
        }
        if (indexOfAt == 1 || stringArray.length == 1) {
            throw new TaskException("Oops! An event task needs a description");
        } else if (indexOfAt == stringArray.length - 1 || indexOfAt == -1) {
            throw new TaskException("Oops! An event task needs a date");
        } else {
            String at = stringArrayToString(stringArray, indexOfAt + 1, stringArray.length);
            String description = stringArrayToString(stringArray, 1, indexOfAt);
            Event event = new Event(description, at);
            addToMemory(event);
            printAddMessage(event);
        }
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
