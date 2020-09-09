package raythx98.grandma.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.exception.UnknownCommandException;
import raythx98.grandma.task.Deadline;
import raythx98.grandma.task.Event;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.task.ToDo;

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
     * Something.
     *
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new TaskList();
    }

    /**
     * Something.
     */
    public void save() throws DukeException {
        if (tasks.getSize() > 0) {
            Writer.overwrite(filepath, tasks.getTask(0).toPrint());
            for (int i = 1; i < tasks.getSize(); i++) {
                Writer.writeOn(filepath, "\n" + tasks.getTask(i).toPrint());
            }
        }
    }

    /**
     * Something.
     *
     * @return
     */
    public TaskList load() throws DukeException, FileNotFoundException {
        File myObj = new File(filepath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] dataSplit = data.split("\\|");
            assert !dataSplit[0].isEmpty();
            switch (dataSplit[0]) {
            case TODO:
                tasks.addTask(new ToDo(dataSplit));
                break;
            case DEADLINE:
                tasks.addTask(new Deadline(dataSplit));
                break;
            case EVENT:
                tasks.addTask(new Event(dataSplit));
                break;
            default:
                throw new UnknownCommandException();
            }
        }
        myReader.close();
        return tasks;
    }
}
