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
    public void save() {
        if (tasks.getSize() > 0) {
            try {
                Writer.overwrite(filepath, tasks.getTask(0).toPrint());
                for (int i = 1; i < tasks.getSize(); i++) {
                    Writer.writeOn(filepath, "\n" + tasks.getTask(i).toPrint());
                }
            } catch (DukeException e) {
                //Find a way to send error message
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Something.
     *
     * @return
     */
    public TaskList load() {
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split("\\|");
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
                    throw new UnknownCommandException("Unknown Task Type");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No current data exists");
        } catch (DukeException e) {
            System.out.println(e);
        }
        return tasks;
    }
}
