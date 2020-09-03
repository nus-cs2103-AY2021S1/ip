package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /** The filename for the data file */
    private String fileName;

    /** Boolean for whether or not Storage should append data to the data file */
    private boolean canAppendToFile = false;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Constructs a new Storage object.
     * @param fileName name of the data file
     * @param appendToFile whether or not the file should be appended to
     */
    public Storage(String fileName, boolean appendToFile) {
        this.fileName = fileName;
        this.canAppendToFile = appendToFile;
    }

    /**
     * Loads all the tasks from the data file into a list.
     * @return an arraylist with all of the tasks from the data file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            for (int i = 1; i <= getNumOfTasks(); i++) {
                String taskLine = printLine(i);
                String[] taskInfo = taskLine.trim().split(" [|] ");
                String taskType = taskInfo[0];
                boolean isDone = taskInfo[1].equals(String.valueOf(1)) ? true : false;
                String taskName = taskInfo[2];
                Task taskToAdd;

                switch (taskType) {
                case "T":
                    taskToAdd = new Todo(taskName);
                    if (isDone) {
                        taskToAdd.markAsDone();
                    }
                    taskList.add(taskToAdd);
                    break;
                case "E":
                    String at = taskInfo[3];
                    taskToAdd = new Event(taskName, at);
                    if (isDone) {
                        taskToAdd.markAsDone();
                    }
                    taskList.add(taskToAdd);
                    break;
                case "D":
                    String by = taskInfo[3];
                    taskToAdd = new Deadline(taskName, by);
                    if (isDone) {
                        taskToAdd.markAsDone();
                    }
                    taskList.add(taskToAdd);
                    break;
                default:
                    break;
                }
            }
            return taskList;
        } catch (IOException e) {
            printFileError();
            return taskList;
        }
    }

    /**
     * Tells the user if there has been an error with the data file.
     */
    protected static void printFileError() {
        System.out.println("Oops! There's been an error with the data file, please try again!");
    }

    /**
     * Gets the number of tasks from the data file.
     * @return the number of tasks in the data file
     */
    public int getNumOfTasks() {
        try {
            createFile(this.fileName);
            Scanner sc = new Scanner(new FileReader(this.fileName));
            sc.useDelimiter("\\n");
            int count = 0;
            while (sc.hasNext()) {
                String next = sc.next();
                count++;
            }
            sc.close();
            return count;
        } catch (FileNotFoundException e) {
            printFileError();
        }
        return 0;
    }

    /**
     * Creates a data file if it doesn't exist.
     * @param fileName the name of the data file
     */
    protected void createFile(String fileName) {
        try {
            File dataFile = new File(fileName);
            if (dataFile.createNewFile()) {
                ;
            }
        } catch (IOException e) {
            printFileError();
        }
    }

    /**
     * Processes a line from the data file to the form of the toString of the Tasks.
     * @param taskLine a line from the data file
     * @return the line from the file to the form of the toString of the Tasks
     */
    public String processLine(String taskLine) {
        String[] taskInfo = taskLine.trim().split(" [|] ");
        String taskType = taskInfo[0];
        String isDone = taskInfo[1].equals(String.valueOf(1)) ? "[\u2713] " : "[\u2718] ";
        String taskName = taskInfo[2];
        String result = "";

        switch (taskType) {
        case "T":
            result = String.format("[T]%1$s%2$s", isDone, taskName);
            break;
        case "E":
            String at = taskInfo[3];
            at = LocalDate.parse(at).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            result = String.format("[E]%1$s%2$s (at: %3$s)", isDone, taskName, at);
            break;
        case "D":
            String by = taskInfo[3];
            by = LocalDate.parse(by).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            result = String.format("[D]%1$s%2$s (by: %3$s)", isDone, taskName, by);
            break;
        default:
            result = "There's been an error!";
            break;
        }
        return result;
    }

    /**
     * Reads the data from the data file.
     */
    public void readFile() {
        createFile(this.fileName);
        BufferedReader reader = null;
        int i = 1;
        String curr;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
            while ((curr = reader.readLine()) != null) {
                System.out.println(i + ". " + processLine(curr));
                i++;
            }
        } catch (IOException e) {
            printFileError();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                printFileError();
            }
        }
    }

    /**
     * Writes data to the data file.
     * @param text the data to be written to the data file
     * @throws IOException if there is a problem reading the file
     */
    protected void writeToFile(String text) throws IOException {
        FileWriter writer = new FileWriter(this.fileName, this.canAppendToFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    /**
     * Saves data to be written into the data file.
     * @param text the text to be saved into the data file
     */
    public void saveData(String text) {
        try {
            createFile(this.fileName);
            Storage data = new Storage(this.fileName, true);
            data.writeToFile(text);
        } catch (IOException e) {
            printFileError();
        }
    }

    /**
     * Prints a specific line from the data file.
     * @param lineNumber the line number to be printed
     * @return the text at the specific line from the data file
     * @throws IOException if there is a problem reading the file
     */
    public String printLine(int lineNumber) throws IOException {
        assert lineNumber > 0;
        lineNumber = lineNumber - 1;
        String lineToPrint = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
        return lineToPrint;
    }

    /**
     * Deletes the task at a specific line.
     * @param lineNumber the line of task to delete
     * @throws IOException if there is a problem reading the file
     */
    public void deleteFromFile(int lineNumber) throws IOException {
        File currFile = new File(this.fileName);
        File tempFile = new File("cait_data_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(currFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        lineNumber = lineNumber - 1;
        String lineToRemove = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
        String currLine;

        while ((currLine = reader.readLine()) != null) {
            String trimLine = currLine.trim();
            if (trimLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currLine + '\n');
        }

        writer.close();
        reader.close();

        if (currFile.delete()) {
            if (!tempFile.renameTo(currFile)) {
                printFileError();
            }
        } else {
            printFileError();
        }
    }

    /**
     * Updates the task at a specific line to be done.
     * @param lineNumber the line of task to update to done
     * @throws IOException if there is a problem reading the file
     */
    public void setDoneLine(int lineNumber) throws IOException {
        File currFile = new File(this.fileName);
        File tempFile = new File("cait_data_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(currFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        assert lineNumber > 0;
        lineNumber = lineNumber - 1;
        String lineToUpdate = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
        String[] taskInfo = lineToUpdate.trim().split(" [|] ");
        taskInfo[1] = String.valueOf(1);
        String doneLine = String.join(" | ", taskInfo);
        String currLine;

        while ((currLine = reader.readLine()) != null) {
            String trimLine = currLine.trim();
            if (trimLine.equals(lineToUpdate)) {
                writer.write(doneLine + '\n');
            } else {
                writer.write(currLine + '\n');
            }
        }

        writer.close();
        reader.close();

        if (currFile.delete()) {
            if (!tempFile.renameTo(currFile)) {
                printFileError();
            }
        } else {
            printFileError();
        }
    }

}
