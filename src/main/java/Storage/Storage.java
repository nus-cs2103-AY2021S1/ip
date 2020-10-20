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

    private String tempFileName = "cait_data_temp.txt";

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
                boolean isDone = taskInfo[1].equals(String.valueOf(1));
                String taskName = taskInfo[2];
                Task taskToAdd;

                switch (taskType) {
                case "T":
                    taskToAdd = new Todo(taskName);
                    loadDoneTask(taskList, isDone, taskToAdd);
                    break;
                case "E":
                    String at = taskInfo[3];
                    taskToAdd = new Event(taskName, at);
                    loadDoneTask(taskList, isDone, taskToAdd);
                    break;
                case "D":
                    String by = taskInfo[3];
                    taskToAdd = new Deadline(taskName, by);
                    loadDoneTask(taskList, isDone, taskToAdd);
                    break;
                default:
                    break;
                }
            }
            return taskList;
        } catch (IOException e) {
            System.out.println("load()");
            printFileError();
            return taskList;
        }
    }

    protected void loadDoneTask(ArrayList<Task> taskList, boolean isDone, Task taskToAdd) {
        if (isDone) {
            taskToAdd.markAsDone();
        }
        taskList.add(taskToAdd);
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
            System.out.println("getnumtasks()");
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
            System.out.println("createfile()");
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

    protected static String processTasks(Task task) {
        String result = "";
        int isDone = task.getIsDone().equals("[\u2713] ") ? 1 : 0;
        if (task instanceof Todo) {
            result = "T | " + isDone + " | " + task.getTaskName();
        } else if (task instanceof Deadline) {
            result = "D | " + isDone + " | " + task.getTaskName() + " | " + ((Deadline) task).getByDate();
        } else if (task instanceof Event) {
            result = "E | " + isDone + " | " + task.getTaskName() + " | " + ((Event) task).getAtDate();
        }
        return result;
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
            System.out.println("savedata()");
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
        try {
            File currFile = new File(this.fileName);
            File tempFile = new File(tempFileName);
            BufferedReader reader = new BufferedReader(new FileReader(currFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            lineNumber = lineNumber - 1;
            String lineToRemove = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);

            String currLine;
            lineToRemove = lineToRemove.trim();

            int count = 0;
            while ((currLine = reader.readLine()) != null) {
                String trimLine = currLine.trim();
                if (count == lineNumber) {
                    ;
                } else {
                    writer.write(currLine + '\n');
                }
                count++;
            }

            writer.close();
            reader.close();

            if (currFile.delete()) {
                if (!tempFile.renameTo(currFile)) {
                    System.out.println("delete");
                    printFileError();
                }
            } else {
                System.out.println("delete");
                printFileError();
            }
        } catch (Exception e) {
            System.out.println("delete");
            System.out.println(e);
        }
    }

    /**
     * Updates the task at a specific line to be done.
     * @param lineNumber the line of task to update to done
     * @throws IOException if there is a problem reading the file
     */
    public void setDoneLine(int lineNumber) throws IOException {
        try {
            File currFile = new File(this.fileName);
            File tempFile = new File(tempFileName);
            BufferedReader reader = new BufferedReader(new FileReader(currFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            assert lineNumber > 0;
            lineNumber = lineNumber - 1;
            String lineToUpdate = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
            String[] taskInfo = lineToUpdate.trim().split(" [|] ");
            taskInfo[1] = String.valueOf(1);
            String doneLine = String.join(" | ", taskInfo);

            String currLine;
            lineToUpdate = lineToUpdate.trim();

            Task doneTask = this.load().get(lineNumber);
            doneTask.markAsDone();
            doneLine = processTasks(doneTask).trim();

            int count = 0;
            while ((currLine = reader.readLine()) != null) {
                String trimLine = currLine.trim();
                if (count == lineNumber) {
                    writer.write(doneLine + '\n');
                } else {
                    writer.write(currLine + '\n');
                }
                count++;
            }

            writer.close();
            reader.close();

            if (currFile.delete()) {
                if (!tempFile.renameTo(currFile)) {
                    System.out.println("done");
                    printFileError();
                }
            } else {
                System.out.println("done");
                printFileError();
            }
        } catch (Exception e) {
            System.out.println("done");
            System.out.println(e);
        }
    }

}
