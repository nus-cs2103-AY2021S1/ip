import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Main class that takes in user input.
 */
public class Duke {

    /**
     * List used to store all tasks.
     */
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final String FILEPATH = "data/duke.txt";

    private static void welcome() {
        printOutput("     Hello! I'm Duke\n"
                + "     What can I do for you?", true);
    }

    private static void printOutput(String input, boolean hasLastNewLine) {
        if (hasLastNewLine) {
            System.out.println("    ____________________________________________________________\n"
                    + input + "\n"
                    + "    ____________________________________________________________");

        } else {
            System.out.println("    ____________________________________________________________\n"
                    + input + "    ____________________________________________________________");
        }
    }

    /**
     * Handles input, and calls corresponding functions.
     *
     * @param input String input from user.
     * @return returns boolean of whether or not to continue.
     */
    private static boolean handleInput(String input) {
        // TODO: do this (exit) in a more elegant way, else boolean method would violate coding standards
        String second;
        int index;

        String[] parts = input.split(" ", 2);
        String action = parts[0].toUpperCase();

        try {
            Command command = tryEnum(action);
            switch(command) {
            case LIST:
                listTasks();
                return true;
            case DONE:
                second = parts[1];
                index = Integer.parseInt(second);
                markAsDone(index);
                return true;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                try {
                    handleTask(parts, command);
                } catch (DukeException e) {
                    printOutput(e.getMessage(), true);
                }
                return true;
            case DELETE:
                second = parts[1];
                index = Integer.parseInt(second);
                deleteTask(index);
                return true;
            case BYE:
                doExit();
                return false;
            default:
                return true;
            }
        } catch (DukeException e) {
            printOutput(e.getMessage(), true);
            return true;
        }
    }

    /**
     * Takes in task info and command and calls respective handler (e.g. handle event).
     *
     * @param inputs Task info, after removing first word from user input.
     * @param command Command (e.g. Event), first word from user input.
     * @throws DukeException If deadline date not input for deadline, or event date not input for event.
     */
    private static void handleTask(String[] inputs, Command command) throws DukeException {
        try {
            String taskInfo = inputs[1];
            Task task;
            String[] taskInfoParts;
            String description;
            String date;
            //TODO: in switch/if-else statement, okay to define early if we use the same variable name?

            switch (command) {
            case TODO:
                task = handleTodo(taskInfo);
                break;
            case DEADLINE:
                taskInfoParts = taskInfo.split(" /by ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                task = handleDeadline(description, date);
                break;
            case EVENT:
                taskInfoParts = taskInfo.split(" /at ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                task = handleEvent(description, date);
                break;
            default:
                task = new Task("");
            }

            int len = taskList.size();
            printOutput("     Got it. I've added this task:\n       "
                    + task.toString() + "\n     Now you have " + len
                    + " tasks in the list.", true);
            writeTasksToFile();

        } catch (ArrayIndexOutOfBoundsException e) {
            String error = "     OOPS!!! The description of a " + command.toString().toLowerCase() + " cannot be empty.";
            throw new DukeException(error);
        }
    }

    private static Command tryEnum(String action) throws DukeException {
        try {
            return Command.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void doExit() {
        printOutput("     Bye. Hope to see you again soon!", true);
    }

    private static void deleteTask(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        int len = taskList.size();
        printOutput("     Noted. I've removed this task:\n       "
                + task.toString() + "\n     Now you have " + len
                + " tasks in the list.", true);
        writeTasksToFile();
    }

    private static Task handleTodo(String todoTask) {
        Task todo = new Todo(todoTask);
        taskList.add(todo);
        return todo;
    }

    private static Task handleTodo(String todoTask, boolean isDone) {
        Task todo = new Todo(todoTask, isDone);
        taskList.add(todo);
        return todo;
    }

    private static Task handleDeadline(String deadlineTask, String deadlineBy) throws DukeException {
        try {
            LocalDate deadlineByLocalDate = LocalDate.parse(deadlineBy);
            Task deadline = new Deadline(deadlineTask, deadlineByLocalDate);
            taskList.add(deadline);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private static Task handleDeadline(String deadlineTask, String deadlineBy, boolean isDone) throws DukeException {
        try {
            LocalDate deadlineByLocalDate = LocalDate.parse(deadlineBy);
            Task deadline = new Deadline(deadlineTask, deadlineByLocalDate, isDone);
            taskList.add(deadline);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private static Task handleEvent(String eventTask, String eventAt) throws DukeException {
        try {
            LocalDate eventAtLocalDate = LocalDate.parse(eventAt);
            Task event = new Event(eventTask, eventAtLocalDate);
            taskList.add(event);
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private static Task handleEvent(String eventTask, String eventAt, boolean isDone) throws DukeException {
        try {
            LocalDate eventAtLocalDate = LocalDate.parse(eventAt);
            Task event = new Event(eventTask, eventAtLocalDate, isDone);
            taskList.add(event);
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("     OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private static void listTasks() {
        StringBuilder output = new StringBuilder("     Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : taskList) {
            output.append("     ").append(index).append(".").append(task.toString()).append("\n");
            index++;
        }
        printOutput(output.toString(), false);
    }

    private static void markAsDone(int index) {
        Task task = taskList.get(index - 1);
        task.setDone(true);
        String output = "     Nice! I've marked this task as done: \n"
                + "       " + task.toString();
        printOutput(output, true);
        writeTasksToFile();
    }

    private static void createFileOrReadFile() {
        File f = new File(FILEPATH);
        f.getParentFile().mkdirs();
        try {
            if (!f.exists()) {
                f.createNewFile();
                printOutput("     Creating new output file!", true);
            } else {
                readTasksFromFile();
                printOutput("     Output file already exists!", true);
            }
        } catch (IOException e) {
            printOutput("     A file error has occurred!", true);
        }
    }

    private static void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(FILEPATH); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            printOutput("     A file error has occurred!", true);
        }
    }

    private static void writeTasksToFile() {
        StringBuilder output = new StringBuilder();
        for (Task task : taskList) {
            output.append(task.toStringSimple()).append(System.lineSeparator());
        }
        appendToFile(output.toString());
    }

    private static void readTasksFromFile() {
        // File structure
        // D | isDoneInt | description | by
        // E | isDoneInt | description | at
        // T | isDoneInt | description

        // TODO: Can store Deadline, Event, Todo string instead, and can use same Enums
        File f = new File(FILEPATH); // create a File for the given file path
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                try {
                    String line = s.nextLine();
                    String[] lineParts = line.split(" \\| ");
                    int isDoneInt = Integer.parseInt(lineParts[1]);
                    boolean isDone = isDoneInt > 0;
                    switch (lineParts[0]) {
                    case "D":
                        handleDeadline(lineParts[2], lineParts[3], isDone);
                        break;
                    case "E":
                        handleEvent(lineParts[2], lineParts[3], isDone);
                        break;
                    case "T":
                        handleTodo(lineParts[2], isDone);
                        break;
                    }
                } catch (DukeException e) {
                    printOutput(e.getMessage(), true);
                }

            }
        } catch (java.io.FileNotFoundException e) {
            printOutput("     A file error has occurred!", true);
        }
    }

    /**
     * Reads user input, and does corresponding action.
     */
    public static void main(String[] args) {
        welcome();
        createFileOrReadFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = handleInput(input);
            if (!shouldContinue) {
                break;
            }
        }
    }
}







