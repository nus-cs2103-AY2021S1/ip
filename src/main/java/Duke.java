import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String HORIZONTAL_BREAK = "    ____________________________________________________________";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_DONE = "done";
    private static final String INPUT_DELETE = "delete";
    private static final String INPUT_TODO = "todo";
    private static final String INPUT_DEADLINE = "deadline";
    private static final String INPUT_EVENT = "event";
    private static final String INPUT_BYE = "bye";
    private static final String SAVED_DATA_PATHNAME = "./data";
    private static final String SAVED_DATA_FILENAME = "duke.txt";
    private static final String GET_EVENTS = "getEvents";

    public static void main(String[] args) throws DukeException{

        greeting();

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        File retrievedFile = retrieveSavedData();
        addRetrievedTasksToList(retrievedFile, tasks);


        while (!scanner.hasNext(INPUT_BYE)) {
            try {

                String command = scanner.next();

                if (command.equals(INPUT_LIST)) {
                    printList(scanner, tasks);
                } else if (command.equals(INPUT_DONE)) {
                    //Need to handle out of bounds number.
                    taskDone(scanner, tasks);
                } else if (command.equals(INPUT_DELETE)) {
                    deleteTask(scanner, tasks);
                } else if (command.equals(GET_EVENTS)) {
                    viewDeadlinesOnDate(scanner, tasks);
                } else {

                    Task newTask = null;

                    if (command.equals(INPUT_TODO)) {
                        newTask = addTodo(scanner, tasks);
                    } else if (command.equals(INPUT_DEADLINE)) {
                        newTask = addDeadline(scanner, tasks);
                    } else if (command.equals(INPUT_EVENT)) {
                        newTask = addEvent(scanner, tasks);
                    } else {
                        invalidCommand(scanner);
                    }

                    if (newTask != null) {
                        addTaskToList(tasks, newTask);
                    }

                }
            } catch (DukeException dukeException) {
                System.out.println(HORIZONTAL_BREAK);
                System.out.println(dukeException);
                System.out.println(HORIZONTAL_BREAK);
            } finally {
                System.out.println("");
            }

        }

        saveData(tasks);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private static void greeting() {
        String logo = "        ___\n"
                              + "    . -^   `--,\n"
                              + "   /# =========`-_\n"
                              + "  /# (--====___====\\\n"
                              + " /#   .- --.  . --.|\n"
                              + "/##   |  * ) (   * ),\n"
                              + "|##   \\    /\\ \\   / |\n"
                              + "|###   ---   \\ ---  |\n"
                              + "|####      ___)    #|\n"
                              + "|######           ##|\n"
                              + " \\##### ---------- /\n"
                              + "  \\####           (\n"
                              + "   `\\###          |\n"
                              + "     \\###         |\n"
                              + "      \\##         |\n"
                              + "       \\###.     .)\n"
                              + "         `======/";

        System.out.println("A massive alien head has appeared\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    SHOW ME WHAT YOU'VE GOT");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    private static void printList(Scanner sc, List<Task> tasks) {
        System.out.println(HORIZONTAL_BREAK);
        System.out.println("     Here are the tasks in your list:");
        int taskCount = 1;
        for (Task task : tasks) {
            System.out.println("     " + (taskCount) + ". " + task);
            taskCount++;
        }
        if (tasks.size() == 0) {
            System.out.println("     This is a very empty list... UwU");
        }
        System.out.println(HORIZONTAL_BREAK);
    }

    private static void taskDone(Scanner scanner, List<Task> tasks) {
        System.out.println(HORIZONTAL_BREAK);
        int itemNumber = scanner.nextInt();
        if (itemNumber > tasks.size()) {
            System.out.println("     Invalid Command!");
            System.out.println(HORIZONTAL_BREAK);
            return;
        }
        Task selectedTask = tasks.get(itemNumber - 1);
        selectedTask.setDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + selectedTask);
        System.out.println(HORIZONTAL_BREAK);
    }

    private static void deleteTask(Scanner scanner, List<Task> tasks) {
        System.out.println(HORIZONTAL_BREAK);
        int itemNumber = scanner.nextInt();
        if (itemNumber > tasks.size()) {
            System.out.println("     Invalid Command!");
            System.out.println(HORIZONTAL_BREAK);
            return;
        }
        Task selectedTask = tasks.remove(itemNumber - 1);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + selectedTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_BREAK);
    }

    private static Task addTodo(Scanner scanner, List<Task> tasks) throws InvalidTaskFormat {
        String nextLine = scanner.nextLine().stripLeading();
        if (nextLine.length() > 0) {
            Task newTask = new ToDo(nextLine);
            return newTask;
        } else {
            throw new InvalidTaskFormat(TaskType.TODO);
        }
    }

    private static Task addDeadline(Scanner scanner, List<Task> tasks) throws InvalidTaskFormat {
        String[] splitParts = scanner.nextLine().split((" /by "));
        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 ||
                    splitParts[1].strip().length() == 0) {
            throw new InvalidTaskFormat(TaskType.DEADLINE);
        } else {
            Task newTask;
            LocalDate localDate = parseDate(splitParts[1].strip());
            if (localDate != null) {
                newTask = new Deadline(splitParts[0].stripLeading(), localDate);
            } else {
                newTask = new Deadline(splitParts[0].stripLeading(), splitParts[1].strip());
            }

            return newTask;
        }
    }

    private static Task addEvent(Scanner scanner, List<Task> tasks) throws InvalidTaskFormat {
        String[] splitParts = scanner.nextLine().split((" /at "));
        if (splitParts.length != 2
                    || splitParts[0].strip().length() == 0
                    || splitParts[1].strip().length() == 0) {
            throw new InvalidTaskFormat(TaskType.EVENT);
        } else {
            Task newTask;
            LocalDate localDate = parseDate(splitParts[1].strip());
            if (localDate != null) {
                newTask = new Event(splitParts[0].stripLeading(), localDate);
            } else {
                newTask = new Event(splitParts[0].stripLeading(), splitParts[1].strip());
            }
            return newTask;
        }
    }

    private static void invalidCommand(Scanner scanner) throws InvalidDukeCommand {
        scanner.nextLine();
        throw new InvalidDukeCommand();
    }

    private static void addTaskToList(List<Task> tasks, Task newTask) {
        System.out.println(HORIZONTAL_BREAK);
        System.out.println("     Got it. I've added this task:");
        tasks.add(newTask);
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_BREAK);
    }

    private static File retrieveSavedData() {
        File directory = new File(SAVED_DATA_PATHNAME);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(SAVED_DATA_PATHNAME + "/" + SAVED_DATA_FILENAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void addRetrievedTasksToList(File file, List<Task> tasks) {
        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\|");
                Task newTask = null;
                LocalDate localDate = null;
                switch (line[0].strip()) {
                case "T":
                    newTask = new ToDo(line[2].strip());
                    break;
                case "D":
                    localDate = parseDate(line[3].strip());
                    if (localDate != null) {
                        newTask = new Deadline(line[0].stripLeading(), localDate);
                    } else {
                        newTask = new Deadline(line[2].strip(), line[3].strip());
                    }
                    break;
                case "E":
                    localDate = parseDate(line[3].strip());
                    if (localDate != null) {
                        newTask = new Event(line[0].stripLeading(), localDate);
                    } else {
                        newTask = new Event(line[2].strip(), line[3].strip());
                    }
                    break;
                }
                if (line[1].strip().equals("1")) {
                    newTask.setDone();
                }
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveData(List<Task> tasks) {
        File file = new File(SAVED_DATA_PATHNAME + "/" + SAVED_DATA_FILENAME);
        try {
            resetFileContents(SAVED_DATA_PATHNAME + "/" + SAVED_DATA_FILENAME);
            for (Task task : tasks) {
                appendToFile(SAVED_DATA_PATHNAME + "/" + SAVED_DATA_FILENAME,
                        task.getSaveFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while trying to save your data... :/");
        }
    }

    private static void resetFileContents(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write("");
        fileWriter.close();
    }

    private static void appendToFile(String filePath, String lineToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(lineToAdd);
        fileWriter.close();
    }

    private static LocalDate parseDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            localDate = null;
        }
        return localDate;
    }


    private static void viewDeadlinesOnDate(Scanner scanner, List<Task> tasks) {
        String date = scanner.nextLine().strip();
        try {
            LocalDate localDate = LocalDate.parse(date);
            System.out.println(HORIZONTAL_BREAK);
            System.out.println("     " + "Here are your events on " +
                                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
            for (Task task : tasks) {
                if (task.hasDate() && task.getDate().isEqual(localDate)) {
                    System.out.println("     " + task);
                }
            }
            System.out.println(HORIZONTAL_BREAK);
        } catch (DateTimeParseException e) {
            System.out.println("Please input a valid date format");
        }
    }


}
