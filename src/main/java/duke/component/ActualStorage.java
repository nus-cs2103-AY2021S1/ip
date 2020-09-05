package duke.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.command.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the actual class for objects that executes reading data from and writing data into a storage file.
 */
public class ActualStorage implements Storage {
    public static final char TODO_INDICATOR = 'T';
    public static final char DEADLINE_INDICATOR = 'D';
    public static final char EVENT_INDICATOR = 'E';
    public static final char DIVIDER = '|';
    public static final String INPUT_FILE_FORMAT_ERROR = "Input file format error!";
    public static final int FILE_DIVIDER_LENGTH = 6;
    private final String filePath;
    private final TaskList list;

    /**
     * Creates a Storage object, and initializes the list of tasks with data in the file.
     * @param filePath the file path holding the targeted data
     * @throws FileNotFoundException if the file is not in the given file path
     * @throws InvalidCommandException should never been thrown if the input file is well-written
     */
    public ActualStorage(String filePath) throws FileNotFoundException, InvalidCommandException {
        this.filePath = filePath;
        list = new TaskList();
        File file = new File(filePath);
        readFile(file);
    }

    private void readFile(File file) throws FileNotFoundException, InvalidCommandException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            readNextTask(sc);
        }
    }

    private void readNextTask(Scanner sc) throws InvalidCommandException {
        String taskType = getTaskType(sc);
        int done = getDoneMarker(sc);
        String fullDescription = getDescription(sc);
        Task toAdd = getTask(taskType, done, fullDescription);
        list.add(toAdd);
    }

    private Task getTask(String taskType, int done, String fullDescription) throws InvalidCommandException {
        Task toAdd;
        if (isToDo(taskType)) {
            toAdd = new ToDo(fullDescription);
        } else {
            if (isDeadline(taskType)) {
                toAdd = new Deadline(fullDescription);
            } else if (isEvent(taskType)) {
                toAdd = new Event(fullDescription);
            } else {
                assert false : INPUT_FILE_FORMAT_ERROR;
                toAdd = new ToDo("error");
            }
        }
        markAsDoneIfNeeded(done, toAdd);
        return toAdd;
    }

    private void markAsDoneIfNeeded(int done, Task toAdd) throws InvalidCommandException {
        if (done == 1) {
            toAdd.markAsDone();
        }
    }

    private boolean isEvent(String taskType) {
        return taskType.charAt(0) == EVENT_INDICATOR;
    }

    private boolean isDeadline(String taskType) {
        return taskType.charAt(0) == DEADLINE_INDICATOR;
    }

    private boolean isToDo(String taskType) {
        return taskType.charAt(0) == TODO_INDICATOR;
    }

    private String getDescription(Scanner sc) {
        String description = sc.nextLine();
        int i = getFirstNonSpaceLocation(description);
        return description.substring(i);
    }

    private int getFirstNonSpaceLocation(String description) {
        int i = 0;
        while (description.charAt(i) == Parser.SPACE_CHAR) {
            i++;
        }
        return i;
    }

    private int getDoneMarker(Scanner sc) {
        sc.next();
        int done = sc.nextInt();
        sc.next();
        return done;
    }

    private String getTaskType(Scanner sc) {
        return sc.next();
    }

    @Override
    public TaskList getList() {
        return list;
    }

    @Override
    public void addToList(Task task) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.outputToFile());
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public void reWrite(TaskList list) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.outputToFile());
            }
            fw.close();
            System.out.println(fw);
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
