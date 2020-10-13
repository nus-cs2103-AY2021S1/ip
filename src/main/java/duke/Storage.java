package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private static String filePath;
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructor which takes in a specified filepath to create a Storage object.
     *
     * @param filePath the path location of the load or save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from the file specified by the filepath and creates each Task object
     * to be stored a List of tasks so it can be passed as a parameter into
     * a TaskList object so that it can be tracked.
     *
     * @return a List of tasks
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public List<Task> load() throws IOException, DukeException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arrOfStr = line.split(" @ ", 0);
            String symbol = arrOfStr[0];
            String status = arrOfStr[1];
            String description = arrOfStr[2];
            Task newTask;

            assert symbol.equals("[T]") || symbol.equals("[D]") || symbol.equals("[E]");
            switch (symbol) {
                case "[T]":
                    newTask = new Todo(description);
                    break;
                case "[D]":
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
                    break;
                case "[E]":
                    String by = arrOfStr[3];
                    newTask = new Event(description, by);
                    break;
                default:
                    throw new DukeException("Invalid task type detected - unable to load");
            }

            if (status.equals("Y")) {
                newTask.markAsDone();
            }
            tasks.add(newTask);
        }
        return tasks;
    }

    /**
     * Returns the filepath of the specified file.
     *
     * @return the filepath
     */
    public static String getFilePath() {
        return filePath;
    }

    /**
     * Prints out the contents of a file.
     *
     * @param filePath the path location of the load or save file
     * @throws FileNotFoundException thrown when an attempt to open the specified file has failed
     */
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the file as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes content to a file based on the text specified and overwrites the file.
     *
     * @param filePath  the path location of the load or save file
     * @param textToAdd the text to be written to the file
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends information to a file based on the text specified and overwrites the file.
     *
     * @param filePath     the path location of the load or save file
     * @param textToAppend the text to be appended to the file
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Overwrites the file by adding in all the tasks from a list of tasks specified.
     *
     * @param filePath the path location of the load or save file
     * @param taskList TaskList object that contains a list of tasks
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public static void updateFile(String filePath, TaskList taskList) throws IOException {
        writeToFile(filePath, "");
        for (Task tsk : taskList.getTasks()) {
            String textToAppend = tsk.getSymbol() + " @ "
                    + (tsk.getStatusIcon().equals("[\u2713]") ? "Y" : "N") + " @ "
                    + tsk.getDescription() + " @ " + tsk.getDate() + "\n";
            appendToFile(filePath, textToAppend);
        }
    }
}
