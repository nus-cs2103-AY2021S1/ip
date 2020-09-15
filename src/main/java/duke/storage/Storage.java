package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * The Storage class handles the reading and writing of data from the hard disk.
 * Data is stored at the provided file path.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new Storage object.
     * @param filePath Path of local save file for Duke's task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves Duke's TaskList in its current state to a file on the hard disk.
     * @param tasks TaskList kept by this instance of Duke.
     */
    public void store(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(Duke.DEFAULT_SAVE_FILE);
            StringBuilder textToWrite = new StringBuilder();

            for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
                Task t = tasks.retrieve(i);
                // Insert / separators.
                String nextEntry = t.getSaveSymbol()
                        + " / " + t.isDoneToString()
                        + " / " + t.getDescription()
                        + t.getDate()
                        .map(result -> " / " + result)
                        .orElse("");

                textToWrite.append(nextEntry).append("\n");
            }
            writer.write(String.valueOf(textToWrite));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads stored information from file and returns a List of tasks to be fed to Duke's TaskList.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(".\\data");
                dir.mkdir();
                boolean created = file.createNewFile();
                assert created;
            }

            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>(Duke.MAX_NUM_OF_TASKS);
            int lineNumber = 0;

            while (sc.hasNext()) {
                // Get next line.
                String nextEntryLine = sc.nextLine();
                lineNumber++;

                // Remove dividers and place into array.
                String[] nextEntryArray = nextEntryLine.split(" / ", 4);

                // Get type of Task.
                String nextEntryTaskType = nextEntryArray[0];

                // Get description of Task.
                String nextEntryDescription = nextEntryArray[2];

                // Get length of the stored entry.
                int nextEntryLength = nextEntryArray.length;

                // Check if Task has been marked as done.
                boolean isDone = nextEntryArray[1].equals("1");

                // Create Task to add to TaskList.
                Task t;
                if (nextEntryTaskType.equals(Task.TODO_SAVE_SYMBOL)
                        && nextEntryLength == 3) { // Todo Task.
                    t = new Todo(nextEntryDescription);

                } else if (nextEntryTaskType.equals(Task.DEADLINE_SAVE_SYMBOL)
                        && nextEntryLength == 4) { // Deadline Task.
                    String by = nextEntryArray[3];
                    t = Deadline.createDeadline(nextEntryDescription, by);

                } else if (nextEntryTaskType.equals(Task.EVENT_SAVE_SYMBOL)
                        && nextEntryLength == 4) { // Event Task.
                    String at = nextEntryArray[3];
                    t = Event.createEvent(nextEntryDescription, at);

                } else {
                    // Unknown Task type.
                    throw new DukeException("Check duke.txt storage file integrity: \n"
                    + "Line No: " + lineNumber + "\n"
                    + "Content: " + nextEntryLine);
                }

                if (isDone) {
                    t.markAsDone();
                }

                // Finally, add task if no exception thrown.
                tasks.add(t);
            }
            // Return updated List of Tasks.
            return tasks;


        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(100);
        }
    }
}
