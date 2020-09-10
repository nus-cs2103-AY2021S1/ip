package raythx.grandma.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import raythx.grandma.exception.DukeException;
import raythx.grandma.exception.UnknownCommandException;
import raythx.grandma.task.Deadline;
import raythx.grandma.task.Event;
import raythx.grandma.task.TaskList;
import raythx.grandma.task.ToDo;

/**
 * Deals with the manipulation of loading and saving data.
 */
public class Storage {

    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";

    private String filepath;
    private TaskList tasks;

    /**
     * Constructor for Storage.
     *
     * @param filepath path of file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new TaskList();
    }
    public TaskList newTasks() {
        return tasks;
    }

    /**
     * Save current information of tasks.
     */
    public void save() throws DukeException {
        if (tasks.getSize() > 0) {
            System.out.println("directory not found");
            Writer.overwrite(filepath, tasks.getTask(0).encodeTask());
            for (int i = 1; i < tasks.getSize(); i++) {
                Writer.writeOn(filepath, "\n" + tasks.getTask(i).encodeTask());
            }
        }
    }

    /**
     * Load information of tasks.
     *
     * @return tasks loaded.
     */
    public TaskList load() throws DukeException, FileNotFoundException {
        File myObj = new File(filepath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String taskInformation = myReader.nextLine();
            String[] taskInformationSplit = taskInformation.split("\\|");
            assert !taskInformationSplit[0].isEmpty();
            switch (taskInformationSplit[0]) {
            case TODO:
                tasks.addTask(new ToDo(taskInformationSplit));
                break;
            case DEADLINE:
                tasks.addTask(new Deadline(taskInformationSplit));
                break;
            case EVENT:
                tasks.addTask(new Event(taskInformationSplit));
                break;
            default:
                throw new UnknownCommandException();
            }
        }
        myReader.close();
        return tasks;
    }
}
