import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Storage object.
 * For reading and writing of the specified file.
 */
public class Storage {
    private static final char TICK = '\u2713';
    private static final char CROSS = '\u2718';
    private static final char CONVERTED_TICK = '1';
    private static final char CONVERTED_CROSS = '0';

    private static final int TYPE_START_INDEX = 1;
    private static final int TYPE_END_INDEX = 2;
    private static final int ISDONE_START_INDEX = 4;
    private static final int ISDONE_END_INDEX = 5;
    private static final int DATE_OFFSET_INDEX = 5;
    private static final int DESCRIPTION_START_INDEX = 7;

    private static final String DONE = "1";
    private static final String DATE_OPEN_BRACKET = "(";
    private static final String DATE_CLOSE_BRACKET = ")";

    private String filePath;
    private List<Task> savedTasks;

    /**
     * Creates a Storage object.
     * It is used reading a file.
     *
     * @param filePath is a relative directory to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.savedTasks = new ArrayList<>();
    }

    /**
     * Loads the file.
     * A java Scanner object is used to read the .txt file and temporarily
     * stores the data.
     */
    public void loadFileContent() {
        File storedFile = new File(filePath); // create a File for the given file path;

        try {
            Scanner scanner = new Scanner(storedFile); // create a Scanner using the File as the source

            while (scanner.hasNext()) {
                String taskSummary = scanner.nextLine();
                lineReader(taskSummary);
            }

        } catch (FileNotFoundException e) {
            File directory = storedFile.getParentFile();

            try {
                if (!directory.exists() || !directory.isDirectory()) {
                    directory.mkdirs();
                }

                storedFile.createNewFile();

            } catch (IOException ex) {
                System.out.println("Unable to open/create file: " + ex.toString());
            }
        }
    }

    /**
     * File line parser.
     * Reads and identifies the tasks specified in the file.
     *
     * @param task is the line String representation of task
     * read from the file.
     */
    public void lineReader(String task) {
        String type = task.substring(TYPE_START_INDEX, TYPE_END_INDEX);
        boolean isDone = false;

        if (task.substring(ISDONE_START_INDEX, ISDONE_END_INDEX).equals(DONE)) {
            isDone = true;
        }

        if (type.equals(Task.TODO_TASK)) {
            todoReader(task, isDone);

        } else if (type.equals(Task.EVENT_TASK)) {
            eventReader(task, isDone);

        } else if (type.equals(Task.DEADLINE_TASK)) {
            deadlineReader(task, isDone);
        }
    }

    public void todoReader(String parsedTask, Boolean isDone) {
        int end = parsedTask.length();
        String description = parsedTask.substring(DESCRIPTION_START_INDEX, end);

        Todo newTodo = new Todo(description, isDone);
        savedTasks.add(newTodo);
    }

    public void eventReader(String parsedTask, Boolean isDone) {
        int dateStart = parsedTask.indexOf(DATE_OPEN_BRACKET);
        int dateEnd = parsedTask.lastIndexOf(DATE_CLOSE_BRACKET);

        String timeDescription = parsedTask.substring(dateStart + DATE_OFFSET_INDEX, dateEnd);
        String description = parsedTask.substring(DESCRIPTION_START_INDEX, dateStart);

        Event newEvent = new Event(description, timeDescription, isDone);
        savedTasks.add(newEvent);
    }

    public void deadlineReader(String parsedTask, Boolean isDone) {
        int dateStart = parsedTask.indexOf(DATE_OPEN_BRACKET);
        int dateEnd = parsedTask.lastIndexOf(DATE_CLOSE_BRACKET);

        String timeDescription = parsedTask.substring(dateStart + DATE_OFFSET_INDEX, dateEnd);
        String description = parsedTask.substring(DESCRIPTION_START_INDEX, dateStart);

        Deadline newDeadline = new Deadline(description, timeDescription, isDone);
        savedTasks.add(newDeadline);
    }

    /**
     * Returns a list of Tasks object stored after reading the file.
     *
     * @return a list of Task objects.
     */
    public List<Task> getSavedTasks() {
        return savedTasks;
    }

    /**
     * Writes the data to the file.
     *
     * @param tasksToWrite is the list of Tasks.
     * This method will convert Task to their String representation,
     * modify them and write in the file.
     *
     * @throws IOException if file is somehow not found.
     */
    public void writeToFile(List<Task> tasksToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int totalTasks = tasksToWrite.size();

        for (int i = 0; i < totalTasks; i++) {
            Task writeTask = tasksToWrite.get(i);
            String taskString = writeTask.toString();
            String replaceTick = taskString.replace(TICK, CONVERTED_TICK);
            String replaceCross = replaceTick.replace(CROSS, CONVERTED_CROSS);
            fw.write(replaceCross + "\n");
        }

        fw.close();
    }
}
