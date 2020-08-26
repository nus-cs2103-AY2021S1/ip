package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents all loading and saving operations to the save file.
 */
public class Storage {
    protected static boolean hasLoadingError = false;
    protected final String location;
    protected final static String filename = "/save.txt";
    protected final File savefile;
    protected FileWriter writer;
    /** Checks whether the user chooses to terminate when corrupted save file encountered. */
    protected boolean isEmptySave;
    protected ArrayList<Task> listFromFile;

    /**
     * @param specifiedLocation The directory for the save file.
     */
    public Storage(String specifiedLocation) {
        this.location = specifiedLocation;
        this.savefile = new File(specifiedLocation + filename);
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
            e.printStackTrace(System.out);
            this.listFromFile = resetSaveFile();
        }
    }

    /**
     * Checks if directory exists, if not creates it.
     * Checks if save file exists, if not creates it.
     * Once save file is newly created, isEmptySave will be true.
     *
     * @throws DukeException If unable to access the specified directory.
     */
    public void checkIfSaveFileExists() throws DukeException {
        try {
                Path path = Paths.get(location);
        if (!Files.isDirectory(path)) {
            File dir = new File(location);
            boolean isDirCreated = dir.mkdir();
            if (isDirCreated) {
                System.out.println("Created directory: " + location);
            }
        }
            this.isEmptySave = savefile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("\u2639 Oops, error checking if save file exists");
        }
    }

    /**
     * Loads the tasks into a new task list.
     * Checks if save file is empty.
     * Reads the number of tasks to be parsed then parses the tasks.
     * Marks completed tasks as done.
     * Prints the number of tasks loaded.
     *
     * @return Task list once tasks are parsed from save file.
     * @throws DukeException If save file contains tasks of wrong format/ invalid tasks numbers/ invalid done tasks.
     */
    public ArrayList<Task> loadListFromFile() throws DukeException {
        System.out.println("Loading from save file...");
        try {
            ArrayList<Task> list = new ArrayList<>();
            if (isEmptySave || savefile.length() == 0) {
                // Empty savefile returns empty task list to start
                System.out.println("Current save file is empty.");
                return list;
            }
            Scanner sc = new Scanner(savefile);
            int noOfTasks = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < noOfTasks; i++) {
                // Read all tasks from savefile
                String input = sc.nextLine();
                if (input.startsWith("todo")) {
                    Task newTodo = Parser.parseNewTaskCommand(input, Task.taskType.TODO);
                    list.add(newTodo);
                } else if (input.startsWith("deadline")) {
                    Task newDeadline = Parser.parseNewTaskCommand(input, Task.taskType.DEADLINE);
                    list.add(newDeadline);
                } else if (input.startsWith("event")){
                    Task newEvent = Parser.parseNewTaskCommand(input, Task.taskType.EVENT);
                    list.add(newEvent);
                } else {
                    throw new DukeException("\u2639 Oops, error parsing " + '"' + input + '"' + " in save file");
                }
            }
            // Mark tasks as done
            while(sc.hasNext()) {
                int doneTaskIndex = Integer.parseInt(sc.next());
                list.get(doneTaskIndex).markAsDone();
            }
            System.out.println("Successfully loaded: " + list.size() + " task(s)");
            return list;

        } catch (IOException e1) {
            throw new DukeException("\u2639 Oops, error reading from " + location + filename);
        } catch (IndexOutOfBoundsException |NoSuchElementException | NumberFormatException e) {
            throw new DukeException("\u2639 Oops, save file is corrupted, error encountered: "
                + e.getLocalizedMessage().toLowerCase());
        } catch (DukeException e) {
            throw new DukeException("\u2639 Oops, save file is corrupted, error loading task");
        }
    }

    /**
     * Prompts user for confirmation to reset task list.
     * Else terminates the program.
     *
     * @return Empty task list.
     */
    public ArrayList<Task> resetSaveFile() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> newList = new ArrayList<>();
        // If fail to load save file, prompts user to reset the savefile
        System.out.println("Type restart to reset task list or anything else to exit.");
        System.out.println();
        if (scanner.nextLine().equals("restart")) {
            try {
                // Resets save file to empty
                this.saveToFile(newList);
                Ui.greet();
            } catch (DukeException e1) {
                e1.printStackTrace(System.out);
            }
            return newList;
        } else {
            hasLoadingError = true;
            return newList;
        }
    }

    /**
     * Writes the number of tasks in list.
     * Writes the each task to file.
     * Writes the index of completed tasks.
     *
     * @param list Task list.
     * @throws DukeException If error write tasks to save file.
     */
    public void saveToFile(ArrayList<Task> list) throws DukeException {
        try {
            this.writer = new FileWriter(location+filename);
            String doneIndexes;

            // System.out.println("Wrote: " + list.size());
            writer.write(list.size() + System.lineSeparator());
            // Saving each task to savefile
            StringBuilder doneIndexesBuilder = new StringBuilder();
            for (int i = 0; i<list.size(); i++) {
                    Task t = list.get(i);
                    if (t.isDone()) {
                        doneIndexesBuilder.append(i).append(" ");
                    }

                    // System.out.println("Wrote: " + t.toSaveFormat());
                    writer.write(t.toSaveFormat() + System.lineSeparator());
            }
            doneIndexes = doneIndexesBuilder.toString();
            if (!doneIndexes.equals("")) {
                writer.write(doneIndexes + System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            throw new DukeException("\u2639 Oops, error saving to save file");
        }
    }
}
