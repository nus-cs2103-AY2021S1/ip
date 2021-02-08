package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final char TODO = 'T';
    private static final char DEADLINE = 'D';
    private static final char EVENT = 'E';
    private static final char DONE = 'O';
    private static final char TAG = 'a';
    private static final char NOT_DONE = 'X';
    private static final int START_OF_TASK_NAME = 7;
    private static final int DONE_OR_NOT = 4;
    private static final int START_OF_DATE = 4;

    private String filePath;
    private Scanner scanner;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the text file in the specified directory (this.filepath).
     */
    public void createDirectory() {
        final File parentDir = new File("data");
        parentDir.mkdir();
        final String hash = "tasks";
        final String fileName = hash + ".txt";
        final File file = new File(parentDir, fileName);
        try {
            file.createNewFile();
            System.out.println("Created path data/tasks.txt");
        } catch (IOException e) {
            System.out.println("Could not create file.");
        }
    }

    /**
     * Generates a ToDo based on the input line of text.
     *
     * @param task The input line of text from the file.
     * @return Returns a new ToDo.
     */
    public ToDo generateTodo(String task) {
        ToDo newTodo = null;
        if (task.charAt(DONE_OR_NOT) == DONE) {
            newTodo = new ToDo(task.substring(START_OF_TASK_NAME));
            newTodo.setDone();
        } else if (task.charAt(DONE_OR_NOT) == NOT_DONE) {
            newTodo = new ToDo(task.substring(START_OF_TASK_NAME));
        }
        return newTodo;
    }

    /**
     * Generates a Deadline based on the input line of text.
     *
     * @param task The input line of text from the file.
     * @return Returns a new Deadline.
     */
    public Deadline generateDeadline(String task) {
        Deadline newDeadline = null;
        if (task.charAt(DONE_OR_NOT) == DONE) {
            int indexOfDeadline = task.indexOf("by:");
            String date = task.substring(indexOfDeadline + START_OF_DATE, task.length() - 1);
            String description = task.substring(START_OF_TASK_NAME, indexOfDeadline - 2);
            newDeadline = new Deadline(description, date);
            newDeadline.setDone();
        } else if (task.charAt(DONE_OR_NOT) == NOT_DONE) {
            int indexOfDeadline = task.indexOf("by:");
            String date = task.substring(indexOfDeadline + START_OF_DATE, task.length() - 1);
            String description = task.substring(START_OF_TASK_NAME, indexOfDeadline - 2);
            newDeadline = new Deadline(description, date);
        }
        return newDeadline;
    }

    /**
     * Generates an Event based on the input line of text.
     *
     * @param task The input line of text from the file.
     * @return Returns a new Event.
     */
    public Event generateEvent(String task) {
        Event newEvent = null;
        if (task.charAt(DONE_OR_NOT) == DONE) {
            int indexOfEvent = task.indexOf("at:");
            String date = task.substring(indexOfEvent + START_OF_DATE, task.length() - 1);
            String description = task.substring(START_OF_TASK_NAME, indexOfEvent - 2);
            newEvent = new Event(description, date);
            newEvent.setDone();
        } else if (task.charAt(DONE_OR_NOT) == NOT_DONE) {
            int indexOfEvent = task.indexOf("at:");
            String date = task.substring(indexOfEvent + START_OF_DATE, task.length() - 1);
            String description = task.substring(START_OF_TASK_NAME, indexOfEvent - 2);
            newEvent = new Event(description, date);
        }
        return newEvent;
    }

    /**
     * Checks if the Duke Project text file exists. If it does, reads each line of the
     * file and processes it into a TaskList. If not, creates the text file in the
     * specified directory.
     *
     * @return Returns a TaskList based on the user's duke project text file.
     * @throws DukeException  If the file was not created correctly/ does not exist.
     */
    public TaskList load() throws DukeException {
        File f = new File(this.filePath); // create a File for the given file path
        if (!f.exists()) {
            createDirectory();
        }
        try {
            scanner = new Scanner(f); // create a Scanner using the File as the source
        } catch (Exception e) {
            throw new DukeException("File not found.");
        }
        ArrayList<Task> loadedTasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            switch (task.charAt(1)) {
            case (TODO):
                ToDo newTodo = generateTodo(task);
                loadedTasks.add(newTodo);
                break;
            case (DEADLINE):
                Deadline newDeadline = generateDeadline(task);
                loadedTasks.add(newDeadline);
                break;
            case (EVENT):
                Event newEvent = generateEvent(task);
                loadedTasks.add(newEvent);
                break;
            case (TAG):
                String[] tags = task.split(" ");
                tagCommand(loadedTasks, tags);
                break;
            default:
                throw new DukeException("I'm sorry, something went wrong!");
            }
        }
        return new TaskList(loadedTasks);
    }

    /**
     * Adds the tags to the rightmost task.
     *
     * @param tasks Loaded tasks from tasks.txt
     * @param tags Array of tags.
     * @throws DukeException  If one of the tags in the file do not exist.
     */
    public void tagCommand(ArrayList<Task> tasks, String[] tags) throws DukeException {
        for (int i = 1; i < tags.length; i++) {
            String tag = tags[i].substring(1).toLowerCase();
            Tag tagToBeAdded = Tag.stringToTag(tag);
            int indexToAddTags = tasks.size() - 1;
            tasks.get(indexToAddTags).addTag(tagToBeAdded);
        }
    }

    /**
     * Writes the input text onto the specified file path.
     *
     * @param textToAdd Text to be written onto the specified file.
     * @throws IOException  If the specified file does not exist.
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the user's current TaskList into the project duke text file in a readable manner.
     *
     * @param tasks The user's current TaskList.
     */
    public static void store(ArrayList<Task> tasks) {
        try {
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks) {
                textToAdd.append(task.toString()).append(System.lineSeparator());
            }
            writeToFile(textToAdd.toString());
        } catch (IOException e) {
            System.out.println("Something Went wrong: " + e.getMessage());
        }
    }
}
