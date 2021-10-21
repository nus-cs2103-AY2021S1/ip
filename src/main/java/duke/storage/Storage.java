package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.FileErrorException;
import duke.exception.FolderErrorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Represents an object that is responsible for retrieving data from and writing
 * to the file on hard disk.
 * Unsuccessful attempt will trigger Exception.
 */
public class Storage {
    protected File data;

    public Storage() {
        //do nothing
    }

    /**
     * Constructs a Storage object.
     * If the folder or target file is not found according to the given path,
     * it will attempt to create these documents along the way.
     * @param filePath the path that leads to the target file
     * @throws DukeException if unsuccessful attempt to create these documents
     */
    public Storage (String filePath) throws DukeException {
        String folder = filePath.split("/")[0];
        String file = filePath.split("/")[1];
        String dir = System.getProperty("user.dir");


        //Look for the folder specified in the path
        //Create the folder and file if folder not found
        java.nio.file.Path dataFolder = java.nio.file.Paths.get(dir, folder);
        if (!java.nio.file.Files.exists(dataFolder)) {
            if (!new File(dataFolder.toString()).mkdir()) {
                throw new FolderErrorException();
            }
        }

        assert dataFolder != null : "Folder not found!";


        //Look for the file specified by the path under the condition that folder has been found
        //Create new file if file not found
        java.nio.file.Path fileLocation = java.nio.file.Paths
                .get(dataFolder.toString(), file);
        if (!java.nio.file.Files.exists(fileLocation)) {
            try {
                if (!fileLocation.toFile().createNewFile()) {
                    throw new FileErrorException();
                }
            } catch (IOException e) {
                throw new FolderErrorException();
            }
        }

        assert fileLocation != null : "file not found!";
        this.data = new File(fileLocation.toString());
    }

    /**
     * Overwrites everything in the target file on hard disk according to the TaskList.
     * @param list a collection of all tasks
     * @throws DukeException if attempt is unsuccessful
     */
    public void generateTxt (TaskList list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.data);
            List<Task> tasks = list.getList();
            for (Task task : tasks) {
                fw.write(task.convertTxt() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unsuccessful txt file update");
        }
    }

    /**
     * Appends the String representation of the task to the last row of the target
     * file on the hard disk.
     * @param task a specific task
     * @throws DukeException if attempt is unsuccessful
     */
    public void appendTxt (Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.data, true);
            fw.write(task.convertTxt() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unsuccessful txt file update");
        }
    }

    /**
     * Loads data from the hard disk to the app.
     * @param list an empty list ready to be filled with tasks
     * @throws DukeException if the data from the file is corrupted or can't find the file
     */
    public void loadData(TaskList list) throws DukeException {
        try {
            Scanner s = new Scanner(this.data);
            while (s.hasNext()) {
                String event = s.nextLine();
                String[] component = event.split("\\|");
                if (component.length > 4) {
                    throw new DukeException("Corrupted data detected! Loading terminated!");
                }
                assert component.length <= 4 : "Corrupted file data";
                switch (component[0].trim()) {
                case "T": {
                    Task current = new ToDo(component[2]);
                    int state = Integer.parseInt(component[1].trim());
                    if (state == 1) {
                        current.markAsDone();
                    }
                    list.add(current);
                    break;
                }
                case "D": {
                    Deadline current = new Deadline(component[2].trim(), component[3].trim());
                    int state = Integer.parseInt(component[1].trim());
                    if (state == 1) {
                        current.markAsDone();
                    }
                    list.add(current);
                    break;
                }
                case "E": {
                    Event current = new Event(component[2].trim(), component[3].trim());
                    int state = Integer.parseInt(component[1].trim());
                    if (state == 1) {
                        current.markAsDone();
                    }
                    list.add(current);
                    break;
                }
                default: {
                    throw new DukeException("Corrupted data detected! Loading terminated!");
                }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("I can't seem to find your file...");

        }
    }
}
