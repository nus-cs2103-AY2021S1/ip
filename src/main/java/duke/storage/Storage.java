package duke.storage;

import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    String filePath;

    /**
     * class constructor
     * @param filePath the file path of the file to load data from and write data to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * creates a new file if the it does not exist in the specified file path.
     * afterwards, the text in the file is decoded and converted to a list of tasks stored in an array list
     * @return the array list of tasks
     * @throws DukeException if there are issues finding the file or decoding the file
     */
    public ArrayList<Task> load() throws DukeException {
        createFileIfNotExist();
        return decodeTxtFile();
    }

    /**
     * encodes the tasks in the given task list to a more appropriate format for storage
     * updates the changes in the task list using the encoded versions of the task
     * @param taskList the task list to reference when updating the file
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);

            for (Task task : taskList.getTaskList()) {
                String encodedTask = task.encode();
                fw.write(encodedTask + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileIfNotExist() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File duke = new File(this.filePath);
        if (!duke.exists()) {
            try {
                duke.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Task> decodeTxtFile() throws DukeException {
        File f = new File(this.filePath);
        ArrayList<Task> decodedTasks = new ArrayList<>();

        try {
            Scanner s = new Scanner(f);

            String string;
            Task task = null;

            while (s.hasNext()) {
                string = s.nextLine();

                switch (string.charAt(0)) {
                    case 'D':
                        task = Deadline.decode(string);
                        break;
                    case 'E':
                        task = Event.decode(string);
                        break;
                    case 'T':
                        task = ToDo.decode(string);
                        break;
                }
                decodedTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("the file could not be found");
        } catch (EmptyDescriptionException | EmptyDateException e) {
            throw new DukeException(e.getMessage());
        }
        return decodedTasks;
    }
}
