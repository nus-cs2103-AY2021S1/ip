import jdk.jfr.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Duke {

    private static final String SAVE_PATH = "data/save.txt";

    public static final String SEPARATOR = ",";

    public static final Pattern TASK_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventType>\\w+)" + SEPARATOR
                    + "(?<isDone>[01])" + SEPARATOR
                    + "(?<description>.+[^,]$?)" + SEPARATOR
                    + "(?<date>.+)");

    public static void main(String[] args) {
        String logo = "   ___      _      _ __   _              \n" +
                "  /   \\    | |    | '_ \\ | |_     __ _   \n" +
                "  | - |    | |    | .__/ | ' \\   / _` |  \n" +
                "  |_|_|   _|_|_   |_|__  |_||_|  \\__,_|  \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ";
        System.out.println("Hello from\n" + logo);

        String greeting = "    ____________________________________________________________\n" +
                "     Hello, Alpha here... welcome to my help centre... again :/\n" +
                "     Would you like to explain what you want?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);

        List<Task> taskList = new ArrayList<>();
        try {
            taskList = loadTasks();
        } catch (StorageOperationException e) {
            System.out.println("    Something went wrong!");
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            try {
                respondToInput(input, taskList);
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            }
            System.out.println("    ____________________________________________________________");
            input = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Try not to come again please... let me live.");
        System.out.println("    ____________________________________________________________");
        try {
            saveTasks(taskList);
        } catch (StorageOperationException soe) {
            System.out.println(soe.getMessage());
        }
    }

    private static void respondToInput(String input, List<Task> taskList) throws DukeException {
        String[] inputList = input.split(" ");
        String command = inputList[0];

        if (input.equalsIgnoreCase(Command.LIST.name())) {
            printTaskList(taskList);
        }
        else if (command.equalsIgnoreCase(Command.DONE.name())) {
            int index = checkDoneDeleteException(inputList, taskList);

            taskList.get(index).setDone();
            System.out.println("    Finally... about time you finished that. Marked this task as done: ");
            System.out.println("      " + taskList.get(index));

        }
        else if (command.equalsIgnoreCase(Command.DELETE.name()))
        {
            int index = checkDoneDeleteException(inputList, taskList);

            System.out.println("    Feeling weak? Giving up? Well done... Removed this task: ");
            System.out.println("      " + taskList.remove(index));
        }

        else if (command.equalsIgnoreCase(Command.DEADLINE.name()) ||
            command.equalsIgnoreCase(Command.EVENT.name()) ||
            command.equalsIgnoreCase(Command.TODO.name())) {

            if (inputList.length < 2) {
                throw new DukeException(String.format("☹ BLEHHHHHH!!! The description of a %s cannot be empty.", command));
            }

            if (command.equalsIgnoreCase(Command.TODO.name())) {
                String pattern = "(todo )(.+)";
                taskList.add(new ToDoTask(input.replaceAll(pattern, "$2")));
                printAddTaskListStatus(taskList);
            } else if (command.equalsIgnoreCase(Command.DEADLINE.name())) {
                String pattern = "(deadline )(.+)";
                taskList.add(new DeadlineTask(input.replaceAll(pattern, "$2")));
                printAddTaskListStatus(taskList);
            } else if (command.equalsIgnoreCase(Command.EVENT.name())) {
                String pattern = "(event )(.+)";
                taskList.add(new EventTask(input.replaceAll(pattern, "$2")));
                printAddTaskListStatus(taskList);
            }
        }
        else {
            throw new DukeException("☹ BLEHHHHHH!!! I'm (not) sorry, but I don't know what that means :/");
        }
    }

    private static int checkDoneDeleteException(String[] inputList, List<Task> taskList) {
        if (inputList.length < 2)
        {
            throw new DukeException("☹ BLEHHHHHH. Tell me which task??");
        }
        int index = Integer.parseInt(inputList[1]) - 1;
        if (index < 0 || index > taskList.size() - 1) {
            throw new DukeException(String.format("☹ BLEHHHHHH. Task no. %d does not exist. Please try again.", (index + 1)));
        }
        return index;
    }

    private static void printAddTaskListStatus(List<Task> taskList)
    {
        int size = taskList.size();
        System.out.println("    Fine. I've added this task:") ;
        System.out.println("      " + taskList.get(size - 1));
        System.out.println("    Now you have a total of " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") +  " in your list.");
    }

    private static int getRemainingTaskCount(List<Task> taskList)
    {
        return (int) taskList.stream().filter(x -> !x.isDone()).count();
    }

    private static void printTaskList(List<Task> taskList) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print("    " + (i+1) + ". ");
            System.out.println(taskList.get(i));
        }
    }

    private static void saveTasks(List<Task> taskList) throws StorageOperationException {
        List<String> dataLines = new ArrayList<>();
        for (Task task : taskList) {
            String line = String.join(",", task.getSaveData());
            dataLines.add(line);
        }
        try {
            Path path = Paths.get(SAVE_PATH);
            Files.write(path, dataLines);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing file: " + Paths.get(SAVE_PATH));
        }
    }

    private static List<Task> loadTasks() throws StorageOperationException {

        Path path = Paths.get(SAVE_PATH);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new ArrayList<Task>();
        }

        try {
            // read the file
            List<String> dataLines = Files.readAllLines(path);
            return DecodeTasksFromSave(dataLines);
        // should be handled above so it's an assertion instead of an exception
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario should already have been handled earlier.");
        // io exception for example no permission to read a file, some process closed the stream
        } catch (IOException ioe) {
            throw new StorageOperationException("Error reading file: " + path);
        // illegalValueException is when arguments cannot form a task
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    private static List<Task> DecodeTasksFromSave(List<String> dataLines) throws IllegalValueException {
        List<Task> output = new ArrayList<>();
        for (String line : dataLines) {
            Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalValueException("Encoded task in invalid format. Unable to decode.");
            }
            String command = matcher.group("eventType");
            boolean isDone = matcher.group("isDone").equals("0") ? false : true;
            String description  = matcher.group("description");
            if (command.equals("T")) {
                output.add(new ToDoTask(description, isDone));
            } else if (command.equals("E")) {
                output.add(new EventTask(description, isDone, matcher.group("date")));
            } else if (command.equals("D")) {
                output.add(new DeadlineTask(description, isDone, matcher.group("date")));
            } else {
                throw new IllegalValueException("Encoded task in invalid format. Unable to decode.");
            }
        }

        return output;
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}