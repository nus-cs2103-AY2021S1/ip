import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Scanner;

public class Duke {

    public static String filePath = "src/main/java/data/duke.txt";

    public static String BORDER = "-----------------------------------------------------------";
    public static String INDENTATION = "    ";
    public static List<Task> tasks = new ArrayList<>();

    public static void interact() throws DukeException, IOException {
        loadTasks(filePath);
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                exit();
                break;
            } else if (line.equals("list")) {
                list();
            } else {
                String[] arrOfStr = line.split(" ", 0);
                String identifier = arrOfStr[0];
                if (identifier.equals("done")) {
//                    mark as done
                    int index = Integer.parseInt(arrOfStr[1]) - 1;
                    markDone(index);
                } else if (identifier.equals("delete")) {
//                    delete
                    int index = Integer.parseInt(arrOfStr[1]) - 1;
                    delete(index);
                } else {
//                    add to list
                    if ((identifier.equals("todo") || identifier.equals("deadline")
                            || identifier.equals("event")) && arrOfStr.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a " + identifier + " cannot be empty.");
                    } else {
                        Task task;
                        if (identifier.equals("todo")) {
                            String[] newArrOfStr = line.split(" ", 2);
                            task = new Todo(newArrOfStr[1]);
                        } else if (identifier.equals("deadline")) {
                            String[] firstSplit = line.split(" /by ", 2);
                            String[] secondSplit = firstSplit[0].split(" ", 2);
                            String description = secondSplit[1];
                            String date = firstSplit[1];
                            String[] dateSplit = date.split(" ", 0);

                            if (dateSplit.length > 1) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                                LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                                task = new Deadline(description, localDateTime);
                            } else {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                                LocalDate localDate = LocalDate.parse(date, formatter);
                                task = new Deadline(description, localDate);
                            }
                        } else if (identifier.equals("event")) {
                            String[] firstSplit = line.split(" /at ", 2);
                            String by = firstSplit[1];
                            String[] secondSplit = firstSplit[0].split(" ", 2);
                            String description = secondSplit[1];
                            task = new Event(description, by);
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        add(task);
                    }
                }
            }
        }
    }

    public static void printBorder() {
        System.out.println(INDENTATION + BORDER);
    }

    public static void greet() {
        printBorder();
        System.out.println(INDENTATION + "Hello! I'm Duke\n    What can I do for you?");
        printBorder();
    }

    public static void exit() {
        printBorder();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        printBorder();
    }

    public static void list() {
        printBorder();
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(INDENTATION + index + "." + task);
        }
        printBorder();
    }

    public static void add(Task task) throws IOException {
        printBorder();
        tasks.add(task);
        String textToAppend = task.getSymbol() + " @ " + task.getStatusIcon() + " @ "
                + task.getDescription() + " @ " + task.getDate() + "\n";
        appendToFile(filePath, textToAppend);

        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + task);
        System.out.println(INDENTATION + "Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    public static void markDone(int index) throws IOException {
        Task task = tasks.get(index);
        Task newTask = task.markAsDone();
        tasks.set(index, newTask);

        writeToFile(filePath, "");
        for (Task tsk : tasks) {
            String textToAppend = tsk.getSymbol() + " @ " + tsk.getStatusIcon() + " @ "
                    + tsk.getDescription() + " @ " + tsk.getDate() + "\n";
            appendToFile(filePath, textToAppend);
        }

        printBorder();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }

    public static void delete(int index) throws IOException {
        Task task = tasks.get(index);
        tasks.remove(task);

        writeToFile(filePath, "");
        for (Task tsk : tasks) {
            String textToAppend = tsk.getSymbol() + " @ " + tsk.getStatusIcon() + " @ "
                    + tsk.getDescription() + " @ " + tsk.getDate() + "\n";
            appendToFile(filePath, textToAppend);
        }

        printBorder();
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + INDENTATION + task);
        System.out.println(INDENTATION + "Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    public static void loadTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (f.exists()) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] arrOfStr = line.split(" @ ", 0);
                String symbol = arrOfStr[0];
                String status = arrOfStr[1];
                String description = arrOfStr[2];
                Task newTask;

                if (symbol.equals("[T]")) {
                    newTask = new Todo(description);
                } else if (symbol.equals("[D]")) {
                    String date = arrOfStr[3];
                    String[] dateSplit = date.split(" ", 0);
                    if (dateSplit.length > 3) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmm a");
                        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                        newTask = new Deadline(description, localDateTime);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        LocalDate localDate = LocalDate.parse(date, formatter);
                        newTask = new Deadline(description, localDate);
                    }
                } else {
                    String by = arrOfStr[3];
                    newTask = new Event(description, by);
                }

                if (status.equals("[✓]")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            }
        }
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the file as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        try {
            interact();
        } catch (Exception e) {
            printBorder();
            System.out.println(INDENTATION + e.getMessage());
            printBorder();
        }
    }
}
