import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is a class to edit data in the save file.
 */
public class TaskList {
    private static final String HOME = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    private Parser parser = new Parser();

    /** Total number of tasks in the task list */
    private int total;

    TaskList(int total) {
        assert total >= 0;
        this.total = total;
    }

    int getTotal() {
        assert total >= 0;
        return this.total;
    }

    /**
     * Returns String representation of data of Task.
     *
     * @param t Task to be converted into String.
     * @return String representation of data.
     */
    public String taskToString(Task t) {
        String type = t.getType();
        String status = t.getStatusIcon();
        String name = t.getName();
        String time = t.getTime();

        return type + " @ " + status + " @ " + name + " @ " + time;
    }

    /**
     * Adds Task to save file.
     *
     * @param t Task to be added.
     * @throws IOException If FileWriter is unable to find file.
     */
    public void writeData(Task t) throws IOException {
        FileWriter fw = new FileWriter(PATH.toFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(taskToString(t));

        pw.flush();

        pw.close();
        bw.close();
        fw.close();
    }

    /**
     * Updates total number of Tasks in save file.
     *
     * @param newTotal New total number of tasks.
     * @throws IOException If FileWriter is unable to find file.
     */
    public void updateTotal(int newTotal) throws IOException {
        File fileToBeModified = PATH.toFile();
        String newText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));

        newText = newTotal + System.lineSeparator();
        reader.readLine(); // skips the first line (total)
        String line = reader.readLine();

        while (line != null) {
            newText = newText + line + System.lineSeparator();
            line = reader.readLine();
        }

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    /**
     * Updates Statistics for user.
     *
     * @param s New statistics.
     * @throws IOException If FileWriter is unable to find file.
     */
    public void updateStatistics(Statistics s) throws IOException {
        String newStatistics = s.statisticsToData();
        File fileToBeModified = PATH.toFile();
        String newText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));

        String line = reader.readLine();
        newText = line + System.lineSeparator();
        reader.readLine(); // skips the second line (statistics)
        newText = newText + newStatistics + System.lineSeparator();
        line = reader.readLine();

        while (line != null) {
            newText = newText + line + System.lineSeparator();
            line = reader.readLine();
        }

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    /**
     * Replaces specified line of text with another line of text.
     *
     * @param prevTask Text to be replaced.
     * @param newTask Text to be added.
     * @throws IOException If FileWriter is unable to find file.
     */
    public void replaceText(String prevTask, String newTask) throws IOException {
        File fileToBeModified = PATH.toFile();
        String oldText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));
        String line = reader.readLine();

        while (line != null) {
            oldText = oldText + line + System.lineSeparator();
            line = reader.readLine();
        }

        String newText = oldText.replaceAll(prevTask, newTask);

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    /**
     * Deletes specified line of text from file.
     *
     * @param task Task to be deleted.
     * @throws IOException If FileWriter is unable to find file.
     */
    public void deleteText(String task) throws IOException {
        File fileToBeModified = PATH.toFile();
        String newText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));

        String line = reader.readLine();

        while (line != null) {
            if (line.equals(task)) {
                line = reader.readLine();
            } else {
                newText = newText + line + System.lineSeparator();
                line = reader.readLine();
            }
        }

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    /**
     * Completes specified Task.
     *
     * @param taskNumber Number of task to be completed.
     * @return Completed task.
     * @throws IOException If FileWriter is unable to find file.
     */
    public Task complete(int taskNumber) throws IOException, UnknownNumberException {
        if (taskNumber <= 0 || taskNumber > total) {
            throw new UnknownNumberException();
        }

        Scanner myReader = new Scanner(PATH);

        String taskData = "";
        for (int i = -1; i <= taskNumber; i++) {
            taskData = myReader.nextLine();
        }
        Task t = parser.stringToTask(taskData);
        t = t.completeTask();
        replaceText(taskData, taskToString(t));

        return t;
    }

    /**
     * Deletes specified Task from save file.
     *
     * @param taskNumber Number of task to be deleted.
     * @return Task to be deleted.
     * @throws IOException If FileWriter is unable to find file.
     */
    public Task delete(int taskNumber) throws IOException, UnknownNumberException {
        if (taskNumber <= 0 || taskNumber > total) {
            throw new UnknownNumberException();
        }

        Scanner myReader = new Scanner(PATH);
        String taskData = "";
        for (int i = -1; i <= taskNumber; i++) {
            taskData = myReader.nextLine();
        }
        Task t = parser.stringToTask(taskData);

        deleteText(taskToString(t));
        total--;
        updateTotal(total);

        return t;
    }

    /**
     * Adds new Task to save file.
     *
     * @param s
     * @return Task to be added.
     * @throws IOException If FileWriter is unable to find file.
     * @throws IncompleteInputException If user input is incomplete.
     */
    public Task add(String s) throws IOException, IncompleteInputException {
        Task t = parser.commandToTask(s);

        writeData(t);
        total++;
        updateTotal(total);

        return t;
    }
}
