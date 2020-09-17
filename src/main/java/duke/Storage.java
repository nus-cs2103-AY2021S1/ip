package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Priority;
import duke.task.Task;
import duke.task.Todo;

public class Storage {

    public static final String LINE = "//";
    private String directoryName;
    private String fileName;
    private File databaseFile;
    private FileWriter fw;

    /**
     * Creates a new Storage object that stores a reference to a file.
     * If the file does not exist yet, creates the file.
     * @param directoryName String path to directory where the file is stored
     * @param fileName Name of file
     */
    public Storage(String directoryName, String fileName) {
        assert directoryName != null && fileName != null : "Directory and file names should be non-null strings";
        this.directoryName = directoryName;
        this.fileName = fileName;

        //Create directory if it does not exist
        File directory = new File(directoryName);
        boolean directoryIsCreated = directory.mkdirs();

        //Create path to file
        databaseFile = new File(directoryName + fileName);
    }

    /**
     * Returns a list of {@code Task} objects corresponding to the information
     * stored in the database file.
     * @return Previously saved list of tasks.
     * @throws DukeException When the information stored in the database file cannot be parsed.
     * @throws IOException If an I/O error occurred.
     */
    public List<Task> generateTaskList() throws DukeException, IOException {
        List<Task> tempTaskList = new ArrayList<>();

        //Create file if it does not yet exist
        boolean fileIsCreated = databaseFile.createNewFile();

        //Read contents of file and transfer into list
        Scanner s = new Scanner(databaseFile);
        while (s.hasNext()) {
            String nextstr = s.nextLine();
            Task next = generateTaskFromFile(nextstr);
            tempTaskList.add(next);
        }
        s.close();
        return tempTaskList;
    }

    /**
     * Converts the existing task list into a format that can be parsed upon re-opening
     * the Duke program and writes it to the database file.
     * @param tl List of tasks to be saved.
     * @throws IOException If an I/O error occurred.
     */
    public void saveTaskList(TaskList tl) throws IOException {
        fw = new FileWriter(directoryName + fileName);
        fw.write(tl.formatForSave());
        fw.flush();
    }

    public void finalise() throws IOException {
        fw.close();
    }

    private Task generateTaskFromFile(String nextLine) throws DukeException {
        String[] taskCharacteristics = nextLine.split(LINE);
        boolean isDone = taskCharacteristics[1].equals("1");
        String type = taskCharacteristics[0];
        String desc = taskCharacteristics[2];
        Priority priority = getPriority(taskCharacteristics[3]);
        switch (type) {
        case ("T"):
            return new Todo(desc, priority);
        case ("D"):
            return new Deadline(desc, priority, LocalDate.parse(taskCharacteristics[4]));
        case ("E"):
            return new Event(desc, priority, LocalDate.parse(taskCharacteristics[4]));
        default:
            throw new DukeException(type,
                    DukeException.ExceptionType.UNEXPECTED_INPUT_IN_FILE);
        }
    }

    private Priority getPriority(String priorityStr) {
        System.out.println(priorityStr);
        switch (priorityStr) {
        case "HIGH":
            return Priority.HIGH;
        case "MEDIUM":
            return Priority.MEDIUM;
        case "LOW":
            return Priority.LOW;
        default:
            return Priority.UNDEFINED;
        }
    }
}
