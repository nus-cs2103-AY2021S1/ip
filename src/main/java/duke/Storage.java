package duke;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents all loading and saving operations to the save file.
 */
public class Storage {
    /** Checks whether the user chooses to terminate when corrupted save file encountered. */
    protected static final String FILE_NAME = "/save.txt";
    protected static boolean hasLoadingError = false;
    protected final String location;
    protected final File savefile;
    protected ArrayList<Task> listFromFile;
    protected boolean isEmptySave;
    protected FileWriter writer;


    /**
     * @param specifiedLocation The directory for the save file.
     */
    public Storage(String specifiedLocation) {
        this.location = specifiedLocation;
        this.savefile = new File(specifiedLocation + FILE_NAME);
        run();
    }

    /**
     * Retrieves tasks loaded from save file.
     *
     * @return Task list retrieved from save file.
     */
    public ArrayList<Task> getListFromFile() {
        return listFromFile;
    }

    /**
     * Verifies save file directory and loads the save file from disk
     * If unable to load, then resets the save file and the task list to empty.
     */
    public void run() {
        try {
            checkIfSaveFileExists();
            this.listFromFile = loadListFromFile();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.listFromFile = resetSaveFile();
        }
    }

    /**
     * Checks whether the directory path and the save file exist.
     *
     * @throws DukeException If unable to access the specified directory.
     */
    public void checkIfSaveFileExists() throws DukeException {
        try {
            Path path = Paths.get(location);
            // Checks if directory exists, if not creates it
            if (!Files.isDirectory(path)) {
                // Checks if save file exists, if not creates it
                File dir = new File(location);
                boolean isDirCreated = dir.mkdir();
                if (isDirCreated) {
                    System.out.println("Created directory at: " + location);
                }
            }
            // Once save file is newly created, isEmptySave will be true
            this.isEmptySave = savefile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("\u2639 Whoops, error checking if save file exists");
        }
    }

    /**
     * Loads the tasks and task list from the save file.

     * @return Task list of tasks parsed from save file
     * @throws DukeException If save file contains tasks of wrong format/ invalid tasks numbers/ invalid done tasks.
     */
    public ArrayList<Task> loadListFromFile() throws DukeException {
        System.out.println("Loading from save file...");
        try {
            ArrayList<Task> list = new ArrayList<>();
            if (isEmptySave || savefile.length() == 0) {
                // Checks if save file is empty, returns empty task list
                System.out.println("Current save file is empty.");
                return list;
            }
            Scanner sc = new Scanner(savefile);
            // Reads the number of tasks to be parsed then parses the tasks
            int noOfTasks = Integer.parseInt(sc.nextLine());
            // Read all tasks from savefile
            for (int i = 0; i < noOfTasks; i++) {
                String input = sc.nextLine();
                if (input.startsWith("todo")) {
                    Task newTodo = Parser.parseNewTaskCommand(input, Task.TaskType.TODO);
                    list.add(newTodo);
                } else if (input.startsWith("deadline")) {
                    Task newDeadline = Parser.parseNewTaskCommand(input, Task.TaskType.DEADLINE);
                    list.add(newDeadline);
                } else if (input.startsWith("event")) {
                    Task newEvent = Parser.parseNewTaskCommand(input, Task.TaskType.EVENT);
                    list.add(newEvent);
                } else {
                    throw new DukeException("\u2639 Whoops, error parsing " + '"' + input + '"' + " in save file");
                }
            }
            // Marks completed tasks as done
            while (sc.hasNext()) {
                int doneTaskIndex = Integer.parseInt(sc.next());
                list.get(doneTaskIndex).markAsDone();
            }
            // Prints the number of tasks loaded
            System.out.println("Successfully loaded: " + list.size() + " task(s)");
            System.out.println();
            return list;

        } catch (IOException e1) {
            throw new DukeException("\u2639 Whoops, error reading from " + location + FILE_NAME);
        } catch (IndexOutOfBoundsException | NoSuchElementException | NumberFormatException e) {
            throw new DukeException("\u2639 Whoops, save file is corrupted, error encountered: "
                + e.getLocalizedMessage().toLowerCase());
        } catch (DukeException e) {
            throw new DukeException("\u2639 Whoops, save file is corrupted, error loading task");
        }
    }

    /**
     * Prompts user for confirmation to reset task list.
     *
     * @return Empty task list.
     */
    public ArrayList<Task> resetSaveFile() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> newList = new ArrayList<>();
        // If fail to load save file, prompts user to reset the savefile
        System.out.println("Type restart to reset task list or anything else to exit.");
        System.out.println();
        if (!scanner.nextLine().equals("restart")) {
            // No confirmation, terminates the program
            hasLoadingError = true;
            return newList;
        }
        try {
            // Resets save file to empty
            this.saveToFile(newList);
            Ui.greet();
        } catch (DukeException e1) {
            System.out.println(e1.getMessage());
        }
        assert !hasLoadingError : "Error resetting the save file after user confirmation";
        return newList;
    }

    /**
     * Saves the task list and its tasks to a .txt file.
     *
     * @param list Task list.
     * @throws DukeException If error write tasks to save file.
     */
    public void saveToFile(ArrayList<Task> list) throws DukeException {
        try {
            this.writer = new FileWriter(location + FILE_NAME);
            String doneIndexes;
            // Saves the current number of tasks in task list
            writer.write(list.size() + System.lineSeparator());
            StringBuilder doneIndexesBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                if (t.isDone()) {
                    doneIndexesBuilder.append(i).append(" ");
                }
                // Saves each task to file
                writer.write(t.printSaveFormat() + System.lineSeparator());
            }
            doneIndexes = doneIndexesBuilder.toString();
            if (!doneIndexes.equals("")) {
                // Saves the index of completed tasks
                writer.write(doneIndexes + System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            throw new DukeException("\u2639 Whoops, error saving to save file");
        }
    }
}
