import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private static final String HORIZONTAL_LINE =
            "\t-------------------------------------------------------";
    private static final String STANDARD_GREETING = HORIZONTAL_LINE
            + "\n\tHello! I'm Duke\n" + "\t" + "What can I do for you?\n"
            + HORIZONTAL_LINE;
    private static final String GOODBYE_MESSAGE = "\tBye. Hope to see you again soon!";
    private static final String ERROR_PREFIX = "\t\u2639" + " OOPS!!! ";
    private static final String CURRENT_TASKS = "\tNow you have %d task(s) in the list.";
    private static final String INVALID_TASK = ERROR_PREFIX + "Sorry, that is not a valid task.";
    private static final String ADD_TASK = "\tGot it. I've added this task:";
    private static final ArrayList<Task> list = new ArrayList<>();

    private static File createTaskFile() {
        File folder = new File("../data");
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }
            File taskFile = new File("../data/Duke.txt");
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            return taskFile;
        } catch (IOException e) {
            System.out.println("Error in getting file");
            return null;
        }
    }

    private static void updateList(String userInput) {
        Scanner inputScanner = new Scanner(userInput);
        String command = inputScanner.next();

        try {
            Files.copy(Paths.get("../data/Duke.txt"), Paths.get("../data/temp.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File copy = new File("../data/temp.txt");

        switch (command.toLowerCase()) {
        case "done":
            int index = inputScanner.nextInt() - 1;
            try {
                FileWriter fw = new FileWriter("../data/Duke.txt");
                Scanner taskScanner = new Scanner(copy);
                Task task = list.get(index);
                task.markAsDone();
                System.out.println("\tNice! I've marked this as done:");
                System.out.println("\t  " + task.toString());
                for (int i = 0; i < list.size(); i++) {
                    if (i == index) {
                        fw.write(task.toFileString() + System.lineSeparator());
                    } else {
                        fw.write(taskScanner.nextLine() + System.lineSeparator());
                    }
                }
                fw.close();
                taskScanner.close();
                Files.delete(Paths.get("../data/temp.txt"));
            } catch (IOException e) {
                System.out.println("Encountered an unexpected error with the file :(");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INVALID_TASK);
            }
            break;
        case "delete":
            index = inputScanner.nextInt() - 1;
            try {
                FileWriter fw = new FileWriter("../data/Duke.txt");
                Scanner taskScanner = new Scanner(copy);
                System.out.println("\tNoted. I've removed this task:");
                Task task = list.remove(index);
                System.out.println("\t" + task.toString());
                System.out.println(String.format(CURRENT_TASKS, list.size()));
                for (int i = 0; i < list.size(); i++) {
                    if (i != index) {
                        fw.write(taskScanner.nextLine() + System.lineSeparator());
                    }
                }
                fw.close();
                taskScanner.close();
                Files.delete(Paths.get("../data/temp.txt"));
            } catch (IOException e) {
                System.out.println("Encountered an unexpected error with the file :(");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INVALID_TASK);
            }
            break;
        case "todo":
            if (!inputScanner.hasNext()) {
                System.out.println(ERROR_PREFIX
                        + "The description of a todo cannot be empty.");
            } else {
                try {
                    FileWriter fw = new FileWriter("../data/Duke.txt", true);
                    System.out.println(ADD_TASK);
                    ToDo todo = new ToDo(inputScanner.nextLine());
                    list.add(todo);
                    System.out.println("\t  " + todo.toString());
                    System.out.println(String.format(CURRENT_TASKS, list.size()));
                    fw.write(todo.toFileString() + System.lineSeparator());
                    fw.close();
                    Files.delete(Paths.get("../data/temp.txt"));
                } catch (IOException e) {
                    System.out.println("Encountered an unexpected error with the file :(");
                }
            }
            break;
        case "deadline":
            if (!inputScanner.hasNext()) {
                System.out.println(ERROR_PREFIX
                        + "The description of a deadline cannot be empty.");
            } else {
                try {
                    FileWriter fw = new FileWriter("../data/Duke.txt", true);
                    System.out.println(ADD_TASK);
                    inputScanner.useDelimiter("( /by )");
                    String description = inputScanner.next();
                    inputScanner.reset();
                    inputScanner.next();
                    String by = inputScanner.nextLine().trim();
                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);
                    System.out.println("\t  " + deadline.toString());
                    System.out.println(String.format(CURRENT_TASKS, list.size()));
                    fw.write(deadline.toFileString() + System.lineSeparator());
                    fw.close();
                    Files.delete(Paths.get("../data/temp.txt"));
                } catch (IOException e) {
                    System.out.println("Encountered an unexpected error with the file :(");
                }
            }
            break;
        case "event":
            if (!inputScanner.hasNext()) {
                System.out.println(ERROR_PREFIX
                        + "The description of an event cannot be empty.");
            } else {
                try {
                    FileWriter fw = new FileWriter("../data/Duke.txt", true);
                    System.out.println(ADD_TASK);
                    inputScanner.useDelimiter("( /at )");
                    String description = inputScanner.next();
                    inputScanner.reset();
                    inputScanner.next();
                    String at = inputScanner.nextLine().trim();
                    Event event = new Event(description, at);
                    list.add(event);
                    System.out.println("\t  " + event.toString());
                    System.out.println(String.format(CURRENT_TASKS, list.size()));
                    fw.write(event.toFileString() + System.lineSeparator());
                    fw.close();
                    Files.delete(Paths.get("../data/temp.txt"));
                } catch (IOException e) {
                    System.out.println("Encountered an unexpected error with the file :(");
                }
            }
            break;
        default:
            System.out.println(ERROR_PREFIX
                    + "I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    public static void main(String[] args) {
        System.out.println(STANDARD_GREETING);
        createTaskFile();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            System.out.println(HORIZONTAL_LINE);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                System.out.println(HORIZONTAL_LINE);
                scanner.close();
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int index = 1;
                for (Task task : list) {
                    System.out.println("\t" + index++ + ". " + task.toString());
                }
                if (index == 1) {
                    System.out.println("\tYou have no tasks in your list :)");
                }
            } else {
                updateList(userInput);
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }
}

