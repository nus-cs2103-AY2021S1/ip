package raythx98.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import raythx98.duke.exception.DukeException;
import raythx98.duke.exception.UnknownCommandException;
import raythx98.duke.task.Deadline;
import raythx98.duke.task.Event;
import raythx98.duke.task.ToDos;
import raythx98.duke.task.TaskList;

/**
 * Deals with the manipulation of loading and saving data.
 */
public class Storage {

    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";

    private String filepath;
    private TaskList tasks;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new TaskList();
    }

    public void save() {
        if (tasks.getSize() > 0) {
            Writer.overwrite(filepath, tasks.getTask(0).toPrint());
            for (int i = 1; i < tasks.getSize(); i++) {
                Writer.writeOn(filepath, "\n" + tasks.getTask(i).toPrint());
            }
        }
    }

    public TaskList load() {
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split("\\|");
                switch (dataSplit[0]) {
                case TODO:
                    tasks.addTask(new ToDos(dataSplit));
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
